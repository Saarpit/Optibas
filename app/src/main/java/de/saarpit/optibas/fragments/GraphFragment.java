package de.saarpit.optibas.fragments;

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
    public GraphFragment() {
    }

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
        graph.getViewport().setScalableY(true);

        LineGraphSeries<DataPoint> seriesKleinkinder =
                new LineGraphSeries<>(generateData(Constants.BASALVERTEILUNG_KLEINKINDER));
        seriesKleinkinder.setDrawDataPoints(true);
        seriesKleinkinder.setColor(getResources().getColor(R.color.graphColorKleinkinder));
        seriesKleinkinder.setTitle("Kleinkinder");
        graph.addSeries(seriesKleinkinder);

        LineGraphSeries<DataPoint> seriesKinder =
                new LineGraphSeries<>(generateData(Constants.BASALVERTEILUNG_KINDER));
        seriesKinder.setDrawDataPoints(true);
        seriesKinder.setColor(getResources().getColor(R.color.graphColorKinder));
        seriesKinder.setTitle("Kinder");
        graph.addSeries(seriesKinder);

        LineGraphSeries<DataPoint> seriesErwachsene =
                new LineGraphSeries<>(generateData(Constants.BASALVERTEILUNG_ERWACHSENE));
        seriesErwachsene.setDrawDataPoints(true);
        seriesErwachsene.setColor(getResources().getColor(R.color.graphColorErwachsene));
        seriesErwachsene.setTitle("Erwachsene");
        graph.addSeries(seriesErwachsene);

        return rootView;
    }

    private DataPoint[] generateData(double[] values) {
        DataPoint[] dataValues = new DataPoint[24];
        for (int i=0; i<24; i++) {
            double x = i;
            double y = values[i];
            DataPoint v = new DataPoint(x, y);
            dataValues[i] = v;
        }
        return dataValues;
    }
}
