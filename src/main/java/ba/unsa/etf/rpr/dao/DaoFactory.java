package ba.unsa.etf.rpr.dao;

/**
 * The DaoFactory class provides static methods to get instances of TrainDao, UserDao, and ReservationDao.
 * These instances are implemented using the SQL database, using the SQL implementations of each Dao.
 * @author Tajra Selimovic
 */


public class DaoFactory {
    /**
     * A singleton instance of the TrainDao class, which is used to access and manipulate train data in a database.
    */
    private static final TrainDao trainDao = new TrainDaoSQLImpl();
    /**
     * A singleton instance of the UserDao class, which is used to access and manipulate user data in a database.
     */
    private static final UserDao userDao = new UserDaoSQLImpl();
    /**
     * A singleton instance of the ReservationDao class, which is used to access and manipulate reservation data in a database.
     */
    private static final ReservationDao reservationDao = new ReservationDaoSQLImpl();

    /**
     * A private constructor to prevent instantiation of this class.
     */
    public DaoFactory() {
    }
    /**
     * Returns a singleton instance of the TrainDao class.
     * @return A singleton instance of the TrainDao class.
     */
    public static TrainDao trainDao() {
        return trainDao;
    }

    /**
     * Returns a singleton instance of the UserDao class.
     * @return A singleton instance of the UserDao class.
     */
    public static UserDao userDao() {
        return userDao;
    }
    /**
     * Returns a singleton instance of the ReservationDao class.
     * @return A singleton instance of the ReservationDao class.
     */
    public static ReservationDao reservationDao() {
        return reservationDao;
    }
}
