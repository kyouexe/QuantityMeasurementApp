package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ===== ENUM TESTS =====

    @Test
    public void testFeetToBase() {
        assertEquals(5.0,
                LengthUnit.FEET.convertToBaseUnit(5.0),
                0.01);
    }

    @Test
    public void testInchesToBase() {
        assertEquals(1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                0.01);
    }

    @Test
    public void testYardsToBase() {
        assertEquals(3.0,
                LengthUnit.YARDS.convertToBaseUnit(1.0),
                0.01);
    }

    @Test
    public void testCmToBase() {
        assertEquals(1.0,
                LengthUnit.CENTIMETERS.convertToBaseUnit(30.48),
                0.01);
    }

    @Test
    public void testFromBaseToInches() {
        assertEquals(12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                0.01);
    }

    @Test
    public void testFromBaseToYards() {
        assertEquals(1.0,
                LengthUnit.YARDS.convertFromBaseUnit(3.0),
                0.01);
    }

    @Test
    public void testFromBaseToCm() {
        assertEquals(30.48,
                LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0),
                0.01);
    }

    // ===== FUNCTIONAL TESTS =====

    @Test
    public void testEquality() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testConversion() {
        Length l = new Length(1.0, LengthUnit.FEET);
        Length result = l.convertTo(LengthUnit.INCHES);

        assertTrue(result.equals(new Length(12.0, LengthUnit.INCHES)));
    }

    @Test
    public void testAdditionUC6() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertTrue(result.equals(new Length(2.0, LengthUnit.FEET)));
    }

    @Test
    public void testAdditionUC7() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.add(l2, LengthUnit.YARDS);

        assertTrue(result.equals(new Length(0.67, LengthUnit.YARDS)));
    }

    @Test
    public void testNullUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                new Length(1.0, null));
    }

    @Test
    public void testInvalidValue() {
        assertThrows(IllegalArgumentException.class, () ->
                new Length(Double.NaN, LengthUnit.FEET));
    }
}