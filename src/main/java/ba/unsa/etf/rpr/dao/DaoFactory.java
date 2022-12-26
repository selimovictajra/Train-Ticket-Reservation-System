package ba.unsa.etf.rpr.dao;

public class DaoFactory {
    private static final TrainDao trainDao = new TrainDaoSQLImpl();

    public DaoFactory() {
    }

    public static TrainDao trainDao() {
        return trainDao;
    }
}
