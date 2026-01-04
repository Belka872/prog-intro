package expression.exceptions;

public class ParseException extends IllegalArgumentException {
    public ParseException(String message) {
        super(message);
    }
}
