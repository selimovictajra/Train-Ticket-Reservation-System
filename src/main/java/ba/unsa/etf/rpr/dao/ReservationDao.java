package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.exceptions.TrainException;
import java.util.List;

/**
 * Dao interface for Reservation domain bean
 *
 * @author Tajra Selimovic
 */
public interface ReservationDao extends Dao<Reservation> {
    /**
     * Fetches all reservations from database with given idUser
     * @param idUser int
     * @return List of Trains
     * @throws TrainException in case of problems with database
     */
    public List<Train> getByUser(int idUser) throws TrainException;
}
