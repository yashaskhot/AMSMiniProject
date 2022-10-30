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
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Record implements Initializable {
    @FXML
    private Button backb;
    @FXML
    private DatePicker absent;
    @FXML
    private Label absent_days;
    @FXML
    private Label defaulterslist;
    @FXML
    private Label textarea;
    @FXML
    private TextField tf_course_id;
    @FXML
    private ComboBox course_idtf;

    @FXML
    private TextField studentroll_number;

    @FXML
    private Button display;
    @FXML
    private Button button;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    PreparedStatement psinsert = null;
    PreparedStatement pscheck = null;
    ResultSet resultSet = null;
    @FXML
    private String[] course123 = {"ITC301", "ITC302", "ITC303", "ITC304", "ITC305"};

    @FXML
    public void showcourse() {
        List<String> courseshowed = new ArrayList<>();

        for (String data : course123) {
            courseshowed.add(data);
        }

        ObservableList list = FXCollections.observableArrayList(courseshowed);
        course_idtf.setItems(list);
    }

    @FXML
    public void button_show(ActionEvent event) throws SQLException {
        String course_id = (String) course_idtf.getSelectionModel().getSelectedItem();
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectdb = connectnow.connectDb();
        pscheck = connectdb.prepareStatement("SELECT MAX(lesson_id) AS \"Total No. of Lectures\"  FROM absent_register WHERE course_id = ?");
        pscheck.setString(1, course_id);
        resultSet = pscheck.executeQuery();


        try {
            Statement statement = connectdb.createStatement();
            ResultSet queryOutput = pscheck.executeQuery();
            while (queryOutput.next()) {
                textarea.setText(queryOutput.getString("Total No. of Lectures"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void display_button(ActionEvent event) throws SQLException {
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectdb = connectnow.connectDb();
        pscheck = connectdb.prepareStatement("SELECT count(absent) as absent_days\n FROM absent_register\n  where date = ?");
        ResultSet rs;
        LocalDate today = LocalDate.now();
        LocalDate date = absent.getValue();
        pscheck.setString(1, String.valueOf(date));

        resultSet = pscheck.executeQuery();
        if (date == null || date.isAfter(today)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("please fill a valid date");
            alert.showAndWait();
        } else {
            try {
                Statement statement = connectdb.createStatement();
                ResultSet queryOutput = pscheck.executeQuery();
                while (queryOutput.next()) {
                    absent_days.setText(queryOutput.getString("absent_days"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


    @FXML
    public void defaulters_list(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Defaulterslist.fxml"));
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
    public void backbutton(ActionEvent event ){
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
        showcourse();
    }
}


