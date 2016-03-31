/** This class represents the CorruptDatabaseException
* @author Ngoc Anh Thai
* @version 1.0
*/
public class CorruptDatabaseException extends Exception {
    private static String message = "This file has been corrupted.";
    /** Creates CorruptDatabseException that calls to the super class
    */
    public CorruptDatabaseException() {
        super(message);
    }
    /** Creates CorruptDatabaseException that takes in a message
    * @param m is the message
    */
    public CorruptDatabaseException(String m) {
        message = m;
    }
    /** Overriding the getMessage method
    * @ return message is the message of the exception
    */
    @Override
    public String getMessage() {
        return message;
    }
}