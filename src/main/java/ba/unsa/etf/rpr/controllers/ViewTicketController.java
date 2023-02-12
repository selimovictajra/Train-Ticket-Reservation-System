package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;

import java.time.LocalDateTime;
import javafx.scene.control.Label;

/**
 * This class is a JavaFX controller for the user's "View Ticket" screen.
 * It is responsible for displaying the details of a specific train ticket reservation,
 * including the train id, departure time, passenger name, date, and ticket number.
 * @author Tajra Selimovic
 */
public class ViewTicketController {
    /**
     * Train id of reservation
     */
    @FXML
    public Label trainIdLabel;
    /**
     * Departure of reservation
     */
    @FXML
    public Label departureLabel;
    /**
     * Passenger name
     */
    @FXML
    public Label passengerNameLabel;
    /**
     * Passenger name
     */
    @FXML
    public Label passengerNameLabel1;
    /**
     * Route of reservation
     */
    @FXML
    public Label routeLabel;
    /**
     * Route of reservation
     */
    @FXML
    public Label routeLabel1;
    /**
     * Label for displaying the date.
     */
    @FXML
    public Label dateLabel;
    /**
     * Label for displaying the date.
     */
    @FXML
    public Label dateLabel1;
    /**
     * Label for displaying id of reservation
     */
    @FXML
    public Label ticketLabel;
    /**
     * Label for displaying id of reservation
     */
    @FXML
    public Label ticketLabel1;

    /**
     * It sets the text of various labels to the values of properties of Train and Reservation objects retrieved from the Model class,
     * and a User object also retrieved from the Model class.
     */
    public void initialize() {
        Model model = Model.getInstance();
        int i = model.getTrain().getId();
        trainIdLabel.setText(Integer.toString(i));
        LocalDateTime localDateTime = model.getTrain().getDeparture();
        departureLabel.setText(localDateTime.toLocalTime().toString());
        passengerNameLabel.setText(model.getUser().getName());
        passengerNameLabel1.setText(model.getUser().getName());
        dateLabel.setText(localDateTime.toLocalDate().toString());
        dateLabel1.setText(localDateTime.toLocalDate().toString());
        ticketLabel.setText(Integer.toString(model.getReservation().getId()));
        ticketLabel1.setText(Integer.toString(model.getReservation().getId()));
        routeLabel.setText(model.getTrain().getRoute());
        routeLabel1.setText(model.getTrain().getRoute());
    }
}
