package by.pwt.pilipenko.payments.model.exceptions;

/**
 * Created by Darrko on 28.08.2016.
 */
public class RateNotFoundException extends Exception {
    private Exception exception;

    public RateNotFoundException() {}

    public RateNotFoundException(String message)
    {
        super(message);
    }

}
