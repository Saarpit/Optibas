package de.saarpit.optibas.calculator;

import de.saarpit.optibas.insulinpump.InsulinPump;

public class BasalCalculator {
    protected Double[] mBasalverteilung = new Double[24];
    protected InsulinPump mPump;
    protected Double mDailyInsulinValue;
    protected Double[] mBasalRateValues = new Double[24];

    public BasalCalculator (InsulinPump pump, Double dailyInsulinValue) {
        mPump = pump;
        mDailyInsulinValue = dailyInsulinValue;
    }

    protected void setBasalverteilung(Double[] basalverteilung) {
        mBasalverteilung = basalverteilung;
        mBasalRateValues = calculateRate();
    }

    public Double[] getBasalRateValues() {
        return mBasalRateValues;
    }

    private Double[] calculateRate() {
        Double[] rateArray = new Double[mBasalverteilung.length];

        for(int i=0; i < mBasalverteilung.length; i++)
        {
            double roundingStep = mPump.getRoundingStep();
            double currentRelative = mBasalverteilung[i];
            double optimalRate = (currentRelative / 100) * mDailyInsulinValue;

            rateArray[i] = Math.round(optimalRate/roundingStep) * roundingStep;
        }
        return rateArray;
    }
}
