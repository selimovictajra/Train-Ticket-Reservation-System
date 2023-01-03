package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.TrainException;

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
}
