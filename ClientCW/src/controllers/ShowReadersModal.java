package controllers;

import client.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import library.Book;
import library.Order;
import persons.Reader;

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
            ObservableList<Reader> bk= FXCollections.observableArrayList(readersVector);
            TableView<Reader> table = new TableView<>(bk);
            table.setPrefWidth(570);
            table.setPrefHeight(300);

            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);




        });

    }

    @FXML
    void clickShowUsers(ActionEvent event) {

    }

}
