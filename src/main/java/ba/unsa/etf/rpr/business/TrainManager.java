package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.exceptions.TrainException;

public class TrainManager {

    public Train update(Train cat) throws TrainException {
        return DaoFactory.trainDao().update(cat);
    }
}
