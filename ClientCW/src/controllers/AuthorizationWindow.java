package controllers;
import client.*;
import persons.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

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
            Connect.client.sendMessage("authorization");
            String login = loginTextField.getText();
            String password = passTextField.getText();
            System.out.println(login + " "+ password);
            User user = new User(login,password);
            Connect.client.sendObject(user);


            if(adminCheck.isSelected()){
//                SceneChanger.changeScene("Вход",SceneName.ADMINWINDOW,false);
                Connect.client.sendMessage("admin");
            }
            else {
//                SceneChanger.changeScene("Вход", SceneName.READERWINDOW, false);
                Connect.client.sendMessage("reader");
            }
            String answer;
            try {
                 answer = Connect.client.readMessage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            switch (answer) {
                case "approved": {
                    Connect.id=login;
                    SceneChanger.changeScene("Вход", SceneName.READERWINDOW, false);
                    break;
                }
                case "approvedAdmin": {
                    SceneChanger.changeScene("Вход", SceneName.ADMINWINDOW, false);
                    break;
                }
                case "refused": {
//                    error.setText("Неверный логин или пароль");
//                    error.setVisible(true);
                    break;
                }
            }



//            SceneChanger.changeScene("Вход",SceneName.SIGNINWINDOW,false);




        });

    }

    @FXML
    void enterback(ActionEvent event) {
        backBttn.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Библиотека", SceneName.STARTWINDOW, false);
        });

    }



}