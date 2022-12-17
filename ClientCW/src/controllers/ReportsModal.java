package controllers;

import client.Client;
import client.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import library.Book;

import java.io.IOException;
import java.util.Vector;

public class ReportsModal {

    @FXML
    private Button createDiagram;
    @FXML
    private Button DiagramAllBooks;


    @FXML
    private PieChart genreReport;
    @FXML
    private PieChart reportAllBooks;

    @FXML
    void clickCreateDiagram(ActionEvent event) {
        createDiagram.setOnAction(actionEvent -> {

            Connect.client.sendMessage("createDiagram");

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

            Vector<Integer> data = new Vector<Integer>();

            for(int i=0;i<count;i++){
                String st = (String) Connect.client.readObject();
                int d = Integer.parseInt (st);
                data.add(d);

            }



            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Роман", data.get(0)),
                            new PieChart.Data("Детектив", data.get(1)),
                            new PieChart.Data("Фантастика", data.get(2)),
                            new PieChart.Data("Фентези", data.get(3)),
                            new PieChart.Data("Другое", data.get(4)));
            genreReport.setData(pieChartData);
            genreReport.setTitle("Статистика предпочитаемой пользователями литературы");
        });

    }

    @FXML
    void createDiagramAllBooks(ActionEvent event) {
        DiagramAllBooks.setOnAction(actionEvent -> {

            Connect.client.sendMessage("createDiagramAllBooks");

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

            Vector<Integer> data = new Vector<Integer>();

            for(int i=0;i<count;i++){
                String st = (String) Connect.client.readObject();
                int d = Integer.parseInt (st);
                data.add(d);

            }



            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Выданные книги", data.get(1)),
                            new PieChart.Data("Книги в наличии", data.get(0)));
            reportAllBooks.setData(pieChartData);
            reportAllBooks.setTitle("Статистика выданных книг");
        });
    }

}