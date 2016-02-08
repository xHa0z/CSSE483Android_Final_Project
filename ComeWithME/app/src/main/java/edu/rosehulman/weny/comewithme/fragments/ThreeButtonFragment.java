package edu.rosehulman.weny.comewithme.fragments;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import edu.rosehulman.weny.comewithme.R;

/**
 * A simple {@link Fragment} subclass.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class ThreeButtonFragment extends Fragment implements View.OnClickListener, Toolbar.OnMenuItemClickListener{

    private OnLogoutListener mListener;
    private final String TAG = "FRAG";


    public ThreeButtonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText(R.string.hello_blank_fragment);
//        return textView;

        Log.d(TAG, "create view");

        View v = inflater.inflate(R.layout.fragment_main_fragment, container, false);
        Button createButton = (Button)v.findViewById(R.id.create_event_button);
        Button pendingButton = (Button)v.findViewById(R.id.pending_event_button);
        Button upcomingButton = (Button)v.findViewById(R.id.upcoming_event_button);

        createButton.setOnClickListener(this);
        pendingButton.setOnClickListener(this);
        upcomingButton.setOnClickListener(this);


        return v;
    }

    public interface OnLogoutListener {
        void onLogout();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.action_logout:
                Log.d("PK", "LOGOUT Menu Item Clicked!");
                mListener.onLogout();
                return true;
        }
        return false;
    }
}
