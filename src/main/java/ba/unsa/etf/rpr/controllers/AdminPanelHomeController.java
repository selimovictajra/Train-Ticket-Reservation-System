package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.exceptions.TrainException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Objects;

public class AdminPanelHomeController {

    public void menuLinkOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/adminpanelmenu.fxml")));
            Stage stage = (Stage)((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception exception) {
            exception.printStackTrace();
            exception.getCause();
        }
    }
    public void logoutLinkOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/login.fxml")));
            Stage stage = (Stage)((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception exception) {
            exception.printStackTrace();
            exception.getCause();
        }
    }

}
