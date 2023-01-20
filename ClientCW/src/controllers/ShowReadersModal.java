package controllers;

import client.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import library.Book;
import library.Order;
import persons.Reader;
import persons.User;

import java.io.IOException;
import java.util.Vector;

public class ShowReadersModal {

    @FXML
    private AnchorPane readersTable;

    @FXML
    private Button showReaders;

    @FXML
    private Button showUsers;

    @FXML
    private AnchorPane usersTable;

    @FXML
    void clickShowReaders(ActionEvent event) {
        showReaders.setOnAction(actionEvent -> {
            Connect.client.sendMessage("showReaders");

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

            Vector<Reader> readersVector = new Vector<Reader>();

            for(int i=0;i<count;i++){
                Reader reader = (Reader) Connect.client.readObject();
                readersVector.add(reader);
            }
            ObservableList<Reader> rdr= FXCollections.observableArrayList(readersVector);
            TableView<Reader> table = new TableView<>(rdr);
            table.setPrefWidth(570);
            table.setPrefHeight(300);

            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


            TableColumn<Reader,String>  loginColumn = new TableColumn<>("ID");
            loginColumn.setCellValueFactory(new PropertyValueFactory<Reader,String>("login"));
            table.getColumns().add(loginColumn);

            TableColumn<Reader,String>  nameColumn = new TableColumn<>("Имя");
            nameColumn.setCellValueFactory(new PropertyValueFactory<Reader,String>("name"));
            table.getColumns().add(nameColumn);

            TableColumn<Reader,String>  patColumn = new TableColumn<>("Отчество");
            patColumn.setCellValueFactory(new PropertyValueFactory<Reader,String>("patronymic"));
            table.getColumns().add(patColumn);

            TableColumn<Reader,String>  surnameColumn = new TableColumn<>("Фамилия");
            surnameColumn.setCellValueFactory(new PropertyValueFactory<Reader,String>("surname"));
            table.getColumns().add(surnameColumn);

            TableColumn<Reader,String>  passportIDColumn = new TableColumn<>("Паспорт");
            passportIDColumn.setCellValueFactory(new PropertyValueFactory<Reader,String>("passportID"));
            table.getColumns().add(passportIDColumn);

            TableColumn<Reader,String>  phoneColumn = new TableColumn<>("Телефо");
            phoneColumn.setCellValueFactory(new PropertyValueFactory<Reader,String>("phone"));
            table.getColumns().add(phoneColumn);

            TableColumn<Reader,String>  birthDayСolumn = new TableColumn<>("Дата рождения");
            birthDayСolumn.setCellValueFactory(new PropertyValueFactory<Reader,String>("birthDay"));
            table.getColumns().add(birthDayСolumn);

            AnchorPane.setLeftAnchor(table,0.0);
            AnchorPane.setBottomAnchor(table,0.0);
            AnchorPane.setRightAnchor(table,0.0);
            AnchorPane.setTopAnchor(table,0.0);

            readersTable.getChildren().add(table);










        });

    }

    @FXML
    void clickShowUsers(ActionEvent event) {
        showUsers.setOnAction(actionEvent -> {
            Connect.client.sendMessage("showUsers");
            String size = null;
            try {
                size = Connect.client.readMessage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            int n = Integer.parseInt (size);
            int count = n;
            int j = 0;

            Vector<User> usersVector = new Vector<User>();

            for(int i=0;i<count;i++){
                User user = (User) Connect.client.readObject();
                usersVector.add(user);

                ObservableList<User> usr= FXCollections.observableArrayList(usersVector);
                TableView<User> table = new TableView<>(usr);
                table.setPrefWidth(570);
                table.setPrefHeight(300);

                table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

                TableColumn<User,String> liginColumn = new TableColumn<>("Логин");
                liginColumn.setCellValueFactory(new PropertyValueFactory<User,String>("login"));
                table.getColumns().add(liginColumn);

                TableColumn<User,String> passColumn = new TableColumn<>("Пароль");
                passColumn.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
                table.getColumns().add(passColumn);

                AnchorPane.setLeftAnchor(table,0.0);
                AnchorPane.setBottomAnchor(table,0.0);
                AnchorPane.setRightAnchor(table,0.0);
                AnchorPane.setTopAnchor(table,0.0);

                usersTable.getChildren().add(table);



            }


        });

    }

}
