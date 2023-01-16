package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserManagerWindow {

    @FXML
    private Button delete;

    @FXML
    private Button showReaders;

    @FXML
    private Button showUsers;

    @FXML
    void clickDelete(ActionEvent event) {
        delete.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Удаление",SceneName.DELETEUSERMODAL,true);

        });

    }

    @FXML
    void clickShowReaders(ActionEvent event) {
        showReaders.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Просмотр данных читателей",SceneName.DELETEUSERMODAL,true);

        });

    }

    @FXML
    void clickShowUsers(ActionEvent event) {
        showUsers.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Просмотр данных пользователей",SceneName.DELETEUSERMODAL,true);

        });

    }

}