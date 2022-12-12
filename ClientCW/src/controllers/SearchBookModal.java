package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SearchBookModal {

    @FXML
    private Button back;

    @FXML
    void clickBack(ActionEvent event) {
        back.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Ошибка", SceneName.SEARHBOOKWINDOW, false);
        });

    }

}
