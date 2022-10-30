package com.example.sample2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TotalPresent implements Initializable {
    @FXML
    private TableColumn<StudentData2, String> attendance_status;

    @FXML
    private TableView<StudentData5> pressent_table;

    @FXML
    private TableColumn<StudentData2, String> student_coloumn;

    @FXML
    private TableColumn<StudentData2, String> studentid_coloumn;
    @FXML
    private DatePicker present;
    private Statement statement = null;
    private PreparedStatement prepare;
    private ResultSet result;
    PreparedStatement psinsert = null;
    PreparedStatement pscheck = null;
    ResultSet resultSet = null;

    @FXML
    private Button present_button;
    public ObservableList<StudentData5> getStudentdata5() throws SQLException {

        ObservableList<StudentData5> listData1 = FXCollections.observableArrayList();
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectdb = connectnow.connectDb();
        pscheck = connectdb.prepareStatement( "SELECT student_info.stud_id, student_info.stud_name\n" +
                ",  CASE WHEN absent IS NOT NULL THEN 'Absent' ELSE 'Present' END as attendance_status\n" +
                "FROM student_info\n" +
                "LEFT JOIN absent_register on student_info.stud_id=absent_register.stud_id ");
        Statement st;
        ResultSet rs;
        LocalDate today = LocalDate.now();
        LocalDate date = present.getValue();
        statement = connectdb.createStatement();
        resultSet = pscheck.executeQuery();

        StudentData5 studentData5 = null;

            try {
                st = connectdb.createStatement();
                rs = pscheck.executeQuery();

                while (rs.next()) {
                    studentData5 = new StudentData5(rs.getString("stud_id"),
                            rs.getString("stud_name"),
                            rs.getString("attendance_status"));
                            listData1.add(studentData5);


                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        return listData1;
    }

    public void showStudentsdata() throws SQLException {
        System.out.println("inside show");
        ObservableList<StudentData5> list = getStudentdata5();

        student_coloumn.setCellValueFactory(new PropertyValueFactory<StudentData2 ,String>("stud_id"));
        studentid_coloumn.setCellValueFactory(new PropertyValueFactory<StudentData2 ,String>("stud_name"));
        attendance_status.setCellValueFactory(new PropertyValueFactory<StudentData2 ,String>("attendance_status"));

        System.out.println("have set values");

        pressent_table.setItems(list);
        System.out.println("displayed");

    }

    @FXML
    public void present_onaction(ActionEvent event) throws SQLException {

        ObservableList<StudentData5> listData1 = getStudentdata5();
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectdb = connectnow.connectDb();

        LocalDate today = LocalDate.now();
        LocalDate date = present.getValue();
        String sql = "SELECT student_info.stud_id, student_info.stud_name ,  CASE WHEN absent IS NOT NULL THEN 'Absent' ELSE 'Present' END as attendance_status FROM student_info LEFT JOIN absent_register on student_info.stud_id=absent_register.stud_id AND absent_register.date = date ";
        pscheck.setString(1, String.valueOf(date));
        statement = connectdb.createStatement();
        resultSet = statement.executeQuery(sql);
        if (date == null || date.isAfter(today)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("please fill a valid date");
            alert.showAndWait();
        } else {
            try {
                try {
                    Statement statement = connectdb.createStatement();
                    ResultSet queryOutput = pscheck.executeQuery();

                    while (queryOutput.next()) {
                        StudentData5 studentData5 = new StudentData5(queryOutput.getString("stud_abs"), queryOutput.getString("student_name"), queryOutput.getString("attendance_status"));
                        listData1.add(studentData5);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                showStudentsdata();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showStudentsdata();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
