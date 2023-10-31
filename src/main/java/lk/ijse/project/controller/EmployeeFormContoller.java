package lk.ijse.project.controller;

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
import javafx.scene.paint.Color;
import lk.ijse.project.bo.BOFactory;
import lk.ijse.project.bo.SuperBO;
import lk.ijse.project.bo.custom.EmployeeBO;
import lk.ijse.project.dto.EmployeeDTO;
import lk.ijse.project.dto.tm.EmployeeTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeFormContoller implements Initializable {

    @FXML
    public JFXTextField txtEId;
    @FXML
    public JFXTextField txtEName;
    @FXML
    public JFXTextField txtEAddress;
    @FXML
    public JFXTextField txtESalary;
    @FXML
    public JFXTextField txtEContactNo;
    @FXML
    public JFXTextField txtEJob;
    @FXML
    public TableView tblEmployee;
    @FXML
    public TableColumn colEmployeeId;
    @FXML
    public TableColumn colEmployeeName;
    @FXML
    public TableColumn colEmployeeAddress;
    @FXML
    public TableColumn colEmployeeSalary;
    @FXML
    public TableColumn colEmployeeContactNo;
    @FXML
    public TableColumn colEmployeeJob;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValueFactory();
        getAll();
    }

    @FXML
    public void clearBtnOnAction(ActionEvent actionEvent) {
        txtEId.clear();
        txtEName.clear();
        txtEAddress.clear();
        txtEContactNo.clear();
        txtESalary.clear();
        txtEJob.clear();
    }

    @FXML
    public void saveBtnOnAction(ActionEvent actionEvent) {
        String id = txtEId.getText();
        String name = txtEName.getText();
        String address = txtEAddress.getText();
        String contactNo = txtEContactNo.getText();
        double salary = Double.parseDouble(txtESalary.getText());
        String job = txtEJob.getText();

        try {
            boolean isSaved = employeeBO.saveEmployee(new EmployeeDTO(id,name,address,job,salary,contactNo));
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee saved !").show();
                getAll();
            }else{
                getAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING,"SQLException !").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteBtnOnAction(ActionEvent actionEvent) {
        String id = txtEId.getText();
        try {
            boolean isDeleted = employeeBO.deleteEmployee(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee deleted !").show();
                getAll();
            }else {
                getAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Somthing happend !").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateBtnOnAction(ActionEvent actionEvent) {
        String id = txtEId.getText();
        String name = txtEName.getText();
        String address = txtEAddress.getText();
        String contactNo = txtEContactNo.getText();
        double salary = Double.parseDouble(txtESalary.getText());
        String job = txtEJob.getText();

        try {
            boolean isUpdated = employeeBO.updateEmployee(new EmployeeDTO(id,name,address,job,salary,contactNo));
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer updated").show();
                getAll();
            }else {
                getAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQLException");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    void setValueFactory() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmployeeContactNo.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
        colEmployeeSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colEmployeeJob.setCellValueFactory(new PropertyValueFactory<>("job"));
    }
    void getAll() {
        ObservableList<EmployeeTM> oblist = FXCollections.observableArrayList();
        try {
            List<EmployeeDTO> employeeList = employeeBO.getAllEmployee();
            for (EmployeeDTO employee:employeeList) {
                oblist.add(new EmployeeTM(
                        employee.getEId(),
                        employee.getEName(),
                        employee.getEAddress(),
                        employee.getEJob(),
                        employee.getESalary(),
                        employee.getEContactNo()
                ));
            }
            tblEmployee.setItems(oblist);

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void searchOnAction(ActionEvent actionEvent) {
        String id = txtEId.getText();


        try {
            EmployeeDTO employee = employeeBO.searchEmployeeId(id);
            if (employee != null){
                txtEId.setText(employee.getEId());
                txtEName.setText(employee.getEName());
                txtEAddress.setText(employee.getEAddress());
                txtEJob.setText(employee.getEJob());
                txtESalary.setText(String.valueOf(employee.getESalary()));
                txtEContactNo.setText(employee.getEContactNo());
            }else {
                new Alert(Alert.AlertType.ERROR,"Employee not found").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING,"SQLException");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void NewIdOnAction(ActionEvent actionEvent) {
        try {
            String newID = employeeBO.generateNextEmployeeId();
            txtEId.setText(newID);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void txtENameOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.NAME_PATTERN).matcher(txtEName.getText()).matches()){
//            txtEName.setFocusColor(Color.BLUE);
//        }else {
//            txtEName.setFocusColor(Color.RED);
//        }
    }

    public void txtEAddressOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.ADDRESS_PATTERN).matcher(txtEAddress.getText()).matches()){
//            txtEAddress.setFocusColor(Color.BLUE);
//        }else {
//            txtEAddress.setFocusColor(Color.RED);
//        }
    }

    public void txtESalaryOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.DOUBLE_PATTERN).matcher(txtESalary.getText()).matches()){
//            txtESalary.setFocusColor(Color.BLUE);
//        }else {
//            txtESalary.setFocusColor(Color.RED);
//        }
    }

    public void txtEContactNoOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.CONTACT_PATTERN).matcher(txtEContactNo.getText()).matches()){
//            txtEContactNo.setFocusColor(Color.BLUE);
//        }else {
//            txtEContactNo.setFocusColor(Color.RED);
//        }
    }

    public void txtEJobOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.NAME_PATTERN).matcher(txtEJob.getText()).matches()){
//            txtEJob.setFocusColor(Color.BLUE);
//        }else {
//            txtEJob.setFocusColor(Color.RED);
//        }
    }

    public void reportBtnOnAction(ActionEvent actionEvent) throws JRException, SQLException {
//        JasperDesign load = JRXmlLoader.load(new File("D:\\Final Project (2)\\Final Project (2)\\Final Project\\Final Project\\src\\main\\resources\\report\\Employee.jrxml"));
//        JRDesignQuery newQuery = new JRDesignQuery();
//        String sql = "select * from employee";
//        newQuery.setText(sql);
//        load.setQuery(newQuery);
//        JasperReport js = JasperCompileManager.compileReport(load);
//        HashMap<String,Object> hm = new HashMap<>();
//        JasperPrint jp = JasperFillManager.fillReport(js,hm, DBConnection.getInstance().getConnection());
//        JasperViewer viewer = new JasperViewer(jp, false);
//        viewer.show();
    }
}
