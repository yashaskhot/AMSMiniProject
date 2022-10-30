package com.example.sample2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class SignupController {
    @FXML
    private Button back;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_firstname;
    @FXML
    private TextField tf_lastname;
    @FXML
    private TextField tf_email;
    @FXML
    private Button register;


    @FXML
    public void validateSignup(ActionEvent event) throws SQLException {


        String username = tf_username.getText();
        String Firstname = tf_firstname.getText();
        String Lastname = tf_lastname.getText();
        String Email = tf_email.getText();
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectdb = connectnow.connectDb();
        PreparedStatement psinsert = null;
        PreparedStatement pscheck = null;
        ResultSet resultSet = null;
        if (!tf_username.getText().isBlank() && !tf_firstname.getText().isBlank() && !tf_lastname.getText().isBlank() && !tf_email.getText().isBlank()) {
            try {
                pscheck = connectdb.prepareStatement("select * from teacher_details where teacher_name= ?");
                pscheck.setString(1, username);
                resultSet = pscheck.executeQuery();
                if (resultSet.isBeforeFirst()) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("YOU CANNOT USE THIS USERNAME.");
                    alert.show();
                } else {

                    psinsert = connectdb.prepareStatement("insert into teacher_details VALUES (?,?,?,?)");
                    psinsert.setString(1, username);
                    psinsert.setString(2, Firstname);
                    psinsert.setString(3, Lastname);
                    psinsert.setString(4, Email);

                    psinsert.executeUpdate();

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("next.fxml"));
                        ((Node) (event.getSource())).getScene().getWindow().hide();
                        Parent root1 = fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));
                        stage.show();
                    } catch (Exception ep) {
                        ep.printStackTrace();
                    }

                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{

                Alert alert;
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error message");
                alert.setHeaderText("YOU HAVE LEFT SOME SPACES EMPTY");
                alert.setContentText("please fill all the details");
                alert.showAndWait();

        }
    }
    @FXML
    public void backbutton(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample1.fxml"));
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










