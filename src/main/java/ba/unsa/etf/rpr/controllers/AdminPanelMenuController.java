package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.TrainManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.exceptions.TrainException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Objects;

public class AdminPanelMenuController {
    private final TrainManager trainManager = new TrainManager();
    private final UserManager userManager = new UserManager();
    private final ReservationManager reservationManager = new ReservationManager();

    @FXML
    BorderPane adminPane;

    @FXML
    public TableView<Train> trainTable;
    @FXML
    public TableColumn<Train, String> trainIdColumn;
    @FXML
    public TableColumn<Train, String> routeColumn;
    @FXML
    public TableColumn<Train, LocalDate> departureColumn;
    @FXML
    public TableColumn<Train, String> capacityColumn;

    @FXML
    public TableView<Train> usersTable;
    @FXML
    public TableColumn<Train, String> userIdColumn;
    @FXML
    public TableColumn<Train, String> nameColumn;
    @FXML
    public TableColumn<Train, String> usernameColumn;
    @FXML
    public TableColumn<Train, String> passwordColumn;

    @FXML
    public void initialize() {
        trainIdColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("id"));
        routeColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("route"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<Train, LocalDate>("departure"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("capacity"));
        refreshTrains();
    }

    void refreshTrains() {
        try {
            trainTable.setItems(FXCollections.observableList(trainManager.getAll()));
            trainTable.refresh();
        } catch (TrainException e) {
            e.printStackTrace();
        }
    }

    void refreshUsers() {
        try {
            usersTable.setItems(FXCollections.observableList(trainManager.getAll()));
            usersTable.refresh();
        } catch (TrainException e) {
            e.printStackTrace();
        }
    }

    public void homeLinkOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/adminpanelhome.fxml")));
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
        catch(Exception exception) {
            exception.printStackTrace();
            exception.getCause();
        }
    }
}
