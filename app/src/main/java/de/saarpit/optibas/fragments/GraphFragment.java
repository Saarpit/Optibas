package de.saarpit.optibas.fragments;

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
import de.saarpit.optibas.util.DateUtils;

public class GraphFragment extends Fragment {
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

        GraphView graph = (GraphView) rootView.findViewById(R.id.graphView);

        // set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(23);

        // enable scaling and scrolling
        graph.getViewport().setScalable(true);

        Bundle args = this.getArguments();

        assert args != null;
        mName = args.getString(NewUserActivity.EXTRA_FULLNAME);
        mWeight = args.getInt(NewUserActivity.EXTRA_WEIGHT);
        mDailyInsulin = args.getDouble(NewUserActivity.EXTRA_INSULIN);
        mBasalQuota = args.getInt(NewUserActivity.EXTRA_BASALRELATIVE);
        mBirthday = args.getString(NewUserActivity.EXTRA_BIRTHDAY);
        mWakeupTime = args.getString(NewUserActivity.EXTRA_WAKEUPTIME);

        Calendar lCalendar = Calendar.getInstance();
        String myFormat = "dd.MM.yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.GERMANY);

        DateUtils lDateUtil = DateUtils.getInstance();

        try {
            Date lDate = sdf.parse(mBirthday);

            int lAge = lDateUtil.getAge(lDate);

            return rootView;
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return rootView;
    }

    public void addData(GraphView graph, Double[] values, String title, int color) {
        LineGraphSeries<DataPoint> mySeries = new LineGraphSeries<>( generateData(values) );
        mySeries.setDrawDataPoints(true);
        mySeries.setColor(color);
        mySeries.setTitle(title);
        graph.addSeries(mySeries);
    }

    private DataPoint[] generateData(Double[] values) {
        DataPoint[] dataValues = new DataPoint[24];
        for (int i=0; i<24; i++) {
            int x = i;
            Double y = values[i];
            DataPoint v = new DataPoint(x, y);
            dataValues[i] = v;
        }
        return dataValues;
    }
}
