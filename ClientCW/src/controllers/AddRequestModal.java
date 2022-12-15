package controllers;

import client.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import library.Request;

public class AddRequestModal {

    @FXML
    private TextField newBookTitle;

    @FXML
    private Button sendNewBookTitle;

    @FXML
    void clickSendNewBookTitle(ActionEvent event) {
        sendNewBookTitle.setOnAction(actionEvent -> {
            Connect.client.sendMessage("addRequest");
            String title =newBookTitle.getText();
            String idReader = Connect.id;
            Request request = new Request(idReader,title);
            Connect.client.sendObject(request);
            SceneChanger.changeScene("Завершено", SceneName.DELETEOKBOOK, true);


        });


    }

}
