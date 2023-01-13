package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditTrainController {
    @FXML
    private ChoiceBox<Integer> routeBox;
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
}
