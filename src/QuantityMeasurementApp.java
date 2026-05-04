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

    // ===== OPERATION ENUM =====
    private enum Operation {
        ADD((a,b)->a+b),
        SUBTRACT((a,b)->a-b),
        DIVIDE((a,b)->{
            if(b==0) throw new ArithmeticException("Divide by zero");
            return a/b;
        });

        private final java.util.function.DoubleBinaryOperator op;

        Operation(java.util.function.DoubleBinaryOperator op) {
            this.op = op;
        }

        double apply(double a, double b) {
            return op.applyAsDouble(a,b);
        }
    }

    // ===== VALIDATION =====
    private void validate(Quantity<U> other, U target, boolean needTarget) {
        if (other == null)
            throw new IllegalArgumentException("Null quantity");

        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different categories");

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid number");

        if (needTarget && target == null)
            throw new IllegalArgumentException("Target unit required");
    }

    // ===== CENTRAL HELPER =====
    private double perform(Quantity<U> other, Operation op) {
        double base1 = this.toBase();
        double base2 = other.toBase();
        return op.apply(base1, base2);
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    // ===== ADD =====
    public Quantity<U> add(Quantity<U> other) {
        validate(other, null, false);
        double result = perform(other, Operation.ADD);
        return new Quantity<>(round(unit.convertFromBaseUnit(result)), unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validate(other, targetUnit, true);
        double result = perform(other, Operation.ADD);
        return new Quantity<>(round(targetUnit.convertFromBaseUnit(result)), targetUnit);
    }

    // ===== SUBTRACT =====
    public Quantity<U> subtract(Quantity<U> other) {
        validate(other, null, false);
        double result = perform(other, Operation.SUBTRACT);
        return new Quantity<>(round(unit.convertFromBaseUnit(result)), unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validate(other, targetUnit, true);
        double result = perform(other, Operation.SUBTRACT);
        return new Quantity<>(round(targetUnit.convertFromBaseUnit(result)), targetUnit);
    }

    // ===== DIVIDE =====
    public double divide(Quantity<U> other) {
        validate(other, null, false);
        return perform(other, Operation.DIVIDE);
    }

    // ===== CONVERT =====
    public Quantity<U> convertTo(U targetUnit) {
        double base = toBase();
        return new Quantity<>(round(targetUnit.convertFromBaseUnit(base)), targetUnit);
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