package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.TrainManager;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.exceptions.TrainException;
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

    @FXML
    public void initialize() throws TrainException {
        List<Train> list = trainManager.getAll();
        for (int i = 0; i < list.size(); i++) {
            Train train = list.get(i);
            trainIds.add(train.getId());
        }
        trainRoutes.getItems().addAll(trainIds);
    }
}
