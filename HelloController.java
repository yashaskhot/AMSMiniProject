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

public class HelloController {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordPasswordField;

    @FXML
    private Button button_login;
    @FXML
    private Button signup;


    private Parent root;
    private Stage stage;
    private Scene scene;
    public static String Username;


    @FXML
    private void validateLogin(ActionEvent event) {
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDb = connectnow.connectDb();
        String verifylogin = "select count(1) from credentials where username = '" + usernameTextField.getText() + "' and password  = '" + passwordPasswordField.getText() + "'";

        Statement statement = null;
        try {
            statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    try {
                        Alert alert;
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("INFORMATION MESSAGE");
                        alert.setContentText("Successfully login");
                        Username=usernameTextField.getText();
                        button_login.getScene().getWindow().hide();
                        alert.showAndWait();
                        Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();



                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else{

                        Alert alert;
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("error message");
                        alert.setHeaderText("null");
                        alert.setContentText("wrong username and password");
                        alert.showAndWait();

                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void switchTologinpage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Signup.fxml"))); //pass scene name here
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}

