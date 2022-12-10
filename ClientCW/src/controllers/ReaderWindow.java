package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ReaderWindow {

    @FXML
    private Button backBttn;

    @FXML
    private Button edit;

    @FXML
    private Button hide;

    @FXML
    private Button show;

    @FXML
    void clickB(ActionEvent event) {
        backBttn.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Вход",SceneName.STARTWINDOW,false);
        });

    }

    @FXML
    void clickEdit(ActionEvent event) {

    }

    @FXML
    void clickHide(ActionEvent event) {

    }

    @FXML
    void clickShow(ActionEvent event) {

    }

}



//package controllers;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//
//public class ReaderWindow {
//
//    @FXML
//    private Button backBttn;
//
//    @FXML
//    void clickB(ActionEvent event) {
//        backBttn.setOnAction(actionEvent -> {
//            SceneChanger.changeScene("Вход",SceneName.STARTWINDOW,false);
//        });
//
//    }
//
//}
