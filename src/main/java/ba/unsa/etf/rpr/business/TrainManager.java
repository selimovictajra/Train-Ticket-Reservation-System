package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.exceptions.TrainException;

import java.util.List;

public class TrainManager {

    public Train update(Train cat) throws TrainException {
        return DaoFactory.trainDao().update(cat);
    }

    public void delete(int movieId) throws TrainException {
        try{
            DaoFactory.trainDao().delete(movieId);
        }
        catch (TrainException e){
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new TrainException("Cannot delete this train since tickets have already been reserved.");
            }
            throw e;
        }
    }

    public List<Train> getAll() throws TrainException {
        return DaoFactory.trainDao().getAll();
    }

    public void validateAddFields(String name, String genre, String datetime, Integer duration,
                                  Integer min, Integer hour) throws TrainException {
        if(name.isEmpty() || genre.isEmpty() || datetime.isEmpty() || duration == null
                || min == null || hour == null)
            throw  new TrainException("To successfully perform an action, all fields must be filled in!");
    }

    public Train add(Train train) throws TrainException {
        return DaoFactory.trainDao().add(train);
    }

    public  Train getById(int id) throws TrainException {
        return DaoFactory.trainDao().getById(id);
    }
}
