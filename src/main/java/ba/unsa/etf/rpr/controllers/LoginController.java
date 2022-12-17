package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Button cancelButton;

    public void CancelButtonOnAction(ActionEvent ae) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}

