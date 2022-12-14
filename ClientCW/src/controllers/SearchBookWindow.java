
package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.*;

import client.Connect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Vector;

public class SearchBookWindow {

    @FXML
    private Button back;

    @FXML
    private Button searchBttn;

    @FXML
    private TextField searchField;

    @FXML
    private AnchorPane viewTableBooks;

//    @FXML
//    void clickBack(ActionEvent event) {
//        back.setOnAction(actionEvent -> {
//            SceneChanger.changeScene("Работа с книгами", SceneName.BOOKMANAGERWINDOW, false);
//        });
//
//    }

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
                for(int i=0;i<count;i++){
//                Book book = new Book();
                    Book book = getBookFromDatabase();
                    booksVector.add(book);
//                book = (Book) Connect.client.readObject();
                }


                ObservableList<Book> bk= FXCollections.observableArrayList(booksVector);
                TableView<Book> table = new TableView<>(bk);
                table.setPrefWidth(570);
                table.setPrefHeight(300);
                table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

                TableColumn<Book,String> idColumn = new TableColumn<>("ID");
                idColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("ID"));
                table.getColumns().add(idColumn);

                TableColumn<Book,String> titleColumn = new TableColumn<>("Название");
                titleColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
                table.getColumns().add(titleColumn);

                TableColumn<Book,String> authorColumn = new TableColumn<>("Автор");
                authorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
                table.getColumns().add(authorColumn);

                TableColumn<Book,String> publisherColumn = new TableColumn<>("Издательство");
                publisherColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("publisher"));
                table.getColumns().add(publisherColumn);

                TableColumn<Book,String> genreColumn = new TableColumn<>("Жанр");
                genreColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("genre"));
                table.getColumns().add(genreColumn);

                TableColumn<Book,String> yearColumn = new TableColumn<>("Год издания");
                yearColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("year"));
                table.getColumns().add(yearColumn);

                TableColumn<Book,String> countColumn = new TableColumn<>("Количество");
                countColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("count"));
                table.getColumns().add(countColumn);


                AnchorPane.setLeftAnchor(table,0.0);
                AnchorPane.setBottomAnchor(table,0.0);
                AnchorPane.setRightAnchor(table,0.0);
                AnchorPane.setTopAnchor(table,0.0);
                viewTableBooks.getChildren().add(table);
            }
            else {
                SceneChanger.changeScene("Ошибка", SceneName.SEARHBOOKMODAL, true);
                searchField.setText("");
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



//package controllers;
//
//import library.*;
//
//import client.Connect;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//
//import java.io.IOException;
//import java.util.Vector;
//
//public class SearchBookWindow {
//
//    @FXML
//    private Button searchBttn;
//
//    @FXML
//    private TextField searchField;
//
//    @FXML
//    void search(ActionEvent event) {
//        searchBttn.setOnAction(actionEvent -> {
//            Connect.client.sendMessage("searchBook");
//            String searchParm=searchField.getText();
//            Connect.client.sendMessage(searchParm);
//            String n;
//            try {
//                n= (String) Connect.client.readMessage();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            int size = Integer.valueOf(n);
//            int count = size;
//            Vector<Book> booksVector = new Vector<>();
//            if(size!=0 ){
//
//            }
//
//
////            for(int i=0;i<count;i++){
//////                Book book = new Book();
////                Book book = getBookFromDatabase();
////                booksVector.add(book);
//////                book = (Book) Connect.client.readObject();
////
////
////            }
//
//
//
//        });
//
//    }
//
//
//    public Book getBookFromDatabase(){
//        String id,title,publisher,genre,year,count,author;
//
//        id = (String) Connect.client.readObject();
//
//        title= (String) Connect.client.readObject();
//        publisher = (String)Connect.client.readObject();
//        genre = (String) Connect.client.readObject();
//        year = (String) Connect.client.readObject();
//        count = (String) Connect.client.readObject();
//        author = (String) Connect.client.readObject();
//
//
//
////
//        Book book=new Book(id,title,publisher,genre,year,count,author);
//        return book;
//
//    }
//}


