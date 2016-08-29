package by.pwt.pilipenko.payments.model.exceptions;

/**
 * Created by Darrko on 28.08.2016.
 */
public class InsufficientFundsException extends Exception {
    private Exception exception;

    public InsufficientFundsException() {}

    public InsufficientFundsException(String message)
    {
        super(message);
    }

}
