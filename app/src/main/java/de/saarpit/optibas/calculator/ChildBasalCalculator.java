package de.saarpit.optibas.calculator;

import de.saarpit.optibas.insulinpump.InsulinPump;

public class ChildBasalCalculator extends BasalCalculator {
    public ChildBasalCalculator (InsulinPump pump, Double dailyInsulinValue) {
        super(pump, dailyInsulinValue);

        Double[] lBasalverteilung = new Double[]{
                4.2, 4.27, 4.41, 4.62,  // 0 - 3 Uhr
                4.92, 5.09, 5.01, 4.47, // 4 - 7 Uhr
                3.89, 3.33, 3.1, 2.91,  // 8 - 11 Uhr
                2.97, 3.08, 3.36, 3.93, // 12 - 15 Uhr
                4.52, 4.76, 4.69, 4.63, // 16 - 19 Uhr
                4.63, 4.47, 4.47, 4.31  // 20 - 23 Uhr
        };
        this.setBasalverteilung(lBasalverteilung);
    }
}
