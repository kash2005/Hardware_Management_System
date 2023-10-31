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
import lk.ijse.project.bo.custom.CustomerBO;
import lk.ijse.project.bo.custom.impl.CustomerBOImpl;
import lk.ijse.project.dao.DAOFactory;
import lk.ijse.project.dao.custom.CustomerDAO;
import lk.ijse.project.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.project.db.DBConnection;
import lk.ijse.project.dto.CustomerDTO;
import lk.ijse.project.dto.tm.CustomerTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
    @FXML
    public TableView tblcustomer;
    @FXML
    public TableColumn colCustomerId;
    @FXML
    public TableColumn colCustomerName;
    @FXML
    public TableColumn colCustomerAddress;
    @FXML
    public TableColumn colCustomercontact;
    @FXML
    public JFXTextField txtcustid;
    @FXML
    public JFXTextField txtcustomerAddress;
    @FXML
    public JFXTextField txtcustomerContact;
    @FXML
    public JFXTextField txtcustname;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValueFactory();
        getAll();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtcustid.clear();
        txtcustname.clear();
        txtcustomerAddress.clear();
        txtcustomerContact.clear();

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        String id = txtcustid.getText();
        String name = txtcustname.getText();
        String address = txtcustomerAddress.getText();
        String contact = txtcustomerContact.getText();
        String date = String.valueOf(LocalDate.now());
        System.out.println(date);
        try {
            boolean isSaved = customerBO.saveCustomer(new CustomerDTO(id,name,address,contact,date));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer saved").show();
                getAll();
            }else {
                getAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQLException").show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void btnDeleteOnAction(ActionEvent actionEvent) {
            String id = txtcustid.getText();

            try {
                boolean isDeleted = customerBO.deleteCustomer(id);
                if(isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted !").show();
                    getAll();
                }else {
                    getAll();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "something happened !").show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    @FXML
    public void bthnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtcustid.getText();
        String name = txtcustname.getText();
        String address = txtcustomerAddress.getText();
        String contact = txtcustomerContact.getText();
        String date = String.valueOf(LocalDate.now());
        try {
            boolean isUpdated = customerBO.updateCustomer(new CustomerDTO(id,name,address,contact,date));
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer updated").show();
                getAll();
            }else {
                //new Alert(Alert.AlertType.ERROR, "Customer not updated").show();
                getAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQLException");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }     

    @FXML
    public void searchOnAction(ActionEvent actionEvent) {
        String id = txtcustid.getText();

        try {
            CustomerDTO customer = customerBO.searchCustomerId(id);
            if (customer != null){
                txtcustid.setText(customer.getCustId());
                txtcustname.setText(customer.getName());
                txtcustomerAddress.setText(customer.getAddress());
                txtcustomerContact.setText(customer.getContactNo());

            }else {
                new Alert(Alert.AlertType.ERROR, "Customer not found").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "SQLException").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void getAll() {
        ObservableList<CustomerTM> oblist = FXCollections.observableArrayList();
        try {
            List<CustomerDTO> custList = customerBO.getAllCustomer();
            for (CustomerDTO customer:custList) {
                oblist.add(new CustomerTM(
                        customer.getCustId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getContactNo()
                ));
            }
            tblcustomer.setItems(oblist);

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void setValueFactory() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colCustomercontact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
    }

    public void txtcustidOnKeyRelease(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.CUSTOMER_ID_PATTERN).matcher(txtcustid.getText()).matches()){
//            txtcustid.setFocusColor(Color.BLUE);
//        }else {
//            txtcustid.setFocusColor(Color.RED);
//        }
    }

    public void txtcustomerAddressOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.ADDRESS_PATTERN).matcher(txtcustomerAddress.getText()).matches()){
//            txtcustomerAddress.setFocusColor(Color.BLUE);
//        }else {
//            txtcustomerAddress.setFocusColor(Color.RED);
//        }
    }

    public void txtcustomerContactOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.CONTACT_PATTERN).matcher(txtcustomerContact.getText()).matches()){
//            txtcustomerContact.setFocusColor(Color.BLUE);
//        }else {
//            txtcustomerContact.setFocusColor(Color.RED);
//        }
    }

    public void txtcustnameOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.NAME_PATTERN).matcher(txtcustname.getText()).matches()){
//            txtcustname.setFocusColor(Color.BLUE);
//        }else {
//            txtcustname.setFocusColor(Color.RED);
//        }
    }

    public void btnReportOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign load = JRXmlLoader.load(new File("D:\\Final Project (2)\\Final Project (2)\\Final Project\\Final Project\\src\\main\\resources\\report\\Customer1.jrxml"));
        JRDesignQuery newQuery = new JRDesignQuery();
        String sql = "select * from customer";
        newQuery.setText(sql);
        load.setQuery(newQuery);
        JasperReport js = JasperCompileManager.compileReport(load);
        HashMap<String,Object> hm = new HashMap<>();
        JasperPrint jp = JasperFillManager.fillReport(js,hm, DBConnection.getDbConnection().getConnection());
        JasperViewer viewer = new JasperViewer(jp, false);
        viewer.show();
    }
}
