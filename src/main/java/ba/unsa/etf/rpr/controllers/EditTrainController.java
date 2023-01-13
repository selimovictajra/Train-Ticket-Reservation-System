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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EditTrainController {
    @FXML
    private ChoiceBox<Integer> routeBox;
    @FXML
    private List<Integer> trainIds = new ArrayList<Integer>();
    @FXML
    private TextField routeText;
    @FXML
    private DatePicker date;
    @FXML
    private ChoiceBox<Integer> hourBox;
    @FXML
    private ChoiceBox<Integer> minBox;
    @FXML
    private Label message1;
    @FXML
    private Label message2;
    TrainManager trainManager = new TrainManager();
    private Integer[] hour = {5, 6, 7, 8, 9, 10};
    private Integer[] min = {0, 10, 20, 30, 40, 50};

    public void initialize() {
        try {
            List<Train> trains = trainManager.getAll();
            for (int i = 0; i < trains.size(); i++) {
                Train train = trains.get(i);
                trainIds.add(train.getId());
            }
            routeBox.getItems().addAll(trainIds);
            hourBox.getItems().addAll(hour);
            minBox.getItems().addAll(min);
            routeBox.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> {
                Train train1 = null;
                try {
                    train1 = trainManager.getById(newvalue);
                    routeText.setText(train1.getRoute());
                    LocalDateTime localDateTime = train1.getDeparture();
                    date.setValue(localDateTime.toLocalDate());
                    LocalTime localTime = localDateTime.toLocalTime();
                    hourBox.setValue(localTime.getHour());
                    minBox.setValue(localTime.getMinute());
                }
                catch (TrainException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void editButtonOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            trainManager.validateDeleteFields(routeBox.getValue());
            trainManager.validateAddFields(routeText.getText(), date.toString(), hourBox.getValue(), minBox.getValue());
            LocalDate localDate = date.getValue();
            LocalDateTime localDateTime = localDate.atTime(hourBox.getValue(), minBox.getValue());
            Train train = new Train();
            train.setCapacity(100);
            train.setRoute(routeText.getText());
            train.setDeparture(localDateTime);
            trainManager.add(train);
            message1.setText("");
            message2.setText("You have been successfully edited train route with id " + routeBox.getValue() + "!");
        }
        catch (Exception e) {
            message1.setText(e.getMessage());
            message2.setText("");
        }
    }

    public void homeLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
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
    public void menuLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
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
    public void deleteTrainLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
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
    public void addTrainLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/addLink.fxml")));
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
    public void logoutLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/login.fxml")));
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
}
