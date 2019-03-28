package model.exceptions;

public class NegativeInputException extends IllegalArgumentException {

    public NegativeInputException(){}

    public NegativeInputException(String v1) {
        super(v1);
    }
}
