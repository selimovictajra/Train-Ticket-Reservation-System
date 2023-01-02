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

    boolean checkUsernamePassword(String usernameTextField, String passwordField) throws TrainException;
    boolean findUsername(String usernameField) throws TrainException;
    boolean isRole(String usernameField) throws TrainException;

}
