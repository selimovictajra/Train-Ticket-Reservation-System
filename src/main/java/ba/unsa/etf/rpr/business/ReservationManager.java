package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.exceptions.TrainException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReservationManager {
    public void delete (int id) throws TrainException {
        try {
            DaoFactory.reservationDao().delete(id);
        }
        catch (TrainException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new TrainException("NO");
            }
            throw e;
        }
    }

    public Reservation update(Reservation cat) throws TrainException {
        return DaoFactory.reservationDao().update(cat);
    }

    public List<Reservation> getAll() throws TrainException {
        return DaoFactory.reservationDao().getAll();
    }

    public void validateBookFields(String route, LocalDate date, LocalTime time) throws TrainException {
        if(route.isEmpty() || date == null || time == null) {
            throw new TrainException("All fields must be filled in!");
        }
    }
    public Reservation add(Reservation r) throws TrainException {
        return DaoFactory.reservationDao().add(r);
    }
}
