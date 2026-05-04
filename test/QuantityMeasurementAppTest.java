package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;

public class QuantityMeasurementAppTest {

    /**
     * testEquality_SameValue
     * Given two same values (1.0, 1.0)
     * When compared
     * Then should return true
     */
    @Test
    public void testFeetEquality_SameValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        assertTrue(f1.equals(f2), "Expected values to be equal");
    }

    /**
     * testEquality_DifferentValue
     */
    @Test
    public void testFeetEquality_DifferentValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(2.0);

        assertFalse(f1.equals(f2), "Expected values to be NOT equal");
    }

    /**
     * testEquality_NullComparison
     */
    @Test
    public void testFeetEquality_NullComparison() {
        Feet f1 = new Feet(1.0);

        assertFalse(f1.equals(null), "Expected comparison with null to be false");
    }

    /**
     * testEquality_DifferentClass (Non-numeric input simulation)
     */
    @Test
    public void testFeetEquality_DifferentClass() {
        Feet f1 = new Feet(1.0);
        String nonNumeric = "1.0";

        assertFalse(f1.equals(nonNumeric), "Expected different type comparison to be false");
    }

    /**
     * testEquality_SameReference (Reflexive property)
     */
    @Test
    public void testFeetEquality_SameReference() {
        Feet f1 = new Feet(1.0);

        assertTrue(f1.equals(f1), "Expected object to be equal to itself");
    }
}