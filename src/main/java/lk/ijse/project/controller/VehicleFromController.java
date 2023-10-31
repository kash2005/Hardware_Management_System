package lk.ijse.project.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.project.bo.BOFactory;
import lk.ijse.project.bo.custom.DeliveryBO;
import lk.ijse.project.bo.custom.VehicleBO;
import lk.ijse.project.dto.DeliveryDTO;
import lk.ijse.project.dto.VehicleDTO;
import lk.ijse.project.dto.tm.DeliveryTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class VehicleFromController implements Initializable {
    public TableView tbl1Vehicle;
    public TableColumn colVehicleId;
    public TableColumn colVehicleType;
    public TableColumn colVehicleNumberPlate;
    public TableView tbl2Vehicle;
    public TableColumn colVehicleDelivaryId;
    public TableColumn colVehicleOrderId;
    public TableColumn colVehicleDistance;
    public TableColumn colVehicleAmount;
    public JFXTextField txtVehicleId;
    public JFXTextField txtDriverName;
    public JFXTextField txtNoPlate;
    public JFXTextField txtVehicleType;

    @FXML
    private TableView<DeliveryTM> tblDelivery;

    @FXML
    private JFXComboBox<String> cmbStatus;

    VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.VEHICLE);
    DeliveryBO deliveryBO = (DeliveryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.VEHICLE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllVehicle();
        setCellValueFactoryVehicle();
        loadDeliveryStatus();
        getAllDelivery();
        setCellValueFactoryDelivery();
    }

    private void setCellValueFactoryDelivery() {
        colVehicleDelivaryId.setCellValueFactory(new PropertyValueFactory<>("deliId"));
        colVehicleOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colVehicleDistance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        colVehicleAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void getAllDelivery() {
        ObservableList<DeliveryTM> observableList = FXCollections.observableArrayList();
        try {
            List<DeliveryDTO> deliveries = deliveryBO.getAllDelivery();
            for (DeliveryDTO delivery: deliveries) {
                observableList.add(new DeliveryTM(delivery.getOId(),delivery.getDId(),delivery.getDistance(),delivery.getAmount()));
            }
            tblDelivery.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadDeliveryStatus() {
        String[] type = {"No Delivery","Off Delivery"};
        ObservableList<String> list = FXCollections.observableArrayList(type);
        cmbStatus.setItems(list);

    }

    void getAllVehicle(){
        ObservableList<VehicleDTO> observableList = FXCollections.observableArrayList();
        try {
            List<VehicleDTO> vehicles= vehicleBO.getAllVehicle();
            for (VehicleDTO vehicle: vehicles) {
                observableList.add(new VehicleDTO(vehicle.getVehicleId(),vehicle.getVehicleType(),vehicle.getVehicleNo(),vehicle.getStatus()));
            }
            tbl1Vehicle.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactoryVehicle() {
        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colVehicleNumberPlate.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtVehicleId.getText();
        try {
            boolean isDeleted = vehicleBO.deleteVehicle(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle deleted!...").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnClearOnACtion(ActionEvent actionEvent) {
        txtVehicleId.clear();
        txtVehicleType.clear();
        txtNoPlate.clear();
        txtDriverName.clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String vehicleId = txtVehicleId.getText();
        String vehicleType = txtVehicleType.getText();
        String numberPate = txtNoPlate.getText();
        String status = cmbStatus.getValue();
        try {
            boolean isUpdated = vehicleBO.updateVehicle(new VehicleDTO(vehicleId,vehicleType,numberPate,status));
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle Updated!...").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        String vehicleId = txtVehicleId.getText();
        try {
            VehicleDTO vehicle = vehicleBO.searchVehicle(vehicleId);
            if (vehicle != null) {
                txtVehicleId.setText(vehicle.getVehicleId());
                txtVehicleType.setText(vehicle.getVehicleType());
                txtNoPlate.setText(vehicle.getVehicleNo());
                cmbStatus.setValue(vehicle.getStatus());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String vehicleId = txtVehicleId.getText();
        String vehicleType = txtVehicleType.getText();
        String numberPate = txtNoPlate.getText();
        String status = cmbStatus.getValue();

        try {
            boolean isSaved = vehicleBO.saveVehicle(new VehicleDTO(vehicleId,vehicleType,numberPate,status));
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle Saved!..").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void txtVehicleTypeOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.NAME_PATTERN).matcher(txtVehicleType.getText()).matches()){
//            txtVehicleType.setFocusColor(Color.BLUE);
//        }else {
//            txtVehicleType.setFocusColor(Color.RED);
//        }
    }

    public void txtVehicleIdOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.VEHICLE_ID_PATTERN).matcher(txtVehicleId.getText()).matches()){
//            txtVehicleId.setFocusColor(Color.BLUE);
//        }else {
//            txtVehicleId.setFocusColor(Color.RED);
//        }
    }

    public void txtDriverNameOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.NAME_PATTERN).matcher(txtDriverName.getText()).matches()){
//            txtDriverName.setFocusColor(Color.BLUE);
//        }else {
//            txtDriverName.setFocusColor(Color.RED);
//        }
    }

    public void txtNoPlateOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.ADDRESS_PATTERN).matcher(txtNoPlate.getText()).matches()){
//            txtNoPlate.setFocusColor(Color.BLUE);
//        }else {
//            txtNoPlate.setFocusColor(Color.RED);
//        }
    }
}
