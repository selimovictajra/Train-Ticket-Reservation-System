package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 *
 * @author Tajra Selimovic
 */
public class Reservation implements Idable {
    private int id;
    private int price;
    private User u;
    private Train t;

    public int getId() {
        return id;
    }

    public void setId(int reservation_id) {
        this.id = reservation_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public Train getT() {
        return t;
    }

    public void setT(Train t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "Reservations{" +
                "reservation_id=" + id +
                ", price=" + price +
                ", u=" + u +
                ", t=" + t +
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
        return Objects.hash(id, price, u, t);
    }
}
