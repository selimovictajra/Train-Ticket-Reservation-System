package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
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

    public void CancelButtonOnAction(ActionEvent ae) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void loginButtonOnAction(ActionEvent ae) throws TrainException, IOException {
        if (passwordField.getText().isEmpty() || usernameTextField.getText().isEmpty()) {
            loginMessage.setText("Invalid login. Please try again.");
        }
        /*else {
            UserDaoSQLImpl u = new UserDaoSQLImpl();
            if (u.checkUsernamePassword(usernameTextField.getText(),passwordField.getText())) {

            } else {
                loginMessage.setText("Invalid login. Please try again.");
            }
        }*/
    }

    public void registerButtonOnAction(ActionEvent ae) throws Exception {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/register.fxml")));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 600, 536));
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
            registerStage.show();
        }
        catch(Exception exception) {
            exception.printStackTrace();
            exception.getCause();
        }
    }
}

