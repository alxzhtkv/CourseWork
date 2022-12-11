package controllers;
import library.*;

import client.Client;
import client.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Vector;

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
    private Button showBooks;

    @FXML
    private Button sort;

    @FXML
    void clickAdd(ActionEvent event) {

    }

    @FXML
    void clickBack(ActionEvent event) {
        back.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Вход",SceneName.ADMINWINDOW,false);
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
    void clickShowBooks(ActionEvent event) {

        showBooks.setOnAction(actionEvent -> {
            Connect.client.sendMessage("showBooks");
            String size = null;
            try {
                size = Connect.client.readMessage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(size);
            int n = Integer.parseInt (size);
            int count = n;
            int j = 0;

            Vector<Book> booksVector = new Vector<>();

            for(int i=0;i<count;i++){
//                Book book = new Book();
                Book book = getBookFromDatabase();
                booksVector.add(book);
//                book = (Book) Connect.client.readObject();
                booksVector.add(book);


            }




        });
    }

    @FXML
    void clickSort(ActionEvent event) {

    }

    public Book getBookFromDatabase(){
        String id,title,publisher,genre,year,count,author;

            id = (String) Connect.client.readObject();

            title= (String) Connect.client.readObject();
            publisher = (String)Connect.client.readObject();
            genre = (String) Connect.client.readObject();
            year = (String) Connect.client.readObject();
            count = (String) Connect.client.readObject();
            author = (String) Connect.client.readObject();



//
        Book book=new Book(id,title,publisher,genre,year,count,author);
        return book;

    }
}
