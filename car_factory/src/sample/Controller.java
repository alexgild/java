package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.util.converter.NumberStringConverter;
import ru.nsu.ccfit.gild.ThreadPool.Execute;
import ru.nsu.ccfit.gild.factory.Initialization;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.sun.java.accessibility.util.AWTEventMonitor.addTextListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Slider bodyProducingTime;

    @FXML
    private Slider motorProducingTime;

    @FXML
    private Slider accessoryProducingTime;

    @FXML
    private Slider carDealingTime;

    @FXML
    private Label bodyOnStorage = new Label();

    @FXML
    private Label bodyTotalCount= new Label();

    @FXML
    private Label motorOnStorage= new Label();

    @FXML
    private Label motorTotalCount= new Label();

    @FXML
    private Label accessoryOnStorage= new Label();

    @FXML
    private Label accessoryTotalCount= new Label();

    @FXML
    private Label carDelivered= new Label();

    @FXML
    void initialize() throws FileNotFoundException {
        Initialization initializer;
        Execute execute = new Execute();
        initializer = execute.getInitialization();


        //bodyOnStorage.textProperty().bind((new SimpleIntegerProperty(initializer.getBodyStorage().getFullness())).asString());
        //bodyOnStorage.textProperty().addListener(event -> bodyOnStorage.setText());
        bodyOnStorage.textProperty().bindBidirectional(new SimpleIntegerProperty(initializer.getBodyStorage().getFullness()), new NumberStringConverter());
        motorOnStorage.textProperty().bindBidirectional(new SimpleIntegerProperty(initializer.getMotorStorage().getFullness()), new NumberStringConverter());
        accessoryOnStorage.textProperty().bindBidirectional(new SimpleIntegerProperty(initializer.getAccessoryStorage().getFullness()), new NumberStringConverter());

        bodyTotalCount.textProperty().bindBidirectional(new SimpleIntegerProperty(initializer.getBodyStorage().getTotalCount()), new NumberStringConverter());
        motorTotalCount.textProperty().bindBidirectional(new SimpleIntegerProperty(initializer.getBodyStorage().getTotalCount()), new NumberStringConverter());
        accessoryTotalCount.textProperty().bindBidirectional(new SimpleIntegerProperty(initializer.getBodyStorage().getTotalCount()), new NumberStringConverter());
        carDelivered.textProperty().bindBidirectional(new SimpleIntegerProperty(initializer.getAutoStorage().getTotalCount()), new NumberStringConverter());

        bodyProducingTime.valueProperty().addListener((observable, oldValue, newValue) -> execute.setBodyDelay((int)bodyProducingTime.getValue()));

        motorProducingTime.valueProperty().addListener((observable, oldValue, newValue) -> execute.setMotorDelay((int)motorProducingTime.getValue()));

        accessoryProducingTime.valueProperty().addListener((observable, oldValue, newValue) -> execute.setAccessoryDelay((int)accessoryProducingTime.getValue()));

        carDealingTime.valueProperty().addListener((observable, oldValue, newValue) -> execute.setDealerDelay((int) carDealingTime.getValue()));


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                try {
                    execute.stopFactory();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
    }



}



//    private javafx.event.EventHandler<WindowEvent> closeEventHandler = new javafx.event.EventHandler<WindowEvent>() {
//        @Override
//                public void handle(WindowEvent event) {
//
//        }
//    };
//
//        public javafx.event.EventHandler<WindowEvent> getCloseEventHandler() {
//        return closeEventHandler;
//    }
