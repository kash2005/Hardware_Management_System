package lk.ijse.project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.ijse.project.bo.BOFactory;
import lk.ijse.project.bo.custom.OrderBO;
import lk.ijse.project.bo.custom.PlaceOrderBO;
import lk.ijse.project.db.DBConnection;
import lk.ijse.project.dto.CustomerDTO;
import lk.ijse.project.dto.DeliveryDTO;
import lk.ijse.project.dto.PlaceOrderDTO;
import lk.ijse.project.entity.Customer;
import lk.ijse.project.entity.PlaceOrder;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;

public class DelivaryFormController {
    public JFXTextField txtOrderid;
    public JFXTextField txtDeliveryId;
    public JFXTextField txtVehicleId;
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomername;
    public JFXTextField txtDelivaryTotal;
    public JFXTextField txtDistance;
    public JFXTextField txtFullTotal;
    public JFXButton btnPlaceorder;
    public JFXButton btnBill;
    public PlaceOrderDTO placeOrder;

    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);
    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACE_ORDER);

    public void initialize() throws SQLException, ClassNotFoundException {
        generateNextDeliveryId();
        placeOrder = OrderFormController.placeOrder;
        CustomerDTO customer = OrderFormController.customer;
        txtOrderid.setText(placeOrder.getCode());
        txtCustomerId.setText(customer.getCustId());
        txtCustomername.setText(customer.getName());
        txtCustomerAddress.setText(customer.getAddress());



    }

    private void generateNextDeliveryId() {
        try {
            String nextId = orderBO.generNextDeliveryId();
            txtDeliveryId.setText(nextId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void PlaceorderOnAction(ActionEvent actionEvent) {
        String d_id = txtDeliveryId.getText();
        String c_id = txtCustomerId.getText();
        String o_id = txtOrderid.getText();
        String v_id = txtVehicleId.getText();
        int distance = Integer.parseInt(txtDistance.getText());
        double amount = Double.parseDouble(txtDelivaryTotal.getText());
        DeliveryDTO d1 = new DeliveryDTO(d_id,o_id,v_id,c_id,distance,amount);
        try {
            boolean placeOrder = placeOrderBO.placeOrder(this.placeOrder, d1);
            if (placeOrder){
                new Alert(Alert.AlertType.CONFIRMATION,"Order Placed", ButtonType.OK).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void generateBilOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign load = JRXmlLoader.load(new File("D:\\Final Project (2)\\Final Project (2)\\Final Project\\Final Project\\src\\main\\resources\\report\\OrderBill1.jrxml"));
        JRDesignQuery newQuery = new JRDesignQuery();
        String sql = "select i.itemCode as code, i.name as name, i.sell_price as price, od.orderId as orderId,od.quantity, i.sell_price*od.quantity as total from item i inner join orderdetails od on i.itemCode=od.itemId where orderId= '"+txtOrderid.getText()+"'";
        newQuery.setText(sql);
        load.setQuery(newQuery);
        JasperReport js = JasperCompileManager.compileReport(load);
        HashMap<String,Object> hm = new HashMap<>();
        JasperPrint jp = JasperFillManager.fillReport(js,hm, DBConnection.getDbConnection().getConnection());
        JasperViewer viewer = new JasperViewer(jp, false);
        viewer.show();
    }

    public void DelivaryTotalOnAction(ActionEvent actionEvent) {
        int disc = Integer.parseInt(txtDistance.getText());
        double amount = disc * 450.0;
        txtDelivaryTotal.setText(String.valueOf(amount));
        double price = placeOrder.getUnitPrice();
        double total = amount + price;
        txtFullTotal.setText(String.valueOf(total));

    }

}
