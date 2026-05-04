package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // UC3/UC4
    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    // UC5
    public static double convert(double value,
                                 Length.LengthUnit from,
                                 Length.LengthUnit to) {

        if (from == null || to == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid input");
        }

        double inches = value * from.getFactor();
        return inches / to.getFactor();
    }

    // UC6
    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }
}