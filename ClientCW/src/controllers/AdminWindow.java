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
    private Button workWithReviews;

    @FXML
    private Button workWitchOrder;

    @FXML
    private Button workWitchUsers;

    @FXML
    private Button workWithBooks;
    @FXML
    private Button workWithRequests;

    @FXML
    private Button WorkWithReviews;


    @FXML
    void enterWorkWithRequests(){

    }
    @FXML
    void enterBack(ActionEvent event) {
        backBttn.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Вход",SceneName.SIGNINWINDOW,false);
        });

    }

    @FXML
    void enterWorkWitchAdmins(ActionEvent event) {
//        backBttn.setOnAction(actionEvent -> {
//            SceneChanger.changeScene("Вход",SceneName.BOOKMANAGERWINDOW,false);
//        });

    }



    @FXML
    void enterWorkWithReviews(ActionEvent event) {

    }

    @FXML
    void enterworkWitchOrder(ActionEvent event) {
        workWitchOrder.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Управление заказами",SceneName.ORDERMANAGERWINDOW,false);

        });
    }

    @FXML
    void enterworkWithBooks(ActionEvent event) {
        workWithBooks.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Вход",SceneName.BOOKMANAGERWINDOW,false);
        });
    }

    @FXML
    void enterWorkWitchUsers(){}


}
