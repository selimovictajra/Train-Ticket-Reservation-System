package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.TrainException;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao {
    public UserDaoSQLImpl(){
        super("Users");
    }
    @Override
    public User row2object(ResultSet rs) throws TrainException {
        try {
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setName(rs.getString("name"));
            user.setRole(rs.getBoolean("role"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        } catch (SQLException e) {
            throw new TrainException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(User object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("user_id", object.getId());
        item.put("name", object.getName());
        item.put("role", object.isRole());
        item.put("username", object.getUsername());
        item.put("password", object.getPassword());
        return item;
    }

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

    @Override
    public boolean checkUsernamePassword(String usernameTextField, String passwordField) throws TrainException{
        String insert = "SELECT count(1) from Users where username ='" + usernameTextField + "' AND password='"
                + passwordField + "'";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { // result set is iterator.
                return rs.getInt(1) == 1;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

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
