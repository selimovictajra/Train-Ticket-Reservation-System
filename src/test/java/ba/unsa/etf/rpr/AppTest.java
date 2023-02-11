package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.TrainManager;
import ba.unsa.etf.rpr.controllers.Model;
import ba.unsa.etf.rpr.dao.TrainDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.TrainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for simple App.
 * @author Tajra Selimovic
 */
public class AppTest {
    @Test
    public void checkTrainRouteTest1() {
        try {
            new TrainDaoSQLImpl().checkTrainRoute("hi");
            fail("Problem with 'checkTrainRoute' method.");
        }
        catch(TrainException e) {
            assertTrue(e.getMessage().contains("Invalid train route!"));
        }
    }

    @Test
    public void checkTrainRouteTest2() {
        try {
            assertTrue((new TrainDaoSQLImpl()).checkTrainRoute("City1 - City2"));
        }
        catch(TrainException e) {
            fail("Problem with 'checkTrainRoute' method.");
        }
    }

    @Test
    public void validateBookFieldsTest1() {
        try {
            TrainManager trainManager = new TrainManager();
            trainManager.validateBookFields("", "date", "time");
            fail("Problem with 'validateBookFields' method.");
        }
        catch (TrainException e) {
            assertTrue(e.getMessage().contains("All fields must be filled in!"));
        }
    }

    @Test
    public void validateBookFieldsTest2() {
        try {
            TrainManager trainManager = new TrainManager();
            trainManager.validateBookFields("One - Route", "16-10-2022", "04:10");
        }
        catch (TrainException e) {
            fail("Problem with 'validateBookFields' method.");
        }
    }

    @Test
    public void validateAddFields1() {
        try {
            TrainManager trainManager = new TrainManager();
            trainManager.validateAddFields("One - Route", "2-2-2022", null, 7, 25);
            fail("Problem with 'validateAddFields' method.");
        }
        catch (TrainException e) {
            assertTrue(e.getMessage().contains("All fields must be filled in!"));
        }
    }

    @Test
    public void validateAddFields2() {
        try {
            TrainManager trainManager = new TrainManager();
            trainManager.validateAddFields("One - Route", "2-2-2022", 10, 7, 25);
        }
        catch (TrainException e) {
            fail("Problem with 'validateAddFields' method.");
        }
    }

    @Test
    public void setAndGetTrain() {
        Model model = Model.getInstance();
        Train train = new Train();
        model.setTrain(train);
        assertSame(train, model.getTrain());
    }

    @Test
    public void setAndGetUser() {
        Model model = Model.getInstance();
        User user = new User();
        model.setUser(user);
        assertSame(user, model.getUser());
    }

    @Test
    public void setAndGetReservation() {
        Model model = Model.getInstance();
        Reservation reservation = new Reservation();
        model.setReservation(reservation);
        assertSame(reservation, model.getReservation());
    }
}
