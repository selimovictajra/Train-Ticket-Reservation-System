package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.TrainManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.TrainException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.Objects;

/**
 * This class serves as a controller for the AdminPanelMenu.fxml file. It manages the functionality of the admin panel menu,
 * including displaying and refreshing data for the train, user, and reservation tables.
 *
 * @author Tajra Selimovic
 */

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
    public TableColumn<Train, String> priceColumn;

    @FXML
    public TableView<User> usersTable;
    @FXML
    public TableColumn<User, String> userIdColumn;
    @FXML
    public TableColumn<User, String> nameColumn;
    @FXML
    public TableColumn<User, String> usernameColumn;
    @FXML
    public TableColumn<User, String> passwordColumn;

    @FXML
    public TableView<Reservation> reservationTable;
    @FXML
    public TableColumn<Reservation, String> ridColumn;
    @FXML
    public TableColumn<Reservation, String> trainColumn;
    @FXML
    public TableColumn<Reservation, String> userColumn;

    /**
     * The initialize method is responsible for initializing the TableViews for trains, users, and reservations, as well as
     * setting the cell value factories for each column in the TableViews.
     */
    @FXML
    public void initialize() {
        trainIdColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("id"));
        routeColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("route"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<Train, LocalDate>("departure"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("capacity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("price"));
        refreshTrains();
        userIdColumn.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        refreshUsers();
        ridColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("id"));
        trainColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("train"));
        userColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("user"));
        refreshReservations();
    }

    /**
     * The method refreshTrains is used to refresh the trainTable with the updated data from the trainManager.
     */
    void refreshTrains() {
        try {
            trainTable.setItems(FXCollections.observableList(trainManager.getAll()));
            trainTable.refresh();
        } catch (TrainException e) {
            e.printStackTrace();
        }
    }
    /**
     * The method refreshUsers is used to refresh the userTable with the updated data from the userManager.
     */
    void refreshUsers() {
        try {
            usersTable.setItems(FXCollections.observableList(userManager.getAll()));
            usersTable.refresh();
        } catch (TrainException e) {
            e.printStackTrace();
        }
    }
    /**
     * The method refreshReservations is used to refresh the reservationTable with the updated data from the reservationManager.
     */
    void refreshReservations() {
        try {
            reservationTable.setItems(FXCollections.observableList(reservationManager.getAll()));
            reservationTable.refresh();
        } catch (TrainException e) {
            e.printStackTrace();
        }
    }
    /**
     * The homeTrainLinkOnAction() method is called when the user clicks the "Home" link.
     * Switches the user to the home window.
     * If any exception is thrown during the execution, it will be caught and the error message will be printed to the console.
     * @param actionEvent the event that triggers the action
     */
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
    /**
     * The deleteTrainLinkOnAction() method is called when the user clicks the "Delete" link.
     * Switches the user to the delete Train window.
     * If any exception is thrown during the execution, it will be caught and the error message will be printed to the console.
     * @param actionEvent the event that triggers the action
     */
    public void deleteTrainOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/deleteLink.fxml")));
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
    /**
     * The addTrainLinkOnAction() method is called when the user clicks the "Add" link.
     * Switches the user to the add Train window.
     * If any exception is thrown during the execution, it will be caught and the error message will be printed to the console.
     * @param actionEvent the event that triggers the action
     */
    public void addTrainLinkOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/addLink.fxml")));
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
    /**
     * The editTrainLinkOnAction() method is called when the user clicks the "Edit" link.
     * Switches the user to the edit Train window.
     * If any exception is thrown during the execution, it will be caught and the error message will be printed to the console.
     * @param actionEvent the event that triggers the action
     */
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
    /**
     * The logoutLinkOnAction() method is called when the user clicks the "Logout" link.
     * Switches the user to the login window.
     * If any exception is thrown during the execution, it will be caught and the error message will be printed to the console.
     * @param actionEvent the event that triggers the action
     */
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
