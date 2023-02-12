package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.TrainManager;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.exceptions.TrainException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * AddTrainController class is a JavaFX controller class for adding new trains to database.
 * It has several FXML-annotated fields for user input, including a text field for the train route, date picker for the departure date,
 * and choice boxes for the departure time (hour and minute) and price.
 * The class also has two label fields for displaying error messages to the user.
 *
 * @author Tajra Selimovic
 */

public class AddTrainController {

    /**
     * The default constructor for the AddTrainController class.
     */
    public AddTrainController() {}
    @FXML
    private TextField routeText;
    @FXML
    private DatePicker date;
    @FXML
    private ChoiceBox<Integer> hourBox;
    @FXML
    private ChoiceBox<Integer> minBox;
    @FXML
    private  ChoiceBox<Integer> priceBox;
    @FXML
    private Label message1;
    @FXML
    private Label message2;
    TrainManager trainManager = new TrainManager();
    private final Integer[] hour = {5, 6, 7, 8, 9, 10};
    private final Integer[] min = {0, 10, 20, 30, 40, 50};
    private final Integer[] prices = {10, 15, 20, 25, 30};

    /**
     * Sets up the available options for the user to select from when entering the train's departure time and price.
     */
    public void initialize() {
        hourBox.getItems().addAll(hour);
        minBox.getItems().addAll(min);
        priceBox.getItems().addAll(prices);
    }

    /**
     * Handles the action when the "Add" button is clicked.
     * Adds train route in database table Trains
     * @param actionEvent the event that triggers the action
     * @throws TrainException if the train is invalid or already exists
     */
    public void addButtonOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            trainManager.validateAddFields(routeText.getText(), date.toString(), hourBox.getValue(), minBox.getValue(), priceBox.getValue());
            trainManager.validateDuplicate(routeText.getText(), date.getValue().atTime(hourBox.getValue(), minBox.getValue()));
            LocalDate localDate = date.getValue();
            LocalDateTime localDateTime = localDate.atTime(hourBox.getValue(), minBox.getValue());
            Train train = new Train();
            if (trainManager.checkTrainRoute(routeText.getText())) {
                train.setRoute(routeText.getText());
                train.setCapacity(100);
                train.setDeparture(localDateTime);
                train.setPrice(priceBox.getValue());
                trainManager.add(train);
                message1.setText("");
                message2.setText("You have been successfully added train route!");
            }
        }
        catch(Exception e) {
            message1.setText(e.getMessage());
            message2.setText("");
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
    /**
     * The menuTrainLinkOnAction() method is called when the user clicks the "Menu" link.
     * Switches the user to the menu window.
     * If any exception is thrown during the execution, it will be caught and the error message will be printed to the console.
     * @param actionEvent the event that triggers the action
     */
    public void menuLinkOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/adminpanelmenu.fxml")));
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
    /**
     * The deleteTrainLinkOnAction() method is called when the user clicks the "Delete" link.
     * Switches the user to the delete Train window.
     * If any exception is thrown during the execution, it will be caught and the error message will be printed to the console.
     * @param actionEvent the event that triggers the action
     */
    public void deleteTrainLinkOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/deleteLink.fxml")));
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
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
