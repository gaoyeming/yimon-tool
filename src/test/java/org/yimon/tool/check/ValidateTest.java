package org.yimon.tool.check;

import org.junit.Test;
import org.yimon.tool.exception.ValidateException;

import static org.junit.Assert.assertThrows;

public class ValidateTest {

    @Test
    public void testIsNotBank() {
        // Setup
        // Run the test
        Validate.isNotBank("str", "message");

        // Verify the results
    }

    @Test
    public void testIsNonNull() {
        // Setup
        // Run the test
        Validate.isNonNull("object", "message");

        // Verify the results
    }

    @Test
    public void testThrowIllegality() {
        // Setup
        // Run the test
        assertThrows(ValidateException.class, () -> Validate.throwIllegality(false, "message"));


        // Verify the results
    }

    @Test
    public void testThrowNotAvailable() {
        assertThrows(ValidateException.class, () -> Validate.throwNotAvailable("message"));
    }

    @Test
    public void testMinLength() {
        // Setup
        // Run the test
        Validate.minLength("str", "message", 0);

        // Verify the results
    }

    @Test
    public void testMaxLength() {
        // Setup
        // Run the test
        Validate.maxLength("str", "message", 4);

        // Verify the results
    }

    @Test
    public void testEqualLength() {
        // Setup
        // Run the test
        Validate.equalLength("str", "message", 3);

        // Verify the results
    }
}
