package ba.unsa.etf.rpr.dao;

public class DaoFactory {
    private static final TrainDao trainDao = new TrainDaoSQLImpl();
    private static final UserDao userDao = new UserDaoSQLImpl();

    public DaoFactory() {
    }

    public static TrainDao trainDao() {
        return trainDao;
    }

    public static UserDao userDao() {
        return userDao;
    }
}
