package lk.ijse.project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.project.bo.BOFactory;
import lk.ijse.project.bo.custom.UserBO;
import lk.ijse.project.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginPageFormController implements Initializable {
    @FXML
    public JFXTextField txtUsername;
    @FXML
    public JFXPasswordField psPassword;
    @FXML
    public JFXTextField txtid;
    @FXML
    public ImageView imgHide;
    @FXML
    public ImageView imgShow;
    @FXML
    public JFXButton loginBtn;
    @FXML
    private AnchorPane root;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    public void loginBtnOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        if (txtUsername.getText()!=null && (txtid.getText()!=null || psPassword.getText()!=null )) {
            if (userBO.checkPaswordAndUserName(new UserDTO(txtUsername.getText(),txtid.getText(),psPassword.getText()))) {
                AnchorPane load = FXMLLoader.load(getClass().getResource("/view/dashBoardPageForm.fxml"));
                Scene scene = new Scene(load);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("DashBoard Page");
                stage.centerOnScreen();
            }else {
                new Alert(Alert.AlertType.ERROR, "Wrong Username and Password").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Please enter User name and Password").show();
        }
    }

    public void HidePAsssowrdOnMouseClicked(MouseEvent mouseEvent) {
        psPassword.setText(txtid.getText());
        txtid.setVisible(false);
        imgHide.setVisible(false);
        psPassword.setVisible(true);
        imgShow.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtid.setVisible(false);
        imgHide.setVisible(false);
        imgShow.setVisible(true);
        txtUsername.requestFocus();
    }

    public void ShowPasswordOnMouseClicked(MouseEvent mouseEvent) {
        txtid.setText(psPassword.getText());
        txtid.setVisible(true);
        imgHide.setVisible(true);
        psPassword.setVisible(false);
        imgShow.setVisible(false);
    }

    public void txtUserNameOnAction(ActionEvent actionEvent) {
        psPassword.requestFocus();
    }

    public void psPasswordOnAction(ActionEvent actionEvent) {
        loginBtn.fire();
    }
}
