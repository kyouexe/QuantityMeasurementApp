package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ===== VOLUME EQUALITY =====
    @Test
    void testLitreToMillilitre() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testLitreToGallon() {
        assertTrue(new Quantity<>(3.78541, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.GALLON)));
    }

    // ===== CONVERSION =====
    @Test
    void testLitreToMLConversion() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE);

        assertTrue(result.equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testGallonToLitreConversion() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.GALLON)
                        .convertTo(VolumeUnit.LITRE);

        assertTrue(result.equals(new Quantity<>(3.78541, VolumeUnit.LITRE)));
    }

    // ===== ADDITION =====
    @Test
    void testAdditionLitreAndML() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));

        assertTrue(result.equals(new Quantity<>(2.0, VolumeUnit.LITRE)));
    }

    @Test
    void testAdditionWithTargetUnit() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                                VolumeUnit.MILLILITRE);

        assertTrue(result.equals(new Quantity<>(2000.0, VolumeUnit.MILLILITRE)));
    }

    // ===== CROSS CATEGORY =====
    @Test
    void testVolumeVsLength() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, LengthUnit.FEET)));
    }

    @Test
    void testVolumeVsWeight() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, WeightUnit.KILOGRAM)));
    }

    // ===== EDGE CASES =====
    @Test
    void testZero() {
        assertTrue(new Quantity<>(0.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(0.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testNegative() {
        assertTrue(new Quantity<>(-1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(-1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }
}