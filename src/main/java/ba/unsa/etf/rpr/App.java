package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.TrainManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Train;
import ba.unsa.etf.rpr.exceptions.TrainException;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tajra Selimovic
 * CLI (Command Line Interface) implementation in following class
 * Even though this type of presentation layer (called CLI) is becoming past tense for GUI apps
 * it's good to see how you can manipulate data through command line and database also
 *
 */
public class App {
    /**
     * Defining final variables to describe all code having options
     */
    private static final Option addTrain = new Option("add","add-train",false, "Adding new train route to database");
    private static final Option editTrain = new Option("edit","edit-train",false, "Editing new train route to database");
    private static final Option deleteTrain = new Option("delete","delete-train",false, "Deleting new train route to database");
    private static final Option getTrain = new Option("getT", "get-trains",false, "Printing all train routes from database");
    private static final Option getUser = new Option("getU", "get-users",false, "Printing all users from database");
    private static final Option getReservation = new Option("getR", "get-reservations",false, "Printing all reservations from database");




    /**
     * Prints the formatted options to the standard output.
     * @param options The command line options to be printed
     */
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar rpr_poject.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    /**
     * Adds command line options to an 'Options' object.
     * @return The 'Options' object with the added options
     */
    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addTrain);
        options.addOption(editTrain);
        options.addOption(deleteTrain);
        options.addOption(getTrain);
        options.addOption(getUser);
        options.addOption(getReservation);
        return options;
    }


    /**
     * The main method of the program which implements the functionality of adding, editing, deleting, and retrieving
     * trains, users and reservations.
     * @param args the command line arguments
     * @throws Exception if an error occurs while parsing the command line arguments
     */

    public static void main(String[] args) throws Exception {
        Options options = addOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine cl = commandLineParser.parse(options, args);

        if (cl.hasOption(addTrain.getOpt())) {
            try {
                TrainManager trainManager = new TrainManager();
                Train train = new Train();
                if (trainManager.checkTrainRoute(cl.getArgList().get(0))) {
                    train.setRoute(cl.getArgList().get(0));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    train.setDeparture(LocalDateTime.parse(cl.getArgList().get(1), formatter));
                    train.setCapacity(Integer.parseInt(cl.getArgList().get(2)));
                    train.setPrice(Integer.parseInt(cl.getArgList().get(3)));
                    trainManager.add(train);
                    System.out.println("You have been successfully added a train route!");
                }
            }
            catch (TrainException e) {
                System.out.println("Something went wrong, please check the format and try again.\n"
                        + "Format: \"Train route\" \"Departure\" \"Capacity\" \"Price\"");
            }
        }
        else if (cl.hasOption(editTrain.getOpt())) {
            try {
                TrainManager trainManager = new TrainManager();
                List<Train> trains = trainManager.getAll();
                List<Integer> ids = new ArrayList<>();
                for (Train t : trains) {
                    ids.add(t.getId());
                }
                if (!ids.contains(Integer.parseInt(cl.getArgList().get(0)))) System.out.println("Train route with the given id does not exist!");
                else if (trainManager.checkTrainRoute(cl.getArgList().get(1))) {
                    Train train = new Train();
                    train.setId(Integer.parseInt(cl.getArgList().get(0)));
                    train.setRoute(cl.getArgList().get(1));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    train.setDeparture(LocalDateTime.parse(cl.getArgList().get(2), formatter));
                    train.setCapacity(Integer.parseInt(cl.getArgList().get(3)));
                    train.setPrice(Integer.parseInt(cl.getArgList().get(4)));
                    trainManager.update(train);
                    System.out.println("You have been successfully edited a train route!");
                }
            }
            catch (TrainException e) {
                System.out.println("Something went wrong, please check the format and try again.\n"
                        + "Format: \"ID\" \"Train route\" \"Departure\" \"Capacity\" \"Price\"");
            }
        }
        else if (cl.hasOption(deleteTrain.getOpt())) {
            try {
                TrainManager trainManager = new TrainManager();
                List<Train> trains = trainManager.getAll();
                List<Integer> ids = new ArrayList<>();
                for (Train t : trains) {
                    ids.add(t.getId());
                }
                if (!ids.contains(Integer.parseInt(cl.getArgList().get(0)))) System.out.println("Train route with the given id does not exist!");
                else {
                    trainManager.delete(Integer.parseInt(cl.getArgList().get(0)));
                    System.out.println("You have been successfully deleted a train route!");
                }
            }
            catch (TrainException e) {
                System.out.println("Something went wrong, please check the format and try again."
                        + "Format: \"ID\"");
            }
        }
        else if(cl.hasOption(getTrain.getOpt())){
            TrainManager trainManager = new TrainManager();
            trainManager.getAll().forEach(c -> System.out.println("ID: " + c.getId() + " Route: " + c.getRoute() + " Departure: " +
                    c.getDeparture() + " Capacity: " + c.getCapacity() + " Price: " + c.getPrice()));
        }
        else if(cl.hasOption(getUser.getOpt())){
            UserManager userManager = new UserManager();
            userManager.getAll().forEach(c -> System.out.println("ID: " + c.getId() + " Full name: " + c.getName() + " Username: " +
                    c.getUsername() + " Password: " + c.getPassword()));
        }
        else if(cl.hasOption(getReservation.getOpt())){
            ReservationManager reservationManager = new ReservationManager();
            reservationManager.getAll().forEach(c -> System.out.println("ID: " + c.getId() + " Username: " + c.getUser().getUsername() +
                    " Train route: " + c.getTrain().getRoute()));
        }
        else {
            printFormattedOptions(options);
            System.exit(-1);
        }
    }
}
