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
import library.Review;

import java.io.IOException;
import java.util.Vector;

import static controllers.BookManagerWindow.getBookFromDatabase;

public class ShowOrderModal {

    @FXML
    private AnchorPane TableOrders;

    @FXML
    private Button show;

    @FXML
    void clickShow(ActionEvent event) {

        show.setOnAction(actionEvent -> {
            Connect.client.sendMessage("showOrders");
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

            Vector<Order> ordersVector = new Vector<>();
            for(int i=0;i<count;i++){
                Order order = (Order) Connect.client.readObject();
                ordersVector.add(order);
            }

            ObservableList<Order> bk= FXCollections.observableArrayList(ordersVector);
            TableView<Order> table = new TableView<>(bk);
            table.setPrefWidth(570);
            table.setPrefHeight(300);

            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            TableColumn<Order,String> IDbookColumn = new TableColumn<>("ID книги");
            IDbookColumn.setCellValueFactory(new PropertyValueFactory<Order,String>("booksID"));
            table.getColumns().add(IDbookColumn);

            TableColumn<Order,String> bookTitleColumn = new TableColumn<>("Название");
            bookTitleColumn.setCellValueFactory(new PropertyValueFactory<Order,String>("bookTitle"));
            table.getColumns().add(bookTitleColumn);

            TableColumn<Order,String> IDorderColumn = new TableColumn<>("ID заказа");
            IDorderColumn.setCellValueFactory(new PropertyValueFactory<Order,String>("orderID"));
            table.getColumns().add(IDorderColumn);

            TableColumn<Order,String> statusColumn = new TableColumn<>("Статус");
            statusColumn.setCellValueFactory(new PropertyValueFactory<Order,String>("status"));
            table.getColumns().add(statusColumn);
            AnchorPane.setLeftAnchor(table,0.0);
            AnchorPane.setBottomAnchor(table,0.0);
            AnchorPane.setRightAnchor(table,0.0);
            AnchorPane.setTopAnchor(table,0.0);
            TableOrders.getChildren().add(table);

        });
    }

}