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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Profile implements Initializable {
    /*
    @FXML
    private MenuButton courses;

    @FXML
    private MenuButton department;

    @FXML
    private MenuButton sem;

    @FXML
    private TextField tf_professorid;

    @FXML
    private TextField tf_professorname;


    public void showprofile(ActionEvent event)throws IOException;

    String professorid = tf_professorid.getText();
    String Firstname = tf_professorname.getText();
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
    }

     */
    @FXML
    private ComboBox addsem;
    @FXML
    private ComboBox department;
    @FXML
    private ComboBox courseid;
    @FXML
    private TextField tf_professorname;
    @FXML
    private TextField tf_professorid;
    @FXML
    private TextField course_id;
    @FXML
    private Button save;
    @FXML
    private Button home;
    @FXML
    private ImageView imageview;
    private Image image;

    private String[] yearList = {"First Year", "Second Year", "Third Year", "Fourth Year"};

    @FXML
    public void addStudentsYearList() {

        List<String> yearL = new ArrayList<>();

        for (String data : yearList) {
            yearL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(yearL);
        addsem.setItems(ObList);

    }

    @FXML
    private String[] departmentlist = {"COMPUTER", "INFORMATION TECHNOLOGY", "MECHANICAL", "ELECTRONICS AMD TELECOMMUNICATION"};

    @FXML
    public void adddepartment() {
        List<String> departmentl = new ArrayList<>();

        for (String data : departmentlist) {
            departmentl.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(departmentl);
        department.setItems(ObList);
    }

    @FXML
    private String[] course = {"ENGINEERING MATHEMATICS-3", "DATA STRUCTURES AND ALGORITHMS", "DATABASE MANAGEMENT SYSTEM", "PARADIGMS OF PROGRAMMING LANGUAGE", "JAVA LAB", "DBMS LAB", "PCPF LAB", "DSA LAB", "PRINCIPLE OF COMMUNICATION"};

    public void showcourse() {
        List<String> coursel = new ArrayList<>();

        for (String data : course) {
            coursel.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(coursel);
        courseid.setItems(ObList);
    }
    @FXML
    public void addStudentsAdd() {
        System.out.println("inside student add");

        String insertData = "INSERT INTO `ams2.0`.`courses` (`course_id`, `course`, `dept`, `sem`, `prof_id`,`prof_name`,`image`) VALUES(?,?,?,?,?,?,?)";

        Connection connect = DatabaseConnection.connectDb();
        System.out.println("database connected");

        try {
            Alert alert;
            if (course_id.getText().isEmpty()
                    || courseid.getSelectionModel().getSelectedItem() == null
                    || department.getSelectionModel().getSelectedItem() == null
                    || addsem.getSelectionModel().getSelectedItem() == null
                    || tf_professorid.getText().isEmpty()
                    ||tf_professorname.getText().isEmpty()
                    ||getData.path==null || getData.path=="") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                PreparedStatement prepare = connect.prepareStatement(insertData);
                prepare.setString(1, course_id.getText());
                prepare.setString(2, (String) courseid.getSelectionModel().getSelectedItem());
                prepare.setString(3, (String) department.getSelectionModel().getSelectedItem());
                prepare.setString(4, (String) addsem.getSelectionModel().getSelectedItem());
                prepare.setString(5, tf_professorid.getText());
                prepare.setString(6, tf_professorname.getText());
                String uri = getData.path;
                uri = uri.replace("\\", "\\\\");

                prepare.setString(7, uri);
                prepare.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();
                System.out.println("value added");
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }


    }
    public void addEmployeeInsertImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*jpg", "*png"));

        File file = open.showOpenDialog(new Stage());

        if (file != null) {

            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 177, 170, false, true);
            imageview.setImage(image);

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addStudentsYearList();
        adddepartment();
        showcourse();
    }
}





