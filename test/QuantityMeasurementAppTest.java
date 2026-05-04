package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testAdd() {
        assertTrue(
                new Quantity<>(1.0, LengthUnit.FEET)
                        .add(new Quantity<>(12.0, LengthUnit.INCHES))
                        .equals(new Quantity<>(2.0, LengthUnit.FEET))
        );
    }

    @Test
    void testSubtract() {
        assertTrue(
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES))
                        .equals(new Quantity<>(9.5, LengthUnit.FEET))
        );
    }

    @Test
    void testDivide() {
        double result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(5.0, result);
    }

    @Test
    void testCrossCategory() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(10.0, LengthUnit.FEET)
                        .add(new Quantity<>(5.0, WeightUnit.KILOGRAM))
        );
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () ->
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(0.0, LengthUnit.FEET))
        );
    }

    @Test
    void testExplicitTargetUnit() {
        assertTrue(
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES),
                                LengthUnit.INCHES)
                        .equals(new Quantity<>(114.0, LengthUnit.INCHES))
        );
    }
}