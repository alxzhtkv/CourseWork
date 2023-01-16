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
import library.IssuedOrder;
import library.Order;

import java.io.IOException;
import java.util.Vector;

public class IssuedBooksModal {

    @FXML
    private AnchorPane IssuedBooksTable;

    @FXML
    private Button show;

    @FXML
    void clickShow(ActionEvent event) {
        show.setOnAction(actionEvent -> {
            Connect.client.sendMessage("showIssuesOrders");
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

            Vector<IssuedOrder> ordersVector = new Vector<IssuedOrder>();
            for(int i=0;i<count;i++){
                IssuedOrder order = (IssuedOrder) Connect.client.readObject();
                ordersVector.add(order);
            }

            ObservableList<IssuedOrder> bk= FXCollections.observableArrayList(ordersVector);
            TableView<IssuedOrder> table = new TableView<>(bk);
            table.setPrefWidth(570);
            table.setPrefHeight(300);

            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            TableColumn<IssuedOrder,String> IDorder= new TableColumn<>("ID заказа");
            IDorder.setCellValueFactory(new PropertyValueFactory<IssuedOrder,String>("orderID"));
            table.getColumns().add(IDorder);

            TableColumn<IssuedOrder,String> IDbookColumn = new TableColumn<>("ID книги");
            IDbookColumn.setCellValueFactory(new PropertyValueFactory<IssuedOrder,String>("booksID"));
            table.getColumns().add(IDbookColumn);

            TableColumn<IssuedOrder,String> bookTitleColumn = new TableColumn<>("Название");
            bookTitleColumn.setCellValueFactory(new PropertyValueFactory<IssuedOrder,String>("bookTitle"));
            table.getColumns().add(bookTitleColumn);

            TableColumn<IssuedOrder,String> dateIColumn = new TableColumn<>("Дата выдачи");
            dateIColumn.setCellValueFactory(new PropertyValueFactory<IssuedOrder,String>("dateI"));
            table.getColumns().add(dateIColumn);

            TableColumn<IssuedOrder,String> dateBColumn = new TableColumn<>("Дата возврата");
            dateBColumn.setCellValueFactory(new PropertyValueFactory<IssuedOrder,String>("dateB"));
            table.getColumns().add(dateBColumn);

            AnchorPane.setLeftAnchor(table,0.0);
            AnchorPane.setBottomAnchor(table,0.0);
            AnchorPane.setRightAnchor(table,0.0);
            AnchorPane.setTopAnchor(table,0.0);
            IssuedBooksTable.getChildren().add(table);



        });

    }

}
