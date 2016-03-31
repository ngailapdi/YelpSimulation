// Colaboration statement: "I worked on the
// homework assignment alone, using only course materials."
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Sylvia Necula
 * @version 1.0
 */
public class Driver {
    private static Scanner scan = new Scanner(System.in);

    /**
     * The main method that runs the yelp simulated program.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Driver driver = new Driver();
        YelpDB database = new YelpDB();

        //TO DO: load the database file
        boolean exist = false;
        while (!exist) {
            try {
                database.load();
                exist = true;
            } catch (FileNotFoundException fe) {
                System.out.println(fe.getMessage());
            } catch (CorruptDatabaseException ce) {
                System.out.println(ce.getMessage());
            }
        }

        boolean cont = true;
        while (cont) {
            System.out.println("Welcome to Yelp! What would you like to do?");
            System.out.println("1. View all Restaurants in database");
            System.out.println("2. Search for a Restaurant");
            System.out.println("3. Add a Restaurant to the Database");
            System.out.println("4. Exit");
            int select = scan.nextInt();

            if (select == 1) {
                driver.viewRestaurants(database);
            } else if (select == 2) {
                driver.search(database);
            } else if (select == 3) {
                driver.addRestaurant(database);
            } else if (select == 4) {
                System.out.println("Goodbye!");
                cont = false;
            } else {
                System.out.println("That's not really an option, try again.");
            }
        }

        //TO DO: Save the database.
        boolean exist1 = false;
        while (!exist1) {
            try {
                database.save();
                exist1 = true;
            } catch (FileNotFoundException fe) {
                System.out.println(fe.getMessage());
            }
        }

    }

    /**
     * viewRestaurants allows a user to select a restaurant to add a review to
     *
     * @param db the database being used
     */
    public void viewRestaurants(YelpDB db) {
        System.out.println("============================================");
        System.out.println("Restaurants currently in Database");
        System.out.println("============================================");
        boolean go = true;
        while (go) {
            int i = 0;
            //TO DO: Uncomment the below lines once you have implemented
            // getRestaurants

            for (Restaurant r : db.getRestaurants()) {
                if (r != null) {
                    System.out.print(++i + ". " + r);
                }
            }
            System.out.println(++i + ". Go back");
            System.out.println("If you would like to add a review, type the"
                + " number of the restaurant you would like to add a review to."
                + " Otherwise type " + i +  " to quit.\n");
            System.out.print("What would you like to do? ");
            int select = scan.nextInt();
            if (select == i) {
                go = false;
            } else {
                // TO DO: Uncomment the lines below once you have implemented
                // getRestaurants()

                Restaurant r = db.getRestaurants()[select - 1];
                System.out.println("You are editing: " + r);
                System.out.println("What would you like to do?");
                System.out.println("1. Add a review");
                System.out.println("2. Delete a review");
                int sel = scan.nextInt();
                scan.nextLine();
                //TO DO
                //Implement adding or deleting a review here
                if (sel == 1) {
                    System.out.println("What is the rate of this restaurant? "
                                      + "Please enter a whole "
                                      + "number from 1 to 5.");
                    int ra = scan.nextInt();
                    scan.nextLine();
                    System.out.println("What is the date?");
                    String d = scan.nextLine();
                    Review re = new Review(ra, d, r);
                    r.addReview(re);
                } else if (sel == 2) {
                    r.deleteReview();
                }
            }
        }
    }

    /**
     * Allows a user to add a restaurant to the database
     *
     * @param db the database being used
     */
    public void addRestaurant(YelpDB db) {
        System.out.println("=============================================");
        System.out.println("Adding a Restaurant");
        System.out.println("=============================================");

        //TO DO: Implement the following code for getting input for the
        //restaurant to be added. Remember when adding to the database, if there
        //is a duplicate restaurant your code should not crash!
        scan.nextLine();
        System.out.println("What is the Restaurant's name?");
        String name = scan.nextLine();
        System.out.println("What is the type of cuisine served at " + name
            + "?");
        String cuisine = scan.nextLine();
        System.out.println("What is the price range?");
        String priceRange = scan.nextLine();
        //TO DO: Create a restaurant with the above information
        Restaurant res = new Restaurant(name, cuisine, priceRange);
        //TO DO: Add your restaurant to the database.
        try {
            db.addToDatabase(res);
        } catch (DuplicateRestaurantException de) {
            System.out.println(de.getMessage());
        }
    }

    /**
     * Allows a user to search for a restaurant in the database
     *
     * @param db the database being used
     */
    public void search(YelpDB db) {
        System.out.println("==============================================");
        System.out.println("Searching");
        System.out.println("==============================================");
        //TO DO: finish implementing menu and functionality
        //for searching the database. Feel free to uncomment the search calls
        //after you have implemented them.
        boolean go = true;
        while (go) {
            System.out.println("What search option would you like to use?");
            System.out.println("1. Search by name");
            System.out.println("2. Search by cuisine and price range");
            System.out.println("3. Give me your best option");
            System.out.println("4. Go back");

            int opt = scan.nextInt();

            if (opt == 1) {
                System.out.println("What restaurant would you like to search"
                    + " for?");
                scan.nextLine();
                System.out.println(db.search(scan.nextLine()));
            } else if (opt == 2) {
                scan.nextLine();
                System.out.println("What cuisine would you like to search"
                    + " for?");
                String cuisine = scan.nextLine();
                System.out.println("What price range?");
                System.out.println(db.search(cuisine, scan.nextLine()));
            } else if (opt == 3) {
                System.out.println("Our highest rated restaurant is ");
                System.out.println(db.search());
            } else if (opt == 4) {
                go = false;
            } else {
                System.out.println("That's not an option, try again.");
            }
        }
    }
}