package lk.ijse.project.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lk.ijse.project.bo.BOFactory;
import lk.ijse.project.bo.custom.ItemBO;
import lk.ijse.project.dto.ItemDTO;
import lk.ijse.project.dto.tm.ItemTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItemFormController {

    public JFXTextField txtName;
    public JFXTextField txtQty;
    public TableView tblItem;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colPrice;
    public TableColumn ColQty;
    public TableColumn ColType;
    public TableColumn colJob;
    public JFXTextField txtSellPrice;
    public JFXTextField txtCode;
    public JFXTextField txtType;
    public JFXTextField txtGetPrice;
    public JFXTextField txtSupplier;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValuefactory();
        getAll();
    }

    private void getAll() {
        ObservableList<ItemTM> observableList = FXCollections.observableArrayList();
        try {
            List<ItemDTO> itemList = itemBO.getAllItem();
            for (ItemDTO item:itemList) {
                observableList.add(new ItemTM(
                        item.getItemCode(),
                        item.getName(),
                        item.getItemType(),
                        item.getSellPrice(),
                        item.getQuantity()
                ));
            }
            tblItem.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValuefactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColType.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        ColQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    public void tblItemOnMouseClicked(MouseEvent mouseEvent) {
        ItemTM selectedItem = (ItemTM) tblItem.getSelectionModel().getSelectedItem();
        try {
            ItemDTO item = itemBO.searchItem(selectedItem.getItemCode());
            txtCode.setText(item.getItemCode());
            txtName.setText(item.getName());
            txtQty.setText(String.valueOf(item.getQuantity()));
            txtType.setText(item.getItemType());
            txtGetPrice.setText(String.valueOf(item.getGetPrice()));
            txtSellPrice.setText(String.valueOf(item.getSellPrice()));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String item_code = txtCode.getText();
        try {
            boolean isDeleted=itemBO.deleteItem(item_code);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Item deleted").show();
                getAll();
            }else {
                new Alert(Alert.AlertType.ERROR,"Item not deleted").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING,"SQLException").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtCode.clear();
        txtName.clear();
        txtType.clear();
        txtQty.clear();
        txtGetPrice.clear();
        txtSellPrice.clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String item_code = txtCode.getText();
        String item_name = txtName.getText();
        String item_type = txtType.getText();
        int item_qty = Integer.parseInt(txtQty.getText());
        double item_get_price = Double.parseDouble(txtGetPrice.getText());
        double item_sell_price = Double.parseDouble(txtSellPrice.getText());
        try {
            boolean isUpdated = itemBO.updateItem(new ItemDTO(item_code,item_name,item_type,item_sell_price,item_get_price,item_qty));
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Item updated").show();

                getAll();
            }else {
                new Alert(Alert.AlertType.ERROR,"Item not Updated").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING,"SQLException").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnsaveOnAction(ActionEvent actionEvent) {
        String item_code = txtCode.getText();
        String item_name = txtName.getText();
        String item_type = txtType.getText();
        int item_qty = Integer.parseInt(txtQty.getText());
        double item_get_price = Double.parseDouble(txtGetPrice.getText());
        double item_sell_price = Double.parseDouble(txtSellPrice.getText());


        try {
            boolean isSaved = itemBO.saveItem(new ItemDTO(item_code,item_name,item_type,item_sell_price,item_get_price,item_qty));
            if (!isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Item saved").show();
                getAll();
            }else {
                new Alert(Alert.AlertType.ERROR,"Item not saved").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING,"SQLException").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void txtCodeOnAction(ActionEvent actionEvent) {
        String item_code = txtCode.getText();
        try {
            ItemDTO item = itemBO.searchItem(item_code);
            if (item != null){
                txtCode.setText(item.getItemCode());
                txtName.setText(item.getName());
                txtType.setText(item.getItemType());
                txtSellPrice.setText(String.valueOf(item.getSellPrice()));
                txtGetPrice.setText(String.valueOf(item.getGetPrice()));
                txtQty.setText(String.valueOf(item.getQuantity()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void NewItemIdOnAction(ActionEvent actionEvent) {
        try {
            txtCode.setText(itemBO.generateNextItemId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void newLoadFrameOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/itemLoad.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setTitle("Item Load");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.NAME_PATTERN).matcher(txtName.getText()).matches()){
//            txtName.setFocusColor(Color.BLUE);
//        }else {
//            txtName.setFocusColor(Color.RED);
//        }
    }

    public void txtSellPriceOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.DOUBLE_PATTERN).matcher(txtSellPrice.getText()).matches()){
//            txtSellPrice.setFocusColor(Color.BLUE);
//        }else {
//            txtSellPrice.setFocusColor(Color.RED);
//        }
    }

    public void txtTypeOnLeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.NAME_PATTERN).matcher(txtType.getText()).matches()){
//            txtType.setFocusColor(Color.BLUE);
//        }else {
//            txtType.setFocusColor(Color.RED);
//        }
    }

    public void txtGetPriceOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.DOUBLE_PATTERN).matcher(txtGetPrice.getText()).matches()){
//            txtGetPrice.setFocusColor(Color.BLUE);
//        }else {
//            txtGetPrice.setFocusColor(Color.RED);
//        }
    }

    public void txtQtyOnKeyReleased(KeyEvent keyEvent) {
//        if (Regex.getInstance().getPattern(RegexPattern.INT_PATTERN).matcher(txtQty.getText()).matches()){
//            txtQty.setFocusColor(Color.BLUE);
//        }else {
//            txtQty.setFocusColor(Color.RED);
//        }
    }
}
