package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exceptions.TrainException;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class ReservationDaoSQLImpl extends AbstractDao<Reservation> implements ReservationDao{
    public ReservationDaoSQLImpl(){
        super("Reservations");
    }

    @Override
    public Reservation row2object(ResultSet rs) throws TrainException {
        try {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getInt("id"));
            reservation.setUser(DaoFactory.userDao().getById(rs.getInt("user_id")));
            reservation.setTrain(DaoFactory.trainDao().getById(rs.getInt("train_id")));
            reservation.setPrice(rs.getInt("price"));
            return reservation;
        } catch (Exception e) {
            throw new TrainException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Reservation object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("id", object.getId());
        item.put("user_id", object.getUser().getId());
        item.put("train_id", object.getTrain().getId());
        item.put("price", object.getPrice());
        return item;
    }

}
