package com.mysports.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mysports.R;
import com.mysports.activity.VenueProviderActivity;
import com.mysports.activity.VenuesActivity;
import com.mysports.interfacepackage.OnFragmentInteractionListener;


public class ListWithUsFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CardView mVenueProvider;
    public static final String VENUEPARAMS = "venue_params";
    public OnFragmentInteractionListener mListener;
    CardView mEventTour;
    CardView mStoreOwner;
    CardView mTrainingAcademics;

    public ListWithUsFragment() {
        // Required empty public constructor
    }

    public static ListWithUsFragment newInstance(String param1, String param2) {
        ListWithUsFragment fragment = new ListWithUsFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_with_us, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVenueProvider = view.findViewById(R.id.venue_provider);
        mVenueProvider.setOnClickListener(this);
        mEventTour = view.findViewById(R.id.events_and_tournament);
        mEventTour.setOnClickListener(this);
        mStoreOwner = view.findViewById(R.id.sports_store_owner);
        mStoreOwner.setOnClickListener(this);
        mTrainingAcademics = view.findViewById(R.id.training_academics);
        mTrainingAcademics.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.venue_provider) {
            Intent venueActivity = new Intent(getActivity(), VenueProviderActivity.class);
            venueActivity.putExtra(VENUEPARAMS, "venue");
            startActivity(venueActivity);
        }

        if (view.getId() == R.id.events_and_tournament) {

        }
        if (view.getId() == R.id.sports_store_owner) {
            Intent venueActivity = new Intent(getActivity(), VenueProviderActivity.class);
            venueActivity.putExtra(VENUEPARAMS, "sports_store");
            startActivity(venueActivity);
        }
        if (view.getId() == R.id.training_academics) {
            Intent venueActivity = new Intent(getActivity(), VenueProviderActivity.class);
            venueActivity.putExtra(VENUEPARAMS, "training_academics");
            startActivity(venueActivity);
        }
    }
}
