package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserManagerWindow {


    @FXML
    private Button showReaders;

    @FXML
    private Button deleteUsers;

    @FXML
    void clickDeleteUsers(ActionEvent event) {
        deleteUsers.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Удаление",SceneName.DELETEUSERMODAL,true);

        });

    }

    @FXML
    void clickShowReaders(ActionEvent event) {
        showReaders.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Просмотр данных читателей",SceneName.SHOWREADERSMODAL,true);

        });

    }


}