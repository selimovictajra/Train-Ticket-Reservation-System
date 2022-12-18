package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
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
    }

    public void loginButtonOnAction(ActionEvent ae) {
        if (passwordField.getText().isEmpty() && usernameTextField.getText().isEmpty()) {
            loginMessage.setText("Invalid login. Please try again.");
        }
        else validateLogin();
    }

    public void validateLogin() {
        String str = "SELECT count(1) FROM Users WHERE username = '"
                + usernameTextField.getText() + "' AND password = '" + passwordField.getText() + "'";
        try {
            UserDaoSQLImpl user = new UserDaoSQLImpl();
            PreparedStatement ps = user.getConnection().prepareStatement(str, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                if (rs.getInt(1) == 1) loginMessage.setText("");
                else loginMessage.setText("Invalid Login. Please try again.");
            }
            rs.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerButtonOnAction(ActionEvent ae) throws Exception {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/register.fxml")));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 600, 536));
            registerStage.show();
        }
        catch(Exception exception) {
            exception.printStackTrace();
            exception.getCause();
        }
    }
}

