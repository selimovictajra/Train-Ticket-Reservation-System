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
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setRole(rs.getBoolean("admin"));
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
        item.put("id", object.getId());
        item.put("name", object.getName());
        item.put("admin", object.isRole());
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
        String insert = "SELECT username from Users where role=1";
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
        String insert = "SELECT count(1) from Users where username='" + usernameTextField + "' AND password='"
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




    /*@Override
    public User getById(int id) {
        String query = "SELECT * FROM Users WHERE user_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getBoolean("role"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                rs.close();
                return user;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }
    @Override
    public User add(User item) {
        String insert = "INSERT INTO Users(name,role,username,password) VALUES(?,?,?,?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getName());
            stmt.setBoolean(2, item.isRole());
            stmt.setString(3, item.getUsername());
            stmt.setString(4, item.getPassword());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setUser_id(rs.getInt(1)); //set id to return it back
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(User item) {
        String insert = "UPDATE Users SET name = ?, role = ?, username = ?, password = ? WHERE user_id = ? ";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getName());
            stmt.setObject(2, item.isRole());
            stmt.setObject(3, item.getUsername());
            stmt.setObject(4, item.getPassword());
            stmt.setObject(5, item.getUser_id());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void delete(int id) {
        String insert = "DELETE FROM Users WHERE user_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users";
        List<User> users= new ArrayList<>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getBoolean("role"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return users;
    }
*/
}
