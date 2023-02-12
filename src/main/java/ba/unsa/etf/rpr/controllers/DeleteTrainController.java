package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.TrainManager;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.exceptions.TrainException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The DeleteTrainController class is the controller class for the delete train feature in the admin panel.
 * It handles the deletion of train routes and displays appropriate messages to the user.
 * @author Tajra Selimovic
 */
public class DeleteTrainController {
    /**
     * Default constructor
     */
    public DeleteTrainController() {}
    @FXML
    private ChoiceBox<Integer> trainRoutes;
    @FXML
    private final List<Integer> trainIds = new ArrayList<Integer>();
    private final TrainManager trainManager = new TrainManager();
    @FXML
    private Label message1;
    @FXML
    private Label message2;

    /**
     * The initialize method is called when the class is first loaded.
     * Populates the trainRoutes ChoiceBox with list of train ids.
     * @throws TrainException if an error occurs when retrieving the list of trains from the trainManager.
     */
    @FXML
    public void initialize() throws TrainException {
        List<Train> list = trainManager.getAll();
        for (Train train : list) {
            trainIds.add(train.getId());
        }
        trainRoutes.getItems().addAll(trainIds);
    }
    /**
     * Handles the action when the "Delete" button is clicked.
     * Deletes train route in database table Trains
     * @param actionEvent the event that triggers the action
     * @throws TrainException if the train is invalid or already exists
     */
    public void deleteButtonOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            trainManager.validateDeleteFields(trainRoutes.getValue());
            trainManager.delete(trainRoutes.getValue());
            message1.setText("You have been successfully deleted train route!");
            message2.setText("");
        }
        catch (TrainException e) {
            message1.setText("");
            message2.setText(e.getMessage());
        }
    }
    /**
     * The homeTrainLinkOnAction() method is called when the user clicks the "Home" link.
     * Switches the user to the home window.
     * If any exception is thrown during the execution, it will be caught and the error message will be printed to the console.
     * @param actionEvent the event that triggers the action
     */
    public void homeLinkOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/adminpanelhome.fxml")));
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
