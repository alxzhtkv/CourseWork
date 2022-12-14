package controllers;

import client.Client;
import client.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import library.Favourites;

import java.io.IOException;

public class AddFavouritesModal {

    @FXML
    private TextField FavouritesIDfield;

    @FXML
    private Button addFavourites;

    @FXML
    void clickAddFavourites(ActionEvent event) {
        addFavourites.setOnAction(actionEvent -> {
            String bookID = FavouritesIDfield.getText();
            Favourites favourites = new Favourites(Connect.id,bookID);
            Connect.client.sendMessage("addFavourites");
            Connect.client.sendObject(favourites);
            String answer;
            try {
                answer = (String) Connect.client.readMessage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            switch (answer) {
                case "added": {
                    SceneChanger.changeScene("Добавление завершено",SceneName.DELETEOKBOOK,true);

                    break;
                }
                case "error": {
                    SceneChanger.changeScene("Ошибка",SceneName.DELETEERRORBOOK,true);

                    break;
                }
            }
        });

    }

}
