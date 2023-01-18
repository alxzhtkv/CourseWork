package controllers;

import client.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import library.Book;

public class EditBookWindow {

    @FXML
    private TextField statusField;
    @FXML
    private TextField IDField;

    @FXML
    private Button addBook;

    @FXML
    private TextField authorField;

    @FXML
    private Button back;

    @FXML
    private ChoiceBox<String> genreField;

    @FXML
    private Label idL;

    @FXML
    private TextField publisherField;

    @FXML
    private Button search;

    @FXML
    private TextField titleField;

    @FXML
    private TextField yearField;

    @FXML
    void clickAddBook(ActionEvent event) {
        addBook.setOnAction(actionEvent -> {
            Connect.client.sendMessage("editBook");
            String id=IDField.getText();
            String title = titleField.getText();
            String author = authorField.getText();
            String publisher = publisherField.getText();
            String genre=genreField.getValue().toString();
            String year= yearField.getText();
            String status = statusField.getText();
            Book book = new Book(id,title,publisher,genre,year,status,author);
            Connect.client.sendObject(book);
            SceneChanger.changeScene("Редактирование завершено!",SceneName.DELETEOKBOOK,true);







        });

    }

    @FXML
    void clickBack(ActionEvent event) {
        back.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Управление книгами",SceneName.BOOKMANAGERWINDOW,false);
        });

    }

    @FXML
    void clickSearch(ActionEvent event) {
        search.setOnAction(actionEvent -> {
            Connect.client.sendMessage("getBookByID");
            String bookID = IDField.getText();
            Connect.client.sendMessage(bookID);
            Book book = (Book) Connect.client.readObject();

            titleField.setText(book.getTitle());
            authorField.setText(book.getAuthor());
            publisherField.setText(book.getPublisher());
            genreField.setValue(book.getGenre());
            yearField.setText(book.getYear());
            statusField.setText(book.getCount());
            if(!book.getTitle().equals("не определено")){
                statusField.setEditable(false);
                IDField.setEditable(false);
            }


        });

    }

}
