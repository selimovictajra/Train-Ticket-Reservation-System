package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * AppFX class is responsible for loading and displaying the JavaFX UI using FXML.
 * @author Tajra Selimovic
 * @version 1.0
 */
public class AppFX extends Application {
    /**
     * Default constructor
     */
    public AppFX() {}
    /**
     * The `start` method is called when the Application is launched.
     * @param primaryStage The primary Stage of the Application.
     * @throws Exception if the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // FXML style
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/login.fxml")));
        primaryStage.setScene(new Scene(root, 520, 400));
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
