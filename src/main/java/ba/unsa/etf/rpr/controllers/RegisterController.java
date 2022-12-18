package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class RegisterController {
    @FXML
    private Button closeButton;
    @FXML
    private TextField fullnameTextFiled;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField conpassField;
    @FXML
    private Label messageLabel1;
    @FXML
    private Label messageLabel2;
    @FXML
    private Label messageLabel3;
    @FXML
    private Label messageLabel4;


    public void closeButtonOnAction(ActionEvent ae) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

}
