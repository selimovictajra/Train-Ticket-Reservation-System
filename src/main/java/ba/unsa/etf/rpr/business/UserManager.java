package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.TrainException;

import java.util.List;

/**
 * This is a Java class called "UserManager" that provides a set of methods for managing User objects.
 * @author Tajra Selimovic
 */

public class UserManager {
    /**
     * Deletes user from database table Users
     * @param id int
     * @return int
     * @throws TrainException in case of problem with database
     */
    public void delete(int id) throws TrainException {
        try{
            DaoFactory.userDao().delete(id);
        }
        catch (TrainException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new TrainException("NO");
            }
            throw e;
        }
    }

    /**
     * Updates user in database table Users
     * @param cat User
     * @return User
     * @throws TrainException in case of problems with database
     */
    public User update(User cat) throws TrainException {
        return DaoFactory.userDao().update(cat);
    }

    /**
     * Fetches all users from the database and stores them in the list of users
     * @return List of Users
     * @throws TrainException in case of problems with database
     */
    public List<User> getAll() throws TrainException {
        return DaoFactory.userDao().getAll();
    }

    /**
     * Adds User in database table Users
     * @param u User
     * @return User
     * @throws TrainException in case of problems with database
     */
    public User add(User u) throws TrainException {
        return DaoFactory.userDao().add(u);
    }

    /**
     * Fetches User with the given id
     * @param id Integer
     * @return User
     * @throws TrainException in case of problems with database
     */
    public User getById(Integer id) throws TrainException {
        return DaoFactory.userDao().getById(id);
    }

    /**
     * Checks if username and password are correct
     * @param usernameTextField String
     * @param passwordField String
     * @return Integer
     * @throws TrainException in case of problems with database
     */
    public Integer checkUsernamePassword(String usernameTextField, String passwordField) throws TrainException {
        return DaoFactory.userDao().checkUsernamePassword(usernameTextField,passwordField);
    }

    /**
     * Checks if user is admin or not
     * @param usernameField String
     * @return boolean
     * @throws TrainException in case of problems with database
     */
    public boolean isRole(String usernameField) throws TrainException {
        return DaoFactory.userDao().isRole(usernameField);
    }

    /**
     * Checks if username already exists
     * @param usernameField String
     * @return boolean
     * @throws TrainException in case of problems with database
     */
    public boolean findUsername(String usernameField) throws TrainException {
        return DaoFactory.userDao().findUsername(usernameField);
    }
}
