package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class BookManagerWindow {

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private Button search;

    @FXML
    private Button sort;

    @FXML
    void clickAdd(ActionEvent event) {

    }

    @FXML
    void clickBack(ActionEvent event) {
        back.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Вход",SceneName.STARTWINDOW,false);
        });

    }

    @FXML
    void clickDelete(ActionEvent event) {

    }

    @FXML
    void clickEdit(ActionEvent event) {

    }

    @FXML
    void clickSearch(ActionEvent event) {

    }

    @FXML
    void clickSort(ActionEvent event) {

    }

}