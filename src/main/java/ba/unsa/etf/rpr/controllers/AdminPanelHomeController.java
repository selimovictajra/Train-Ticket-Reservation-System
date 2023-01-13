package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.exceptions.TrainException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Objects;

public class AdminPanelHomeController {
    public javafx.scene.control.Label usersLabel;

    public void initialize() {
        UserDaoSQLImpl userDaoSQL = new UserDaoSQLImpl();
        try {
            int num = userDaoSQL.numberOfUsers();
            usersLabel.setText(String.valueOf(num));
        }
        catch(Exception e)  {
            e.printStackTrace();
        }
    }

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

    public void deleteTrainLinkOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/deleteLink.fxml")));
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
    public void addTrainLinkOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/addLink.fxml")));
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
    public void editTrainLinkOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/editLink.fxml")));
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
