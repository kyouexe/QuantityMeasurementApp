package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.Length;
import com.apps.quantitymeasurement.Length.LengthUnit;

public class QuantityMeasurementAppTest {

    // ===== UC6 ADDITION TESTS =====

    @Test
    void testAddFeetAndFeet() {
        Length result = new Length(1, LengthUnit.FEET)
                .add(new Length(2, LengthUnit.FEET));

        assertEquals(new Length(3, LengthUnit.FEET), result);
    }

    @Test
    void testAddFeetAndInches() {
        Length result = new Length(1, LengthUnit.FEET)
                .add(new Length(12, LengthUnit.INCHES));

        assertEquals(new Length(2, LengthUnit.FEET), result);
    }

    @Test
    void testAddInchesAndFeet() {
        Length result = new Length(12, LengthUnit.INCHES)
                .add(new Length(1, LengthUnit.FEET));

        assertEquals(new Length(24, LengthUnit.INCHES), result);
    }

    @Test
    void testAddYardAndFeet() {
        Length result = new Length(1, LengthUnit.YARDS)
                .add(new Length(3, LengthUnit.FEET));

        assertEquals(new Length(2, LengthUnit.YARDS), result);
    }

    @Test
    void testAddCmAndInch() {
        Length result = new Length(2.54, LengthUnit.CENTIMETERS)
                .add(new Length(1, LengthUnit.INCHES));

        assertTrue(result.equals(new Length(5.08, LengthUnit.CENTIMETERS)));
    }

    @Test
    void testAddWithZero() {
        Length result = new Length(5, LengthUnit.FEET)
                .add(new Length(0, LengthUnit.INCHES));

        assertEquals(new Length(5, LengthUnit.FEET), result);
    }

    @Test
    void testAddNegative() {
        Length result = new Length(5, LengthUnit.FEET)
                .add(new Length(-2, LengthUnit.FEET));

        assertEquals(new Length(3, LengthUnit.FEET), result);
    }

    @Test
    void testAddNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1, LengthUnit.FEET).add(null));
    }

    // ===== UC5 CONVERSION TEST =====

    @Test
    void testFeetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.convert(1, LengthUnit.FEET, LengthUnit.INCHES),
                1e-6);
    }

    // ===== UC4 EQUALITY TEST =====

    @Test
    void testEqualityFeetAndInches() {
        assertTrue(new Length(1, LengthUnit.FEET)
                .equals(new Length(12, LengthUnit.INCHES)));
    }
}