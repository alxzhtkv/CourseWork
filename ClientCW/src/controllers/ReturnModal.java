package controllers;

import client.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import library.IssuedOrder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReturnModal {

    @FXML
    private Label bookIdL;

    @FXML
    private Label bookTitle;

    @FXML
    private Label dateB;

    @FXML
    private Label dateI;

    @FXML
    private Label dateR;

    @FXML
    private Button ok;

    @FXML
    private TextField orderID;

    @FXML
    private Label readerID;

    @FXML
    private Button send;

    @FXML
    void clickOk(ActionEvent event) {
        ok.setOnAction(actionEvent -> {
            String idOrder = orderID.getText();
            Connect.client.sendMessage("checkReturnOrder");
            Connect.client.sendMessage(idOrder);
            String answ="Не определенo;Не определенo;Не определенo;Не определенo;Не определенo";
            try {
                answ = (String) Connect.client.readMessage();
                String[] arr = answ.split(";");
                String title= arr[0];
                String reader= arr[1];
                String book= arr[2];
                String Idate = arr[3];
                String Bdate = arr[4];
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                String Rdate =formater.format(date).toString();

                readerID.setText(reader);
                bookTitle.setText(title);
                bookIdL.setText(book);
                dateI.setText(Idate);
                dateB.setText(Bdate);
                dateR.setText(Rdate);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    void clickSend(ActionEvent event) {

        send.setOnAction(actionEvent -> {



            String reader = readerID.getText();

            if(!reader.equals("Не определенo")) {
                Connect.client.sendMessage("returnOrder");

                String bookID= bookIdL.getText();
                String order = orderID.getText();

                Connect.client.sendMessage(bookID);
                Connect.client.sendMessage(order);
                String ms = (String) Connect.client.readObject();
                if(ms.equals("ok")){
                    SceneChanger.changeScene("Завершено",SceneName.DELETEOKBOOK,true);
                }else
                    SceneChanger.changeScene("Ошибка",SceneName.DELETEERRORBOOK,true);

            }
            else
                SceneChanger.changeScene("Ошибка",SceneName.DELETEERRORBOOK,true);

//            Connect.client.sendObject(order);

        });




    }

}