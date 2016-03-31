/** This class represents the Review object
* @author Ngoc Anh Thai
* @version 1.0
*/
public class Review {
    private int rating;
    private String date;
    private Restaurant restaurant;
    /** Creates Review that takes in a rating, date and restaurant
    * @param rating of type int is the rate
    * @param date of type String is the date the rate is added
    * @param restaurant of type Restaurant
    */
    public Review(int rating, String date, Restaurant restaurant) {
        this.rating = rating;
        this.date = date;
        this.restaurant = restaurant;
    }
    /** This method returns the rating
    * @return rating is the rate of the restaurant
    */
    public int getRating() {
        return rating;
    }
    /** This method returns the date
    * @return date is the date the rate is added
    */
    public String getDate() {
        return date;
    }
    /** This method returns the restaurant
    * @return restaurant in the database
    */
    public Restaurant getRestaurant() {
        return restaurant;
    }
    /** Overriding the toString() method
    * @return s the string represents the review object
    */
    @Override
    public String toString() {
        String s1 = String.format("Restaurant: %1$s%n", restaurant.getName());
        String s2 = String.format("Date: %1$s%n", date);
        String s3 = String.format("Rating: %1$s%n%n", rating);
        String s = s1 + s2 + s3;
        return s;
    }
    /** Overrding equals method
    * @return boolean whether 2 reviews are equal
    */
    @Override
    public boolean equals(Object o) {
        Review r;
        if (o instanceof Review) {
            r = (Review) o;
            return (rating == r.rating && date.equals(r.date)
                   && restaurant.equals(r.restaurant));
        } else {
            return false;
        }
    }
    public int hashCode() {
        return 1;
    }
}