package com.mysports.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mysports.R;
import com.mysports.activity.AcademicsDetailsActivity;
import com.mysports.adapter.AcademicsRecycleView;
import com.mysports.bean.Academics;
import com.mysports.bean.AcademicsGameListBean;
import com.mysports.interfacepackage.OnFragmentInteractionListener;

import java.util.ArrayList;


public class AcademiesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String LOC_PASS = "location_pass";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OnFragmentInteractionListener mListener;
    RecyclerView academicsRv;
    ArrayList<Academics> mAcademicsList;

    public AcademiesFragment() {
        // Required empty public constructor
    }

    public static AcademiesFragment newInstance(String param1, String param2) {
        AcademiesFragment fragment = new AcademiesFragment();
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
        return inflater.inflate(R.layout.fragment_academies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        academicsRv = view.findViewById(R.id.academies_rv);
        dataForAcademics();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        academicsRv.setLayoutManager(layoutManager);

        AcademicsRecycleView academicsRecycleView = new AcademicsRecycleView(getActivity(), mAcademicsList);
        academicsRv.setAdapter(academicsRecycleView);

        academicsRecycleView.SetOnItemClickListener(new AcademicsRecycleView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (view.getId() == R.id.academics_register) {
                    Intent newAcademicsDetailItent = new Intent(getActivity(), AcademicsDetailsActivity.class);
                    startActivity(newAcademicsDetailItent);
                }
                if (view.getId() == R.id.load_map) {
                    LocationDialogFragment locationDialogFragment = new LocationDialogFragment();
                    if (getChildFragmentManager().findFragmentById(R.id.acadmics_location) != null) {
                        getChildFragmentManager()
                                .beginTransaction().
                                remove(getChildFragmentManager().findFragmentById(R.id.acadmics_location)).commit();
                    }
                    Bundle args = new Bundle();
                    args.putString(LOC_PASS, "academics");
                    locationDialogFragment.setArguments(args);
                    getChildFragmentManager()
                            .beginTransaction()
                            .addToBackStack("academics_backstack")
                            .replace(R.id.acadmics_location, locationDialogFragment, "venue_location_fragment_tag")
                            .commit();
                }
            }
        });
    }

    private void dataForAcademics() {
        mAcademicsList = new ArrayList<>();
        String[] academicsMainHeading = getResources().getStringArray(R.array.name);
        String[] academicsSubHeading = getResources().getStringArray(R.array.place);
        int[] academicsImage = {R.drawable.game_logo, R.drawable.game_logo, R.drawable.game_logo, R.drawable.game_logo, R.drawable.game_logo, R.drawable.game_logo,
                R.drawable.game_logo, R.drawable.game_logo, R.drawable.game_logo, R.drawable.game_logo, R.drawable.game_logo, R.drawable.game_logo};
        int[] mAcademicsImage = {R.drawable.ic_basket_ball, R.drawable.ic_basket_court};
        ArrayList<AcademicsGameListBean> mgameList = new ArrayList<>();

        for (int j = 0; j < mAcademicsImage.length; j++) {
            AcademicsGameListBean academicsGameListBean = new AcademicsGameListBean();
            academicsGameListBean.setmGameListImage(mAcademicsImage[j]);
            mgameList.add(academicsGameListBean);
        }

        for (int i = 0; i < academicsMainHeading.length; i++) {
            Academics academics = new Academics();
            academics.setMainHeading(academicsMainHeading[i]);
            academics.setSubHeading(academicsSubHeading[i]);
            academics.setmGameList(mgameList);
            academics.setmAcademicsImage(academicsImage[i]);
            mAcademicsList.add(academics);
        }
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


}
