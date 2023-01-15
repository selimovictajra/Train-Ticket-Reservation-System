package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.TrainManager;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.TrainException;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
            for (int i = 0; i < trains.size(); i++) {
                Train train = trains.get(i);
                routes.add(train.getRoute());
            }
            routeBox.getItems().addAll(routes);
            routeBox.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> {
                try {
                    for (int i = 0; i < trains.size(); i++) {
                        if (trains.get(i).getRoute().equals(routeBox.getValue())) {
                            //System.out.println("usao");
                            Train train = trains.get(i);
                            LocalDateTime localDateTime = train.getDeparture();
                            dateL.add(localDateTime.toLocalDate());
                            timeL.add(localDateTime.toLocalTime());
                        }
                    }
                    dateBox.getItems().addAll(dateL);
                    timeBox.getItems().addAll(timeL);
                }
                catch(Exception e) {
                    message1.setText(e.getMessage());
                    message2.setText("");
                    priceLabel.setText("Price: 30$");
                }
            });
        }
        catch (Exception e) {
            message1.setText(e.getMessage());
            message2.setText("");
            priceLabel.setText("");
        }
    }

    public void bookButtonOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            reservationManager.validateBookFields(routeBox.toString(), dateBox.getValue(), timeBox.getValue());
            Reservation reservation = new Reservation();
            Model model = Model.getInstance();
            User user = model.getUser();
            //train building
            Train train = new Train();
            LocalDate localDate = dateBox.getValue();
            LocalDateTime localDateTime = localDate.atTime(timeBox.getValue());
            List<Train> trains = trainManager.getAll();
            System.out.println("ok");
            for (int i = 0; i < trains.size(); i++) {
                Train train1 = trains.get(i);
                if (train1.getRoute().equals(routeBox.getValue()) && train1.getDeparture().equals(localDateTime)) {
                    train.setId(train1.getId());
                    reservation.setTrain(train1);
                    System.out.println("usao " + train1.getId());
                }
            }
            //reservation building
            reservation.setPrice(30);
            reservation.setUser(user);
            reservationManager.add(reservation);
            message1.setText("");
            message2.setText("You have been successfully booked train ticket!");

        }
        catch (TrainException e) {
            message1.setText(e.getMessage());
            System.out.println(e.getMessage());
            message2.setText("");
        }
    }
}
