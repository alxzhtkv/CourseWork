package controllers;

import persons.*;
import client.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.util.Random;

public class RegistrationWindow {

    @FXML
    private Button IDgenerate;

    @FXML
    private Button back;

    @FXML
    private TextField birthdayField;

    @FXML
    private Label loginIDL;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField passportIDField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button signUp;

    @FXML
    void clickBack(ActionEvent event) {
        back.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Библиотека",SceneName.STARTWINDOW,false);
        });

    }

    @FXML
    void clicksignUp(ActionEvent event) {
        signUp.setOnAction(actionEvent -> {
            Connect.client.sendMessage("registration");
            String fioname = nameTextField.getText();
            String birthDay=birthdayField.getText();
            String phone=phoneField.getText();
            String passportID=passportIDField.getText();
            String login= loginIDL.getText();
            String password=passwordField.getText();
            String[] fio = fioname.split(" ");
            String name= fio[1];
            String surname= fio[0];
            String patronymic= fio[2];
            User user = new User(login,password);
            Reader reader = new Reader(login,password,name,surname,patronymic,passportID,phone,birthDay);
            Connect.client.sendObject(user);
            Connect.client.sendObject(reader);







        });

    }

    @FXML
    void generate(ActionEvent event) {
        IDgenerate.setOnAction(actionEvent -> {
            Random random = new Random();
            String loginID =String.valueOf(random.nextInt(100000)+1);
            loginIDL.setText(loginID);

        });

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