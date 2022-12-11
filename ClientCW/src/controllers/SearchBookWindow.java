package controllers;

import library.*;

import client.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Vector;

public class SearchBookWindow {

    @FXML
    private Button searchBttn;

    @FXML
    private TextField searchField;

    @FXML
    void search(ActionEvent event) {
        searchBttn.setOnAction(actionEvent -> {
            Connect.client.sendMessage("searchBook");
            String searchParm=searchField.getText();
            Connect.client.sendMessage(searchParm);
            String n;
            try {
                n= (String) Connect.client.readMessage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int size = Integer.valueOf(n);
            int count = size;
            Vector<Book> booksVector = new Vector<>();
            if(size!=0 ){

            }


//            for(int i=0;i<count;i++){
////                Book book = new Book();
//                Book book = getBookFromDatabase();
//                booksVector.add(book);
////                book = (Book) Connect.client.readObject();
//
//
//            }



        });

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


