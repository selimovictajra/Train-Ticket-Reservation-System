package ba.unsa.etf.rpr.domain;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author Tajra Selimovic
 */
public class Trains {
    private int train_id;
    private String route;
    private Timestamp departure;
    private int capacity;

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Timestamp getDeparture() {
        return departure;
    }

    public void setDeparture(Timestamp departure) {
        this.departure = departure;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Trains{" +
                "train_id=" + train_id +
                ", route='" + route + '\'' +
                ", departure=" + departure +
                ", capacity=" + capacity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trains trains = (Trains) o;
        return train_id == trains.train_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(train_id, route, departure, capacity);
    }
}
