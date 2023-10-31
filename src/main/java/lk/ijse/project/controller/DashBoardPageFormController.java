package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardPageFormController implements Initializable  {
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane root1;

    @FXML
    public void dashboardBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/dashBoardLoaderForm.fxml"));
        root1.getChildren().clear();
        root1.getChildren().add(load);
    }
    @FXML
    public void signOutBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/loginPageForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.centerOnScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dashboardLoader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dashboardLoader() throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/dashBoardLoaderForm.fxml"));
        root1.getChildren().clear();
        root1.getChildren().add(load);
    }

    @FXML
    public void customerBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
        root1.getChildren().clear();
        root1.getChildren().add(load);
    }
    @FXML
    public void employeeBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/EmployeeForm.fxml"));
        root1.getChildren().clear();
        root1.getChildren().add(load);
    }
    @FXML
    public void supplierBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/supplierForm.fxml"));
        root1.getChildren().clear();
        root1.getChildren().add(load);
    }
    @FXML
    public void itemBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/itemForm.fxml"));
        root1.getChildren().clear();
        root1.getChildren().add(load);
    }
    @FXML
    public void orderBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/orderForm.fxml"));
        root1.getChildren().clear();
        root1.getChildren().add(load);
    }

    public void vehicleBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/vehicleFrom.fxml"));
        root1.getChildren().clear();
        root1.getChildren().add(load);
    }
}
