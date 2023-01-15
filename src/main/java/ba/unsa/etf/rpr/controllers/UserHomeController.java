package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;

import java.awt.*;

public class UserHomeController {
    @FXML
    private Label userLabel;

    public void initialize() {
        Model model = Model.getInstance();
        userLabel.setText(model.getUser().getName());
    }
}
