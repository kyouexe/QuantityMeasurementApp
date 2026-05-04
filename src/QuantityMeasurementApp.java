package com.apps.quantitymeasurement;

/**
 * UC3: Generic Quantity Class for DRY Principle
 */
public class QuantityMeasurementApp {

    /**
     * Enum to represent length units and conversion factors
     * Base unit: INCH
     */
    public enum Unit {
        FEET(12.0),   // 1 foot = 12 inches
        INCH(1.0);    // base unit

        private final double conversionFactor;

        Unit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double toBase(double value) {
            return value * conversionFactor;
        }
    }

    /**
     * Generic Quantity class
     */
    public static class Quantity {
        private final double value;
        private final Unit unit;

        public Quantity(double value, Unit unit) {
            this.value = value;
            this.unit = unit;
        }

        /**
         * Convert to base unit (inches)
         */
        private double toBase() {
            return unit.toBase(value);
        }

        /**
         * Override equals for value-based comparison
         */
        @Override
        public boolean equals(Object obj) {

            // Reference check
            if (this == obj) return true;

            // Null + type check
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            // Compare after converting both to base unit
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }
    }

    // Demonstration methods

    public static void demonstrateFeetEquality() {
        Quantity q1 = new Quantity(1.0, Unit.FEET);
        Quantity q2 = new Quantity(1.0, Unit.FEET);

        System.out.println("Feet vs Feet: " + q1.equals(q2));
    }

    public static void demonstrateInchesEquality() {
        Quantity q1 = new Quantity(12.0, Unit.INCH);
        Quantity q2 = new Quantity(12.0, Unit.INCH);

        System.out.println("Inches vs Inches: " + q1.equals(q2));
    }

    public static void demonstrateCrossUnitEquality() {
        Quantity q1 = new Quantity(1.0, Unit.FEET);
        Quantity q2 = new Quantity(12.0, Unit.INCH);

        System.out.println("Feet vs Inches: " + q1.equals(q2));
    }

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateCrossUnitEquality();
    }
}