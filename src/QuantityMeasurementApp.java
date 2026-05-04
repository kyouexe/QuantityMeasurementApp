package com.apps.quantitymeasurement;

public class Length {

    private final double value;
    private final LengthUnit unit;

    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(1.0 / 30.48);

        private final double factorToFeet;

        LengthUnit(double factorToFeet) {
            this.factorToFeet = factorToFeet;
        }

        public double toBase(double value) {
            return value * factorToFeet;
        }
    }

    public Length(double value, LengthUnit unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    private double toBaseUnit() {
        return unit.toBase(value);
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Length other = (Length) obj;
        return Double.compare(round(this.toBaseUnit()), round(other.toBaseUnit())) == 0;
    }

    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double valueInFeet = this.toBaseUnit();
        double convertedValue = valueInFeet / targetUnit.toBase(1.0);

        return new Length(convertedValue, targetUnit);
    }

    // UC5 REQUIRED STATIC METHOD
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        double base = source.toBase(value);
        return base / target.toBase(1.0);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}