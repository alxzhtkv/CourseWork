package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminWindow {

    @FXML
    private Button backBttn;

    @FXML
    private Button workWitchAdmins;

    @FXML
    private Button workWitchBooks;

    @FXML
    private Button workWitchOrder;

    @FXML
    private Button workWitchUsers;

    @FXML
    private Button workWithBooks;

    @FXML
    void enterBack(ActionEvent event) {
        backBttn.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Вход",SceneName.BOOKMANAGERWINDOW,false);
        });

    }

    @FXML
    void enterWorkWitchAdmins(ActionEvent event) {
        backBttn.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Вход",SceneName.BOOKMANAGERWINDOW,false);
        });

    }

    @FXML
    void enterWorkWitchBooks(ActionEvent event) {
        workWithBooks.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Вход",SceneName.BOOKMANAGERWINDOW,false);
        });

    }

    @FXML
    void enterWorkWitchUsers(ActionEvent event) {

    }

    @FXML
    void enterworkWitchOrder(ActionEvent event) {

    }

    @FXML
    void enterworkWithBooks(ActionEvent event) {

    }

}
