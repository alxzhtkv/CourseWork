package controllers;

import client.Connect;
import library.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class IssueModal {


    @FXML
    private Label bookIdL;

    @FXML
    private Label bookTitle;

    @FXML
    private DatePicker dateB;

    @FXML
    private DatePicker dateI;

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
            Connect.client.sendMessage("checkOrder");
            Connect.client.sendMessage(idOrder);
            String answ="Не определенo;Не определенo;Не определенo";
            try {
                answ = (String) Connect.client.readMessage();
                String[] arr = answ.split(";");
                String title= arr[0];
                String reader= arr[1];
                String book= arr[2];
                readerID.setText(reader);
                bookTitle.setText(title);
                bookIdL.setText(book);


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
                Connect.client.sendMessage("issueOrder");

                String order = orderID.getText();
                String book = bookTitle.getText();
                String bookID= bookIdL.getText();
                String Idate = dateI.getValue().toString();
                System.out.println(Idate);
                String Bdate = dateB.getValue().toString();

                IssuedOrder issuedOrder = new IssuedOrder(order,reader,bookID,book,Idate,Bdate);
                Connect.client.sendObject(issuedOrder);

            }

//            Connect.client.sendObject(order);

        });

    }
}