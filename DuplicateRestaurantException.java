/** This class represents the DuplicateRestaurantException
* @author Ngoc Anh Thai
* @version 1.0
*/
public class DuplicateRestaurantException extends RuntimeException {
    private static String message = "The restaurant has already been existed.";
    /** Creates DuplicateRestaurantException that takes in a message
    * @param m is the message
    */
    public DuplicateRestaurantException(String m) {
        message = m;
    }
    /** Creates DuplicateRestaurantException that calls to the super class
    */
    public DuplicateRestaurantException() {
        super(message);
    }
    /** Overriding the getMessage method
    * @ return message is the message of the exception
    */
    @Override
    public String getMessage() {
        return message;
    }
}