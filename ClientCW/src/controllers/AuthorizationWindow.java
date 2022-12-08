package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthorizationWindow {

    @FXML
    private CheckBox adminCheck;

    @FXML
    private Button backBttn;
    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passTextField;

    @FXML
    private Button signInBttn;

    @FXML
    void enterSignIN(ActionEvent event) {
        signInBttn.setOnAction(actionEvent -> {
            String login = loginTextField.getText();
            String password = passTextField.getText();
            System.out.println(login + " "+ password);
            if(adminCheck.isSelected()){
                SceneChanger.changeScene("Вход",SceneName.ADMINWINDOW,false);
            }
            else
                SceneChanger.changeScene("Вход",SceneName.READERWINDOW,false);

//            SceneChanger.changeScene("Вход",SceneName.SIGNINWINDOW,false);




        });

    }

    @FXML
    void enterback(ActionEvent event) {

    }

}