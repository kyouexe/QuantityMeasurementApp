package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ===== SUBTRACTION =====
    @Test
    void testFeetMinusInches() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES));

        assertTrue(result.equals(new Quantity<>(9.5, LengthUnit.FEET)));
    }

    @Test
    void testExplicitTargetUnit() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES),
                                LengthUnit.INCHES);

        assertTrue(result.equals(new Quantity<>(114.0, LengthUnit.INCHES)));
    }

    @Test
    void testNegativeResult() {
        Quantity<LengthUnit> result =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(10.0, LengthUnit.FEET));

        assertTrue(result.equals(new Quantity<>(-5.0, LengthUnit.FEET)));
    }

    @Test
    void testZeroResult() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(120.0, LengthUnit.INCHES));

        assertTrue(result.equals(new Quantity<>(0.0, LengthUnit.FEET)));
    }

    // ===== DIVISION =====
    @Test
    void testDivideSameUnit() {
        double result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(5.0, result);
    }

    @Test
    void testDivideCrossUnit() {
        double result =
                new Quantity<>(24.0, LengthUnit.INCHES)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(1.0, result);
    }

    @Test
    void testDivideLessThanOne() {
        double result =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .divide(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(0.5, result);
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () ->
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(0.0, LengthUnit.FEET)));
    }

    // ===== CROSS CATEGORY =====
    @Test
    void testCrossCategorySubtract() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(5.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testCrossCategoryDivide() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(5.0, WeightUnit.KILOGRAM)));
    }
}