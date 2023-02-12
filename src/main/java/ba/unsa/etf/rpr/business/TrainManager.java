package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.exceptions.TrainException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This is a Java class called "TrainManager" that provides a set of methods for managing Train objects.
 * @author Tajra Selimovic
 */
public class TrainManager {
    /**
     * Default constructor
     */
    public TrainManager() {}
    /**
     * Updates train in database table Trains
     * @param cat Train
     * @return Train
     * @throws TrainException in case of problem with database
     */
    public Train update(Train cat) throws TrainException {
        return DaoFactory.trainDao().update(cat);
    }
    /**
     * Deletes train from database table Trains
     * @param trainId int
     * @throws TrainException in case of problem with database
     */
    public void delete(int trainId) throws TrainException {
        try{
            DaoFactory.trainDao().delete(trainId);
        }
        catch (TrainException e){
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new TrainException("Cannot delete this train route since tickets have already been reserved.");
            }
            throw e;
        }
    }
    /**
     * Fetches all trains from the database and stores them in the list of trains
     * @return List of Trains
     * @throws TrainException in case of problems with database
     */
    public List<Train> getAll() throws TrainException {
        return DaoFactory.trainDao().getAll();
    }

    /**
     * Validates the fields for adding a train.
     * @param route The route of the train.
     * @param datetime The datetime of the train's departure.
     * @param min The minutes of the train's departure time.
     * @param hour The hours of the train's departure time.
     * @param price The price of a ticket for the train.
     * @throws TrainException If any of the fields are empty.
     */
    public void validateAddFields(String route, String datetime, Integer min, Integer hour, Integer price) throws TrainException {
        if(route.isEmpty() || datetime.isEmpty() || min == null || hour == null || price == null) {
            throw new TrainException("All fields must be filled in!");
        }
    }
    /**
     * Validates the fields for booking a train.
     * @param route The route of the train to be booked.
     * @param date The date of the train to be booked.
     * @param time The time of the train to be booked.
     * @throws TrainException If any of the fields are empty.
     */
    public void validateBookFields(String route, String date, String time) throws TrainException {
        if(route.isEmpty() || date.isEmpty() || time.isEmpty()) {
            throw new TrainException("All fields must be filled in!");
        }
    }
    /**
     * Adds train in database table Trains
     * @param train Train
     * @return Train
     * @throws TrainException in case of problems with database
     */
    public Train add(Train train) throws TrainException {
        return DaoFactory.trainDao().add(train);
    }
    /**
     * Fetches Train with the given id
     * @param id Integer
     * @return Train
     * @throws TrainException in case of problems with database
     */
    public  Train getById(int id) throws TrainException {
        return DaoFactory.trainDao().getById(id);
    }

    /**
     * Checks if delete field is filled in
     * @param id Integer
     * @throws TrainException in case of problems with database
     */
    public void validateDeleteFields(Integer id) throws TrainException {
        if (id == null) throw new TrainException("Id field must be filled in!");
    }

    /**
     * Checks if train route already exists
     * @param route String
     * @param localDateTime LocalDateTime
     * @throws TrainException in case of problems with database
     */
    public void validateDuplicate(String route, LocalDateTime localDateTime) throws TrainException {
        try {
            List<Train> trains = DaoFactory.trainDao().getAll();
            for (Train train : trains) {
                if (train.getRoute().equals(route) && train.getDeparture().equals(localDateTime)) {
                    throw new TrainException("This train route already exists!");
                }
            }
        }
        catch (Exception e) {
            throw new TrainException(e.getMessage());
        }
    }

    /**
     * Checks if the given train route is valid.
     * @param route The train route to be checked.
     * @return true if the route is valid, false otherwise.
     * @throws TrainException in case of problems
     */
    public boolean checkTrainRoute(String route) throws TrainException {
        return DaoFactory.trainDao().checkTrainRoute(route);
    }
}
