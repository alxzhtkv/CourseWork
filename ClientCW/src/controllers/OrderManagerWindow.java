package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OrderManagerWindow {

    @FXML
    private Button back;

    @FXML
    private Button issueOrder;

    @FXML
    private Button refundOrder;

    @FXML
    private Button showAllOrders;

    @FXML
    private Button showOrders;

    @FXML
    void clickBack(ActionEvent event) {
        back.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Меню администратора",SceneName.ADMINWINDOW,false);

        });
    }

    @FXML
    void clickIssueOrder(ActionEvent event) {
        issueOrder.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Выдача заказа",SceneName.ISSUEMODAL,true);

        });
    }

    @FXML
    void clickRefundOrder(ActionEvent event) {

    }

    @FXML
    void clickShowAllOrders(ActionEvent event) {

    }

    @FXML
    void clickShowOrders(ActionEvent event) {

    }

}