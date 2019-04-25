package com.mysports.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mysports.R;
import com.mysports.activity.GalleryExpandActivity;
import com.mysports.adapter.ImageGalleryRecycleView;
import com.mysports.bean.GalleryBean;
import com.mysports.interfacepackage.OnFragmentInteractionListener;
import com.mysports.interfacepackage.OnVenueFragmentInterationListener;

import java.util.ArrayList;


public class VenueImagesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnVenueFragmentInterationListener mListener;
    ArrayList<GalleryBean> mGalleryList;
    RecyclerView mGalleryrv;

    public VenueImagesFragment() {
        // Required empty public constructor
    }


    public static VenueImagesFragment newInstance(String param1, String param2) {
        VenueImagesFragment fragment = new VenueImagesFragment();
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
        return inflater.inflate(R.layout.fragment_venue_images, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        galleryimageList();
        mGalleryrv = view.findViewById(R.id.image_list_rv);
        ImageGalleryRecycleView galleryRecycleView = new ImageGalleryRecycleView(getActivity(), mGalleryList);
        mGalleryrv.setAdapter(galleryRecycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mGalleryrv.setLayoutManager(gridLayoutManager);

        galleryRecycleView.SetOnClickListener(new ImageGalleryRecycleView.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Intent newGalleryExpand = new Intent(getActivity(), GalleryExpandActivity.class);
                newGalleryExpand.putExtra("image_position", position);
                startActivity(newGalleryExpand);
            }
        });
    }

    private void galleryimageList() {
        mGalleryList = new ArrayList<>();
        String[] galleryList = new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwUR7Qvw0YAXeNX8gP9_OCOCfkK3gmUs57zOsG7RtDtK-lCwzCHg", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKnoMpGLQQic7WRyd9k5b_OXpWRB42otzXOpBvCfelDhdwxBjYqQ",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQfqNRrsDy2DEHpnMyT5BZyDPbEcHMZEGlxuQonG7uBuA-n6Uze", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVPiFZVOzl4YtwlYueYFYeCpe9D8QtYKA0jkXdBtO8BDmwY7xADQ"};
        for (int i = 0; i < galleryList.length; i++) {
            GalleryBean galleryBean = new GalleryBean();
            galleryBean.setImageURL(galleryList[i]);
            mGalleryList.add(galleryBean);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnVenueFragmentInterationListener) {
            mListener = (OnVenueFragmentInterationListener) context;
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
