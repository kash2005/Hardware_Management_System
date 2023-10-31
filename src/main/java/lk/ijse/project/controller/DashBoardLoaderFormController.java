package lk.ijse.project.controller;




//import com.gn.lab.GNCalendarTile;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.ijse.project.bo.BOFactory;
import lk.ijse.project.bo.custom.CustomerBO;
import lk.ijse.project.bo.custom.OrderBO;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class DashBoardLoaderFormController implements Initializable {

    /*@FXML
    private GNCalendarTile calenderId;*/

    @FXML
    public Label lblOrderCount;
    @FXML
    public Label lblCustomerCount;
    @FXML
    public Pane lblDateAndTime;
    @FXML
    public Label lblDate;
    @FXML
    public Label lblTime;
    @FXML
    public Label lblTodaySales;
    @FXML
    public Label lblTotalSales;
    @FXML
    private BarChart<String, Integer> barGraph;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadOrderCount();
        loadCustomerCount();
        generateDate();
        generateTime();
        setBarGraphValue();
        setTodaySales();
        setTotalSales();
    }

    private void setTotalSales() {
        try {

            String totalSales = orderBO.getTotalSales();
            lblTotalSales.setText(totalSales);
            System.out.println(totalSales);
        }catch (SQLException e){

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setTodaySales() {
        try {

            String todaySales = orderBO.getTodaySales(LocalDate.now());
            lblTodaySales.setText(todaySales);
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setBarGraphValue() {
        try {

            Integer[] data = customerBO.getCustomerValueMonths();

            XYChart.Series<String, Integer> series = new XYChart.Series();
            series.setName("No. of Customers");
            series.getData().add(new XYChart.Data("JAN", data[0]));
            series.getData().add(new XYChart.Data("FEB", data[1]));
            series.getData().add(new XYChart.Data("MAR", data[2]));
            series.getData().add(new XYChart.Data("APR", data[3]));
            series.getData().add(new XYChart.Data("MAY", data[4]));
            series.getData().add(new XYChart.Data("JUN", data[5]));
            series.getData().add(new XYChart.Data("JUL", data[6]));
            series.getData().add(new XYChart.Data("AUG", data[7]));
            series.getData().add(new XYChart.Data("SEP", data[8]));
            series.getData().add(new XYChart.Data("OCT", data[9]));
            series.getData().add(new XYChart.Data("NOV", data[10]));
            series.getData().add(new XYChart.Data("DEC", data[11]));

            barGraph.getData().addAll(series);
        }catch (SQLException e){} catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void generateTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e ->{
            LocalTime time = LocalTime.now();
            if (time.getMinute()< 10 && time.getSecond() < 10) {
                lblTime.setText(time.getHour() + ":" + "0" + time.getMinute() + ":" + "0" + time.getSecond());
            }else if (time.getMinute() < 10 && time.getSecond() > 10){
                lblTime.setText(time.getHour() + ":" + "0" + time.getMinute() + ":" + time.getSecond());
            }else {
                lblTime.setText(time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());
            }
        }), new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void generateDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = String.valueOf(format.format(date));
        lblDate.setText(date1);
    }

    private void loadCustomerCount() {
        try {
            int index = customerBO.customerCount();
            lblCustomerCount.setText(String.valueOf(index));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadOrderCount() {
        try {
            int index = orderBO.orderCount();
            lblOrderCount.setText(String.valueOf(index));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void userControlOnMouseClick(MouseEvent mouseEvent) {
        System.out.println("User management");
    }
}
