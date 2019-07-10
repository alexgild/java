package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.nsu.ccfit.gild.ThreadPool.Execute;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(new File("src/sample/sample.fxml").toURI().toURL());
        primaryStage.setTitle("The Car Factory");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
