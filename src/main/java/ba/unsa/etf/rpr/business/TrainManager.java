package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.exceptions.TrainException;

import java.util.List;

public class TrainManager {

    public Train update(Train cat) throws TrainException {
        return DaoFactory.trainDao().update(cat);
    }

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

    public Train add(Train train) throws TrainException {
        return DaoFactory.trainDao().add(train);
    }

    public  Train getById(int id) throws TrainException {
        return DaoFactory.trainDao().getById(id);
    }

    public void validateDeleteFields(Integer id) throws TrainException {
        if (id == null) throw new TrainException("Id field must be filled in!");
    }
}
