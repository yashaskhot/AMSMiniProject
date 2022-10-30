package com.example.sample2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Chart implements Initializable {

        @FXML
        private BarChart<?, ?> barchart;
    @FXML
    private CategoryAxis x;
    @FXML
    private Button statistics;

    @FXML
    private NumberAxis y;
        private Connection connection;
        private PreparedStatement prepare;
        private ResultSet result;

        public void chart(){
            DatabaseConnection connectnow = new DatabaseConnection();
            Connection connect = connectnow.connectDb();
            String chartsql ="SELECT course_id ,count(absent) FROM absent_register group by course_id ";
            try {
                XYChart.Series chartData = new XYChart.Series();
                prepare = connect.prepareStatement(chartsql);
                result = prepare.executeQuery();

                while(result.next()){
                    chartData.getData().add(new XYChart.Data(result.getString(1),result.getInt(2 )));

                }
                barchart.getData().addAll(chartData);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        public void statisticsbutton(ActionEvent event){
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
        chart();

    }
}


