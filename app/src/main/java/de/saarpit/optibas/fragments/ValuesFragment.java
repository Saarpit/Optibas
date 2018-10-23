package de.saarpit.optibas.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.saarpit.optibas.R;

public class ValuesFragment extends Fragment {
    public ValuesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_values, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText("Hier könnte ihr Text stehen");
        return rootView;
    }
}
