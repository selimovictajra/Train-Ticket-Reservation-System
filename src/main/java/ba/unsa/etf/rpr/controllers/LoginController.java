package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
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

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class LoginController {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessage;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    private UserManager userManager = new UserManager();

    public void CancelButtonOnAction(ActionEvent ae) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction(ActionEvent ae) throws TrainException, IOException {
        if (passwordField.getText().isEmpty() || usernameTextField.getText().isEmpty()) {
            loginMessage.setText("Invalid login. Please try again.");
        }
        else {
            Integer index = userManager.checkUsernamePassword(usernameTextField.getText(), passwordField.getText());
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

