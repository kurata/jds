package br.com.aqueteron.jds.core;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JdsRuntimeExceptionUnitTest {

    @Test
    void jdsRuntimeExceptionConstructorWithMessage() {
        try {
            throw new JdsRuntimeException("Message");
        } catch (JdsRuntimeException jre) {
            assertEquals("Message", jre.getMessage());
        }
    }

    @Test
    void jdsRuntimeExceptionConstructorWithMessageAndCause() {
        IOException ioException = new IOException();
        try {
            throw new JdsRuntimeException("Message", ioException);
        } catch (JdsRuntimeException jre) {
            assertEquals("Message", jre.getMessage());
            assertEquals(ioException, jre.getCause());
        }
    }
}
