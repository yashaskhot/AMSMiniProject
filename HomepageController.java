package com.example.sample2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomepageController extends HelloController implements Initializable {
    @FXML
    private Button importbutton;
    @FXML
    private Button profile;
    @FXML
    private Button student;
    @FXML
    private Button reports;
    @FXML
    private Button logout;
    @FXML
    private Button modify;
    @FXML
    private Label label;
    @FXML
    private Button addattendance;
    @FXML
    private ImageView imageView;
    String image[]={"C:\\Users\\adity\\IdeaProjects\\sample2\\src\\main\\resources\\com\\example\\sample2\\images_1542638058298_mumbai_university.jpg",
            "C:\\Users\\adity\\IdeaProjects\\sample2\\src\\main\\resources\\com\\example\\sample2\\IMAGE 55.jpg",
            "C:\\Users\\adity\\IdeaProjects\\sample2\\src\\main\\resources\\com\\example\\sample2\\newimage.jpg",
            "C:\\Users\\adity\\IdeaProjects\\sample2\\src\\main\\resources\\com\\example\\sample2\\MUMBAIUNIVERSITY.jpg",
            "C:\\Users\\adity\\IdeaProjects\\sample2\\src\\main\\resources\\com\\example\\sample2\\university_of_manchester.jpeg"};

    @FXML
    public void showprofile(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile.fxml"));
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
    public void showstudent(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("studentstable.fxml"));
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }
    public void buttonlogout(ActionEvent event){
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
    public void importbutton(ActionEvent event)throws IOException{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Importattendance.fxml"));
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
    public void attendance(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("attendanceadd.fxml"));
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
    public void report_button(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Record.fxml"));
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
    public void modifybutton(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Chart.fxml"));
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        }
        public void ShowLabel(){
        label.setText(Username);
        }








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ShowLabel();
        SliderThread sliderThread = new SliderThread();
        sliderThread.start();

    }
    class SliderThread extends Thread{
        int i=0;
        @Override
        public void run(){
            try{
                while(true){
                    sleep(1000);
                    if(i>= image.length)
                        i=0;

                    imageView.setImage(new Image(image[i]));
                    i++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    /*
    String image[]={"C:\\Users\\adity\\IdeaProjects\\sample2\\src\\main\\resources\\com\\example\\sample2\\imag1.jpg",
            "C:\\Users\\adity\\IdeaProjects\\sample2\\src\\main\\resources\\com\\example\\sample2\\image.jpg",
            "C:\\Users\\adity\\IdeaProjects\\sample2\\src\\main\\resources\\com\\example\\sample2\\image3.png"};

     */
}
