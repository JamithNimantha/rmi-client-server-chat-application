package controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController {

    @FXML
    private TextArea txtChatArea;

    @FXML
    private TextField txtMsg;

    @FXML
    private JFXButton btnEnter;

    private String localUserName;

    private PrintWriter out;

    private String remoteUserName;



    @FXML
    void btnOnAction(ActionEvent event) {
        if (out!=null){
            txtChatArea.appendText(localUserName +" : "+txtMsg.getText()+"\n");
            out.println(txtMsg.getText());
            out.flush();
            txtMsg.setText("");
        }

    }

    @FXML
    void txtChatAreaOnAction(ActionEvent event) {


    }
    public void initialize() {
        Platform.runLater(() -> txtMsg.requestFocus());
        try {
            Socket socket = new Socket("127.0.0.1", 6060);
            System.out.println("Connection has established.......");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out =new PrintWriter(socket.getOutputStream());


            new Thread(() -> {
                try {
                    out.write(localUserName);
                    out.println();
                    out.flush();
                    remoteUserName=in.readLine();
                    txtChatArea.setText("ChatBot : "+remoteUserName+" IS JOINED !!! \n");
                    readMessages(in);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void readMessages(BufferedReader in) throws IOException {
        String line = null;

        while ((line = in.readLine()) != null) {
            txtChatArea.appendText(remoteUserName+" : " + line + "\n");
        }
    }

    public void setName(String name){
        this.localUserName =name;
    }

}
