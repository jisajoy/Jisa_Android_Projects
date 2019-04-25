package com.mysports.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mysports.R;


public class LocationDialogFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String LOC_PASS = "location_pass";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    GoogleMap myMap;
    TextView mCloseDialog;
    String locaData;
    public LocationDialogFragment() {
        // Required empty public constructor
    }

    public static LocationDialogFragment newInstance(String param1, String param2) {
        LocationDialogFragment fragment = new LocationDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_dialog, container, false);
        // Inflate the layout for this fragment
        if (getArguments() != null){
            locaData = getArguments().getString(LOC_PASS);
            System.out.println("location_data"+locaData);
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.location_venue_map);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCloseDialog = view.findViewById(R.id.txt_dialog_close);
        mCloseDialog.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;
        System.out.println("Maps Created");
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        myMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.txt_dialog_close) {// remove fragment from the id venue_filter_holder not the object itself
            if (locaData.equals("venue")) {
                getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.venue_filter_holder)).commit();
            }
            if (locaData.equals("academics")){
                getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.acadmics_location)).commit();
            }
            if (locaData.equals("tournament")){
                getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.tour_map_loader)).commit();
            }
        }

    }
}
