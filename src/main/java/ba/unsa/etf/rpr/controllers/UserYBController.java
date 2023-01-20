package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.TrainManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.controllers.components.ButtonCellFactory;
import ba.unsa.etf.rpr.dao.ReservationDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.exceptions.TrainException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.text.TabableView;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserYBController {
    @FXML
    Pane YBPane;
    @FXML
    public TableView<Train> bookingTable;
    @FXML
    public TableColumn<Train, String> routeColumn;
    @FXML
    public TableColumn<Train, LocalDateTime> departureColumn;
    @FXML
    public TableColumn<Train, Integer> priceColumn;
    @FXML
    public TableColumn<Train, Integer> ticketColumn;
    @FXML
    private Label userLabel;
    private Train train;
    private Reservation reservation;
    private final TrainManager trainManager = new TrainManager();
    private final ReservationManager reservationManager = new ReservationManager();

    public void initialize() {
        Model model = Model.getInstance();
        userLabel.setText(model.getUser().getName());
        routeColumn.setCellValueFactory(new PropertyValueFactory<>("route"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        ticketColumn.setCellValueFactory(new PropertyValueFactory<Train, Integer>("id"));
        ticketColumn.setCellFactory(new ButtonCellFactory(editEvent -> {
            int trainId = Integer.parseInt(((Button)editEvent.getSource()).getUserData().toString());
            viewButtonOnAction(trainId);
        }));
        refreshTrains();
    }

    void refreshTrains() {
        try {
            Model model = Model.getInstance();
            bookingTable.setItems(FXCollections.observableList(reservationManager.getByUser(model.getUser().getId())));
            bookingTable.refresh();
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void viewButtonOnAction(Integer trainId) {
        try {
            Model model = Model.getInstance();
            train = trainManager.getById(trainId);
            reservation = reservationManager.getByTrainId(train.getId(), model.getUser().getId());
            model.setTrain(train);
            model.setReservation(reservation);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/viewTicket.fxml"));
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(new Scene(loader.load()));
            stage.show();
            stage.setOnHiding(event -> {
                ((Stage)YBPane.getScene().getWindow()).show();
                refreshTrains();
            });

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
    public void homeLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/userPanelHome.fxml")));
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
    public void bookingLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/userPanelBooking.fxml")));
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
