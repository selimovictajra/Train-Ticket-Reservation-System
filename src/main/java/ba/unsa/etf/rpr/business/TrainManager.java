package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.exceptions.TrainException;

public class TrainManager {

    public Train update(Train cat) throws TrainException {
        return DaoFactory.trainDao().update(cat);
    }

    public void delete(int movieId) throws TrainException{
        try{
            DaoFactory.trainDao().delete(movieId);
        }
        catch (TrainException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new TrainException("Cannot delete this train since tickets have already been reserved.");
            }
            throw e;
        }
    }
}
