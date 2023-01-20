package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.domain.User;

/**
 * This class is a Singleton model that holds the current train, user, and reservation data.
 * @author Tajra Selimovic
 */


public class Model {
    /**
     * A private static instance of the Model class.
     */
    private static Model instance;
    /**
     * The current train data.
     */
    private Train train;
    /**
     * The current user data.
     */
    private User user;
    /**
     * The current reservation data.
     */
    private Reservation reservation;

    /**
     * A private constructor for the Model class.
     */
    private Model() {}

    /**
     * A public static method for getting the instance of the Model class.
     * @return the instance of the Model class
     */
    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    /**
     * A getter method for the current train data.
     * @return the current train data
     */
    public Train getTrain() {
        return train;
    }

    /**
     * A setter method for the current train data.
     * @param train the train data to set
     */
    public void setTrain(Train train) {
        this.train = train;
    }

    /**
     * A getter method for the current user data.
     * @return the current user data
     */
    public User getUser() {
        return user;
    }

    /**
     * A setter method for the current user data.
     * @param user the user data to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * A getter method for the current reservation data.
     * @return the current reservation data
     */
    public Reservation getReservation() {
        return reservation;
    }

    /**
     * A setter method for the current reservation data.
     * @param reservation the reservation data to set
     */
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
