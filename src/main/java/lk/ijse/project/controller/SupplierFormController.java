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
import lk.ijse.project.bo.custom.SupplierBO;
import lk.ijse.project.dto.SupplierDTO;
import lk.ijse.project.dto.tm.SupplierTM;
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
import java.util.function.Supplier;

public class SupplierFormController implements Initializable {
    @FXML
    public TableView tblSupplier;
    @FXML
    public TableColumn colSupId;
    @FXML
    public TableColumn colSupName;
    @FXML
    public TableColumn colSupContactNo;
    @FXML
    public TableColumn colSupCompany;
    @FXML
    public JFXTextField txtSupCompany;
    @FXML
    public JFXTextField txtSupName;
    @FXML
    public JFXTextField txtSupId;
    @FXML
    public JFXTextField txtSupContactNo;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAll();
        setValueFactory();
    }

    void getAll(){
        ObservableList<SupplierTM> oblist = FXCollections.observableArrayList();
        try {
            List<SupplierDTO> supplierList = supplierBO.getAllSupplier();
            for (SupplierDTO supplier:supplierList) {
                oblist.add(new SupplierTM(
                        supplier.getSupplierId(),
                        supplier.getName(),
                        supplier.getCompany(),
                        supplier.getContactNo()

                ));
            }
            tblSupplier.setItems(oblist);

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void setValueFactory() {
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colSupContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));

    }

    @FXML
    public void deleteBtnOnAction(ActionEvent actionEvent) {
        String id = txtSupId.getText();

        try {
            boolean isDeleted = supplierBO.deleteSupplier(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier deleted !").show();
                getAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING,"SQLExeption !").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clearBtnOnAction(ActionEvent actionEvent) {
        txtSupId.clear();
        txtSupName.clear();
        txtSupContactNo.clear();
        txtSupCompany.clear();
    }

    @FXML
    public void saveBtnOnAction(ActionEvent actionEvent) {
        String id = txtSupId.getText();
        String name = txtSupName.getText();
        String contactNo = txtSupContactNo.getText();
        String company = txtSupCompany.getText();

        boolean isSaved = false;
        try {
            isSaved = supplierBO.saveSupplier(new SupplierDTO(id,name,company,contactNo));
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier saved !").show();
                getAll();
            }else {
                getAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void updateBtnOnAction(ActionEvent actionEvent) {
        System.out.println("clicked");
        String id = txtSupId.getText();
        String name = txtSupName.getText();
        String contactNo = txtSupContactNo.getText();
        String company = txtSupCompany.getText();

        try {
            boolean isUpdated = supplierBO.updateSupplier(new SupplierDTO(id,name,company,contactNo));
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier updated !").show();
                getAll();
            }else {
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
    public void searchOnAction(ActionEvent actionEvent) {
        String id = txtSupId.getText();
        try {
                SupplierDTO supplier = supplierBO.searchSupplier(id);
            if (supplier != null){
                txtSupId.setText(supplier.getSupplierId());
                txtSupName.setText(supplier.getName());
                txtSupContactNo.setText(supplier.getContactNo());
                txtSupCompany.setText(supplier.getCompany());
            }else {
                new Alert(Alert.AlertType.ERROR,"Supplier not found !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING,"SQLException !");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void txtSupCompanyOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.ADDRESS_PATTERN).matcher(txtSupCompany.getText()).matches()){
//            txtSupCompany.setFocusColor(Color.BLUE);
//        }else {
//            txtSupCompany.setFocusColor(Color.RED);
//        }
    }

    public void txtSupNameOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.NAME_PATTERN).matcher(txtSupName.getText()).matches()){
//            txtSupName.setFocusColor(Color.BLUE);
//        }else {
//            txtSupName.setFocusColor(Color.RED);
//        }
    }

    public void txtSupIdOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.SUPPLIER_ID_PATTERN).matcher(txtSupId.getText()).matches()){
//            txtSupId.setFocusColor(Color.BLUE);
//        }else {
//            txtSupId.setFocusColor(Color.RED);
//        }
    }

    public void txtSupContactNoOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.CONTACT_PATTERN).matcher(txtSupContactNo.getText()).matches()){
//            txtSupContactNo.setFocusColor(Color.BLUE);
//        }else {
//            txtSupContactNo.setFocusColor(Color.RED);
//        }
    }

    public void reportBtnOnAction(ActionEvent actionEvent) throws JRException, SQLException {
//        JasperDesign load = JRXmlLoader.load(new File("D:\\Final Project (2)\\Final Project (2)\\Final Project\\Final Project\\src\\main\\resources\\report\\Supplier.jrxml"));
//        JRDesignQuery newQuery = new JRDesignQuery();
//        String sql = "select * from supplier";
//        newQuery.setText(sql);
//        load.setQuery(newQuery);
//        JasperReport js = JasperCompileManager.compileReport(load);
//        HashMap<String,Object> hm = new HashMap<>();
//        JasperPrint jp = JasperFillManager.fillReport(js,hm, DBConnection.getInstance().getConnection());
//        JasperViewer viewer = new JasperViewer(jp, false);
//        viewer.show();
    }
}
