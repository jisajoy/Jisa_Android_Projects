package com.mysports.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.clans.fab.FloatingActionButton;
import com.mysports.R;
import com.mysports.adapter.StoreProductListRV;
import com.mysports.adapter.StoreRecycleView;
import com.mysports.bean.Store;

import java.util.ArrayList;


public class StoreFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView mStoreRV;
    ArrayList<Store> mStoreList;
    Activity storeActivity;
    FloatingActionButton mSortFab;
    FloatingActionButton mFilterFab;

    public StoreFragment() {
        // Required empty public constructor
    }

    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
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

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mStoreRV = view.findViewById(R.id.store_rv);
        dataForStore();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        mStoreRV.setLayoutManager(layoutManager);
        StoreRecycleView venueRecycleView = new StoreRecycleView(getActivity(), mStoreList);
        mStoreRV.setAdapter(venueRecycleView);
        venueRecycleView.SetOnItemClickListener(new StoreRecycleView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                StoreDetailFragment storeDetailFragment = new StoreDetailFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.store_ui_holder, storeDetailFragment, "store_detail_fragment_tag");
                fragmentTransaction.addToBackStack("store_detail_fragment_backstack");
                fragmentTransaction.commit();
            }
        });
        mSortFab = view.findViewById(R.id.sort_item);
        mSortFab.setOnClickListener(this);

        mFilterFab = view.findViewById(R.id.filter_item);
        mFilterFab.setOnClickListener(this);
    }

    private void dataForStore() {
        mStoreList = new ArrayList<>();
        String[] features = {"Munzino Women's Wave Sayonara Running....", "PUMA Italia Tribute PANTS Peacoat Team-blue",
                "Yonex SHB 65EX BADMINTON SHOES...", "Munzino Women's Wave Sayonara Running....", "PUMA Italia Tribute PANTS Peacoat Team-blue"};
        String[] rate = {"369 AED", "165 AED", "410 AED", "555 AED", "369 AED", "165 AED"};

        String[] storeImage = {"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPjgml6iS5oF3YHeKVK2tU0sTOLqRx1tq3PDv9NbEV7064rqcqsQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPjgml6iS5oF3YHeKVK2tU0sTOLqRx1tq3PDv9NbEV7064rqcqsQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ"};
        for (int i = 0; i < features.length; i++) {
            Store store = new Store();
            store.setmFeatures(features[i]);
            store.setmRate(rate[i]);
            store.setmStoreImage(storeImage[i]);
            mStoreList.add(store);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sort_item) {
            StoreSortFragment storeSortFragment = new StoreSortFragment();
            if (getChildFragmentManager().findFragmentById(R.id.store_option_holder) != null) {
                getChildFragmentManager()
                        .beginTransaction().
                        remove(getChildFragmentManager().findFragmentById(R.id.store_option_holder)).commit();
            }
            getChildFragmentManager()
                    .beginTransaction()
                    .addToBackStack("store_sort")
                    .replace(R.id.store_option_holder, storeSortFragment)
                    .commit();
        }

        if (v.getId() == R.id.filter_item) {
            StoreFilterFragment storeFilterFragment = new StoreFilterFragment();
            if (getChildFragmentManager().findFragmentById(R.id.store_option_holder) != null) {
                getChildFragmentManager()
                        .beginTransaction().
                        remove(getChildFragmentManager().findFragmentById(R.id.store_option_holder)).commit();
            }
            getChildFragmentManager()
                    .beginTransaction()
                    .addToBackStack("store_filter")
                    .replace(R.id.store_option_holder, storeFilterFragment)
                    .commit();
        }
    }
}
