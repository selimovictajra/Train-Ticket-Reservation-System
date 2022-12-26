package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservation;

public class DaoFactory {
    private static final TrainDao trainDao = new TrainDaoSQLImpl();
    private static final UserDao userDao = new UserDaoSQLImpl();
    private static final ReservationDao reservationDao = new ReservationDaoSQLImpl();

    public DaoFactory() {
    }

    public static TrainDao trainDao() {
        return trainDao;
    }

    public static UserDao userDao() {
        return userDao;
    }

    public static ReservationDao reservationDao() {
        return reservationDao;
    }
}
