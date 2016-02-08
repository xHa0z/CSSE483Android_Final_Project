package edu.rosehulman.weny.comewithme.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import edu.rosehulman.weny.comewithme.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventFragment extends Fragment implements View.OnClickListener {


    public CreateEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_create, container, false);
        TextView title = (TextView)v.findViewById(R.id.create_title_view);
        EditText titleText = (EditText)v.findViewById(R.id.create_title_text);
        TextView time = (TextView)v.findViewById(R.id.create_time_view);
        EditText timeText = (EditText)v.findViewById(R.id.create_time_text);
        TextView location = (TextView)v.findViewById(R.id.create_location_view);
        EditText locationText = (EditText)v.findViewById(R.id.create_location_text);
        ListView attendees = (ListView)v.findViewById(R.id.contract_listView);
        return v;
    }

    @Override
    public void onClick(View v) {


    }
}
