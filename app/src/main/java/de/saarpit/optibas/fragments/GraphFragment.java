package de.saarpit.optibas.fragments;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import de.saarpit.optibas.R;
import de.saarpit.optibas.core.Constants;

public class GraphFragment extends Fragment {
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
