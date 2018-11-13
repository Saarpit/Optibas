package de.saarpit.optibas.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.saarpit.optibas.NewUserActivity;
import de.saarpit.optibas.R;
import de.saarpit.optibas.calculator.BasalCalculator;
import de.saarpit.optibas.util.DateUtils;

public class GraphFragment extends Fragment {

    private GraphView mGraphView;
    protected BasalCalculator mCalculator;
    private String mName;
    private int mWeight;
    private double mDailyInsulin;
    private int mBasalQuota;
    private String mBirthday;
    private String mWakeupTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_graph, container, false);

        mGraphView = (GraphView) rootView.findViewById(R.id.graphView);

        // set manual X bounds
        mGraphView.getViewport().setXAxisBoundsManual(true);
        mGraphView.getViewport().setMinX(0);
        mGraphView.getViewport().setMaxX(23);

        // enable scaling and scrolling
        mGraphView.getViewport().setScalable(true);
        addData("Test", Color.BLUE);
        /*Bundle args = this.getArguments();

        assert args != null;
        mName = args.getString(NewUserActivity.EXTRA_FULLNAME);
        mWeight = args.getInt(NewUserActivity.EXTRA_WEIGHT);
        mDailyInsulin = args.getDouble(NewUserActivity.EXTRA_INSULIN);
        mBasalQuota = args.getInt(NewUserActivity.EXTRA_BASALRELATIVE);
        mBirthday = args.getString(NewUserActivity.EXTRA_BIRTHDAY);
        mWakeupTime = args.getString(NewUserActivity.EXTRA_WAKEUPTIME);*/

        return rootView;
    }

    public void setCalculator(BasalCalculator calculator) {
        mCalculator = calculator;
    }

    public void addData(String title, int color) {
        LineGraphSeries<DataPoint> mySeries = new LineGraphSeries<>( generateData(mCalculator.getBasalRateValues()) );
        mySeries.setDrawDataPoints(true);
        mySeries.setColor(color);
        mySeries.setTitle(title);
        mGraphView.addSeries(mySeries);
    }

    private DataPoint[] generateData(Double[] values) {
        DataPoint[] dataValues = new DataPoint[values.length];
        for (int i=0; i<values.length; i++) {
            int x = i;
            Double y = values[i];
            DataPoint v = new DataPoint(x, y);
            dataValues[i] = v;
        }
        return dataValues;
    }
}
