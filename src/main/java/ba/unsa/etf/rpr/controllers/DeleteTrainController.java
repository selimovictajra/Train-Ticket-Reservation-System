package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.TrainManager;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class DeleteTrainController {
    @FXML
    private ChoiceBox<Integer> trainRoutes;
    @FXML
    private List<Integer> trainIds = new ArrayList<Integer>();
    private TrainManager trainManager = new TrainManager();
    private Label message;


}
