package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
        logIn();

    }

    @FXML
    void txtNameOnAction(ActionEvent event) throws IOException {
        logIn();

    }

    private void logIn() throws IOException{
        if (!txtName.getText().isEmpty()) {
            Stage stage = (Stage) btnEnter.getScene().getWindow();
//        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/Client.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/Client.fxml"));
            Parent parent =(Parent) fxmlLoader.load();
            ClientController clientController = (ClientController)fxmlLoader.getController();
            clientController.setName(txtName.getText());

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(txtName.getText()+" Chat Application");
            stage.centerOnScreen();
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Error", ButtonType.OK);
            alert.setHeaderText(null);
            alert.setContentText("Enter a Nick Name ");
            alert.show();
            txtName.requestFocus();
        }

    }

}
