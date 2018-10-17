package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtName;

    @FXML
    private JFXButton btnEnter;

    @FXML
    void btnEnterOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnEnter.getScene().getWindow();
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/Client.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Client Chat Application");
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

    }

}
