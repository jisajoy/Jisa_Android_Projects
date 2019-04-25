package com.mysports.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jaygoo.widget.RangeSeekBar;
import com.mysports.R;
import com.mysports.adapter.BrandRecycleView;
import com.mysports.adapter.StoreFilterAdapter;
import com.mysports.bean.BrandBean;
import com.mysports.bean.Sports;
import com.mysports.bean.SportsItems;

import java.util.ArrayList;
import java.util.Arrays;


public class StoreFilterFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView mCategoryRV;
    RecyclerView mBrandRV;
    ImageView mExpandBrand;
    ImageView mExpandcategory;
    public static boolean mCategoryclicked;
    public static boolean mBrandClicked;
    RangeSeekBar seekbar1;
    public StoreFilterFragment() {
        // Required empty public constructor
    }

    public static StoreFilterFragment newInstance(String param1, String param2) {
        StoreFilterFragment fragment = new StoreFilterFragment();
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
        return inflater.inflate(R.layout.fragment_store_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCategoryclicked = true;
        mBrandClicked = true;
        mCategoryRV = view.findViewById(R.id.category_rv);
        mCategoryRV.setVisibility(View.GONE);
        mBrandRV = view.findViewById(R.id.brands_rv);
        mExpandBrand = view.findViewById(R.id.expand_brand);
        mExpandBrand.setOnClickListener(this);
        mExpandcategory = view.findViewById(R.id.expand_category);
        mExpandcategory.setOnClickListener(this);

        seekbar1 = (RangeSeekBar) view.findViewById(R.id.seekbar1);
        seekbar1.setRange(0.0f, 450.0f);
        seekbar1.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float min, float max, boolean isFromUser) {
                seekbar1.setLeftProgressDescription((int) min + "");
                seekbar1.setRightProgressDescription((int) max + "");
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.expand_brand) {
            if (mBrandClicked) {
                mExpandBrand.setImageResource(R.drawable.ic_minus);
                mBrandClicked = false;
                mBrandRV.setVisibility(View.VISIBLE);
                String[] brandList = new String[]{"Adidas", "Aeroplane", "Ashaway", "Mizuno", "Nike", "SS"};
                ArrayList<BrandBean> brandBeansList = new ArrayList<>();
                for (int i = 0; i < brandList.length; i++) {
                    BrandBean brandBean = new BrandBean();
                    brandBean.setBrandName(brandList[i]);
                    brandBeansList.add(brandBean);
                }

                BrandRecycleView brandRecycleView = new BrandRecycleView(getActivity(), brandBeansList);
                mBrandRV.setAdapter(brandRecycleView);
                mBrandRV.setLayoutManager(new LinearLayoutManager(getActivity()));
            }else {
                mExpandBrand.setImageResource(R.drawable.ic_plus);
                mBrandRV.setVisibility(View.GONE);
                mBrandClicked = true;
            }

        }
        if (v.getId() == R.id.expand_category) {
            if (mCategoryclicked) {
                mExpandcategory.setImageResource(R.drawable.ic_minus);
                mCategoryRV.setVisibility(View.VISIBLE);
                mCategoryclicked = false;
                String[] batmintonItems = new String[]{"Rackets", "Towel", "Head Band", "Grip Tape", "Shuttle Cocks", "Batminton Accessories"};
                String[] footballItems = new String[]{"FootBall Jerseys", "FootBall Accessories", "Football Shoes", "Boots"};
                String[] volleyBall = new String[]{"Beach Voleyball", "VolleyBall Accessories"};
                String[] apparelsItems = new String[]{"pants", "shorts", "jackets", "Tracksuit", "Socks", "T Shirt"};


                ArrayList<SportsItems> emptyList = new ArrayList<>();
                ArrayList<SportsItems> batmintonItemsList = new ArrayList<>();
                for (int i = 0; i < batmintonItems.length; i++) {
                    SportsItems sportsItems = new SportsItems();
                    sportsItems.setSportsItems(batmintonItems[i]);
                    batmintonItemsList.add(sportsItems);
                }


                ArrayList<SportsItems> footballItemsList = new ArrayList<>();
                for (int i = 0; i < footballItems.length; i++) {
                    SportsItems sportsItems = new SportsItems();
                    sportsItems.setSportsItems(footballItems[i]);
                    footballItemsList.add(sportsItems);
                }


                ArrayList<SportsItems> volleyBallList = new ArrayList<>();
                for (int i = 0; i < volleyBall.length; i++) {
                    SportsItems sportsItems = new SportsItems();
                    sportsItems.setSportsItems(volleyBall[i]);
                    volleyBallList.add(sportsItems);
                }


                ArrayList<SportsItems> apparelsItemsList = new ArrayList<>();
                for (int i = 0; i < apparelsItems.length; i++) {
                    SportsItems sportsItems = new SportsItems();
                    sportsItems.setSportsItems(apparelsItems[i]);
                    apparelsItemsList.add(sportsItems);
                }

                ArrayList<String> sportsNames = new ArrayList<>(Arrays.asList("Batminton", "cricket", "Football", "volleyball", "bicycle", "Training", "Tennies"));
                ArrayList<ArrayList<SportsItems>> sportsItems = new ArrayList<>(Arrays.asList(batmintonItemsList, emptyList, footballItemsList, volleyBallList, emptyList, emptyList, apparelsItemsList));
                ArrayList<Sports> totalFilterList = new ArrayList();
                for (int i = 0; i < sportsNames.size(); i++) {
                    Sports sports = new Sports(sportsItems.get(i), sportsNames.get(i));
                    totalFilterList.add(sports);
                }

                StoreFilterAdapter storeFilterAdapter = new StoreFilterAdapter(getActivity(), totalFilterList);
                mCategoryRV.setAdapter(storeFilterAdapter);
                mCategoryRV.setLayoutManager(new LinearLayoutManager(getActivity()));

                storeFilterAdapter.SetOnSportsClickListener(new StoreFilterAdapter.SportsClickListener() {
                    @Override
                    public void sportsOnItemClickListener(View view, int position) {
                        Toast.makeText(getActivity(), "Sports", Toast.LENGTH_SHORT).show();
                    }
                });

                storeFilterAdapter.SetOnSportsItemClickListener(new StoreFilterAdapter.SportsItemClickListener() {
                    @Override
                    public void sportsItemOnItemClickListener(View view, int position) {
                        Toast.makeText(getActivity(), "SportsItems", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                mExpandcategory.setImageResource(R.drawable.ic_plus);
                mCategoryRV.setVisibility(View.GONE);
                mCategoryclicked = true;
            }
        }
    }
}
