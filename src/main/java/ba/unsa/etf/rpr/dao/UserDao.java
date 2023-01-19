package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.TrainException;

/**
 * Dao interface for User domain bean
 *
 * @author Tajra Selimovic
 */
public interface UserDao extends Dao<User> {
    /**
     * Checks if username and password are correct
     * @param usernameTextField String
     * @param passwordField String
     * @return Integer
     * @throws TrainException in case of problems with database
     */
    Integer checkUsernamePassword(String usernameTextField, String passwordField) throws TrainException;
    /**
     * Checks if username already exists
     * @param usernameField String
     * @return boolean
     * @throws TrainException in case of problems with database
     */
    boolean findUsername(String usernameField) throws TrainException;
    /**
     * Checks if user is admin or not
     * @param usernameField String
     * @return boolean
     * @throws TrainException in case of problems with database
     */
    boolean isRole(String usernameField) throws TrainException;
    /**
     * Returns the number of users from database
     * @return Integer
     * @throws TrainException in case of problems
     */
    int numberOfUsers() throws TrainException;

}
