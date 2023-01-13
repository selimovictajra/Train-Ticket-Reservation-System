package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.TrainException;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class TrainDaoSQLImpl extends AbstractDao<Train> implements TrainDao{
    public TrainDaoSQLImpl(){
        super("Trains");
    }

    @Override
    public Train row2object(ResultSet rs) throws TrainException {
        try {
            Train movie = new Train();
            movie.setId(rs.getInt("id"));
            movie.setRoute(rs.getString("route"));
            movie.setDeparture(rs.getTimestamp("departure").toLocalDateTime());
            movie.setCapacity(rs.getInt("capacity"));
            return movie;
        } catch (Exception e) {
            throw new TrainException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Train object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("route", object.getRoute());
        item.put("departure", object.getDeparture());
        item.put("capacity", object.getCapacity());
        return item;
    }

}
