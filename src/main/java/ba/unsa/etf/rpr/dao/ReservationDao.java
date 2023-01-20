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
    /**
     * Finds a Reservation for the given idTrain and idUser
     * @param idTrain The id of the train for which the reservation is being searched.
     * @param idUser The id of the user for which the reservation is being searched.
     * @return The Reservation object that matches the given train id and user id.
     * @throws TrainException If there is an error searching for the reservation or if the reservation does not exist.
     */
    public Reservation getByTrainId(int idTrain, int idUser) throws TrainException;
}
