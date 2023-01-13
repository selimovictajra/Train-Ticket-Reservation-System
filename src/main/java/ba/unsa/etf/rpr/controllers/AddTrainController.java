package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.TrainManager;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddTrainController {
    @FXML
    private TextField routeText;
    @FXML
    private DatePicker date;
    @FXML
    private ChoiceBox<Integer> hourBox;
    @FXML
    private ChoiceBox<Integer> minBox;
    @FXML
    private Label message1;
    @FXML
    private Label message2;
    TrainManager trainManager = new TrainManager();
    private Integer[] hour = {5, 6, 7, 8, 9, 10};
    private Integer[] min = {0, 10, 20, 30, 40, 50};

    public void initialize() {
        hourBox.getItems().addAll(hour);
        minBox.getItems().addAll(min);
    }
}
