package lk.ijse.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        Application.launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        //Parent load = FXMLLoader.load(getClass().getResource("/view/loginPageForm.fxml"));
        Parent load = FXMLLoader.load(getClass().getResource("/view/dashBoardPageForm.fxml"));
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.centerOnScreen();
        stage.show();
    }
}
