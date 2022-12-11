package controllers;

import client.Connect;
import library.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Random;

public class AddBookWindow {

    @FXML
    private Button addBook;

    @FXML
    private TextField authorField;

    @FXML
    private Button back;

    @FXML
    private TextField countField;

    @FXML
    private Button generateID;

    @FXML
    private ChoiceBox<?> genreField;

    @FXML
    private Label idL;

    @FXML
    private TextField publisherField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField yearField;

    @FXML
    void clickAddBook(ActionEvent event) {
        addBook.setOnAction(actionEvent -> {

            Connect.client.sendMessage("addingBook");
            String title=titleField.getText();
            String publisher=publisherField.getText();
            String genre=genreField.getValue().toString();
            String year=yearField.getText();
            String count=countField.getText();
            String author=authorField.getText();
            String ID = idL.getText();
            Book book=new Book(ID,title,publisher,genre,year,count,author);
//            Connect.client.sendObject(book);
            sendBook(book);

        });

    }

    @FXML
    void clickBack(ActionEvent event) {
        back.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Работа с книгами", SceneName.BOOKMANAGERWINDOW, false);
        });

    }

    @FXML
    void generate(ActionEvent event) {
        generateID.setOnAction(actionEvent -> {
            Random random = new Random();
            String loginID =String.valueOf(random.nextInt(100000)+1);
            idL.setText(loginID);
        });

    }

    public void sendBook(Book book){

            Connect.client.sendObject(book.getID());
            Connect.client.sendObject(book.getTitle());
            Connect.client.sendObject(book.getPublisher());
            Connect.client.sendObject(book.getGenre());
            Connect.client.sendObject(book.getYear());
            Connect.client.sendObject(book.getCount());
            Connect.client.sendObject(book.getAuthor());


    }
}