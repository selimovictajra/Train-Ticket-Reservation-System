package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.TrainException;

import java.sql.*;
import java.util.*;

/**
 * MySQL implementation of the DAO
 * @author Tajra Selimovic
 */
public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao {
    /**
     * Private constructor for the UserDaoSQLImpl class.
     */
    public UserDaoSQLImpl(){
        super("Users");
    }
    /**
     * Maps a row from the result set to a User object
     * @param rs The result set from the database query
     * @return A User object with properties set according to the values in the result set
     * @throws TrainException if there is an error when retrieving values from the result set
     */
    @Override
    public User row2object(ResultSet rs) throws TrainException {
        try {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setRole(rs.getBoolean("role"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        } catch (SQLException e) {
            throw new TrainException(e.getMessage(), e);
        }
    }

    /**
     * Takes in a User object and converts it into a Map of String keys and Object values.
     * @param object - a bean object for specific table
     * @return A Map<String, Object> containing the fields of the User object as key-value pairs.
     */
    @Override
    public Map<String, Object> object2row(User object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("id", object.getId());
        item.put("name", object.getName());
        item.put("role", object.isRole());
        item.put("username", object.getUsername());
        item.put("password", object.getPassword());
        return item;
    }
    /**
     * Checks if username already exists
     * @param usernameField String
     * @return boolean
     * @throws TrainException in case of problems with database
     */
    @Override
    public boolean findUsername(String usernameField) throws TrainException {
        String insert = "SELECT count(username) from Users where username='" + usernameField + "'";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) { // result set is iterator.
                return rs.getInt(1) != 0;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * Checks if user is admin or not
     * @param usernameField String
     * @return boolean
     * @throws TrainException in case of problems with database
     */
    @Override
    public boolean isRole(String usernameField) throws TrainException {
        String insert = "SELECT username from Users where role = 1";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) { // result set is iterator.
                if(rs.getString(1).equals(usernameField)) return true;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * Checks if username and password are correct
     * @param usernameTextField String
     * @param passwordField String
     * @return Integer
     * @throws TrainException in case of problems with database
     */
    @Override
    public Integer checkUsernamePassword(String usernameTextField, String passwordField) throws TrainException{
        String insert = "SELECT id from Users where username ='" + usernameTextField + "' AND password='"
                + passwordField + "'";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns the number of users from database
     * @return Integer
     * @throws TrainException in case of problems
     */
    @Override
    public int numberOfUsers() throws TrainException {
        String insert = "SELECT count(1) FROM Users";
        int nou = 0;
        try {
            PreparedStatement stmt = getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                nou = rs.getInt(1);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return nou;
    }

}
