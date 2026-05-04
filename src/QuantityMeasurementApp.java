package com.apps.quantitymeasurement;

// ===== INTERFACE =====
interface IMeasurable {
    double getConversionFactor();
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    String getUnitName();
}

// ===== LENGTH =====
enum LengthUnit implements IMeasurable {
    FEET(1.0), INCHES(1.0/12.0), YARDS(3.0), CENTIMETERS(1.0/30.48);

    private final double factor;

    LengthUnit(double factor) { this.factor = factor; }

    public double getConversionFactor() { return factor; }
    public double convertToBaseUnit(double value) { return value * factor; }
    public double convertFromBaseUnit(double baseValue) { return baseValue / factor; }
    public String getUnitName() { return name(); }
}

// ===== WEIGHT =====
enum WeightUnit implements IMeasurable {
    KILOGRAM(1.0), GRAM(0.001), POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) { this.factor = factor; }

    public double getConversionFactor() { return factor; }
    public double convertToBaseUnit(double value) { return value * factor; }
    public double convertFromBaseUnit(double baseValue) { return baseValue / factor; }
    public String getUnitName() { return name(); }
}

// ===== VOLUME =====
enum VolumeUnit implements IMeasurable {
    LITRE(1.0), MILLILITRE(0.001), GALLON(3.78541);

    private final double factor;

    VolumeUnit(double factor) { this.factor = factor; }

    public double getConversionFactor() { return factor; }
    public double convertToBaseUnit(double value) { return value * factor; }
    public double convertFromBaseUnit(double baseValue) { return baseValue / factor; }
    public String getUnitName() { return name(); }
}

// ===== GENERIC QUANTITY =====
class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null || !Double.isFinite(value))
            throw new IllegalArgumentException("Invalid input");
        this.value = value;
        this.unit = unit;
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    private void validate(Quantity<U> other) {
        if (other == null)
            throw new IllegalArgumentException("Null quantity");
        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different categories");
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    // ===== CONVERT =====
    public Quantity<U> convertTo(U targetUnit) {
        double base = toBase();
        return new Quantity<>(round(targetUnit.convertFromBaseUnit(base)), targetUnit);
    }

    // ===== ADD =====
    public Quantity<U> add(Quantity<U> other) {
        validate(other);
        double sum = this.toBase() + other.toBase();
        return new Quantity<>(round(unit.convertFromBaseUnit(sum)), unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validate(other);
        if (targetUnit == null) throw new IllegalArgumentException();
        double sum = this.toBase() + other.toBase();
        return new Quantity<>(round(targetUnit.convertFromBaseUnit(sum)), targetUnit);
    }

    // ===== SUBTRACT =====
    public Quantity<U> subtract(Quantity<U> other) {
        validate(other);
        double diff = this.toBase() - other.toBase();
        return new Quantity<>(round(unit.convertFromBaseUnit(diff)), unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validate(other);
        if (targetUnit == null) throw new IllegalArgumentException();
        double diff = this.toBase() - other.toBase();
        return new Quantity<>(round(targetUnit.convertFromBaseUnit(diff)), targetUnit);
    }

    // ===== DIVIDE =====
    public double divide(Quantity<U> other) {
        validate(other);
        double divisor = other.toBase();
        if (divisor == 0) throw new ArithmeticException("Divide by zero");
        return this.toBase() / divisor;
    }

    // ===== EQUALS =====
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?>)) return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!this.unit.getClass().equals(other.unit.getClass()))
            return false;

        return Math.abs(this.toBase() - other.toBase()) < 0.0001;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBase());
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}

// ===== APP =====
public class QuantityMeasurementApp {

    public static <U extends IMeasurable> Quantity<U> subtract(
            Quantity<U> q1, Quantity<U> q2) {
        return q1.subtract(q2);
    }

    public static <U extends IMeasurable> Quantity<U> subtract(
            Quantity<U> q1, Quantity<U> q2, U unit) {
        return q1.subtract(q2, unit);
    }

    public static <U extends IMeasurable> double divide(
            Quantity<U> q1, Quantity<U> q2) {
        return q1.divide(q2);
    }
}