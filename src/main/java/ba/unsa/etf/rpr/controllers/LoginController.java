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

import java.io.IOException;
import java.util.Objects;

/**
 * This class is a JavaFX controller for the login screen.
 * It contains methods for handling button events for the login, register, and cancel buttons.
 * @author Tajra Selimovic
 */
public class LoginController {
    /**
     * Default constructor
     */
    public LoginController() {}
    /**
     * A button for canceling the login process.
     */
    @FXML
    private Button cancelButton;
    /**
     * A label for displaying login error messages.
     */
    @FXML
    private Label loginMessage;
    /**
     * A text field for entering the username.
     */
    @FXML
    private TextField usernameTextField;
    /**
     * A password field for entering the password.
     */
    @FXML
    private PasswordField passwordField;
    /**
     * An instance of the UserManager class for checking the entered username and password.
     */
    private final UserManager userManager = new UserManager();

    /**
     * Handles the action for the cancel button.
     * Closes the current window.
     * @param ae the action event
     */
    public void CancelButtonOnAction(ActionEvent ae) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the action for the login button.
     * Checks the entered username and password against the userManager, and if they match, navigates to the appropriate user or admin panel.
     * @param ae the action event
     * @throws TrainException in case of problems
     * @throws IOException in case of problems
     */
    public void loginButtonOnAction(ActionEvent ae) throws TrainException, IOException {
        if (passwordField.getText().isEmpty() || usernameTextField.getText().isEmpty()) {
            loginMessage.setText("Invalid login. Please try again.");
        }
        else {
            Integer index;
            index = userManager.checkUsernamePassword(usernameTextField.getText(), passwordField.getText());
            if (index != null) {
                User user = userManager.getById(index);
                Model model = Model.getInstance();
                model.setUser(user);
                if (userManager.isRole(usernameTextField.getText())) {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/adminpanelhome.fxml")));
                    Stage stage = (Stage)((javafx.scene.Node)ae.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/userPanelHome.fxml")));
                    Stage stage = (Stage)((javafx.scene.Node)ae.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            }
            else {
                loginMessage.setText("Invalid login. Please try again.");
            }
        }
    }
    /**
     * Handles the action for the register button.
     * Navigates to the registration page.
     * @param ae the action event
     * @throws Exception in case of problems
     */
    public void registerButtonOnAction(ActionEvent ae) throws Exception {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/register.fxml")));
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

