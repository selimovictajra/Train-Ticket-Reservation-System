package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.TrainException;
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
import java.util.Objects;

/**
 * This class is a JavaFX controller for the registration screen.
 * It contains methods for handling button events for the register, login, and close buttons.
 * It also has methods for checking the entered registration information and adding a new user to the database.
 * @author Tajra Selimovic
 */
public class RegisterController {
    private final UserManager userManager = new UserManager();
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

    /**
     * Handles the action for the close button. Closes the current window.
     * @param ae the action event
     */
    public void closeButtonOnAction(ActionEvent ae) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the action for the register button.
     * Checks the entered registration information and adds a new user to the database.
     * @param ae the action event
     * @throws TrainException in case of problems
     */
    public void registerButtonOnAction(ActionEvent ae) throws TrainException {
        if (usernameTextField.getText().isEmpty() || passwordField.getText().isEmpty() || fullnameTextFiled.getText().isEmpty() || conpassField.getText().isEmpty()) {
            messageLabel1.setText("Please fill the empty fields.");
            if(usernameTextField.getText().isEmpty()) messageLabel4.setText("");
        }
        else{
            messageLabel1.setText("");
            boolean usernameFound = userManager.findUsername(usernameTextField.getText());
            if (usernameFound) {
                messageLabel4.setText("Username already taken.");
            } else {
                messageLabel4.setText("");
            }
            boolean password_ok = true;
            if (passwordField.getText().equals(conpassField.getText())) {
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
                userManager.add(user);
                messageLabel2.setText("You have been successfully registered!");
            }
            else{
                messageLabel2.setText("");
            }
        }
    }
    /**
     * Handles the action for the login button.
     * Navigates to the login page.
     * @param ae the action event
     * @throws Exception in case of problems
     */
    public void loginButtonOnAction(ActionEvent ae) throws Exception {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/login.fxml")));
            Stage stage = (Stage)((javafx.scene.Node)ae.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception exception) {
            exception.printStackTrace();
            exception.getCause();
        }
    }

}
