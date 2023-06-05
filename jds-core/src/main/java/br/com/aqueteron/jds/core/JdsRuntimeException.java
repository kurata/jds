package br.com.aqueteron.jds.core;

public class JdsRuntimeException extends RuntimeException {

    public JdsRuntimeException(final String message) {
        super(message);
    }

    public JdsRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
