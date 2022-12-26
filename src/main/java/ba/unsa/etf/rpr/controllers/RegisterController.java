package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.User;
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

    public void registerButtonOnAction(ActionEvent ae) {
        if (fullnameTextFiled.getText().isEmpty() || usernameTextField.getText().isEmpty() ||
                passwordField.getText().isEmpty() || conpassField.getText().isEmpty()) {
            messageLabel1.setText("Please fill the empty fields.");
            messageLabel2.setText("");
            messageLabel3.setText("");
            messageLabel4.setText("");
        }
        else if (!(passwordField.getText().equals(conpassField.getText()))) {
            messageLabel3.setText("kikiriki.");
            messageLabel1.setText("");
            messageLabel2.setText("");
            messageLabel4.setText("");
        }
        else {
            String query = "SELECT count(user_id) FROM Users WHERE username = '" + usernameTextField.getText() + "'";
            boolean isusr = false;
            try {
                UserDaoSQLImpl user = new UserDaoSQLImpl();
                PreparedStatement ps = user.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    if (rs.getInt(1) == 0) {
                        isusr = true;
                        System.out.println(rs.getInt(1));
                    }
                    else isusr = false;
                }
                rs.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            if (!isusr) {
                messageLabel4.setText("Username already exists. Please try with another one.");
                messageLabel1.setText("");
                messageLabel2.setText("");
                messageLabel3.setText("");
            }
            else {
                User user = new User();
                user.setName(fullnameTextFiled.getText());
                user.setUsername(usernameTextField.getText());
                user.setPassword(passwordField.getText());
                user.setRole(false);
                UserDaoSQLImpl userd = new UserDaoSQLImpl();
                userd.add(user);
                messageLabel1.setText("");
                messageLabel2.setText("You have been registered successfully!");
                messageLabel3.setText("");
                messageLabel4.setText("");
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
