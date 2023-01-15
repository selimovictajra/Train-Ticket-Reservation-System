package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.TrainException;

import java.util.List;

public class UserManager {

    public void delete(int categoryId) throws TrainException {
        try{
            DaoFactory.userDao().delete(categoryId);
        }
        catch (TrainException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new TrainException("NO");
            }
            throw e;
        }
    }

    public User update(User cat) throws TrainException {
        return DaoFactory.userDao().update(cat);
    }

    public List<User> getAll() throws TrainException {
        return DaoFactory.userDao().getAll();
    }
    public User add(User u) throws TrainException {
        return DaoFactory.userDao().add(u);
    }
    public User getById(Integer id) throws TrainException {
        return DaoFactory.userDao().getById(id);
    }
    public Integer checkUsernamePassword(String usernameTextField, String passwordField) throws TrainException {
        return DaoFactory.userDao().checkUsernamePassword(usernameTextField,passwordField);
    }
    public boolean isRole(String usernameField) throws TrainException {
        return DaoFactory.userDao().isRole(usernameField);
    }
    public boolean findUsername(String usernameField) throws TrainException {
        return DaoFactory.userDao().findUsername(usernameField);
    }
}
