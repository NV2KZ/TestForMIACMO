package ru.nvkz.socks.exception;

public class NotEnoughException extends RuntimeException {
    public NotEnoughException(String message) {
        super(message);
    }

    public String getMessage() {
        return "Not enough socks with this parameters";
    }
}
