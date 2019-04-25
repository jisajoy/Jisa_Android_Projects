package com.mysports.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mysports.R;
import com.mysports.fragments.VenueDetailFragment;
import com.mysports.fragments.VenueFragment;
import com.mysports.utilities.MyApplication;

public class VenuesActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar mVenueToolbar;
    TextView mVenueTitle;
    ImageView mVenueBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venues);
        mVenueToolbar = findViewById(R.id.toolbar_venues);
        mVenueTitle = mVenueToolbar.findViewById(R.id.venue_title);
        mVenueTitle.setText("Venues");
        mVenueBackBtn = mVenueToolbar.findViewById(R.id.venue_back_btn);
        mVenueBackBtn.setOnClickListener(this);
        VenueFragment venueFragment = new VenueFragment();
        FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
        fragmentManager.add(R.id.venue_ui_holder, venueFragment, "venue_list_fragment_tag");
        /*fragmentManager.addToBackStack("venue_list_fragment_backstack");*/
        fragmentManager.commit();

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                VenueDetailFragment venueDetailBack = (VenueDetailFragment) getSupportFragmentManager().findFragmentByTag("venue_detail_fragment_tag");
                if (venueDetailBack != null && venueDetailBack.isVisible()) {
                    mVenueTitle.setText("Venues Details");
                } else {
                    mVenueTitle.setText("Venues");
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.venue_back_btn) {
            VenueFragment venueFragmentBack = (VenueFragment) getSupportFragmentManager().findFragmentByTag("venue_list_fragment_tag");
            if (venueFragmentBack != null && venueFragmentBack.isVisible()) {
                finish();
            }
            VenueDetailFragment venueDetailBack = (VenueDetailFragment) getSupportFragmentManager().findFragmentByTag("venue_detail_fragment_tag");
            if (venueDetailBack != null && venueDetailBack.isVisible()) {
                mVenueTitle.setText("Venues");
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.popBackStack("venue_detail_fragment_backstack", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            /*VenueImageVedioFragment venueImageVedioFragment = (VenueImageVedioFragment) getSupportFragmentManager().findFragmentByTag("venue_vedioImage_fragment_tag");
            if (venueImageVedioFragment != null && venueImageVedioFragment.isVisible()){
                getSupportFragmentManager()
                        .beginTransaction().
                        remove(getSupportFragmentManager().findFragmentById(R.id.venue_ui_holder)).commit();
            }*/
        }
    }


    @Override
    public void onBackPressed() {
        VenueFragment venueFragmentBack = (VenueFragment) getSupportFragmentManager().findFragmentByTag("venue_list_fragment_tag");
        if (venueFragmentBack != null && venueFragmentBack.isVisible() && venueFragmentBack.getChildFragmentManager().getBackStackEntryCount() > 0) {
            System.out.println("venue backstack");
            venueFragmentBack.getChildFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}
