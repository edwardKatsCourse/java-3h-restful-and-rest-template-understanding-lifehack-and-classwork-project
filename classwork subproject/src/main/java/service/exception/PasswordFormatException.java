package service.exception;

public class PasswordFormatException extends ServiceException {
    public PasswordFormatException() {
        super("Wrong password format");
    }
}
