package de.saarpit.optibas;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import de.saarpit.optibas.core.Constants;
import de.saarpit.optibas.ui.graph.GraphFragment;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_activity);

        SharedPreferences sharedPref = getApplication().getSharedPreferences("main", Context.MODE_PRIVATE);
        String name = sharedPref.getString(getString(R.string.activity_main_textFieldName),
                new String());

        TextView debugger = (TextView)findViewById(R.id.debugTextView);
        debugger.setText(name);

        GraphView graph = (GraphView) findViewById(R.id.graphView);

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
