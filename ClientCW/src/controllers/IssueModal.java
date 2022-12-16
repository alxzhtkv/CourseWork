package controllers;

import client.Connect;
import library.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class IssueModal {

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
    private Label resderID;

    @FXML
    private Button send;

    @FXML
    void clickOk(ActionEvent event) {

    }

    @FXML
    void clickSend(ActionEvent event) {
        send.setOnAction(actionEvent -> {
            Connect.client.sendMessage("issueOrder");
            String order = orderID.getText();
            Connect.client.sendObject(order);

        });

    }
}