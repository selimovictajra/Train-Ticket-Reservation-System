package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.domain.User;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TrainDaoSQLImpl implements TrainDao{
    private Connection connection;
    public TrainDaoSQLImpl(){
        try {
            FileReader reader = new FileReader("src//main//resources//db.properties");
            Properties p = new Properties();
            p.load(reader);
            String s1=p.getProperty("user");
            String s2=p.getProperty("password");
            String s3=p.getProperty("url");
            this.connection = DriverManager.getConnection(s3, s1, s2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Train getById(int id) {
        String query = "SELECT * FROM Trains WHERE train_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Train train = new Train();
                train.setTrain_id(rs.getInt("train_id"));
                train.setRoute(rs.getString("route"));
                train.setDeparture(rs.getTimestamp("departure"));
                train.setCapacity(rs.getInt("capacity"));
                rs.close();
                return train;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    @Override
    public Train add(Train item) {
        String insert = "INSERT INTO Trains(route,departure,capacity) VALUES(?,?,?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getRoute());
            stmt.setTimestamp(2, item.getDeparture());
            stmt.setInt(3, item.getCapacity());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setTrain_id(rs.getInt(1)); //set id to return it back
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Train update(Train item) {
        String insert = "UPDATE Trains SET route = ?,departure = ?,capacity = ? WHERE train_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getRoute());
            stmt.setObject(2, item.getDeparture());
            stmt.setObject(3, item.getCapacity());
            stmt.setObject(4, item.getTrain_id());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM Trains WHERE train_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Train> getAll() {
        String query = "SELECT * FROM movies";
        List<Train> trains= new ArrayList<>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Train train = new Train();
                train.setTrain_id(rs.getInt("train_id"));
                train.setRoute(rs.getString("route"));
                train.setDeparture(rs.getTimestamp("departure"));
                train.setCapacity(rs.getInt("capacity"));
                trains.add(train);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return trains;
    }
}
