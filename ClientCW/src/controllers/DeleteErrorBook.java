package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeleteErrorBook {

    @FXML
    private Button Ok;

    @FXML
    void clickOk(ActionEvent event) {
        Ok.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Удаление",SceneName.DELETEBOOKMODAL,true);

        });
    }

}
