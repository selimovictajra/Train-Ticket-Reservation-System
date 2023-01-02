package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exceptions.TrainException;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ReservationDaoSQLImpl extends AbstractDao<Reservation> implements ReservationDao{
    public ReservationDaoSQLImpl(){
        super("Reservations");
    }

    @Override
    public Reservation row2object(ResultSet rs) throws TrainException {
        try {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getInt("id"));
            reservation.setU(DaoFactory.userDao().getById(rs.getInt("user_id")));
            reservation.setT(DaoFactory.trainDao().getById(rs.getInt("train_id")));
            reservation.setPrice(rs.getInt("price"));
            return reservation;
        } catch (Exception e) {
            throw new TrainException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Reservation object) {
        return null;
    }

   /* @Override
    public Reservation getById(int id) {
        String query = "SELECT * FROM Reservations WHERE reservation_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Reservation reservation = new Reservation();
                reservation.setReservation_id(rs.getInt(1));
                reservation.setPrice(rs.getInt(2));
                reservation.setU(new UserDaoSQLImpl().getById(rs.getInt(3)));
                reservation.setT(new TrainDaoSQLImpl().getById(rs.getInt(4)));
                rs.close();
                return reservation;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    @Override
    public Reservation add(Reservation item) {
        String insert = "INSERT INTO Reservations(price, user_id, train_id) VALUES(?,?,?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, item.getPrice());
            stmt.setInt(2, item.getU().getUser_id());
            stmt.setInt(3, item.getT().getTrain_id());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setReservation_id(rs.getInt(1)); //set id to return it back
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reservation update(Reservation item) {
        String insert = "UPDATE Reservations SET price = ?, user_id= ?, movie_id = ? WHERE reservation_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getPrice());
            stmt.setObject(2, item.getU().getUser_id());
            stmt.setObject(3, item.getT().getTrain_id());
            stmt.setObject(4, item.getReservation_id());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        try{
            PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM Reservations WHERE reservation_id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM reservations");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Reservation reservation = new Reservation();
                reservation.setReservation_id(rs.getInt("reservation_id"));
                reservation.setPrice(rs.getInt("price"));
                reservation.setU(new UserDaoSQLImpl().getById(rs.getInt("user_id")));
                reservation.setT(new TrainDaoSQLImpl().getById(rs.getInt("train_id")));
                reservations.add(reservation);
            }
            rs.close();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return reservations;

    }*/

}
