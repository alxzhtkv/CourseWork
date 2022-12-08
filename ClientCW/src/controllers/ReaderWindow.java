package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ReaderWindow {

    @FXML
    private Button backBttn;

    @FXML
    void clickB(ActionEvent event) {
        backBttn.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Вход",SceneName.STARTWINDOW,false);
        });

    }

}
