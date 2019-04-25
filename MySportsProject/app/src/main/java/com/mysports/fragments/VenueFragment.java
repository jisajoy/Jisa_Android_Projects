package com.mysports.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mysports.R;
import com.mysports.adapter.VenueRecycleView;
import com.mysports.bean.VenueDetails;
import com.mysports.bean.VenueIconBean;
import com.github.clans.fab.FloatingActionButton;
import com.mysports.utilities.MyApplication;

import java.util.ArrayList;

public class VenueFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView mVenueRV;
    ArrayList<VenueDetails> mVenueList;
    Activity VenuesActivity;
    FloatingActionButton mVenueFilter;
    VenueFilterFragment venueFilterFragment;
    private static final String LOC_PASS = "location_pass";

    public VenueFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_venue, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVenueRV = view.findViewById(R.id.venu_rv);
        mVenueFilter = view.findViewById(R.id.venue_filter_fab);
        mVenueFilter.setOnClickListener(this);
        dataForVenue();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mVenueRV.setLayoutManager(layoutManager);
        VenuesActivity = VenueFragment.this.getActivity();
        VenueRecycleView venueRecycleView = new VenueRecycleView(getActivity(), mVenueList);
        mVenueRV.setAdapter(venueRecycleView);
        venueRecycleView.SetOnItemClickListener(new VenueRecycleView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (view.getId() == R.id.book_now) {
                    VenueDetailFragment venueDetailFragment = new VenueDetailFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.venue_ui_holder, venueDetailFragment, "venue_detail_fragment_tag");
                    fragmentTransaction.addToBackStack("venue_detail_fragment_backstack");
                    fragmentTransaction.commit();
                }
                if (view.getId() == R.id.get_location){
                    LocationDialogFragment locationDialogFragment = new LocationDialogFragment();
                    if (getChildFragmentManager().findFragmentById(R.id.venue_filter_holder) != null) {
                        getChildFragmentManager()
                                .beginTransaction().
                                remove(getChildFragmentManager().findFragmentById(R.id.venue_filter_holder)).commit();
                    }
                    Bundle args = new Bundle();
                    args.putString(LOC_PASS, "venue");
                    locationDialogFragment.setArguments(args);
                    getChildFragmentManager()
                            .beginTransaction()
                            .addToBackStack("venue_location")
                            .replace(R.id.venue_filter_holder, locationDialogFragment, "venue_location_fragment_tag")
                            .commit();
                }
            }
        });
    }


    private void dataForVenue() {
        mVenueList = new ArrayList<>();
        String[] mainHeading = {"Play to Win Badminton Court", "Players Badminton Court",
                "Athletic Vision (New Academy School Mankool)", "Athletic Vision Football Pitch Fujerah", "Durham Cricket Club Ajman"};
        String[] smallTitle = {"Muhaisinah4, Dubai", "Behind Sharjah Cricket Stadium", "football, basket ball Volley Ball", "Fujerah, UAE", "Durham Ajman"};

        String[] venueImage = {"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPjgml6iS5oF3YHeKVK2tU0sTOLqRx1tq3PDv9NbEV7064rqcqsQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPjgml6iS5oF3YHeKVK2tU0sTOLqRx1tq3PDv9NbEV7064rqcqsQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ"};

        int[] abstractItemviewList = {R.drawable.venue_item_1, R.drawable.venue_item_2, R.drawable.venue_item_1};

        ArrayList<VenueIconBean> mIconList = new ArrayList<>();
        for (int j = 0; j < abstractItemviewList.length; j++) {
            VenueIconBean venueIconBean = new VenueIconBean();
            venueIconBean.setIconImage(abstractItemviewList[j]);
            mIconList.add(venueIconBean);
        }

        for (int i = 0; i < mainHeading.length; i++) {
            VenueDetails venueDetails = new VenueDetails();
            venueDetails.setMainHeading(mainHeading[i]);
            venueDetails.setSmallTitle(smallTitle[i]);
            venueDetails.setVenueImage(venueImage[i]);
            venueDetails.setmCardIconList(mIconList);
            mVenueList.add(venueDetails);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.venue_filter_fab) {
            venueFilterFragment = new VenueFilterFragment();
            if (getChildFragmentManager().findFragmentById(R.id.venue_filter_holder) != null) {
                getChildFragmentManager()
                        .beginTransaction().
                        remove(getChildFragmentManager().findFragmentById(R.id.venue_filter_holder)).commit();
            }
            getChildFragmentManager()
                    .beginTransaction()
                    .addToBackStack("venue_filter")
                    .replace(R.id.venue_filter_holder, venueFilterFragment)
                    .commit();
        }
    }
}
