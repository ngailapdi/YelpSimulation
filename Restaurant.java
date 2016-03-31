/** This class represents the Restaurant object
* @author Ngoc Anh Thai
* @version 1.0
*/
import java.util.Scanner;
public class Restaurant {
    private String name;
    private String cuisine;
    private Review[] arr;
    private String price;
    private double averageRating;
    private int count;
    /** Creates Restaurant that takes in a name, a cuisine and price
    * @param name is the name of the restaurant
    * @param cuisine is the cuisine the restaurant has
    * @param price is the price range of the restaurant
    */
    public Restaurant(String name, String cuisine, String price) {
        this.name = name;
        this.cuisine = cuisine;
        this.price = price;
        arr = new Review[10];
        averageRating = 0;
    }
    /** This method returns the name of the restaurant
    * @return name is the name of the restaurant
    */
    public String getName() {
        return name;
    }
    /** This method returns the cuisine of the restaurant
    * @return cuisine is the cuisine the restaurant has
    */
    public String getCuisine() {
        return cuisine;
    }
    /** This method returns the price range of the restaurant
    * @return price is the price range of the restaurant
    */
    public String getPrice() {
        return price;
    }
    /** This method returns the average rate of the restaurant
    * @return averageRating is the average rate of the restaurant
    */
    public double getAve() {
        return averageRating;
    }
    /** This method returns the review array of the restaurant
    * @return arr is the review array of the restaurant
    */
    public Review[] getReview() {
        return arr;
    }
    /** This method set the name of the restaurant
    * @param n is the name of the restaurant
    */
    public void setName(String n) {
        name = n;
    }
    /** This method set the cuisine of the restaurant
    * @param c is the cuisine of the restaurant
    */
    public void setCuisine(String c) {
        cuisine = c;
    }
    /** This method set the price range of the restaurant
    * @param p is the price range of the restaurant
    */
    public void setPrice(String p) {
        price = p;
    }
    /** This method set the average rate of the restaurant
    * @param a is the average rate of the restaurant
    */
    public void setAve(double a) {
        averageRating = a;
    }
    /** This method add new review to the database of the restaurant
    * @param r is the new review added
    */
    public void addReview(Review r) {
        if (!reviewExist(r)) {
            System.out.println("Review existed!");
        } else {
            if (arr[arr.length - 1] != null) {
                Review[] temp = new Review[2 * arr.length];
                for (int i = 0; i < arr.length; i++) {
                    temp[i] = arr[i];
                }
                temp[count] = r;
                arr = temp;
            } else {
                arr[count] = r;
            }
            count++;
            double sumRating = 0;
            for (int i = 0; i < count; i++) {
                sumRating += arr[i].getRating();
            }
            averageRating = sumRating / count;
        }
    }
    /** This method delete review in the database of the restaurant
    */
    public void deleteReview() {
        Scanner scan = new Scanner(System.in);
        if (count > 1) {
            System.out.println("Which review do you want to delete? "
                              + "Please enter a whole number "
                              + "from 0 to " + (count - 1));
            int sc = scan.nextInt();
            Review[] temp = new Review[arr.length - 1];
            for (int i = 0; i < sc; i++) {
                temp[i] = arr[i];
            }
            for (int i = sc; i < count - 1; i++) {
                temp[i] = arr[i + 1];
            }
            arr = temp;
            double sumRating = 0;
            for (int i = 0; i < count - 1; i++) {
                sumRating += arr[i].getRating();
            }
            averageRating = sumRating / (count - 1);
        } else {
            Review[] temp = new Review[10];
            arr = temp;
            averageRating = 0;
        }
    }
    /** Overriding the equals method
    * @return boolean whether 2 objects are the same
    */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Restaurant) {
            Restaurant r;
            r = (Restaurant) o;
            return (name.equalsIgnoreCase(r.name));
        } else {
            return false;
        }
    }
    public boolean reviewExist(Review r) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                if (arr[i].equals(r)) {
                    return false;
                }
            }
        }
        return true;
    }
    /** Overriding the toString() method
    * @return s the string represents the restaurant object
    */
    @Override
    public String toString() {
        String s1 = "Name: " + name;
        String s2 = "Cuisine: " + cuisine;
        String s3 = String.format("Average rating: %1$4.2f%n", averageRating);
        String s4 = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                s4 = s4 + arr[i].toString();
            }
        }
        String s = s1 + "; " + s2 + "; " + s3 + s4;
        return s;
    }
    public int hashCode() {
        return 1;
    }
}