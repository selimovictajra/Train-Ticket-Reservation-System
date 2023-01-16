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

public class UserBookingController {
    @FXML
    private ChoiceBox<String> routeBox;
    @FXML
    private ChoiceBox<LocalDate> dateBox;
    @FXML
    private ChoiceBox<LocalTime> timeBox;
    private ArrayList<String> routes = new ArrayList<String>();
    private ArrayList<LocalDate> dateL = new ArrayList<LocalDate>();
    private ArrayList<LocalTime> timeL = new ArrayList<LocalTime>();
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
                            Train train = value;
                            LocalDateTime localDateTime = train.getDeparture();
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
            for (int i = 0; i < trains.size(); i++) {
                Train train1 = trains.get(i);
                if (train1.getRoute().equals(routeBox.getValue()) && train1.getDeparture().equals(localDateTime)) {
                    //train.setId(train1.getId());
                    reservation.setTrain(train1);
                    System.out.println("usao " + train1.getId());
                }
            }
            //reservation building
            reservation.setUser(user);
            reservationManager.add(reservation);
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
