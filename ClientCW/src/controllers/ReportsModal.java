package controllers;

import client.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

public class ReportsModal {

    @FXML
    private Button createDiagram;

    @FXML
    private PieChart genreReport;

    @FXML
    void clickCreateDiagram(ActionEvent event) {
        createDiagram.setOnAction(actionEvent -> {

            Connect.client.sendMessage("createDiagram");




            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Роман", 13),
                            new PieChart.Data("Детектив", 49),
                            new PieChart.Data("Фантастика", 10),
                            new PieChart.Data("Фентези", 22),
                            new PieChart.Data("Другое", 30));
            genreReport.setData(pieChartData);
            genreReport.setTitle("Статистика предпочитаемой пользователями литературы");
        });




    }

}