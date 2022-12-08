package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartWindow {

    @FXML
    private Button registration;

    @FXML
    private Button signIn;

    @FXML
    void enterReg() {
        registration.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Вход",SceneName.REGWINDOW,false);
        });

    }

    @FXML
    void enterSignIn() {
        signIn.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Вход",SceneName.SIGNINWINDOW,false);




        });

    }

}