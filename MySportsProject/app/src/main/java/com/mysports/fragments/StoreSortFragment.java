package com.mysports.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mysports.R;

public class StoreSortFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    LinearLayout mNewArrivals;
    LinearLayout mLowToHigh;
    LinearLayout mHighToLow;
    LinearLayout mProductAToZ;
    LinearLayout mProductZToA;

    public StoreSortFragment() {
        // Required empty public constructor
    }

    public static StoreSortFragment newInstance(String param1, String param2) {
        StoreSortFragment fragment = new StoreSortFragment();
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
        return inflater.inflate(R.layout.fragment_store_sort, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNewArrivals = view.findViewById(R.id.new_arrivals);
        mNewArrivals.setOnClickListener(this);
        mLowToHigh = view.findViewById(R.id.low_to_high_sort);
        mLowToHigh.setOnClickListener(this);
        mHighToLow = view.findViewById(R.id.high_to_low_sort);
        mHighToLow.setOnClickListener(this);
        mProductAToZ = view.findViewById(R.id.product_a_z);
        mProductAToZ.setOnClickListener(this);
        mProductZToA = view.findViewById(R.id.product_z_a);
        mProductZToA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.new_arrivals){

        }
        if (v.getId() == R.id.low_to_high_sort){

        }
        if (v.getId() == R.id.high_to_low_sort){

        }
        if (v.getId() == R.id.product_a_z){

        }
        if (v.getId() == R.id.product_z_a){

        }
    }
}
