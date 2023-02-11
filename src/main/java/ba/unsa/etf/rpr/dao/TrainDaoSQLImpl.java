package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.TrainException;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

/**
 * MySQL implementation of the DAO
 * @author Tajra Selimovic
 */

public class TrainDaoSQLImpl extends AbstractDao<Train> implements TrainDao{
    /**
     * Private constructor for the TrainDaoSQLImpl class.
     */
    public TrainDaoSQLImpl(){
        super("Trains");
    }
    /**
     * Maps a row from the result set to a Train object
     * @param rs The result set from the database query
     * @return A Train object with properties set according to the values in the result set
     * @throws TrainException if there is an error when retrieving values from the result set
     */
    @Override
    public Train row2object(ResultSet rs) throws TrainException {
        try {
            Train train = new Train();
            train.setId(rs.getInt("id"));
            train.setRoute(rs.getString("route"));
            train.setDeparture(rs.getTimestamp("departure").toLocalDateTime());
            train.setCapacity(rs.getInt("capacity"));
            train.setPrice(rs.getInt("price"));
            return train;
        } catch (Exception e) {
            throw new TrainException(e.getMessage(), e);
        }
    }
    /**
     * Takes in a Train object and converts it into a Map of String keys and Object values.
     * @param object - a bean object for specific table
     * @return A Map<String, Object> containing the fields of the Train object as key-value pairs.
     */
    @Override
    public Map<String, Object> object2row(Train object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("route", object.getRoute());
        item.put("departure", object.getDeparture());
        item.put("capacity", object.getCapacity());
        item.put("price", object.getPrice());
        return item;
    }

    /**
     * Checks if the given train route is valid.
     * @param route The train route to be checked.
     * @return true if the route is valid, false otherwise.
     */
    @Override
    public boolean checkTrainRoute(String route) throws TrainException {
        if(route.contains(" - ") && route.length() >= 7) return true;
        else throw new TrainException("Invalid train route!");
    }

}
