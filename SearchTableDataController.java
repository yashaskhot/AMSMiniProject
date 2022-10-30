package com.example.sample2;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SearchTableDataController implements Initializable {
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

    ObservableList<StudentData2> studentData2ObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
}











