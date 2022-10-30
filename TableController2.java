package com.example.sample2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TableController2 implements Initializable {
    @FXML
    private Button addbutton;

    @FXML
    private ComboBox<?> batchbox;

    @FXML
    private ComboBox<?> courseyearbox;

    @FXML
    private Button delete2;

    @FXML
    private ComboBox<?> departmentbox;

    @FXML
    private ComboBox<?> semesterbox;

    @FXML
    private TextField tf_rollno;
    @FXML
    private TextField studentid;

    @FXML
    private Button update2;
    @FXML
    private Button homebutton;
    @FXML
    private TableView<StudentData2> studentdisplay;

    @FXML
    private TableColumn<StudentData2, String> student;

    @FXML
    private TableColumn<StudentData2, String> rollno;

    @FXML
    private TableColumn<StudentData2, String> department;
    @FXML
    private TableColumn<StudentData2, String> course;

    @FXML
    private TableColumn<StudentData2, String> semester;

    @FXML
    private TableColumn<StudentData2, String> batch;
    @FXML
    private TextField keywordstextfield;

    @FXML
    private TextField add;
    @FXML
    private String[] course1 = {"FE", "SE", "TE", "BE"};
    @FXML
    public void courseisshown() {
        List<String> courseshow= new ArrayList<>();

        for (String data : course1) {
            courseshow.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(courseshow);
        courseyearbox .setItems(ObList);
    }
    @FXML
    private String[] department123 = {"COMPUTER", "INFORMATION TECHNOLOGY", "MECHANICAL", "ELECTRONICS AMD TELECOMMUNICATION"};

    @FXML
    public void depratmentisadded() {
        List<String> departmentisadded1 = new ArrayList<>();

        for (String data :department123 ) {
            departmentisadded1.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(departmentisadded1);
        departmentbox.setItems(ObList);
    }
    @FXML
    private String[] semester123= {"1", "2", "3", "4", "5", "6", "7", "8"};

    public void showsemester() {
        List<String> semester1234  = new ArrayList<>();

        for (String data : semester123 ) {
            semester1234.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(semester1234);
        semesterbox.setItems(ObList);
    }
    @FXML
    private String[]batch1234={"A","B","C"};

    @FXML
    public void batchisadded(){
        List<String>batchisadded1  = new ArrayList<>();

        for (String data :batch1234){
           batchisadded1.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(batchisadded1);
        batchbox.setItems(ObList);
    }
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    ObservableList<StudentData2> studentData2ObservableList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showStudents();
        courseisshown();
        depratmentisadded();
        showsemester();
        batchisadded();
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectdb = connectnow.connectDb();

        String productviewquery = "SELECT * FROM `ams2.0`.student_roll_batch";
        try {
            Statement statement = connectdb.createStatement();
            ResultSet queryOutput = statement.executeQuery(productviewquery);
            while (queryOutput.next()) {
                String querystud_id = queryOutput.getString("stud_id");
                String querystud_roll = queryOutput.getString("stud_roll");
                String querystud_batch = queryOutput.getString("stud_batch");
                String querystud_year = queryOutput.getString("stud_year");
                String querystud_sem = queryOutput.getString("stud_sem");
                String querystud_dept = queryOutput.getString("stud_dept");
                studentData2ObservableList.add(new StudentData2(querystud_id, querystud_roll, querystud_batch, querystud_year, querystud_sem, querystud_dept));
            }
            student.setCellValueFactory(new PropertyValueFactory<StudentData2, String>("stud_id"));
            rollno.setCellValueFactory(new PropertyValueFactory<StudentData2, String>("stud_roll"));
            department.setCellValueFactory(new PropertyValueFactory<StudentData2, String>("stud_batch"));
            course.setCellValueFactory(new PropertyValueFactory<StudentData2, String>("stud_year"));
            semester.setCellValueFactory(new PropertyValueFactory<StudentData2, String>("stud_sem"));
            batch.setCellValueFactory(new PropertyValueFactory<StudentData2, String>("stud_dept"));
            studentdisplay.setItems(studentData2ObservableList);
            FilteredList<StudentData2> filteredData = new FilteredList<>(studentData2ObservableList, b -> true);
            keywordstextfield.textProperty().addListener((Observable, oldValue, newValue) -> {
                filteredData.setPredicate(StudentData2 -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue==null) {
                        return true;
                    }
                    String searchkeyword = newValue.toLowerCase();
                    if (StudentData2.getStud_id().toLowerCase().indexOf(searchkeyword) > -1) {
                        return true;

                    } else if (StudentData2.getStud_batch().toLowerCase().indexOf(searchkeyword) > -1) {
                        return true;

                    } else if (StudentData2.getStud_dept().toLowerCase().indexOf(searchkeyword) > -1)
                        return true;
                    else if (StudentData2.getStud_sem().toLowerCase().indexOf(searchkeyword) > -1) {
                        return true;

                    } else if (StudentData2.getStud_year().toLowerCase().indexOf(searchkeyword) > -1) {
                        return true;
                    }

                    return false;

                });
            });


            SortedList<StudentData2> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(studentdisplay.comparatorProperty());
            studentdisplay.setItems(sortedData);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public ObservableList<StudentData2> getStudentdata2() {

        ObservableList<StudentData2> listData = FXCollections.observableArrayList();
        connect = DatabaseConnection.connectDb();
        String sql = "SELECT * FROM `ams2.0`.student_roll_batch;";
        Statement st;
        ResultSet rs;



        try {
            st = connect.createStatement();
            rs = st.executeQuery(sql);
           StudentData2 studentdata2;

            while (rs.next()) {
                studentdata2= new StudentData2(rs.getString("stud_id"),
                        rs.getString("stud_roll"),
                        rs.getString("stud_batch"),
                        rs.getString("stud_year"),
                        rs.getString("stud_sem"),
                        rs.getString("stud_dept"));
                        listData.add(studentdata2);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public void showStudents(){
        System.out.println("inside show");
        ObservableList<StudentData2> list2 = getStudentdata2();

        student.setCellValueFactory(new PropertyValueFactory<StudentData2 ,String>("stud_id"));
        rollno.setCellValueFactory(new PropertyValueFactory<StudentData2 ,String>("stud_roll"));
        department.setCellValueFactory(new PropertyValueFactory<StudentData2 ,String>("stud_batch"));
        course.setCellValueFactory(new PropertyValueFactory<StudentData2 ,String>("stud_year"));
        semester.setCellValueFactory(new PropertyValueFactory<StudentData2 ,String>("stud_sem"));
        batch.setCellValueFactory(new PropertyValueFactory<StudentData2 ,String>("stud_dept"));
        System.out.println("have set values");

        studentdisplay.setItems(list2);
        System.out.println("displayed");

    }
    @FXML
    public void students() {


        String sql = "INSERT INTO student_roll_batch "
                + "(stud_id,stud_roll,stud_batch,stud_year,stud_sem,stud_dept) "
                + "VALUES(?,?,?,?,?,?)";

        connect = DatabaseConnection.connectDb();

        try {
            Alert alert;
            if (tf_rollno.getText().isEmpty()
                    ||studentid.getText().isEmpty()
                    || departmentbox.getSelectionModel().getSelectedItem() == null
                    || courseyearbox.getSelectionModel().getSelectedItem() == null
                    || semesterbox.getSelectionModel().getSelectedItem() == null
                    || batchbox.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, studentid.getText());
                prepare.setString(2, tf_rollno.getText());
                prepare.setString(3, (String)batchbox .getSelectionModel().getSelectedItem());
                prepare.setString(4, (String)courseyearbox.getSelectionModel().getSelectedItem());
                prepare.setString(5, (String) semesterbox.getSelectionModel().getSelectedItem());
                prepare.setString(6, (String) departmentbox.getSelectionModel().getSelectedItem());
                prepare.executeUpdate();
               showStudents();
               ClearData();


                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void addStudentsUpdate() {


        String updateData = "UPDATE  student_roll_batch SET "
                + "stud_roll = '" + tf_rollno.getText()
                + "', stud_dept = '" + departmentbox.getSelectionModel().getSelectedItem()
                + "', stud_sem = '" + semesterbox.getSelectionModel().getSelectedItem()
                + "', stud_year = '" + courseyearbox.getSelectionModel().getSelectedItem()
                + "', stud_batch = '" + batchbox.getSelectionModel().getSelectedItem()
                +  "' WHERE stud_id = '"
                + studentid.getText() + "'";


        connect = DatabaseConnection.connectDb();

        try {
            Alert alert;
            if ( studentid.getText().isEmpty()
                    ||  batchbox.getSelectionModel().getSelectedItem() == null
                    || semesterbox.getSelectionModel().getSelectedItem() == null
                    ||courseyearbox.getSelectionModel().getSelectedItem() == null
                    ||  departmentbox.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Student #" + studentid.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    showStudents();
                    ClearData();
                    // TO CLEAR THE FIELDS


                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void deleterecords() throws SQLException {
        if(studentid.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("please fill student id");
            alert.showAndWait();
        }else {
            try {
                String sql = "DELETE FROM student_roll_batch WHERE stud_id = '"
                        + studentid.getText() + "'";

                connect = DatabaseConnection.connectDb();
                statement = connect.createStatement();
                statement.executeUpdate(sql);

                showStudents();
                ClearData();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    public void ClearData(){
         batchbox.getSelectionModel().clearSelection();
         semesterbox.getSelectionModel().clearSelection();
         tf_rollno.clear();
         studentid.clear();
        departmentbox.getSelectionModel().clearSelection();
        courseyearbox.getSelectionModel().clearSelection();






    }






}

