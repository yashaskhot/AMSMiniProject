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
import java.time.LocalDate;
import java.util.*;
import java.util.Date;


public class AddAttendance implements Initializable {

    @FXML
    private TableView<StudentData3> absent;

    @FXML
    private TableColumn<StudentData3, String> course_id;

    @FXML
    private TableColumn<StudentData3, Date> date;

    @FXML
    private TableColumn<StudentData3, String> lesson_id;
    @FXML
    private ComboBox<?> coursebox;


    @FXML
    private TableColumn<StudentData3, String> roll_no;
    @FXML
    private TextField lesson_no;

    @FXML
    private TextField roll_number;

    @FXML
    private DatePicker select_date;
    @FXML
    private Button addbutton;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button home_Button;
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private String[] course = {"ITC301", "ITC302", "ITC303", "ITC304", "ITC305"};

    @FXML
    public void showcourse() {
        List<String> courseshowed = new ArrayList<>();

        for (String data : course) {
            courseshowed.add(data);
        }

        ObservableList list = FXCollections.observableArrayList(courseshowed);
        coursebox.setItems(list);
    }

    ObservableList<StudentData3> studentData3ObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showData();
        showcourse();
    }

    public ObservableList<StudentData3> getStudentdata3() {

        ObservableList<StudentData3> Data = FXCollections.observableArrayList();
        connect = DatabaseConnection.connectDb();
        String sql = "SELECT * FROM `ams2.0`.absent_register";
        Statement st;
        ResultSet rs;


        try {
            st = connect.createStatement();
            rs = st.executeQuery(sql);
            StudentData3 studentData3;

            while (rs.next()) {
                studentData3 = new StudentData3(rs.getString("stud_id"), rs.getString("course_id"), rs.getString("lesson_id"), rs.getDate("date"));
                Data.add(studentData3);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Data;
    }

    public void showData() {
        ObservableList<StudentData3> absentlist = getStudentdata3();

        course_id.setCellValueFactory(new PropertyValueFactory<StudentData3, String>("course_id"));
        lesson_id.setCellValueFactory(new PropertyValueFactory<StudentData3, String>("lesson_id"));
        roll_no.setCellValueFactory(new PropertyValueFactory<StudentData3, String>("stud_id"));
        date.setCellValueFactory(new PropertyValueFactory<StudentData3, Date>("date"));


        absent.setItems(absentlist);
    }

    @FXML
    private void insertRecord() throws SQLException {
        String query = "INSERT INTO `ams2.0`.`absent_register` ( `lesson_id`, `stud_id`, `date`, `course_id`) VALUES (?,?,?,?)";
        if (lesson_no.getText().isEmpty()
                || roll_number.getText().isEmpty()
                || coursebox.getSelectionModel().getSelectedItem() == null) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {
            prepare = connect.prepareStatement(query);
            prepare.setString(1, lesson_no.getText());
            prepare.setString(2, roll_number.getText());
            LocalDate date= select_date.getValue();
            prepare.setString(3, String.valueOf(date));

            prepare.setString(4, (String) coursebox.getSelectionModel().getSelectedItem());

            prepare.executeUpdate();
            showData();
            Alert alert;

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Added!");
            alert.showAndWait();


        }
    }
    @FXML
    public void updatestudents() throws SQLException {



        LocalDate date1 = select_date.getValue();

        String sql = "UPDATE absent_register SET stud_id = '"
                + roll_number.getText() + "', course_id = '"
                + coursebox.getSelectionModel().getSelectedItem() + "', date = '" + select_date.getValue()+ "' WHERE lesson_id ='"
                + lesson_no.getText() + "'";


        connect = DatabaseConnection.connectDb();

        try {
            Alert alert;
            if (roll_number.getText().isEmpty()
                    || lesson_no.getText().isEmpty()
                    || coursebox.getSelectionModel().getSelectedItem() == null
                    ||date1 == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE roll no: " + roll_number.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    System.out.println("database updated");
                    showData();
                }else {
                    return;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void addEmployeeDelete() {

        String sql = "DELETE FROM absent_register WHERE stud_id = '"
                + roll_number.getText() + "'";

        connect =DatabaseConnection.connectDb();

        try {

            Alert alert;
            if (roll_number.getText().isEmpty()
                    ||lesson_no.getText().isEmpty()
                    || coursebox.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE roll no: " + roll_number.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    showData();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void home_buttononclick(ActionEvent event){
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



}






