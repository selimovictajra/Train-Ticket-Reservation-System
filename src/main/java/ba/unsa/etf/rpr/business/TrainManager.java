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

    public void validateAddFields(String route, String datetime, Integer min, Integer hour, Integer price) throws TrainException {
        if(route.isEmpty() || datetime.isEmpty() || min == null || hour == null || price == null) {
            throw new TrainException("All fields must be filled in!");
        }
    }

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
     */
    public boolean checkTrainRoute(String route) throws TrainException {
        return DaoFactory.trainDao().checkTrainRoute(route);
    }
}
