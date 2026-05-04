package com.apps.quantitymeasurement;

// ===== LENGTH UNIT =====
enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    public double toBase(double value) {
        return value * toFeetFactor;
    }

    public double fromBase(double baseValue) {
        return baseValue / toFeetFactor;
    }
}

// ===== LENGTH CLASS =====
class Length {
    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException();
        }
        this.value = value;
        this.unit = unit;
    }

    private double toBase() {
        return unit.toBase(value);
    }

    public Length convertTo(LengthUnit target) {
        double base = toBase();
        return new Length(target.fromBase(base), target);
    }

    public Length add(Length other) {
        double sum = this.toBase() + other.toBase();
        return new Length(unit.fromBase(sum), unit);
    }

    public Length add(Length other, LengthUnit target) {
        double sum = this.toBase() + other.toBase();
        return new Length(target.fromBase(sum), target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Length)) return false;
        Length other = (Length) obj;
        return Math.abs(this.toBase() - other.toBase()) < 0.0001;
    }
}

// ===== WEIGHT UNIT =====
enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double toKgFactor;

    WeightUnit(double toKgFactor) {
        this.toKgFactor = toKgFactor;
    }

    public double toBase(double value) {
        return value * toKgFactor;
    }

    public double fromBase(double baseValue) {
        return baseValue / toKgFactor;
    }
}

// ===== WEIGHT CLASS =====
class Weight {
    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException();
        }
        this.value = value;
        this.unit = unit;
    }

    private double toBase() {
        return unit.toBase(value);
    }

    public Weight convertTo(WeightUnit target) {
        double base = toBase();
        return new Weight(target.fromBase(base), target);
    }

    public Weight add(Weight other) {
        double sum = this.toBase() + other.toBase();
        return new Weight(unit.fromBase(sum), unit);
    }

    public Weight add(Weight other, WeightUnit target) {
        double sum = this.toBase() + other.toBase();
        return new Weight(target.fromBase(sum), target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Weight)) return false;
        Weight other = (Weight) obj;
        return Math.abs(this.toBase() - other.toBase()) < 0.0001;
    }
}

// ===== MAIN APP =====
public class QuantityMeasurementApp {
}