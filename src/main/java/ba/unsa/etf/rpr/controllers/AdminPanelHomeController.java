package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
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

/**
 * The AdminPanelHomeController class is responsible for the functionality of the admin panel home page.
 *
 * @author Tajra Selimovic
 */

public class AdminPanelHomeController {
    UserManager userManager = new UserManager();
    @FXML
    public javafx.scene.control.Label usersLabel;
    @FXML
    public javafx.scene.control.Label adminLabel;

    /**
     * The initialize() method is called when the admin panel home page is loaded.
     * It retrieves the name of the currently logged in admin user and number of users and sets it on page.
     */
    public void initialize() {
        try {
            int num = userManager.numberOfUsers();
            usersLabel.setText(String.valueOf(num));
            Model model = Model.getInstance();
            adminLabel.setText(model.getUser().getName());
        }
        catch(Exception e)  {
            e.printStackTrace();
        }
    }
    /**
     * The menuTrainLinkOnAction() method is called when the user clicks the "Menu" link.
     * Switches the user to the menu window.
     * If any exception is thrown during the execution, it will be caught and the error message will be printed to the console.
     * @param actionEvent the event that triggers the action
     */
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
    /**
     * The deleteTrainLinkOnAction() method is called when the user clicks the "Delete" link.
     * Switches the user to the delete Train window.
     * If any exception is thrown during the execution, it will be caught and the error message will be printed to the console.
     * @param actionEvent the event that triggers the action
     */
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
    /**
     * The addTrainLinkOnAction() method is called when the user clicks the "Add" link.
     * Switches the user to the add Train window.
     * If any exception is thrown during the execution, it will be caught and the error message will be printed to the console.
     * @param actionEvent the event that triggers the action
     */
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
    /**
     * The editTrainLinkOnAction() method is called when the user clicks the "Edit" link.
     * Switches the user to the edit Train window.
     * If any exception is thrown during the execution, it will be caught and the error message will be printed to the console.
     * @param actionEvent the event that triggers the action
     */
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
    /**
     * The logoutLinkOnAction() method is called when the user clicks the "Logout" link.
     * Switches the user to the login window.
     * If any exception is thrown during the execution, it will be caught and the error message will be printed to the console.
     * @param actionEvent the event that triggers the action
     */
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
