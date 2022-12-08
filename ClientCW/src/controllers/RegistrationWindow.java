package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.util.Random;

public class RegistrationWindow {

    @FXML
    private Button back;

    @FXML
    private TextField birthdayField;

    @FXML
    private Label loginIDL;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField passField;

    @FXML
    private TextField passportIDField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button signUp;

    @FXML
    void clickBack(ActionEvent event) {

    }

    @FXML
    void clicksignUp(ActionEvent event) {

    }


}






//package controllers;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//
//public class RegistrationWindow {
//
//    @FXML
//    private Button back;
//
//    @FXML
//    private TextField birthdayField;
//
//    @FXML
//    private TextField loginField;
//
//    @FXML
//    private TextField nameTextField;
//
//    @FXML
//    private TextField passField;
//
//    @FXML
//    private TextField passportIDField;
//
//    @FXML
//    private TextField phoneField;
//
//    @FXML
//    private Button signUp;
//
//    @FXML
//    void clickBack(ActionEvent event) {
//        back.setOnAction(actionEvent -> {
//            SceneChanger.changeScene("Library",SceneName.STARTWINDOW,false);
//        });
//    }
//
//    @FXML
//    void clicksignUp(ActionEvent event) {
//        signUp.setOnAction(actionEvent -> {
//            String fioname = nameTextField.getText();
//            String  birthDay=birthdayField.getText();
//            String  phone=phoneField.getText();
//            String  passportID=passportIDField.getText();
//            String   login= loginL.getText();
//            String password=passwordTextField.getText();
//            String  passwordCheck=passwordCheckText.getText();
//        });
//
//    }
//
//}