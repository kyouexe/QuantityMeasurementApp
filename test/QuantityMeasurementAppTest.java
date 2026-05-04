package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Quantity;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Unit;

public class QuantityMeasurementAppTest {

    // Same unit equality (Feet)
    @Test
    public void testEquality_FeetSameValue() {
        Quantity q1 = new Quantity(1.0, Unit.FEET);
        Quantity q2 = new Quantity(1.0, Unit.FEET);

        assertTrue(q1.equals(q2));
    }

    // Same unit inequality
    @Test
    public void testEquality_FeetDifferentValue() {
        Quantity q1 = new Quantity(1.0, Unit.FEET);
        Quantity q2 = new Quantity(2.0, Unit.FEET);

        assertFalse(q1.equals(q2));
    }

    // Cross unit equality (1 ft = 12 in)
    @Test
    public void testEquality_FeetToInches() {
        Quantity feet = new Quantity(1.0, Unit.FEET);
        Quantity inches = new Quantity(12.0, Unit.INCH);

        assertTrue(feet.equals(inches));
    }

    // Cross unit inequality
    @Test
    public void testEquality_CrossUnitDifferent() {
        Quantity feet = new Quantity(1.0, Unit.FEET);
        Quantity inches = new Quantity(10.0, Unit.INCH);

        assertFalse(feet.equals(inches));
    }

    // Null comparison
    @Test
    public void testEquality_NullComparison() {
        Quantity q = new Quantity(1.0, Unit.FEET);

        assertFalse(q.equals(null));
    }

    // Different class
    @Test
    public void testEquality_DifferentClass() {
        Quantity q = new Quantity(1.0, Unit.FEET);

        assertFalse(q.equals("1.0"));
    }

    // Reflexive property
    @Test
    public void testEquality_SameReference() {
        Quantity q = new Quantity(1.0, Unit.FEET);

        assertTrue(q.equals(q));
    }
}