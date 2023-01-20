package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.exceptions.TrainException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * This is a Java class called "ReservationManager" that provides a set of methods for managing Reservation objects.
 * @author Tajra Selimovic
 */
public class ReservationManager {
    /**
     * Deletes reservation from database table Reservations
     * @param id int
     * @throws TrainException in case of problem with database
     */
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
    /**
     * Updates reservation in database table Reservation
     * @param cat Reservation
     * @return Reservation
     * @throws TrainException in case of problems with database
     */
    public Reservation update(Reservation cat) throws TrainException {
        return DaoFactory.reservationDao().update(cat);
    }
    /**
     * Fetches all reservations from the database and stores them in the list of reservations
     * @return List of Reservations
     * @throws TrainException in case of problems with database
     */
    public List<Reservation> getAll() throws TrainException {
        return DaoFactory.reservationDao().getAll();
    }

    /**
     * Checks if book fields are filled in
     * @param route String
     * @param date LocalDate
     * @param time LocalTime
     * @throws TrainException in case of problems with database
     */
    public void validateBookFields(String route, LocalDate date, LocalTime time) throws TrainException {
        if(route.isEmpty() || date == null || time == null) {
            throw new TrainException("All fields must be filled in!");
        }
    }
    /**
     * Adds Reservation in database table Reservations
     * @param r Reservation
     * @return Reservation
     * @throws TrainException in case of problems with database
     */
    public Reservation add(Reservation r) throws TrainException {
        return DaoFactory.reservationDao().add(r);
    }

    /**
     * Fetches all reservations from database with given idUser
     * @param idUser int
     * @return List of Trains
     * @throws TrainException in case of problems with database
     */
    public List<Train> getByUser(int idUser) throws TrainException {
        return DaoFactory.reservationDao().getByUser(idUser);
    }
    /**
     * Finds a Reservation for the given idTrain and idUser
     * @param idTrain The id of the train for which the reservation is being searched.
     * @param idUser The id of the user for which the reservation is being searched.
     * @return The Reservation object that matches the given train id and user id.
     * @throws TrainException If there is an error searching for the reservation or if the reservation does not exist.
     */
    public Reservation getByTrainId(int idTrain, int idUser) throws TrainException {
        return DaoFactory.reservationDao().getByTrainId(idTrain, idUser);
    }
}
