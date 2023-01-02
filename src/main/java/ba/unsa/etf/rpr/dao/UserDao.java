package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.TrainException;

import java.sql.ResultSet;
import java.util.Map;

/**
 * Dao interface for User domain bean
 *
 * @author Tajra Selimovic
 */
public interface UserDao extends Dao<User> {
    User row2object(ResultSet rs) throws TrainException;

    Map<String, Object> object2row(User object);

    boolean checkUsernamePassword(String usernameTextField, String passwordField) throws TrainException;
    boolean findUsername(String usernameField) throws TrainException;
    boolean isAdmin(String usernameField) throws TrainException;

}
