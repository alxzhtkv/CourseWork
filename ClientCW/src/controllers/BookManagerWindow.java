package controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane viewTableBooks;

    @FXML
    private Button sort;

    @FXML
    void clickAdd(ActionEvent event) {
        add.setOnAction(actionEvent -> {

        });

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

            AnchorPane.setLeftAnchor(table,0.0);
            AnchorPane.setBottomAnchor(table,0.0);
            AnchorPane.setRightAnchor(table,0.0);
            AnchorPane.setTopAnchor(table,0.0);
            viewTableBooks.getChildren().add(table);






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
