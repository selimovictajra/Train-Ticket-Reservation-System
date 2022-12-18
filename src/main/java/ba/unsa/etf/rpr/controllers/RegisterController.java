package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RegisterController {
    @FXML
    private Button closeButton;

    public void closeButtonOnAction(ActionEvent ae) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
