package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.domain.User;

public class Model {
    private static Model instance;
    private Train train;
    private User user;
    private Reservation reservation;

    private Model() {}

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public Train getTrain() {
        return train;
    }
    public void setTrain(Train train) {
        this.train = train;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
