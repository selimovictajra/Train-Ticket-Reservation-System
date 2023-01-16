package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.TrainManager;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.exceptions.TrainException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class AddTrainController {
    @FXML
    private TextField routeText;
    @FXML
    private DatePicker date;
    @FXML
    private ChoiceBox<Integer> hourBox;
    @FXML
    private ChoiceBox<Integer> minBox;
    @FXML
    private  ChoiceBox<Integer> priceBox;
    @FXML
    private Label message1;
    @FXML
    private Label message2;
    TrainManager trainManager = new TrainManager();
    private Integer[] hour = {5, 6, 7, 8, 9, 10};
    private Integer[] min = {0, 10, 20, 30, 40, 50};
    private Integer[] prices = {10, 15, 20, 25, 30};

    public void initialize() {
        hourBox.getItems().addAll(hour);
        minBox.getItems().addAll(min);
        priceBox.getItems().addAll(prices);
    }

    public void addButtonOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            trainManager.validateAddFields(routeText.getText(), date.toString(), hourBox.getValue(), minBox.getValue(), priceBox.getValue());
            trainManager.validateDuplicate(routeText.getText(), date.getValue().atTime(hourBox.getValue(), minBox.getValue()));
            LocalDate localDate = date.getValue();
            LocalDateTime localDateTime = localDate.atTime(hourBox.getValue(), minBox.getValue());
            Train train = new Train();
            train.setRoute(routeText.getText());
            train.setCapacity(100);
            train.setDeparture(localDateTime);
            train.setPrice(priceBox.getValue());
            trainManager.add(train);
            message1.setText("");
            message2.setText("You have been successfully added train route!");
        }
        catch(Exception e) {
            message1.setText(e.getMessage());
            message2.setText("");
        }
    }
    public void homeLinkOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/adminpanelhome.fxml")));
            Stage stage = (Stage) ((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void menuLinkOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/adminpanelmenu.fxml")));
            Stage stage = (Stage) ((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void deleteTrainLinkOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/deleteLink.fxml")));
            Stage stage = (Stage) ((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void editTrainLinkOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/editLink.fxml")));
            Stage stage = (Stage)((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception exception) {
            exception.printStackTrace();
            exception.getCause();
        }
    }
    public void logoutLinkOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/login.fxml")));
            Stage stage = (Stage)((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
