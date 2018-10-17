package controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ServerController {

    @FXML
    private TextArea txtChatArea;

    @FXML
    private TextField txtMsg;

    @FXML
    private JFXButton btnEnter;

    @FXML
    private Label lblChatNameBar;


    @FXML
    void btnOnAction(ActionEvent event) {

    }

    @FXML
    void txtChatAreaOnAction(ActionEvent event) {

    }
    public void initialize() {
        Platform.runLater(() -> txtMsg.requestFocus());

    }

}
