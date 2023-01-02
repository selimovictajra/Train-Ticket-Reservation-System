package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.TrainException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;
import java.util.Objects;

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

    public void registerButtonOnAction(ActionEvent ae) throws TrainException {
        if (usernameTextField.getText().isEmpty() || passwordField.getText().isEmpty() || fullnameTextFiled.getText().isEmpty() || conpassField.getText().isEmpty()) {
            messageLabel1.setText("Please fill the empty fields.");
            if(usernameTextField.getText().isEmpty()) messageLabel4.setText("");
        }
        else{
            messageLabel1.setText("");
            UserDaoSQLImpl userDaoSQL = new UserDaoSQLImpl();
            boolean usernameFound = userDaoSQL.findUsername(usernameTextField.getText());
            if (usernameFound) {
                messageLabel4.setText("Username already taken.");
            } else {
                messageLabel4.setText("");
            }
            boolean password_ok = true;
            if (passwordField.getText().equals(conpassField.getText())) {
                password_ok = true;
                messageLabel3.setText("");
            }
            else {
                password_ok = false;
                messageLabel3.setText("Password does not match.");
            }
            if(!usernameFound && password_ok) {
                User user = new User();
                user.setUsername(usernameTextField.getText());
                user.setRole(false);
                user.setPassword(passwordField.getText());
                user.setName(fullnameTextFiled.getText());
                userDaoSQL.add(user);
                messageLabel2.setText("You have been successfully registered!");
            }
            else{
                messageLabel2.setText("");
            }
        }
    }

    public void loginButtonOnAction(ActionEvent ae) throws Exception {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/login.fxml")));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 520, 400));
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
            registerStage.show();
        }
        catch(Exception exception) {
            exception.printStackTrace();
            exception.getCause();
        }
    }

}
