package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.TrainManager;
import ba.unsa.etf.rpr.business.UserManager;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.text.TabableView;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserYBController {
    private final ReservationManager reservationManager = new ReservationManager();
    @FXML
    public TableView<Train> bookingTable;
    @FXML
    public TableColumn<Train, String> routeColumn;
    @FXML
    public TableColumn<Train, LocalDateTime> departureColumn;
    @FXML
    public TableColumn<Train, Integer> priceColumn;
    @FXML
    private Label userLabel;

    public void initialize() {
        try {
            Model model = Model.getInstance();
            userLabel.setText(model.getUser().getName());
            routeColumn.setCellValueFactory(new PropertyValueFactory<>("route"));
            departureColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            bookingTable.setItems(FXCollections.observableList(reservationManager.getByUser(model.getUser().getId())));
            bookingTable.refresh();
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
