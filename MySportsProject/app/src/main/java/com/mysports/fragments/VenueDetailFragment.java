package com.mysports.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mysports.DateWheel.DatePickerPopWin;
import com.mysports.R;
import com.mysports.activity.ImageVedioActivity;
import com.mysports.adapter.HoursRateRecycleView;
import com.mysports.adapter.VenueIconRecycleView;
import com.mysports.adapter.VenueSpecificationRecycleView;
import com.mysports.bean.HourRateBean;
import com.mysports.bean.VenueIconBean;
import com.mysports.bean.VenueSpecificationBean;
import com.mysports.custom.MyArrayAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.util.ArrayList;


public class VenueDetailFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    GoogleMap myMap;
    MapView mMapView;
    ViewPager mViewPager;
    int[] mResources;
    CustomPagerAdapter mCustomPagerAdapter;
    RecyclerView mVenueSpecRecycleView;
    RecyclerView mItemDisplayRv;
    ArrayList<VenueSpecificationBean> mVenueSpecificationList;
    ArrayList<VenueIconBean> mIconList;
    ArrayList<HourRateBean> mHourList;
    Dialog priceTagDialog;
    LinearLayout mShowPriceTag;

    AppCompatSpinner mSpinnerCourt;
    AppCompatSpinner mSpinnerDuration;
    AppCompatSpinner mSpinnerTime;

    ArrayList<String> mCourtList;
    ArrayList<String> mCourtDuration;
    ArrayList<String> mCourtTime;
    RelativeLayout mCalendarLayout;
    TextView mShowDate;
    RelativeLayout mVenueDetailTopLayout;

    public VenueDetailFragment() {
        // Required empty public constructor
    }

    public static VenueDetailFragment newInstance(String param1, String param2) {
        VenueDetailFragment fragment = new VenueDetailFragment();
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
        View view = inflater.inflate(R.layout.fragment_venue_detail, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapsInitializer.initialize(this.getActivity());

        mCalendarLayout = view.findViewById(R.id.calendar_layout);
        mCalendarLayout.setOnClickListener(this);
        mShowPriceTag = view.findViewById(R.id.show_pricetag);
        mShowPriceTag.setOnClickListener(this);
        mSpinnerCourt = view.findViewById(R.id.spinner_court);
        mSpinnerDuration = view.findViewById(R.id.spinner_duration);
        mSpinnerTime = view.findViewById(R.id.spinner_time);
        mShowDate = view.findViewById(R.id.date_show);


        mResources = new int[]{
                R.drawable.football1,
                R.drawable.football2,
                R.drawable.football3,
        };
        mCustomPagerAdapter = new CustomPagerAdapter(getActivity());

        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);

        dataForVenueSpecification();
        mVenueSpecRecycleView = view.findViewById(R.id.venue_spec_rv);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        mVenueSpecRecycleView.setLayoutManager(layoutManager);
        VenueSpecificationRecycleView venueRecycleView = new VenueSpecificationRecycleView(getActivity(), mVenueSpecificationList);
        mVenueSpecRecycleView.setAdapter(venueRecycleView);

        dataForItemDisplay();
        mItemDisplayRv = view.findViewById(R.id.item_into_display);
        LinearLayoutManager venueDiplayItemlayoutManager = new LinearLayoutManager(getActivity());
        venueDiplayItemlayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mItemDisplayRv.setLayoutManager(venueDiplayItemlayoutManager);
        VenueIconRecycleView specificationRecycleView = new VenueIconRecycleView(getActivity(), mIconList);
        mItemDisplayRv.setAdapter(specificationRecycleView);


        mCourtList = new ArrayList<>();
        mCourtList.add("Select Court");
        mCourtList.add("FootBall");
        mCourtList.add("Batminton");
        mCourtList.add("FootBall");
        mCourtList.add("BasketBall");
        setCourtListData(mCourtList, mSpinnerCourt);


        mCourtDuration = new ArrayList<>();
        mCourtDuration.add("Select Duration");
        mCourtDuration.add("01:00 Hour");
        mCourtDuration.add("01:30 Hour");
        mCourtDuration.add("02:00 Hour");
        mCourtDuration.add("03:00 Hour");
        setCourtListData(mCourtDuration, mSpinnerDuration);


        mCourtTime = new ArrayList<>();
        mCourtTime.add("Select Duration");
        mCourtTime.add("01:00 Hour");
        mCourtTime.add("01:30 Hour");
        mCourtTime.add("02:00 Hour");
        mCourtTime.add("03:00 Hour");
        setCourtListData(mCourtTime, mSpinnerTime);


       /* mVenueDetailTopLayout = view.findViewById(R.id.venuedetail_top_layout);
        mVenueDetailTopLayout.setOnClickListener(this);*/
      /*  SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/

    }

    private void setCourtListData(ArrayList<String> mCourtList, AppCompatSpinner mSpinnerCourt) {
        MyArrayAdapter arrayAdapterTitle = new MyArrayAdapter(getActivity(), R.layout.layout_drop_title_black, mCourtList, -1);
        arrayAdapterTitle.setDropDownViewResource(R.layout.layout_drop_list);
        mSpinnerCourt.setAdapter(arrayAdapterTitle);
    }


    private void dataForItemDisplay() {
        int[] abstractItemviewList = {R.drawable.venue_item_1, R.drawable.venue_item_2, R.drawable.venue_item_1};

        mIconList = new ArrayList<>();
        for (int j = 0; j < abstractItemviewList.length; j++) {
            VenueIconBean venueIconBean = new VenueIconBean();
            venueIconBean.setIconImage(abstractItemviewList[j]);
            mIconList.add(venueIconBean);
        }
    }

    private void dataForVenueSpecification() {
        mVenueSpecificationList = new ArrayList<>();
        String[] mSpecficationTitle = {"Booking", "Washroom", "Parking", "Equipments", "Indoor"};
        int[] mSpecificationIcon = {R.drawable.ic_booking, R.drawable.ic_washroom, R.drawable.ic_parking, R.drawable.ic_equipments, R.drawable.ic_indoor};
        for (int i = 0; i < mSpecficationTitle.length; i++) {
            VenueSpecificationBean specificationBean = new VenueSpecificationBean();
            specificationBean.setmSpecTitle(mSpecficationTitle[i]);
            specificationBean.setmSpecImage(mSpecificationIcon[i]);
            mVenueSpecificationList.add(specificationBean);
        }
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
        if (v.getId() == R.id.show_pricetag) {
            showPriceTag();
        }
        if (v.getId() == R.id.calendar_layout) {
            DatePickerPopWin pickerPopWin = new DatePickerPopWin.Builder(getActivity(), new DatePickerPopWin.OnDatePickedListener() {
                @Override
                public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                    mShowDate.setText(dateDesc);
                    /*Toast.makeText(getActivity(), dateDesc, Toast.LENGTH_SHORT).show();*/

                }
            }).textConfirm("CONFIRM") //text of confirm button
                    .textCancel("CANCEL") //text of cancel button
                    .btnTextSize(16) // button text size
                    .viewTextSize(25) // pick view text size
                    .colorCancel(Color.parseColor("#999999")) //color of cancel button
                    .colorConfirm(Color.parseColor("#009900"))//color of confirm button
                    .minYear(1990) //min year in loop
                    .maxYear(2550) // max year in loop
                    .dateChose("2013-11-11") // date chose when init popwindow  2013-11-11
                    .build();

            pickerPopWin.showPopWin(getActivity());
        }

    }

    public class CustomPagerAdapter extends PagerAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            this.mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mResources.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ConstraintLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.image_pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.venue_image);
            imageView.setImageResource(mResources[position]);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newVedioImageInent = new Intent(getActivity(), ImageVedioActivity.class);
                    startActivity(newVedioImageInent);
                }
            });
            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ConstraintLayout) object);
        }
    }


    private void showPriceTag() {
        priceTagDialog = new Dialog(getActivity(), R.style.Theme_AppCompat_Dialog);
        priceTagDialog.setTitle(null);
        Window window = priceTagDialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        priceTagDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        priceTagDialog.setContentView(R.layout.show_price_tag);
        priceTagDialog.setCancelable(false);
        priceTagDialog.setCanceledOnTouchOutside(false);
        priceTagDialog.show();
        RecyclerView showPriceTagView = priceTagDialog.findViewById(R.id.hours_rate_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        showPriceTagView.setLayoutManager(layoutManager);
        hoursList();
        HoursRateRecycleView rateRecycleView = new HoursRateRecycleView(getActivity(), mHourList);
        showPriceTagView.setAdapter(rateRecycleView);
        rateRecycleView.SetAdapterOnclickListener(new HoursRateRecycleView.OnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "Select", Toast.LENGTH_SHORT).show();
            }
        });
        TextView close = (TextView) priceTagDialog.findViewById(R.id.txt_dialog_ok);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceTagDialog.dismiss();

            }
        });
    }

    private void hoursList() {
        String[] hoursRate = {"01:00 Hour 50.00 AED", "01:30 Hour 75 AED", "03:00 Hour 50.00 AED"};

        mHourList = new ArrayList<>();
        for (int j = 0; j < hoursRate.length; j++) {
            HourRateBean hourRateBean = new HourRateBean();
            hourRateBean.setHourRate(hoursRate[j]);
            mHourList.add(hourRateBean);
        }
    }


}
