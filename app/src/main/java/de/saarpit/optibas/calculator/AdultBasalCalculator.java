package de.saarpit.optibas.calculator;

import de.saarpit.optibas.insulinpump.InsulinPump;

public class AdultBasalCalculator extends BasalCalculator {
    public AdultBasalCalculator (InsulinPump pump, Double dailyInsulinValue) {
        super(pump, dailyInsulinValue);

        Double[] lBasalverteilung = new Double[]{
                3.47, 3.8, 4.31, 4.95,  // 0 - 3 Uhr
                5.59, 6.11, 5.89, 5.11, // 4 - 7 Uhr
                4.31, 3.78, 3.55, 3.39,  // 8 - 11 Uhr
                3.35, 3.39, 3.64, 3.97, // 12 - 15 Uhr
                4.53, 4.59, 4.5, 4.0,     // 16 - 19 Uhr
                3.69, 3.39, 3.35, 3.35  // 20 - 23 Uhr
        };
        this.setBasalverteilung(lBasalverteilung);
    }
}
