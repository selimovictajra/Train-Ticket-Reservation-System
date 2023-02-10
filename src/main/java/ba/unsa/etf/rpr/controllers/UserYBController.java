package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.TrainManager;
import ba.unsa.etf.rpr.controllers.components.ButtonCellFactory;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.exceptions.TrainException;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is a JavaFX controller for the user's "Your Bookings" screen.
 * It contains methods for displaying the user's booked trains in a table, handling button events for the logout, home, and bookings links, and refreshing the table of trains.
 * It also has a method for initializing the user's name on the screen.
 * @author Tajra Selimovic
 */
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
    private final TrainManager trainManager = new TrainManager();
    private final ReservationManager reservationManager = new ReservationManager();
    private final List<Stage> stageList = new ArrayList<>();

    /**
     * Initializes the user's name on the screen and sets the cell value factories and cell factories for the table columns.
     * Also calls the refreshTrains method to update the table with the user's booked trains.
     */
    public void initialize() {
        Model model = Model.getInstance();
        userLabel.setText(model.getUser().getName());
        routeColumn.setCellValueFactory(new PropertyValueFactory<>("route"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        ticketColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ticketColumn.setCellFactory(new ButtonCellFactory(editEvent -> {
            int trainId = Integer.parseInt(((Button)editEvent.getSource()).getUserData().toString());
            viewButtonOnAction(trainId);
        }));
        refreshTrains();
    }

    /**
     * Refreshes the table of trains by getting the reservations made by the logged-in user and updating the table's items.
     */
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

    /**
     * Handles the action for the view button.
     * Opens the "View Ticket" screen and sets the selected train and reservation in the model.
     * @param trainId the id of the selected train
     */
    public void viewButtonOnAction(Integer trainId) {
        try {
            Model model = Model.getInstance();
            Train train = trainManager.getById(trainId);
            Reservation reservation = reservationManager.getByTrainId(train.getId(), model.getUser().getId());
            model.setTrain(train);
            model.setReservation(reservation);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/viewTicket.fxml"));
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(new Scene(loader.load()));
            stage.show();
            stageList.add(stage);
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
    /**
     * Handles the action for the logout link.
     * Navigates to the login page.
     * @param actionEvent the action event
     * @throws TrainException in case of problems
     */
    public void logoutLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            for(Stage s : stageList)  s.close();
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
    /**
     * Handles the action for the home link.
     * Navigates to the Home page.
     * @param actionEvent the action event
     * @throws TrainException in case of problems
     */
    public void homeLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            for(Stage s : stageList)  s.close();
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
    /**
     * Handles the action for the booking link.
     * Navigates to the Booking page.
     * @param actionEvent the action event
     * @throws TrainException in case of problems
     */
    public void bookingLinkOnAction(javafx.event.ActionEvent actionEvent) throws TrainException {
        try {
            for(Stage s : stageList)  s.close();
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
