package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ===== WEIGHT EQUALITY =====
    @Test
    void testKgToGramEquality() {
        assertTrue(new Weight(1, WeightUnit.KILOGRAM)
                .equals(new Weight(1000, WeightUnit.GRAM)));
    }

    @Test
    void testKgToPoundEquality() {
        assertTrue(new Weight(1, WeightUnit.KILOGRAM)
                .equals(new Weight(2.20462, WeightUnit.POUND)));
    }

    @Test
    void testWeightVsLength() {
        assertFalse(new Weight(1, WeightUnit.KILOGRAM)
                .equals(new Length(1, LengthUnit.FEET)));
    }

    // ===== CONVERSION =====
    @Test
    void testKgToGramConversion() {
        Weight w = new Weight(1, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertTrue(w.equals(new Weight(1000, WeightUnit.GRAM)));
    }

    @Test
    void testGramToPoundConversion() {
        Weight w = new Weight(453.592, WeightUnit.GRAM)
                .convertTo(WeightUnit.POUND);

        assertTrue(w.equals(new Weight(1, WeightUnit.POUND)));
    }

    // ===== ADDITION =====
    @Test
    void testAdditionKgAndGram() {
        Weight result = new Weight(1, WeightUnit.KILOGRAM)
                .add(new Weight(1000, WeightUnit.GRAM));

        assertTrue(result.equals(new Weight(2, WeightUnit.KILOGRAM)));
    }

    @Test
    void testAdditionWithTargetUnit() {
        Weight result = new Weight(1, WeightUnit.KILOGRAM)
                .add(new Weight(1000, WeightUnit.GRAM), WeightUnit.GRAM);

        assertTrue(result.equals(new Weight(2000, WeightUnit.GRAM)));
    }

    @Test
    void testAdditionPoundAndKg() {
        Weight result = new Weight(1, WeightUnit.POUND)
                .add(new Weight(0.453592, WeightUnit.KILOGRAM));

        assertTrue(result.equals(new Weight(2, WeightUnit.POUND)));
    }

    // ===== EDGE CASES =====
    @Test
    void testZero() {
        assertTrue(new Weight(0, WeightUnit.KILOGRAM)
                .equals(new Weight(0, WeightUnit.GRAM)));
    }

    @Test
    void testNegative() {
        assertTrue(new Weight(-1, WeightUnit.KILOGRAM)
                .equals(new Weight(-1000, WeightUnit.GRAM)));
    }

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Weight(1, null));
    }
}