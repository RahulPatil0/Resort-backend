package resortbooking.application.exception;

public class ResortBookingException extends Exception {
    public ResortBookingException(String message) {
        super(message);
    }

    public ResortBookingException(String message, Throwable cause) {
        super(message, cause);
    }
}
