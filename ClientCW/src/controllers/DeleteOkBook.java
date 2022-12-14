package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeleteOkBook {

    @FXML
    private Button ok;

    @FXML
    void clckOk(ActionEvent event) {
        ok.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Работа с книгами",SceneName.BOOKMANAGERWINDOW,true);

        });

    }

}
