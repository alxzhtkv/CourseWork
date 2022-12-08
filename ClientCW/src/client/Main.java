package client;
import java.io.IOException;

import controllers.SceneName;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage currentStage;

    @Override
    public void start(Stage stage) throws IOException {
        currentStage = stage;
        currentStage.setResizable(false);

        FXMLLoader fxmlLoader = new FXMLLoader(client.Main.class.getResource(SceneName.STARTWINDOW));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
//        currentStage.getIcons().add(new Image("C:\\Users\\Anna Soroka\\OneDrive\\Рабочий стол\\Курсач\\Client\\resource\\icon.png"));
        currentStage.setTitle("Library");
        currentStage.setScene(scene);
        currentStage.show();
    }

    public static void main(String[] arg)
         throws IOException {
        Connect.client = new Client("127.0.0.2", "2525");
        System.out.println("Connected");
        launch();
    }

}