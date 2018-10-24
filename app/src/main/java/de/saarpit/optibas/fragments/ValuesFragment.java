package de.saarpit.optibas.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.saarpit.optibas.R;

public class ValuesFragment extends Fragment {
    public ValuesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_values, container, false);
        return rootView;
    }


    public void addData(ListView listView, Double[] values, String title) {
        List<Double> basalListe = new ArrayList<>(Arrays.asList(values));

        ArrayAdapter <Double> basalListeAdapter = new ArrayAdapter<>(
                getActivity(), // Die aktuelle Umgebung (diese Activity)
                R.layout.listitem_basalvalue, // ID der XML-Layout Datei
                R.id.listitem_basalvalue_textview, // ID des TextViews
                basalListe // Beispieldaten in einer ArrayList
        );

        listView.setAdapter(basalListeAdapter);

    }
}
