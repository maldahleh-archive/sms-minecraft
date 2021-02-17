package com.maldahleh.smsminecraft.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidPropertyExceptionTest {
    @Test
    public void messageCorrectWhenConstructed() {
        // GIVEN
        String property = "dummyProperty";
        Exception e = new InvalidPropertyException(property);

        // THEN
        assertEquals("'dummyProperty' is required.", e.getMessage());
    }
}
