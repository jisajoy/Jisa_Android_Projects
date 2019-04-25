package com.mysports.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mysports.R;

public class TournamentDetailActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    GoogleMap myMap;
    Toolbar mTourToolbar;
    ImageView mBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_detail);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mTourToolbar = findViewById(R.id.toolbar_tour_details);
        TextView toolTextView = mTourToolbar.findViewById(R.id.venue_title);
        toolTextView.setText("Tournaments");
        mBackBtn = mTourToolbar.findViewById(R.id.venue_back_btn);
        mBackBtn.setOnClickListener(this);
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
        if (v.getId() == R.id.venue_back_btn) {
            finish();
        }
    }
}
