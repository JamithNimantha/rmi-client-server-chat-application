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
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    @FXML
    private TextArea txtChatArea;

    @FXML
    private TextField txtMsg;

    @FXML
    private JFXButton btnEnter;

    @FXML
    private TextArea txtChatters;


    private PrintWriter out;

    private String localUserName;

    private String remoteUserName;


    @FXML
    void btnOnAction(ActionEvent event) {
        if (out!=null){
            txtChatArea.appendText(localUserName +" :"+txtMsg.getText()+"\n");
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
        new Thread(()->{
            setUpServer();
        }).start();


    }
    private void setUpServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(6060);
            System.out.println("Server has been started..........");

            while (true){
                System.out.println("server is listening at "+serverSocket.getLocalPort());
                Socket accept = serverSocket.accept();
                System.out.println("In comming Connection..........");
                out = new PrintWriter(accept.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(accept.getInputStream()));


                new Thread(() ->{
                    try {
                        remoteUserName=in.readLine();
                        out.write(localUserName);
                        out.println("\n");
                        out.flush();
                        txtChatArea.setText("ChatBot :"+remoteUserName+" IS JOINED !!! \n");
                        txtChatters.setText(remoteUserName+"\n");
                        readMessages(in);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readMessages(BufferedReader in) throws IOException {
        String line = null;

        while ((line = in.readLine()) != null) {

            txtChatArea.appendText(remoteUserName+" : " + line + "\n");
        }

        in.close();
    }

    public void setName(String name){
        this.localUserName =name;
    }
    public void remove(){
        if (out!=null){
            out.close();
        }
    }

}
