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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is used to handle the editing of a train's information in the admin panel.
 * @author Tajra Selimovic
 */
public class EditTrainController {
    @FXML
    private ChoiceBox<Integer> routeBox;
    @FXML
    private final List<Integer> trainIds = new ArrayList<Integer>();
    @FXML
    private TextField routeText;
    @FXML
    private DatePicker date;
    @FXML
    private ChoiceBox<Integer> hourBox;
    @FXML
    private ChoiceBox<Integer> minBox;
    @FXML
    private ChoiceBox<Integer> priceBox;
    @FXML
    private Label message1;
    @FXML
    private Label message2;
    TrainManager trainManager = new TrainManager();
    private final Integer[] hour = {5, 6, 7, 8, 9, 10};
    private final Integer[] min = {0, 10, 20, 30, 40, 50};
    private final Integer[] prices = {10, 15, 20, 25, 30};

    /**
     * Initializes the fields of the Edit Train Route view.
     * Populates the fields with the data of the selected train route from the database.
     */
    public void initialize() {
        try {
            List<Train> trains = trainManager.getAll();
            for (Train train : trains) {
                trainIds.add(train.getId());
            }
            routeBox.getItems().addAll(trainIds);
            hourBox.getItems().addAll(hour);
            minBox.getItems().addAll(min);
            priceBox.getItems().addAll(prices);
            routeBox.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> {
                Train train1 = null;
                try {
                    train1 = trainManager.getById(newvalue);
                    routeText.setText(train1.getRoute());
                    LocalDateTime localDateTime = train1.getDeparture();
                    date.setValue(localDateTime.toLocalDate());
                    LocalTime localTime = localDateTime.toLocalTime();
                    hourBox.setValue(localTime.getHour());
                    minBox.setValue(localTime.getMinute());
                    priceBox.setValue(train1.getPrice());
                }
                catch (TrainException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    /**
     * Handles the action when the "Edit" button is clicked.
     * Validates the fields for editing a train route and updates the train route in the database.
     * @param actionEvent the action event that triggers the method
     * @throws TrainException if the train route cannot be edited
     */
    public void editButtonOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            trainManager.validateDeleteFields(routeBox.getValue());
            trainManager.validateAddFields(routeText.getText(), date.toString(), hourBox.getValue(), minBox.getValue(), priceBox.getValue());
            LocalDate localDate = date.getValue();
            LocalDateTime localDateTime = localDate.atTime(hourBox.getValue(), minBox.getValue());
            if (trainManager.checkTrainRoute(routeText.getText())) {
                Train train = new Train();
                train.setId(routeBox.getValue());
                train.setCapacity(100);
                train.setRoute(routeText.getText());
                train.setDeparture(localDateTime);
                train.setPrice(priceBox.getValue());
                trainManager.update(train);
                message1.setText("");
                message2.setText("You have been successfully edited train route with id " + routeBox.getValue() + "!");
            }
        }
        catch (Exception e) {
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
    public void homeLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
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
    public void menuLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
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
    public void deleteTrainLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
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
     * The addTrainLinkOnAction() method is called when the user clicks the "Add" link.
     * Switches the user to the add Train window.
     * If any exception is thrown during the execution, it will be caught and the error message will be printed to the console.
     * @param actionEvent the event that triggers the action
     */
    public void addTrainLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/addLink.fxml")));
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
     * The logoutLinkOnAction() method is called when the user clicks the "Logout" link.
     * Switches the user to the login window.
     * If any exception is thrown during the execution, it will be caught and the error message will be printed to the console.
     * @param actionEvent the event that triggers the action
     */
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
}
