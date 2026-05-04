package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testAddition_TargetFeet() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                l1, l2, Length.LengthUnit.FEET);

        assertTrue(result.equals(new Length(2.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void testAddition_TargetInches() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                l1, l2, Length.LengthUnit.INCHES);

        assertTrue(result.equals(new Length(24.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testAddition_TargetYards() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                l1, l2, Length.LengthUnit.YARDS);

        assertTrue(result.equals(new Length(0.67, Length.LengthUnit.YARDS)));
    }

    @Test
    public void testAddition_TargetCentimeters() {
        Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(1.0, Length.LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                l1, l2, Length.LengthUnit.CENTIMETERS);

        assertTrue(result.equals(new Length(5.08, Length.LengthUnit.CENTIMETERS)));
    }

    @Test
    public void testAddition_Commutative() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length r1 = QuantityMeasurementApp.demonstrateLengthAddition(
                l1, l2, Length.LengthUnit.YARDS);

        Length r2 = QuantityMeasurementApp.demonstrateLengthAddition(
                l2, l1, Length.LengthUnit.YARDS);

        assertTrue(r1.equals(r2));
    }

    @Test
    public void testAddition_WithZero() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(0.0, Length.LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                l1, l2, Length.LengthUnit.YARDS);

        assertTrue(result.equals(new Length(1.67, Length.LengthUnit.YARDS)));
    }

    @Test
    public void testAddition_Negative() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(-2.0, Length.LengthUnit.FEET);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                l1, l2, Length.LengthUnit.INCHES);

        assertTrue(result.equals(new Length(36.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testAddition_NullTarget() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, null));
    }
}