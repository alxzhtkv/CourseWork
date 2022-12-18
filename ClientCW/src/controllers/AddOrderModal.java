package controllers;

import client.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import library.Order;

import java.io.IOException;
import java.util.Random;

public class AddOrderModal {
    @FXML
    private Label orderIDL;

    @FXML
    private TextField bookId;

    @FXML
    private Button check;

    @FXML
    private Button send;

    @FXML
    private Label statusL;

    @FXML
    private Label titleL;

    @FXML
    void clickCheck(ActionEvent event) {
        check.setOnAction(actionEvent -> {
            String id = bookId.getText();
            Connect.client.sendMessage("checkBook");
            Connect.client.sendMessage(id);
            String answer="Не определена;Не определенo";
            try {
                answer = (String) Connect.client.readMessage();
                String[] fio = answer.split(";");
                String title= fio[0];
                String status= fio[1];
                statusL.setText(status);
                titleL.setText(title);
                Random random = new Random();
                orderIDL.setText(String.valueOf(random.nextInt(100000)+1));


            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        });


    }

    @FXML
    void clickSend(ActionEvent event) {
        send.setOnAction(actionEvent -> {
            Connect.client.sendMessage("addOrder");
            String idBook = bookId.getText();
            String idReader=Connect.id;
            String status = statusL.getText();
            String bookTitle =titleL.getText();
            String orderID = orderIDL.getText();

            if(status.equals("Не определенo")){
                SceneChanger.changeScene("Ошибка",SceneName.DELETEERRORBOOK,true);
                Connect.client.sendMessage("notOk");
            }
            else {
                Order order = new Order(idReader,idBook,orderID,bookTitle);
//                Order order = new Order(idReader,idBook,bookTitle);
                Connect.client.sendMessage("ok");
                Connect.client.sendObject(order);
                SceneChanger.changeScene("Завершено",SceneName.DELETEOKBOOK,true);
                bookId.setText(null);
                statusL.setText(null);
                titleL.setText(null);
            }



        });

    }

}