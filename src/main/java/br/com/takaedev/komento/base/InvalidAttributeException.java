package br.com.takaedev.komento.base;

public class InvalidAttributeException extends Exception{
    public InvalidAttributeException(String pattern, String ... attributes) {
        super(String.format(pattern, attributes));
    }
    public InvalidAttributeException(String msg) {
        super(msg);
    }

}
