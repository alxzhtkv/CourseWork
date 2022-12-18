package controllers;

import client.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class DeleteOrderModal {

    @FXML
    private TextField IdField;

    @FXML
    private Button delete;

    @FXML
    void clickDelete(ActionEvent event) {
        delete.setOnAction(actionEvent -> {
            Connect.client.sendMessage("deleteOrder");
            String id=IdField.getText();
            Connect.client.sendMessage(id);
            String mess=null;
            try {
                mess=(String) Connect.client.readMessage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            switch (mess) {
                case "deleted": {
                    SceneChanger.changeScene("Удаление завершено",SceneName.DELETEOKBOOK,true);

                    break;
                }
                case "error": {
                    SceneChanger.changeScene("Ошибка",SceneName.DELETEERRORBOOK,true);

                    break;
                }
            }


        });
    }

}
