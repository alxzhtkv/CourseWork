package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import client.Main;

import java.io.IOException;

public class SceneChanger {
    public static void changeScene(String sceneTitle, String sceneFileName, boolean isModal) {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(sceneFileName));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage currentStage;
        Parent root = fxmlLoader.getRoot();

        if (!isModal) {
            currentStage = Main.currentStage;
            currentStage.setTitle(sceneTitle);
            currentStage.setScene(new Scene(root));
            Main.currentStage.getScene().getWindow().hide();
            currentStage.show();
        } else {
            currentStage = new Stage();
            currentStage.setTitle(sceneTitle);
            currentStage.setScene(new Scene(root));
            currentStage.initModality(Modality.APPLICATION_MODAL);
            currentStage.initOwner(Main.currentStage);
            currentStage.showAndWait();
        }

    }
}