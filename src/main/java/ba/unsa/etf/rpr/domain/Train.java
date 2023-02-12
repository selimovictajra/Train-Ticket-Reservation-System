package ba.unsa.etf.rpr.domain;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class that contains information about trains made for train tickets.
 * @author Tajra Selimovic
 */
public class Train implements Idable {
    private int id;
    private String route;
    private LocalDateTime departure;
    private int capacity;
    private int price;

    /**
     * Default constructor
     */
    public Train() {}

    /**
     * Getter for Id
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id
     * @param train_id int
     */
    public void setId(int train_id) {
        this.id = train_id;
    }

    /**
     * Getter for route
     * @return String
     */
    public String getRoute() {
        return route;
    }

    /**
     * Setter for route
     * @param route String
     */
    public void setRoute(String route) {
        this.route = route;
    }

    /**
     * Getter for departure
     * @return LocalDateTime
     */
    public LocalDateTime getDeparture() {
        return departure;
    }

    /**
     * Setter for departure
     * @param departure LocalDateTime
     */
    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    /**
     * Getter for capacity
     * @return int
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Setter for capacity
     * @param capacity int
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Getter for price
     * @return int
     */
    public int getPrice() {
        return price;
    }

    /**
     * Setter for price
     * @param price int
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Converts Train to String
     * @return String
     */
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
