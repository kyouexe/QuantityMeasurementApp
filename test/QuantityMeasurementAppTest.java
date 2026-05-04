package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Quantity;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Unit;

public class QuantityMeasurementAppTest {

    @Test
    public void testEquality_FeetToInches() {
        assertTrue(new Quantity(1, Unit.FEET)
                .equals(new Quantity(12, Unit.INCH)));
    }

    @Test
    public void testEquality_YardToFeet() {
        assertTrue(new Quantity(1, Unit.YARD)
                .equals(new Quantity(3, Unit.FEET)));
    }

    @Test
    public void testEquality_CmToInch() {
        assertTrue(new Quantity(2.54, Unit.CM)
                .equals(new Quantity(1, Unit.INCH)));
    }

    @Test
    public void testInequality_DifferentValues() {
        assertFalse(new Quantity(1, Unit.FEET)
                .equals(new Quantity(10, Unit.INCH)));
    }

    @Test
    public void testAddition_InchAndCm() {
        Quantity result = new Quantity(2, Unit.INCH)
                .add(new Quantity(2.54, Unit.CM));

        assertEquals(new Quantity(3, Unit.INCH), result);
    }

    @Test
    public void testAddition_FeetAndInch() {
        Quantity result = new Quantity(1, Unit.FEET)
                .add(new Quantity(2, Unit.INCH));

        assertEquals(new Quantity(14, Unit.INCH), result);
    }

    @Test
    public void testNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity(1, null);
        });
    }

    @Test
    public void testNullAddition() {
        Quantity q = new Quantity(1, Unit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            q.add(null);
        });
    }

    @Test
    public void testSameReference() {
        Quantity q = new Quantity(1, Unit.FEET);
        assertTrue(q.equals(q));
    }

    @Test
    public void testNullComparison() {
        Quantity q = new Quantity(1, Unit.FEET);
        assertFalse(q.equals(null));
    }
}