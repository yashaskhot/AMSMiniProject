package com.example.sample2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TableController implements Initializable {
    @FXML
    private TextField tfUser;

    @FXML
    private TextField tfId;
    @FXML
    private TableView<StudentData> students_table;

    @FXML
    private TableColumn<StudentData, String> collstud_id;

    @FXML
    private TableColumn<StudentData, String>collstud_name;
    @FXML
    private Button adddata;
    @FXML
    private Button  updateRecord;
    @FXML
    private Button delete;
    @FXML
    private Button next;
    @FXML
    private Button home;
    private Connection connect;

    private Statement statement;
    private PreparedStatement prepare;

    private ResultSet result;
    /*public ObservableList<StudentData> addStudentLisData(){
        ObservableList<StudentData> listStudents = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student_info";

        connect = DatabaseConnection.connectDb();

        try {
            StudentData studentD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                studentD = new StudentData(result.getString("Stud_id"),
                        result.getString("Stud_name"));

                listStudents.add(studentD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listStudents;
    }

     */
    /*
    private ObservableList<StudentData> addStudentsListD = FXCollections.observableArrayList();
    ;

    public void addStudentsShowListData() {
        try {
            StudentData studentD;
            connect = DatabaseConnection.connectDb();
            String sql = "SELECT * FROM student_info";
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                /*studentD = new StudentData(result.getString("Stud_id"),
                        result.getString("Stud_name"));


                addStudentsListD.add(new StudentData(result.getString("student_id"), result.getString("student_name")));


            }

        } catch (Exception e) {
            e.printStackTrace();


            connect = DatabaseConnection.connectDb();


            student_id.setCellValueFactory(new PropertyValueFactory<>("stud_id"));
            student_name.setCellValueFactory(new PropertyValueFactory<>("stud_name"));
            students_table.setItems(addStudentsListD);

        }

    }



    ObservableList<StudentData> addStudentsListD = FXCollections.observableArrayList();

     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            showBooks();
        }



    public ObservableList<StudentData> getStudentdata(){
        ObservableList<StudentData> addStudentsListD  = FXCollections.observableArrayList();
        connect = DatabaseConnection.connectDb();

        String query = "SELECT * FROM student_info";
        Statement st;
        ResultSet rs;

        try{

            st = connect.createStatement();
            rs = st.executeQuery(query);
            StudentData studentD;
            while(rs.next()){
                studentD = new StudentData(rs.getString("stud_id"), rs.getString("stud_name"));
                addStudentsListD.add(studentD);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return addStudentsListD ;
    }

    public void showBooks(){
        ObservableList<StudentData> list = getStudentdata();

        collstud_id.setCellValueFactory(new PropertyValueFactory<StudentData, String>("stud_id"));
        collstud_name.setCellValueFactory(new PropertyValueFactory<StudentData, String>("stud_name"));



        students_table.setItems(list);
    }

    @FXML
    private void insertRecord() throws SQLException {
        connect = DatabaseConnection.connectDb();
        String query = "INSERT INTO student_info"
                + "(stud_id ,stud_name)"
                + " VALUES(?,?) ";
        try {
            Alert alert;
            if (tfUser.getText().isEmpty()
                    || tfId.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(query);
                prepare.setString(1, tfId.getText());
                prepare.setString(2, tfUser.getText());
                prepare.executeUpdate();


                showBooks();
                ClearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        @FXML
    private void updateRecord(){
        String query = "UPDATE  student_info SET stud_name  = '" + tfUser.getText() +"'WHERE stud_id = '" +  tfId.getText() + "'";
        executeQuery(query);
        showBooks();
        ClearFields();


    }
    @FXML
    private void deletebutton() throws SQLException {
        if(tfId.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("please fill student id");
            alert.showAndWait();
        }else {
            String sql = "DELETE FROM student_info WHERE stud_id = '"
                    + tfId.getText() + "'";
            connect = DatabaseConnection.connectDb();
            statement = connect.createStatement();
            statement.executeUpdate(sql);
            showBooks();
            ClearFields();
        }
    }
    @FXML
    private void executeQuery(String query) {
        connect = DatabaseConnection.connectDb();
        Statement st;
        try{
            st = connect.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    @FXML
    public void homebutton(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }
    @FXML
    public void nextbutton(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("studentstable2.fxml"));
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }
    public void ClearFields(){
         tfUser.clear();
         tfId.clear();

    }




}




