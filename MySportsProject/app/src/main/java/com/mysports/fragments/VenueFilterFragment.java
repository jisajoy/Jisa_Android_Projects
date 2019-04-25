package com.mysports.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mysports.DateWheel.DatePickerPopWin;
import com.mysports.R;
import com.mysports.adapter.VenueFilterAdapter;
import com.mysports.bean.VenueFilterBean;
import com.mysports.custom.MyArrayAdapter;

import java.util.ArrayList;


public class VenueFilterFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    AppCompatSpinner mSpinnerState;
    RelativeLayout mCalendarLayout;
    ArrayList<String> mStateList;
    TextView mShowDate;
    RecyclerView mGamesList;
    RelativeLayout mSubmitFilter;
    RelativeLayout mCloseFilter;
    String[] mGameTitle;
    int[] mGameImage;
    RelativeLayout mRightMove;
    ArrayList<VenueFilterBean> mGameArrayList;
    LinearLayoutManager layoutManager;
    VenueFilterAdapter venueFilterAdapter;
    RelativeLayout mLeftMove;

    public VenueFilterFragment() {
        // Required empty public constructor
    }

    public static VenueFilterFragment newInstance(String param1, String param2) {
        VenueFilterFragment fragment = new VenueFilterFragment();
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
        return inflater.inflate(R.layout.fragment_venue_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRightMove = view.findViewById(R.id.right_move);
        mRightMove.setOnClickListener(this);
        mLeftMove = view.findViewById(R.id.left_move);
        mLeftMove.setOnClickListener(this);
        mGameImage = new int[]{R.drawable.ic_cock, R.drawable.ic_basket_court, R.drawable.ic_basket_ball, R.drawable.ic_football, R.drawable.ic_cock, R.drawable.ic_basket_court, R.drawable.ic_basket_ball, R.drawable.ic_football};
        mGameTitle = new String[]{"Badminton", "BasketBall", "VolleyBall", "FootBall", "Badminton", "BasketBall", "VolleyBall", "FootBall"};
        mGameArrayList = new ArrayList<>();
        for (int i = 0; i < mGameImage.length; i++) {
            VenueFilterBean venueFilterBean = new VenueFilterBean();
            venueFilterBean.setGameImage(mGameImage[i]);
            venueFilterBean.setGameTitle(mGameTitle[i]);
            mGameArrayList.add(venueFilterBean);
        }

        mGamesList = view.findViewById(R.id.games_list);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mGamesList.setLayoutManager(layoutManager);
        venueFilterAdapter = new VenueFilterAdapter(getActivity(), mGameArrayList, "VenueFilter");
        mGamesList.setAdapter(venueFilterAdapter);


        mSpinnerState = view.findViewById(R.id.spinner_state);
        mShowDate = view.findViewById(R.id.date_show);
        mSubmitFilter = view.findViewById(R.id.submit_filter);
        mSubmitFilter.setOnClickListener(this);
        mCloseFilter = view.findViewById(R.id.close_filter);
        mCloseFilter.setOnClickListener(this);
        mStateList = new ArrayList<>();
        mStateList.add("Select State");
        mStateList.add("Hariyana");
        mStateList.add("Kerala");
        mStateList.add("Karnataka");
        mStateList.add("Tamil Nadu");
        setStateListData(mStateList, mSpinnerState);
        mCalendarLayout = view.findViewById(R.id.calendar_layout);
        mCalendarLayout.setOnClickListener(this);
    }

    private void setStateListData(ArrayList<String> mStateList, AppCompatSpinner mSpinnerState) {
        MyArrayAdapter arrayAdapterTitle = new MyArrayAdapter(getActivity(), R.layout.layout_drop_title_black, mStateList, -1);
        arrayAdapterTitle.setDropDownViewResource(R.layout.layout_drop_list);
        mSpinnerState.setAdapter(arrayAdapterTitle);
    }

    @Override
    public void onClick(View v) {
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
                    .dateChose("2013-11-11") // date chose when init popwindow 2013-11-11
                    .build();

            pickerPopWin.showPopWin(getActivity());
        }
        if (v.getId() == R.id.submit_filter) {
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.venue_filter_holder)).commit();
        }
        if (v.getId() == R.id.close_filter) {
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.venue_filter_holder)).commit();
        }
        if (v.getId() == R.id.right_move) {
            int totalItemCount = mGamesList.getAdapter().getItemCount();
            if (totalItemCount <= 0) return;
            int lastVisibleItemIndex = layoutManager.findLastVisibleItemPosition();

            if (lastVisibleItemIndex >= totalItemCount) return;
            layoutManager.smoothScrollToPosition(mGamesList, null, lastVisibleItemIndex + 1);
        }

        if (v.getId() == R.id.left_move){
            int firstVisibleItemIndex = layoutManager.findFirstCompletelyVisibleItemPosition();
            if (firstVisibleItemIndex > 0) {
                layoutManager.smoothScrollToPosition(mGamesList,null,firstVisibleItemIndex-1);
            }
        }
    }
}
