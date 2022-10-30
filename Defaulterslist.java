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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;


public class Defaulterslist implements Initializable {


    @FXML
    private TableColumn<StudentData4, String> absent_percentage;
    @FXML
    private Button homeb;

    @FXML
    private TableView<StudentData4> defaulters_List;

    @FXML
    private Button defaulters_display;

    @FXML
    private TableColumn<StudentData4,String> stud_abs;

    public ObservableList<StudentData4> getStudentdata4() {

        ObservableList<StudentData4> Data = FXCollections.observableArrayList();
        Connection connect = DatabaseConnection.connectDb();
        String sql = "SELECT stud_id AS Stud_abs, count(absent) * 100.0 / max(lesson_id) Over() as 'absent_percentage'\n" +
                "FROM absent_register\n" +
                "GROUP BY stud_id;";
        Statement st;
        ResultSet rs;


        try {
            st = connect.createStatement();
            rs = st.executeQuery(sql);
            StudentData4 studentData4;

            while (rs.next()) {
                studentData4 = new StudentData4(rs.getString("stud_abs"), rs.getString("absent_percentage"));
                Data.add(studentData4);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Data;
    }
    public void showData1() {
        ObservableList<StudentData4> absentlist1 = getStudentdata4();

        stud_abs.setCellValueFactory(new PropertyValueFactory<StudentData4, String>("stud_abs"));
        absent_percentage.setCellValueFactory(new PropertyValueFactory<StudentData4, String>("absent_percentage"));


        defaulters_List.setItems(absentlist1);
    }


    @FXML
    void homebutton_onaction(ActionEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showData1();

    }
}
