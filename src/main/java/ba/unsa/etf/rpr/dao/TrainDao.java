package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.exceptions.TrainException;

/**
 * Dao interface for Train domain bean
 *
 * @author Tajra Selimovic
 */
public interface TrainDao extends Dao<Train> {
   /**
    * Checks if the given train route is valid.
    * @param route The train route to be checked.
    * @return true if the route is valid, false otherwise.
    */
    boolean checkTrainRoute(String route) throws TrainException;
}
