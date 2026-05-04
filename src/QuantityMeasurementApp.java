package com.apps.quantitymeasurement;

public class Length {

    private double value;
    private LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.value = value;
        this.unit = unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double base = this.toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Length(round(converted), targetUnit);
    }

    // UC6
    public Length add(Length other) {
        if (other == null) throw new IllegalArgumentException("Null length");

        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        double result = this.unit.convertFromBaseUnit(sumBase);

        return new Length(round(result), this.unit);
    }

    // UC7
    public Length add(Length other, LengthUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Length(round(result), targetUnit);
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;

        Length other = (Length) o;
        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < 0.01;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}