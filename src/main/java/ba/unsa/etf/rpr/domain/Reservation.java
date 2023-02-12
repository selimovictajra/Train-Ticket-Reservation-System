package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Class that contains information about reservations made for train tickets.
 * @author Tajra Selimovic
 */
public class Reservation implements Idable {
    private int id;
    private User user;
    private Train train;

    /**
     * Default constructor
     */
    public Reservation() {}

    /**
     * Getter for Id
     * @return int
     */
    public int getId() {
        return id;
    }
    /**
     * Setter for Id
     * @param reservation_id int
     */
    public void setId(int reservation_id) {
        this.id = reservation_id;
    }
    /**
     * Getter for user
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter for User
     * @param u User
     */
    public void setUser(User u) {
        this.user = u;
    }
    /**
     * Getter for train
     * @return Train
     */
    public Train getTrain() {
        return train;
    }
    /**
     * Setter for Train
     * @param t Train
     */
    public void setTrain(Train t) {
        this.train = t;
    }

    @Override
    public String toString() {
        return "Reservations{" +
                "reservation_id=" + id +
                ", u=" + user +
                ", t=" + train +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, train);
    }
}
