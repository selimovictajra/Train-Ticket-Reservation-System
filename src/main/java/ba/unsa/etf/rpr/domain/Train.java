package ba.unsa.etf.rpr.domain;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Tajra Selimovic
 */
public class Train implements Idable {
    private int id;
    private String route;
    private LocalDateTime departure;
    private int capacity;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int train_id) {
        this.id = train_id;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Trains{" +
                "train_id=" + id +
                ", route='" + route + '\'' +
                ", departure=" + departure +
                ", capacity=" + capacity +
                ", price=" + price +
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
        return Objects.hash(id, route, departure, capacity, price);
    }
}
