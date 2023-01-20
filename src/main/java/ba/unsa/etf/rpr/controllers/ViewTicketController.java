package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;

import java.time.LocalDateTime;
import javafx.scene.control.Label;

public class ViewTicketController {
    @FXML
    public Label trainIdLabel;
    @FXML
    public Label departureLabel;
    @FXML
    public Label passengerNameLabel;
    @FXML
    public Label passengerNameLabel1;
    @FXML
    public Label routeLabel;
    @FXML
    public Label routeLabel1;
    @FXML
    public Label dateLabel;
    @FXML
    public Label dateLabel1;
    @FXML
    public Label ticketLabel;
    @FXML
    public Label ticketLabel1;

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
