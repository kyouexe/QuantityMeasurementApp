package com.apps.quantitymeasurement;

/**
 * UC4: Extended Unit Support
 */
public class QuantityMeasurementApp {

    /**
     * Enum for Length Units
     * Base unit: INCH
     */
    public enum Unit {
        FEET(12.0),
        INCH(1.0),
        YARD(36.0),
        CM(0.393701); // 1 cm = 0.393701 inch

        private final double toInchFactor;

        Unit(double toInchFactor) {
            this.toInchFactor = toInchFactor;
        }

        public double toBase(double value) {
            return value * toInchFactor;
        }
    }

    /**
     * Generic Quantity class
     */
    public static class Quantity {
        private final double value;
        private final Unit unit;

        public Quantity(double value, Unit unit) {

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        /**
         * Convert to base unit (inch)
         */
        private double toBase() {
            return unit.toBase(value);
        }

        /**
         * Equality based on base conversion
         */
        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        /**
         * Add two quantities
         */
        public Quantity add(Quantity other) {

            if (other == null) {
                throw new IllegalArgumentException("Cannot add null quantity");
            }

            double sumInInches = this.toBase() + other.toBase();

            // return result in base unit (INCH)
            return new Quantity(sumInInches, Unit.INCH);
        }
    }

    // ----------- DEMO METHODS -----------

    public static void demonstrateConversions() {
        Quantity feet = new Quantity(1, Unit.FEET);
        Quantity inches = new Quantity(12, Unit.INCH);

        System.out.println("1 ft == 12 in → " + feet.equals(inches));
    }

    public static void demonstrateCrossUnits() {
        Quantity yard = new Quantity(1, Unit.YARD);
        Quantity feet = new Quantity(3, Unit.FEET);

        System.out.println("1 yard == 3 ft → " + yard.equals(feet));
    }

    public static void demonstrateCmConversion() {
        Quantity cm = new Quantity(2.54, Unit.CM);
        Quantity inch = new Quantity(1, Unit.INCH);

        System.out.println("2.54 cm == 1 inch → " + cm.equals(inch));
    }

    public static void demonstrateAddition() {
        Quantity inch = new Quantity(2, Unit.INCH);
        Quantity cm = new Quantity(2.54, Unit.CM);

        Quantity result = inch.add(cm);

        System.out.println("2 in + 2.54 cm = " + result);
    }

    public static void main(String[] args) {
        demonstrateConversions();
        demonstrateCrossUnits();
        demonstrateCmConversion();
        demonstrateAddition();
    }
}