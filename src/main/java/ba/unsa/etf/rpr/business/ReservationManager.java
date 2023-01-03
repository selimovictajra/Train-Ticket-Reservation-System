package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exceptions.TrainException;

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
}
