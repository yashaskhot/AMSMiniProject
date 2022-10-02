package com.example.presence;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class LoginController {
    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Button login;

    private Connection connect;


    public void loginButtonOnAction(){
        String Sql ="SELECT * FROM AMS2.0 WHERE username = ? and password = ?";

        Connection connect = DatabaseConnection.connectDb();

        try{
            PreparedStatement prepare = connect.prepareStatement(Sql);
            prepare.setString(1, usernameTextField.getText());
            prepare.setString(1, passwordPasswordField.getText());

            ResultSet result = prepare.executeQuery();
            Alert alert;

            if(usernameTextField.getText().isEmpty()||passwordPasswordField.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error message");
                alert.setHeaderText("null");
                alert.setContentText("please fill all the details");

            }else if (result.next()) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFORMATION MESSAGE");
                alert.setHeaderText("null");
                alert.setContentText("successfully login");
                login.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Homepage.fxml")));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error message");
                alert.setHeaderText("null");
                alert.setContentText("wrong username/password");
            }



        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


    }
}