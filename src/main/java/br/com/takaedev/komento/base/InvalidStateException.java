package br.com.takaedev.komento.base;

public class InvalidStateException extends Exception{
    public InvalidStateException(String pattern, Object ... attributes) {
        super(String.format(pattern, attributes));
    }
    public InvalidStateException(String msg) {
        super(msg);
    }

}
