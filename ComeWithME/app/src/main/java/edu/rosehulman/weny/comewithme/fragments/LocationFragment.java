package edu.rosehulman.weny.comewithme.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.rosehulman.weny.comewithme.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment  {

    private GoogleMap mMap;
    private MapView mMapView;



    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_google_map, container, false);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mMapView = (MapView)v.findViewById(R.id.mapView);
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        mMap = mMapView.getMap();
        LatLng terreHaute = new LatLng(39.4696, -87.3898);
        Marker terreHauteMarker = mMap.addMarker(new MarkerOptions().position(terreHaute).title("Terre Haute"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(terreHaute, 5.0f));

        return v;
    }


}
