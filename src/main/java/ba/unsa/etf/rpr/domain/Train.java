package ba.unsa.etf.rpr.domain;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author Tajra Selimovic
 */
public class Train {
    private int id;
    private String route;
    private Timestamp departure;
    private int capacity;

    public int getTrain_id() {
        return id;
    }

    public void setTrain_id(int train_id) {
        this.id = train_id;
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
                "train_id=" + id +
                ", route='" + route + '\'' +
                ", departure=" + departure +
                ", capacity=" + capacity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train trains = (Train) o;
        return id == trains.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, route, departure, capacity);
    }
}
