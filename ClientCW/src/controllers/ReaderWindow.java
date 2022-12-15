package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import library.Book;
import library.Request;
import library.Review;
import persons.*;

import client.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import persons.Reader;

import java.io.IOException;
import java.util.Vector;

import static controllers.BookManagerWindow.getBookFromDatabase;

public class ReaderWindow {


    @FXML
    private AnchorPane TableRequest;
    @FXML
    private Button newRequest;
    @FXML
    private Button showRequest;
    @FXML
    private AnchorPane TableReview;
    @FXML
    private Button addReview;
    @FXML
    private Button showReview;

    @FXML
    private Button addFavourites;
    @FXML
    private Button backBttn;

    @FXML
    private Button deleteFavourites;
    @FXML
    private AnchorPane favouritesTable;
    @FXML
    private Button showFavourites;

    @FXML
    private TextField birthdayField;

    @FXML
    private TextField passwordField;


    @FXML
    private Button edit;

    @FXML
    private Button hide;

    @FXML
    private Label idL;

    @FXML
    private Label idLabel;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passsportField;

    @FXML
    private TextField patronymicField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button search;

    @FXML
    private Button show;

    @FXML
    private Button showBooks;

    @FXML
    private TextField surnameField;

    @FXML
    private AnchorPane tableBooks;

    @FXML
    void clickB(ActionEvent event) {

    }

    @FXML
    void clickEdit(ActionEvent event) {
        String id = idLabel.getText();
        String passport = passsportField.getText();
        String surname = surnameField.getText();
        String name =  nameField.getText();
        String patronymic=patronymicField.getText();
        String phone =phoneField.getText();
        String birthday = birthdayField.getText();
        String password = passwordField.getText();
        Reader reader = new Reader(id,password,name,surname,patronymic,passport,phone,birthday);

    }

    @FXML
    void clickHide(ActionEvent event) {
        hide.setOnAction(actionEvent -> {
            idLabel.setText(null);
            passsportField.setText(null);
            surnameField.setText(null);
            nameField.setText(null);
            patronymicField.setText(null);
            phoneField.setText(null);
            birthdayField.setText(null);
            passwordField.setText(null);
        });

    }

    @FXML
    void clickSearch(ActionEvent event) {
        search.setOnAction(actionEvent -> {

            SceneChanger.changeScene("Поиск",SceneName.SEARHBOOKWINDOW,true);

        });
    }

    @FXML
    void clickShow(ActionEvent event) {
        show.setOnAction(actionEvent -> {
            Connect.client.sendMessage("getReader");
            Connect.client.sendMessage(Connect.id);
            Reader reader = (Reader) Connect.client.readObject();
          ;
            System.out.println( "yfyfyf "+Connect.id);
            System.out.println( "aaaa "+reader.getName());
            idLabel.setText(reader.getLogin());
            passsportField.setText(reader.getPassportID());
            surnameField.setText(reader.getSurname());
            nameField.setText(reader.getName());
            patronymicField.setText(reader.getPatronymic());
            phoneField.setText(reader.getPhone());
            birthdayField.setText(reader.getBirthDay());
            passwordField.setText(reader.getPassword());

        });


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
            tableBooks.getChildren().add(table);





        });


    }


    @FXML
    void clickDeleteFavourites(ActionEvent event) {

    }

    @FXML
    void clickAddFavourites(ActionEvent event) {
        addFavourites.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Вход",SceneName.ADDFAVOURITESMODAL,true);
        });


    }
    @FXML
    void clickShowfavourites(ActionEvent event) {
        showFavourites.setOnAction(actionEvent -> {
            Connect.client.sendMessage("showFavourites");
            Connect.client.sendMessage(Connect.id);
            String size = null;
            try {
                size = Connect.client.readMessage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("оличество избранного "+size);
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
            favouritesTable.getChildren().add(table);
        });


    }

    @FXML
    void clickAddReview(ActionEvent event) {

        addReview.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Рецензии",SceneName.ADDREVIEWWINDOW,false);
        });
    }
    @FXML
    void clickShowReview(ActionEvent event) {

        showReview.setOnAction(actionEvent -> {
            Connect.client.sendMessage("showReviews");
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

            Vector<Review> reviewVector = new Vector<Review>();

            for(int i=0;i<count;i++){
                Review review = (Review)Connect.client.readObject();
                reviewVector.add(review);
            }

            ObservableList<Review> bk= FXCollections.observableArrayList(reviewVector);
            TableView<Review> table = new TableView<>(bk);
            table.setPrefWidth(570);
            table.setPrefHeight(300);

            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            TableColumn<Review,String> titleColumn = new TableColumn<>("Название");
            titleColumn.setCellValueFactory(new PropertyValueFactory<Review,String>("title"));
            table.getColumns().add(titleColumn);

            TableColumn<Review,String> textColumn = new TableColumn<>("Отзыв");
            textColumn.setCellValueFactory(new PropertyValueFactory<Review,String>("text"));
            table.getColumns().add(textColumn);

            AnchorPane.setLeftAnchor(table,0.0);
            AnchorPane.setBottomAnchor(table,0.0);
            AnchorPane.setRightAnchor(table,0.0);
            AnchorPane.setTopAnchor(table,0.0);
            TableReview.getChildren().add(table);


        });

    }

    @FXML
    void clickNewRequest(ActionEvent event) {
        newRequest.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Cоздание запроса", SceneName.ADDREQUESTWINDOW, true);
        });

    }

    @FXML
    void clickShowRequest(ActionEvent event) {
        showRequest.setOnAction(actionEvent -> {
            Connect.client.sendMessage("showRequests");
            Connect.client.sendMessage(Connect.id);
            String size = null;
            try {
                size = Connect.client.readMessage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            int n = Integer.parseInt (size);
            int count = n;
            int j = 0;

            Vector<Request> requestVector = new Vector<>();

            for(int i=0;i<count;i++){
//
                Request request = (Request)Connect.client.readObject();
                requestVector.add(request);
//
            }

            ObservableList<Request> bk= FXCollections.observableArrayList(requestVector);
            TableView<Request> table = new TableView<>(bk);
            table.setPrefWidth(570);
            table.setPrefHeight(300);
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            TableColumn<Request,String> titleColumn = new TableColumn<>("Название");
            titleColumn.setCellValueFactory(new PropertyValueFactory<Request,String>("title"));
            table.getColumns().add(titleColumn);



            AnchorPane.setLeftAnchor(table,0.0);
            AnchorPane.setBottomAnchor(table,0.0);
            AnchorPane.setRightAnchor(table,0.0);
            AnchorPane.setTopAnchor(table,0.0);
            TableRequest.getChildren().add(table);
        });

    }
}