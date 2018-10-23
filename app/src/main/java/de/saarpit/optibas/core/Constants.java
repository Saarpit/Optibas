package de.saarpit.optibas.core;

import java.lang.reflect.Array;

public abstract class Constants {
    public static final int ALTER_KLEINKINDER = 0;  // 1-5 Jahre, 4-30 kg
    public static final int ALTER_KINDER = 1;       // 6-11 Jahre, 16-50 kg
    public static final int ALTER_ERWACHSENE = 2;   // 12 - 100 Jahre, 25-110 kg

    public static final double[] BASALVERTEILUNG_KLEINKINDER = new double[]{
        3.97, 3.61, 3.46, 3.7,  // 0 - 3 Uhr
        3.76, 3.87, 4.18, 4.01, // 4 - 7 Uhr
        3.76, 3.54, 3.15, 2.8,  // 8 - 11 Uhr
        2.86, 3.21, 3.61, 3.97, // 12 - 15 Uhr
        4.43, 4.96, 5.1, 5.5,   // 16 - 19 Uhr
        5.81, 6.14, 5.52, 5.1   // 20 - 23 Uhr
    };

    public static final double[] BASALVERTEILUNG_KINDER = new double[]{
        4.2, 4.27, 4.41, 4.62,  // 0 - 3 Uhr
        4.92, 5.09, 5.01, 4.47, // 4 - 7 Uhr
        3.89, 3.33, 3.1, 2.91,  // 8 - 11 Uhr
        2.97, 3.08, 3.36, 3.93, // 12 - 15 Uhr
        4.52, 4.76, 4.69, 4.63, // 16 - 19 Uhr
        4.63, 4.47, 4.47, 4.31  // 20 - 23 Uhr
    };

    public static final double[] BASALVERTEILUNG_ERWACHSENE = new double[]{
        3.47, 3.8, 4.31, 4.95,  // 0 - 3 Uhr
        5.59, 6.11, 5.89, 5.11, // 4 - 7 Uhr
        4.31, 3.78, 3.55, 3.39,  // 8 - 11 Uhr
        3.35, 3.39, 3.64, 3.97, // 12 - 15 Uhr
        4.53, 4.59, 4.5, 4,     // 16 - 19 Uhr
        3.69, 3.39, 3.35, 3.35  // 20 - 23 Uhr
    };
}