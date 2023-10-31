package lk.ijse.project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lk.ijse.project.bo.BOFactory;
import lk.ijse.project.bo.custom.CustomerBO;
import lk.ijse.project.bo.custom.ItemBO;
import lk.ijse.project.bo.custom.OrderBO;
import lk.ijse.project.dto.*;
import lk.ijse.project.dto.tm.PlaceOrderTM;
import lk.ijse.project.entity.Customer;
import lk.ijse.project.entity.Orders;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderFormController {

    public Pane txtid;
    public JFXTextField txtorderdate;
    public JFXTextField txtCustomername;
    public JFXTextField txtCustomerID;
    public TableColumn colid;
    public TableColumn coldesc;
    public TableColumn colunitprice;
    public TableColumn colqty;
    public TableColumn coltotal;
    public TableColumn coldelete;
    public JFXButton btnAddcart;
    public JFXTextField txtItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtQty;
    public JFXTextField txtTotal;
    public JFXRadioButton rbYes;
    public JFXRadioButton rbNo;
    public TableView tblOrder;
    public JFXTextField txtorderid;
    private ObservableList<PlaceOrderTM> obList = FXCollections.observableArrayList();
    public static List<OrderDetails> orderDetailsList = new ArrayList<>();
    public static CustomerDTO customer=null;
    public static Orders order;
    public static PlaceOrderDTO placeOrder;

    public static ArrayList<CartDetailDTO> cartDetails;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize() throws SQLException, ClassNotFoundException {
        txtorderdate.setText(new SimpleDateFormat("20yy-MM-dd").format(new Date()));
        setCellValueFactory();
        loadOrderId();
    }

    private void loadOrderId() {
        try {
            String nextOrderId = orderBO.generateNextOrderId();
            txtorderid.setText(nextOrderId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("code"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colunitprice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        coldelete.setCellValueFactory(new PropertyValueFactory<>("btndelete"));
    }

    public void delivaryFormBtnOnAction(ActionEvent actionEvent) throws IOException {
        String type = null;
        if (rbYes.isSelected()){
            type = "YES";
        }if (rbNo.isSelected()){
            type = "NO";
        }
        if(type == null){
            new Alert(Alert.AlertType.WARNING, "Please select the delivery status!").show();
        }
        String oid = txtorderid.getText();
        String date = txtorderdate.getText();
        String time =String.valueOf(LocalTime.now());
        String id = txtCustomerID.getText();
        double price = Double.parseDouble(txtUnitPrice.getText());

        cartDetails = new ArrayList<>();
        /* load all cart items' to orderDetails arrayList */
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            /* get each row details to (PlaceOrderTm)tm in each time and add them to the orderDetails */
            PlaceOrderTM tm = obList.get(i);
            cartDetails.add(new CartDetailDTO(tm.getCode(), tm.getDescription(),tm.getUnitPrice(),tm.getQty(),tm.getTotal()));
            orderDetailsList.add(new OrderDetails(tm.getCode(), txtorderid.getText(), tm.getQty()));

        }
        order = new Orders(oid,id, LocalDate.now(),price , type);
        placeOrder();
    }

    private void placeOrder() {
        Parent load = null;
        try {
            load = FXMLLoader.load(getClass().getResource("/view/delivaryForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Delivery Manage Form");
        stage.centerOnScreen();
     stage.show();

    }

    public void CustomerNameOnAction(ActionEvent actionEvent) {
        String customerId = txtCustomerID.getText();
        try {
            customer = customerBO.searchCustomerId(customerId);
            txtCustomername.setText(customer.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void AddTocartOnAction(ActionEvent actionEvent) {
        int q1 = Integer.parseInt(txtQtyOnHand.getText());
        int q2 = Integer.parseInt(txtQty.getText());
        if (q1 >= q2){
            addCart();
        }else {
            new Alert(Alert.AlertType.WARNING, "there are no enough items to issue in the stock!", ButtonType.OK).show();
        }
    }

    private void addCart() {
        String code = txtItemCode.getText();
        int qty = Integer.parseInt(txtQty.getText());
        String desc = txtItemName.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double total = unitPrice * qty;
        Button btnDelete = new Button("Delete");



        if (!obList.isEmpty()) {
            L1:
            /* check same item has been in table. If so, update that row instead of adding new row to the table */
            for (int i = 0; i < tblOrder.getItems().size(); i++) {
                if (colid.getCellData(i).equals(code)) {
                    qty += (int) colqty.getCellData(i);
                    total = unitPrice * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);
                    tblOrder.refresh();
                    return;
                }
            }
            txtQty.setText("");
        }
        /* set delete button to some action before it put on obList */
        btnDelete.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {

                int index = tblOrder.getSelectionModel().getSelectedIndex();

                obList.remove(index);

                tblOrder.refresh();
                calculateNetTotal();
             }
        });

    }

    private void calculateNetTotal() {
        double netTotal = 0.0;
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            double total = (double) coltotal.getCellData(i);
            netTotal += total;
        }
        txtTotal.setText(String.valueOf(netTotal));
    }

    public void ItemDetailOnAction(ActionEvent actionEvent) {
        String itemCode = txtItemCode.getText();
        try {
            ItemDTO item = itemBO.searchItem(itemCode);
            if(item==null){
                new Alert(Alert.AlertType.ERROR,"Item not found").show();
                return;
            }
            txtItemName.setText(item.getName());
            txtQtyOnHand.setText(String.valueOf(item.getQuantity()));
            txtUnitPrice.setText(String.valueOf(item.getSellPrice()));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void yesOnAction(ActionEvent actionEvent) {
        rbYes.setSelected(true);
        rbNo.setSelected(false);
    }

    public void noOnAction(ActionEvent actionEvent) {
        rbNo.setSelected(true);
        rbYes.setSelected(false);
    }

    public void txtCustomerIDOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.CUSTOMER_ID_PATTERN).matcher(txtCustomerID.getText()).matches()){
//            txtCustomerID.setFocusColor(Color.BLUE);
//        }else {
//            txtCustomerID.setFocusColor(Color.RED);
//        }
    }

    public void txtQtyOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.INT_PATTERN).matcher(txtQty.getText()).matches()){
//            txtQty.setFocusColor(Color.BLUE);
//        }else {
//            txtCustomerID.setFocusColor(Color.RED);
//        }
    }

    public void txtItemCodeOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.ITEM_ID_PATTERN).matcher(txtItemCode.getText()).matches()){
//            txtItemCode.setFocusColor(Color.BLUE);
//        }else {
//            txtItemCode.setFocusColor(Color.RED);
//        }
    }
}
