package com.example.sample2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NextController {
    @FXML
    private TextField tf_user;
    @FXML
    private PasswordField tf_password;
    @FXML
    private TextField tf_teacherid;
    @FXML
    private TextField showpassword;
    @FXML
    public Button done_button;
    @FXML
    public CheckBox password;
    @FXML
    private Button registerd;
    @FXML
    public Button back;


    @FXML
    public void done(ActionEvent event) throws IOException, SQLException {
        System.out.println("inside done");
        Pattern P = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
        Matcher m = P.matcher(tf_password.getText());
        if (m.matches()) {
            String teacherid = tf_teacherid.getText();
            String user = tf_user.getText();
            String password = tf_password.getText();
            DatabaseConnection connectnow = new DatabaseConnection();
            Connection connectdb = connectnow.connectDb();
            PreparedStatement psinsert = null;
            PreparedStatement pscheck = null;
            ResultSet resultSet = null;
            psinsert = connectdb.prepareStatement("insert into credentials VALUES (?,?,?)");
            psinsert.setString(1, teacherid);
            psinsert.setString(2, user);
            psinsert.setString(3, password);


            psinsert.executeUpdate();
            System.out.println("inside scene change");

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
                ((Node) (event.getSource())).getScene().getWindow().hide();
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception ep) {
                ep.printStackTrace();
            }
        } else {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error message");
            alert.setHeaderText("null");
            alert.setContentText("password should have at least one(digit,lowercase,uppercase and special charecter and should be minimun 6-15 charecters long");
            alert.showAndWait();
        }
    }

    @FXML
    public void changevisibility(ActionEvent event) {
        if (password.isSelected()) {
            showpassword.setText(tf_password.getText());
            showpassword.setVisible(true);
            tf_password.setVisible(false);
            return;


        } else {
            tf_password.setText(showpassword.getText());
            tf_password.setVisible(true);
            showpassword.setVisible(false);
        }
    }

    @FXML
    public void registeruser(ActionEvent event) {
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
    @FXML
    public void backbutton(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Signup.fxml"));
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
