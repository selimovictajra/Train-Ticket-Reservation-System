package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.TrainManager;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.TrainException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The class UserBookingController is used for creating the user interface for booking a train reservation.
 * This class uses the JavaFX framework for the creation of the user interface and implements the logic for reserving a train.
 * @author Tajra Selimovic
 * @version 1.0
 */
public class UserBookingController {
    @FXML
    private ChoiceBox<String> routeBox;
    @FXML
    private ChoiceBox<LocalDate> dateBox;
    @FXML
    private ChoiceBox<LocalTime> timeBox;
    private final ArrayList<String> routes = new ArrayList<String>();
    private final ArrayList<LocalDate> dateL = new ArrayList<LocalDate>();
    private final ArrayList<LocalTime> timeL = new ArrayList<LocalTime>();
    @FXML
    private Label userLabel;
    @FXML
    private Label message1;
    @FXML
    private Label message2;
    @FXML
    private Label priceLabel;
    TrainManager trainManager = new TrainManager();
    ReservationManager reservationManager = new ReservationManager();

    /**
     * Default constructor
     */
    public UserBookingController() {}

    /**
     * The initialize method is used to set up the user interface when the window is opened.
     * This method sets the user's name in the userLabel, validates the booking fields using the trainManager, and sets up the route, date, and time ChoiceBoxes.
     */
    public void initialize() {
        try {
            Model model = Model.getInstance();
            userLabel.setText(model.getUser().getName());
            trainManager.validateBookFields(routeBox.toString(), dateBox.toString(), timeBox.toString());
            List<Train> trains = trainManager.getAll();
            System.out.println("ok");
            for (Train train : trains) {
                boolean here = false;
                for (String route : routes) {
                    if (route.equals(train.getRoute())) {
                        here = true;
                        break;
                    }
                }
                if (!here) routes.add(train.getRoute());
            }
            routeBox.getItems().addAll(routes);
            routeBox.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> {
                try {
                    dateBox.getItems().clear();
                    dateL.clear();
                    for (Train value : trains) {
                        if (value.getRoute().equals(routeBox.getValue())) {
                            //System.out.println("usao");
                            LocalDateTime localDateTime = value.getDeparture();
                            dateL.add(localDateTime.toLocalDate());
                            //timeL.add(localDateTime.toLocalTime());
                        }
                    }
                    dateBox.getItems().addAll(dateL);
                    //timeBox.getItems().addAll(timeL);
                }
                catch(Exception e) {
                    message1.setText(e.getMessage());
                    message2.setText("");
                    priceLabel.setText("");
                    System.out.println(e.getMessage());
                }
            });
            dateBox.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> {
                try {
                    timeBox.getItems().clear();
                    timeL.clear();
                    for (Train value : trains) {
                        if (value.getRoute().equals(routeBox.getValue()) && value.getDeparture().toLocalDate().equals(dateBox.getValue())) {
                            //System.out.println("usao");
                            LocalDateTime localDateTime = value.getDeparture();
                            //dateL.add(localDateTime.toLocalDate());
                            timeL.add(localDateTime.toLocalTime());
                        }
                    }
                    //dateBox.getItems().addAll(dateL);
                    timeBox.getItems().addAll(timeL);
                }
                catch(Exception e) {
                    message1.setText(e.getMessage());
                    message2.setText("");
                    priceLabel.setText("");
                    System.out.println(e.getMessage());
                }
            });
        }
        catch (Exception e) {
            message1.setText(e.getMessage());
            message2.setText("");
            priceLabel.setText("");
            System.out.println(e.getMessage());
        }
    }
    /**
     * Handles the action performed when the "Book" button is clicked.
     * Validates the input fields, creates a reservation object, and adds it to the reservation manager.
     * Also sets the reservation in the model, and updates the messages and price label.
     * @param actionEvent the event generated when the button is clicked
     * @throws TrainException if the input fields are invalid
     */
    public void bookButtonOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            reservationManager.validateBookFields(routeBox.toString(), dateBox.getValue(), timeBox.getValue());
            Reservation reservation = new Reservation();
            Model model = Model.getInstance();
            User user = model.getUser();
            //train building
            //Train train = new Train();
            LocalDate localDate = dateBox.getValue();
            LocalDateTime localDateTime = localDate.atTime(timeBox.getValue());
            List<Train> trains = trainManager.getAll();
            System.out.println("ok");
            for (Train train1 : trains) {
                if (train1.getRoute().equals(routeBox.getValue()) && train1.getDeparture().equals(localDateTime)) {
                    //train.setId(train1.getId());
                    reservation.setTrain(train1);
                    System.out.println("usao " + train1.getId());
                }
            }
            //reservation building
            reservation.setUser(user);
            reservationManager.add(reservation);
            model.setReservation(reservation);
            message1.setText("");
            message2.setText("You have been successfully booked train ticket!");
            priceLabel.setText("Price: " + reservation.getTrain().getPrice());
        }
        catch (TrainException e) {
            message1.setText(e.getMessage());
            System.out.println(e.getMessage());
            message2.setText("");
            priceLabel.setText("");
        }
    }
    /**
     * The method is used to logout the current user. It loads the login screen when the logout button is clicked.
     * @param actionEvent The event that is generated when the logout button is clicked.
     * @throws TrainException If any exception occurs during the logout process.
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
     * Handles the action event for the "Home" link in the user panel.
     * This method changes the current scene to the user panel home screen.
     * @param actionEvent The action event triggered by the user clicking the "Home" link.
     * @throws TrainException if there is an error loading the user panel home screen.
     */
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
    /**
     * Handles the action event for the "Your Bookings" link in the user panel.
     * This method changes the current scene to the user panel your bookings screen.
     * @param actionEvent The action event triggered by the user clicking the "Your Bookings" link.
     * @throws TrainException if there is an error loading the user panel home screen.
     */
    public void bookingsLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
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
