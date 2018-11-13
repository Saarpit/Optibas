package de.saarpit.optibas.calculator;

import de.saarpit.optibas.insulinpump.InsulinPump;

public class ToddlerBasalCalculator extends BasalCalculator {
    public ToddlerBasalCalculator (InsulinPump pump, Double dailyInsulinValue) {
        super(pump, dailyInsulinValue);

        Double[] lBasalverteilung = new Double[]{
                3.97, 3.61, 3.46, 3.7,  // 0 - 3 Uhr
                3.76, 3.87, 4.18, 4.01, // 4 - 7 Uhr
                3.76, 3.54, 3.15, 2.8,  // 8 - 11 Uhr
                2.86, 3.21, 3.61, 3.97, // 12 - 15 Uhr
                4.43, 4.96, 5.1, 5.5,   // 16 - 19 Uhr
                5.81, 6.14, 5.52, 5.1   // 20 - 23 Uhr
        };
        super.setBasalverteilung(lBasalverteilung);
    }
}
