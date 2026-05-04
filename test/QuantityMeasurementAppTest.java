package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.Length;
import com.apps.quantitymeasurement.Length.LengthUnit;

public class QuantityMeasurementAppTest {

    // ===== Equality Tests =====

    @Test
    void testFeetEquality() {
        assertTrue(new Length(1, LengthUnit.FEET)
                .equals(new Length(1, LengthUnit.FEET)));
    }

    @Test
    void testInchesEquality() {
        assertTrue(new Length(1, LengthUnit.INCHES)
                .equals(new Length(1, LengthUnit.INCHES)));
    }

    @Test
    void testFeetToInchesEquality() {
        assertTrue(new Length(1, LengthUnit.FEET)
                .equals(new Length(12, LengthUnit.INCHES)));
    }

    @Test
    void testYardToFeetEquality() {
        assertTrue(new Length(1, LengthUnit.YARDS)
                .equals(new Length(3, LengthUnit.FEET)));
    }

    @Test
    void testCentimeterToInchesEquality() {
        assertTrue(new Length(2.54, LengthUnit.CENTIMETERS)
                .equals(new Length(1, LengthUnit.INCHES)));
    }

    @Test
    void testDifferentValuesSameUnit() {
        assertFalse(new Length(1, LengthUnit.FEET)
                .equals(new Length(2, LengthUnit.FEET)));
    }

    @Test
    void testNullComparison() {
        assertFalse(new Length(1, LengthUnit.FEET).equals(null));
    }

    @Test
    void testSameReference() {
        Length l = new Length(1, LengthUnit.FEET);
        assertTrue(l.equals(l));
    }

    // ===== Conversion Tests =====

    @Test
    void testFeetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.convert(1.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES),
                1e-6);
    }

    @Test
    void testInchesToFeet() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(24.0,
                        LengthUnit.INCHES,
                        LengthUnit.FEET),
                1e-6);
    }

    @Test
    void testYardsToInches() {
        assertEquals(36.0,
                QuantityMeasurementApp.convert(1.0,
                        LengthUnit.YARDS,
                        LengthUnit.INCHES),
                1e-6);
    }

    @Test
    void testCentimeterToInches() {
        assertEquals(1.0,
                QuantityMeasurementApp.convert(2.54,
                        LengthUnit.CENTIMETERS,
                        LengthUnit.INCHES),
                1e-6);
    }

    @Test
    void testFeetToYards() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(6.0,
                        LengthUnit.FEET,
                        LengthUnit.YARDS),
                1e-6);
    }

    @Test
    void testZeroConversion() {
        assertEquals(0.0,
                QuantityMeasurementApp.convert(0.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES),
                1e-6);
    }

    @Test
    void testNegativeConversion() {
        assertEquals(-12.0,
                QuantityMeasurementApp.convert(-1.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES),
                1e-6);
    }

    @Test
    void testRoundTripConversion() {
        double original = 5.0;

        double inches = QuantityMeasurementApp.convert(original,
                LengthUnit.FEET,
                LengthUnit.INCHES);

        double backToFeet = QuantityMeasurementApp.convert(inches,
                LengthUnit.INCHES,
                LengthUnit.FEET);

        assertEquals(original, backToFeet, 1e-6);
    }

    @Test
    void testInvalidValue_NaN() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.convert(Double.NaN,
                        LengthUnit.FEET,
                        LengthUnit.INCHES));
    }

    @Test
    void testInvalidValue_Infinite() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.convert(Double.POSITIVE_INFINITY,
                        LengthUnit.FEET,
                        LengthUnit.INCHES));
    }

    @Test
    void testNullSourceUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.convert(1.0,
                        null,
                        LengthUnit.INCHES));
    }

    @Test
    void testNullTargetUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.convert(1.0,
                        LengthUnit.FEET,
                        null));
    }
}