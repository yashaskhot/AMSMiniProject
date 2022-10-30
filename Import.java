package com.example.sample2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static com.example.sample2.DatabaseConnection.connect;

public class Import {
    @FXML
    private TextArea display;
    @FXML
    private Button add;
    @FXML
    private Button select;
    @FXML
    private Button home;
    private String filepath;

    @FXML
    void upload(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser .setInitialDirectory(new File("C:\\Users\\adity\\IdeaProjects\\sample2\\src\\main\\resources\\com\\example\\sample2"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        filepath = selectedFile.getAbsolutePath();
        if(selectedFile!= null){
            Scanner scanner = new Scanner(selectedFile);
            scanner.useDelimiter(",");
            while(scanner.hasNextLine()){
                display.appendText(scanner.nextLine() +"\n");

            }

        }else{
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error message");
            alert.setHeaderText("NO FILE SELECTED");
            alert.setContentText("please select a csv file");
            alert.showAndWait();
        }
    }
    @FXML
    void Select(ActionEvent event) throws FileNotFoundException {
        if(filepath!= null) {

            try {
                DatabaseConnection connectnow = new DatabaseConnection();
                Connection connectDb = connectnow.connectDb();
                String sql = "INSERT INTO `ams2.0`.`absent_register` (`lesson_id`, `stud_id`, `date`,`course_id`) VALUES (?,?,?,?)";

                BufferedReader linereader = new BufferedReader(new FileReader(filepath));
                String lineText = null;
                int count = 0;
                linereader.readLine();
                while ((lineText = linereader.readLine()) != null) {
                    String[] data = lineText.split(",");
                    String lesson_id = data[0];
                    String stud_id = data[1];
                    String date = data[2];
                    String course_id = data[3];
                    PreparedStatement prepare = connect.prepareStatement(sql);


                    prepare.setString(1, lesson_id);
                    prepare.setString(2, stud_id);
                    prepare.setString(3, date);
                    prepare.setString(4, course_id);
                    prepare.executeUpdate();



                }
                linereader.close();
                System.out.println("data has been added");
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION MESSAGE");
            alert.setContentText("data successfully added");
            alert.showAndWait();
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

        }else {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error message");
            alert.setHeaderText("NO FILE SELECTED");
            alert.setContentText("please select a csv file");
            alert.showAndWait();

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


}
