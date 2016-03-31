/** This class represents the YelpDB object
* @author Ngoc Anh Thai
* @version 1.0
*/
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.PrintWriter;
import java.io.File;
public class YelpDB {
    private Restaurant[] array;
    private PrintWriter out = null;
    /** Creates YelpDB that creates an array of Restaurant
    */
    public YelpDB() {
        array = new Restaurant[20];
    }
    /** Method load() that loads the file and add to the array
    */
    public void load() throws FileNotFoundException, CorruptDatabaseException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please input a file's name.");
        String inName = scan.nextLine();
        try {
            File inFile = new File(inName);
            Scanner fileScanner = new Scanner(inFile);
            String firstLine = fileScanner.nextLine();
            int first = Integer.parseInt(firstLine);
            if (first > array.length) {
                Restaurant[] temp = new Restaurant[2 * array.length];
                array = temp;
            }
            for (int i = 0; i < first; i++) {
                String name = fileScanner.nextLine();
                String cuisine = fileScanner.nextLine();
                String averageRatingString = fileScanner.nextLine();
                double ave = Double.parseDouble(averageRatingString);
                String price = fileScanner.nextLine();
                array[i] = new Restaurant(name, cuisine, price);
                array[i].setAve(ave);
                String numRateString = fileScanner.nextLine();
                int numRate = Integer.parseInt(numRateString);
                for (int j = 0; j < numRate; j++) {
                    String rateString = fileScanner.nextLine();
                    int rate = Integer.parseInt(rateString);
                    String date = fileScanner.nextLine();
                    Review r = new Review(rate, date, array[i]);
                    array[i].addReview(r);
                }
            }
            for (int i = 0; i < first; i++) {
                for (int j = 0; j < i; j++) {
                    if (array[j].equals(array[i])) {
                        DuplicateRestaurantException de =
                                       new DuplicateRestaurantException();
                        throw de;
                    }
                }
            }
        } catch (FileNotFoundException fe) {
            throw new FileNotFoundException("File is not found. "
                            + "Please enter a valid file name.");
        } catch (NumberFormatException ne) {
            CorruptDatabaseException ce =
                new CorruptDatabaseException("Input mismatch");
            throw ce;
        } catch (InputMismatchException ie) {
            CorruptDatabaseException ce =
                new CorruptDatabaseException("Input mismatch");
            throw ce;
        } catch (DuplicateRestaurantException de) {
            CorruptDatabaseException ce =
                new CorruptDatabaseException(de.getMessage());
            throw ce;
        }
    }
    /** Method save() that saves the data in the array to YelpDB.txt
    */
    public void save() throws FileNotFoundException {
        try {
            File outFile = new File("YelpDB.txt");
            out = new PrintWriter(outFile);
            int count = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    count++;
                }
            }
            out.println(count);
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    out.println(array[i].getName());
                    out.println(array[i].getCuisine());
                    out.println(array[i].getAve());
                    out.println(array[i].getPrice());
                    int count1 = 0;
                    for (int j = 0; j < array[i].getReview().length; j++) {
                        if (array[i].getReview()[j] != null) {
                            count1++;
                        }
                    }
                    out.println(count1);
                    for (int j = 0; j < array[i].getReview().length; j++) {
                        if (array[i].getReview()[j] != null) {
                            out.println(array[i].getReview()[j].getRating());
                            out.println(array[i].getReview()[j].getDate());
                        }
                    }
                }
            }
        } catch (FileNotFoundException fe) {
            throw new FileNotFoundException("File is not found. "
                                 + "Please enter a valid file name.");
        } finally {
            out.flush();
            out.close();
        }
    }
    /** Method addToDatabase adds a restaurant to the array
    * @param r of type Restaurant is the Restaurant that needs to be added
    */
    public void addToDatabase(Restaurant r)
                               throws DuplicateRestaurantException {
        int counting = 0;
        try {
            for (int i = 0; i < array.length; i++) {
                if (r.equals(array[i])) {
                    DuplicateRestaurantException de =
                                     new DuplicateRestaurantException();
                    throw de;
                } else if (array[i] != null) {
                    counting++;
                }
            }
            for (int i = 0; i < array.length; i++) {
                if (array[array.length - 1] != null) {
                    Restaurant[] temp = new Restaurant[array.length * 2];
                    for (int j = 0; j < array.length; j++) {
                        temp[j] = array[j];
                    }
                    temp[counting] = r;
                    array = temp;
                } else {
                    array[counting] = r;
                }
            }
        } catch (DuplicateRestaurantException de) {
            throw new DuplicateRestaurantException();
        }
    }
    /** Method search searches based on the name of the restaurant
    * @param n is a String that is the name of the restaurant
    * @return the data of the restaurant or a String if it is not found
    */
    public String search(String n) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                if (array[i].getName().equals(n)) {
                    return array[i].toString();
                }
            }
        }
        String s = "Restaurant not found.";
        return s;
    }
    /** Method search searches based on the cuisine and the price range
    * of the restaurant
    * @param c is a String that is the cuisine of the restaurant
    * @param p is a String that is the price range of the restaurant
    * @return the data of the restaurant or a String if it is not found
    */
    public String search(String c, String p) {
        String str = "";
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                if (array[i].getCuisine().equals(c)
                    && array[i].getPrice().equals(p)) {
                    str = str + String.format("%s", array[i].toString());
                }
            }
        }
        if (!str.equals("")) {
            return str;
        } else {
            str = "Restaurant not found.";
            return str;
        }
    }
    /** Method search gives the best options
    * @return the data of the restaurant or a String if it is not found
    */
    public String search() {
        double max = 0;
        String str = "";
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                if (array[i].getAve() > max) {
                    max = array[i].getAve();
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                if (array[i].getAve() == max) {
                    str = str + String.format("%s. %s", i + 1,
                                             array[i].toString());
                }
            }
        }
        if (!str.equals("")) {
            return str;
        } else {
            str = "Restaurant not found.";
            return str;
        }
    }
    /** getRestaurants() methods gives the arrays of Restaurant
    * @return array of type Restaurant
    */
    public Restaurant[] getRestaurants() {
        return array;
    }
}