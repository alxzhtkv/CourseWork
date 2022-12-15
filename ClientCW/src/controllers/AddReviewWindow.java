package controllers;

import client.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import library.Review;

import java.io.IOException;

public class AddReviewWindow {

    @FXML
    private Button back;
    @FXML
    private Label titleL;

    @FXML
    private TextField idBook;

    @FXML
    private Button ok;

    @FXML
    private Button send;

    @FXML
    private TextArea textArea;

    @FXML
    void clickBack(ActionEvent event) {
        back.setOnAction(actionEvent -> {
            SceneChanger.changeScene("Меню читателя",SceneName.READERWINDOW,false);
        });
    }

    @FXML
    void clickSend(ActionEvent event) {

        send.setOnAction(actionEvent -> {
            Connect.client.sendMessage("addReview");
            String id = idBook.getText();
            String text=textArea.getText();
            String title = titleL.getText();
            Review review = new Review(Connect.id,id,title,text);

            if(title.equals("Книга не определена")){
                SceneChanger.changeScene("Ошибка",SceneName.DELETEERRORBOOK,true);
                Connect.client.sendMessage("notOk");

            }else{
                Connect.client.sendMessage("ok");
                Connect.client.sendObject(review);
                SceneChanger.changeScene("Завершено",SceneName.DELETEOKBOOK,true);
                idBook.setText(null);
                textArea.setText(null);
                titleL.setText(null);
            }



        });

    }

    @FXML
    void getBookTitle(ActionEvent event) {

        String id = idBook.getText();
        Connect.client.sendMessage("checkTitle");
        Connect.client.sendMessage(id);
        String title="Книга не определена";
        try {
            title = (String) Connect.client.readMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        if(title.equals("error")){
//            title="Книга не определена";
//        }
        titleL.setText(title);

    }

}