package lk.ijse.project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import lk.ijse.project.bo.BOFactory;
import lk.ijse.project.bo.custom.ItemBO;
import lk.ijse.project.bo.custom.LoadItemBO;
import lk.ijse.project.dto.DetailDTO;
import lk.ijse.project.dto.ItemDTO;
import lk.ijse.project.dto.tm.LoadTM;
import lk.ijse.project.entity.Invoice;
import lk.ijse.project.entity.SupplyOrder;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


public class ItemLoadController {
    public JFXTextField txtItemId;
    public JFXTextField txtItemName;
    public JFXTextField txtTotal;
    public JFXTextField txtLoadId;
    public TableView tblload;
    public TableColumn colItemid;
    public TableColumn colQty;
    public TableColumn colPrice;
    public JFXComboBox cmbStatus;
    public JFXTextField txtQty;
    public JFXTextField txtInvoice;
    public JFXTextField txtSupplier;
    public JFXTextField txtPrice;
    public Label lbldate;
    @FXML
    public TableColumn colAction;
    public JFXButton btnAdd;
    public JFXButton btnLoadItem;
    public Pane txtItemCode;
    ItemDTO item;
    private SupplyOrder supplier_oder;
    private Invoice invoice;

    private ObservableList<LoadTM> obList = FXCollections.observableArrayList();

    LoadItemBO loadItemBO = (LoadItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOAD);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    public void initialize(){
        setCellValueFactory();
        loadId();
        String[] type = {"Cash","Invoice"};
        ObservableList<String> list = FXCollections.observableArrayList(type);
        cmbStatus.setItems(list);
        lbldate.setText(new SimpleDateFormat("20yy-MM-dd").format(new Date()));
    }

    private void loadId() {
        try {
            String id = loadItemBO.generateNextLoadId();
            txtLoadId.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colItemid.setCellValueFactory(new PropertyValueFactory<LoadTM,String >("Loadid"));
        colQty.setCellValueFactory(new PropertyValueFactory<LoadTM,Integer >("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<LoadTM,Double >("price"));
        colAction.setCellValueFactory(new PropertyValueFactory<LoadTM,Button>("remove"));

    }

    public void searchItemOnAction(ActionEvent actionEvent) {
        String itemid = txtItemId.getText();
        try {
            item = itemBO.searchItem(itemid);
            txtItemName.setText(item.getName());
            //txtPrice.setText(String.valueOf(item.getItem_get_price()));
            txtQty.requestFocus();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void LoadItemOnAction(ActionEvent actionEvent) {
        String status = cmbStatus.getSelectionModel().getSelectedItem().toString();
        System.out.println(status);
        if(status.equals(null)){
            new Alert(Alert.AlertType.WARNING, "Please select the status!").show();
        }
        String itemCode = txtItemId.getText();
        int qty = Integer.parseInt(txtQty.getText());
        String so_id = txtLoadId.getText();
        double price = Double.parseDouble(txtPrice.getText());
        String date = lbldate.getText();
        String supid = txtSupplier.getText();
        double total = Double.parseDouble(txtTotal.getText());
        ArrayList<DetailDTO> details = new ArrayList<>();
        /* load all cart items' to orderDetails arrayList */
        for (int i = 0; i < tblload.getItems().size(); i++) {
            /* get each row details to (PlaceOrderTm)tm in each time and add them to the orderDetails */
            LoadTM detailTM = obList.get(i);
            details.add(new DetailDTO(so_id,detailTM.getLoadId(),detailTM.getQty(),detailTM.getPrice()));
        }
        supplier_oder = new SupplyOrder(so_id,supid,date,total,status,details);
        invoice = new Invoice(txtInvoice.getText(),date,supid,total);
        DetailDTO detailDTO = new DetailDTO(so_id,itemCode,qty,price);
        System.out.println(supplier_oder);
        System.out.println(invoice);

        try {
            boolean placeLoad1 = loadItemBO.PlaceLoad(supplier_oder, invoice,detailDTO);
            if (placeLoad1){
                new Alert(Alert.AlertType.CONFIRMATION,"Load is Added!", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR,"CAnnot Added!",ButtonType.CLOSE).show();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AddToLoadOnAction(ActionEvent actionEvent) {
//        String id = cmbitemid.getSelectionModel().getSelectedItem().toString();
        String id = txtItemId.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtPrice.getText());
        Button remove = new Button("Delete");
        remove.setCursor(Cursor.HAND);
        setRemoveBtnAction(remove);
        clear();

        ObservableList items = tblload.getItems();
        if (!obList.isEmpty()) {
            L1:
            /* check same item has been in table. If so, update that row instead of adding new row to the table */
            for (int i = 0; i < items.size(); i++) {
                if (colItemid.getCellData(i).equals(id)) {
                    qty += (int) colQty.getCellData(i);
                    price = item.getGetPrice() * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setPrice(price);
                    tblload.refresh();
                    return;
                }
            }
        }
        obList.add(new LoadTM(id,qty,price,remove));
        tblload.setItems(obList);
        calculateNetTotal();
    }

    private void setRemoveBtnAction(Button remove) {
        remove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {

                int index = tblload.getSelectionModel().getSelectedIndex();

                obList.remove(index);

                tblload.refresh();
                calculateNetTotal();
            }
        });
    }

    private void calculateNetTotal() {
        double total = 0.0;
        for (LoadTM de :obList){
            total+=de.getPrice();
        }
        txtTotal.setText(String.valueOf(total));
    }

    private void clear() {
        txtItemId.clear();
        txtItemName.clear();
        txtPrice.clear();
        txtQty.clear();
    }

    public void txtQtyOnAction(ActionEvent actionEvent) {
        int qty = Integer.parseInt(txtQty.getText());
        double price = item.getGetPrice()*qty;

        txtPrice.setText(String.valueOf(price));
    }

    public void txtItemCodeOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.ITEM_ID_PATTERN).matcher(txtItemId.getText()).matches()){
//            txtItemId.setFocusColor(Color.BLUE);
//        }else {
//            txtItemId.setFocusColor(Color.RED);
//        }
    }

    public void txtQtyOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.INT_PATTERN).matcher(txtQty.getText()).matches()){
//            txtQty.setFocusColor(Color.BLUE);
//        }else {
//            txtQty.setFocusColor(Color.RED);
//        }
    }

    public void txtSupplieronKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.SUPPLIER_ID_PATTERN).matcher(txtSupplier.getText()).matches()){
//            txtSupplier.setFocusColor(Color.BLUE);
//        }else {
//            txtSupplier.setFocusColor(Color.RED);
//        }
    }
}
