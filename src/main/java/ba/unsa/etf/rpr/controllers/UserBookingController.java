package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.TrainManager;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.TrainException;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UserBookingController {
    @FXML
    private ChoiceBox<String> routeBox;
    @FXML
    private ChoiceBox<String> dateBox;
    @FXML
    private ChoiceBox<String> timeBox;
    private List<String> routes = new ArrayList<String>();
    private List<String> dateL = new ArrayList<String>();
    private List<String> timeL = new ArrayList<String>();
    @FXML
    private Label userLabel;
    @FXML
    private Label message1;
    @FXML
    private Label message2;
    TrainManager trainManager = new TrainManager();

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
                            dateL.add(localDateTime.toLocalDate().toString());
                            timeL.add(localDateTime.toLocalTime().toString());
                        }
                    }
                    dateBox.getItems().addAll(dateL);
                    timeBox.getItems().addAll(timeL);
                }
                catch(Exception e) {
                    message1.setText(e.getMessage());
                    message2.setText("");
                }
            });
        }
        catch (Exception e) {
            message1.setText(e.getMessage());
            message2.setText("");
        }
    }

    public void bookButtonOnAction(javafx.event.ActionEvent actionEvent) {

    }
}
