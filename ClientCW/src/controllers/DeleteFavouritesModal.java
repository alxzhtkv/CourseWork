package controllers;

import client.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import library.Favourites;

import javax.xml.stream.events.Comment;
import java.io.IOException;

public class DeleteFavouritesModal {

    @FXML
    private TextField FavouritesIDfield;

    @FXML
    private Button deleteFavourites;

    @FXML
    void clickDeleteFavourites(ActionEvent event) {
        deleteFavourites.setOnAction(actionEvent -> {
            String bookID = FavouritesIDfield.getText();
            Favourites favourites = new Favourites(Connect.id,bookID);

            Connect.client.sendMessage("deleteFavourites");
            Connect.client.sendObject(favourites);
            String answer;
            try {
                answer = (String) Connect.client.readMessage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            switch (answer) {
                case "ok": {
                    SceneChanger.changeScene("Удаление избранного завершено",SceneName.DELETEOKBOOK,true);

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
