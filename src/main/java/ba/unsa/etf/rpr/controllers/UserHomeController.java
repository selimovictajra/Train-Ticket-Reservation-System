package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.exceptions.TrainException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.util.Objects;

/**
 * This class is a JavaFX controller for the user home screen.
 * It contains methods for handling button events for the logout, bookings, and your bookings links.
 * It also has a method for initializing the user's name on the home screen.
 * @author Tajra Selimovic
 */
public class UserHomeController {
    @FXML
    private Label userLabel;
    /**
     * Default constructor
     */
    public UserHomeController() {}
    /**
     * Initializes the user's name on the home screen by getting the current user from the Model class.
     */
    public void initialize() {
        Model model = Model.getInstance();
        userLabel.setText(model.getUser().getName());
    }
    /**
     * Handles the action for the logout link.
     * Navigates to the login page.
     * @param actionEvent the action event
     * @throws TrainException in case of problems
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
    /**
     * Handles the action for the booking link.
     * Navigates to the Booking page.
     * @param actionEvent the action event
     * @throws TrainException in case of problems
     */
    public void bookingLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/userPanelBooking.fxml")));
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
     * Handles the action for the YourBookings link.
     * Navigates to the YourBookings page.
     * @param actionEvent the action event
     * @throws TrainException in case of problems
     */
    public void yBookingsLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/userPanelYourBookings.fxml")));
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
