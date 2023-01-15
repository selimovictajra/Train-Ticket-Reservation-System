package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.exceptions.TrainException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class UserYBController {


    public void logoutLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/login.fxml")));
            Stage stage = (Stage) ((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void homeLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/userPanelHome.fxml")));
            Stage stage = (Stage) ((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
