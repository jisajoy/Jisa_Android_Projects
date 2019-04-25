package com.mysports.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.mysports.R;
import com.mysports.activity.StoreActivity;
import com.mysports.activity.VenuesActivity;
import com.mysports.interfacepackage.OnFragmentInteractionListener;

import java.util.HashMap;


public class HomeFragment extends Fragment  implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OnFragmentInteractionListener mListener;
    SliderLayout sliderLayout;
    HashMap<String, String> hash_files_maps;
    RelativeLayout mVenues;
    RelativeLayout mStore;
    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVenues = view.findViewById(R.id.venues);
        mVenues.setOnClickListener(this);
        mStore = view.findViewById(R.id.store);
        mStore.setOnClickListener(this);
        hash_files_maps = new HashMap<String, String>();
        sliderLayout = (SliderLayout) view.findViewById(R.id.slider);

        hash_files_maps.put("Cup Cake", "http://www.icons101.com/icon_ico/id_83003/Android_15_Cupcake.ico");
        hash_files_maps.put("Donut", "http://androidversion.info/wp-content/uploads/2014/10/donut_icon.png");
        hash_files_maps.put("Eclair", "http://www.icons101.com/icon_ico/id_83005/Android_20_Eclair.ico");
        hash_files_maps.put("Froyo", "http://goodereader.com/blog/uploads/images/android-froyo.png");
        hash_files_maps.put("GingerBread", "https://stocklogos.com/sites/default/files/android-logos-gingerbread.png");
        hash_files_maps.put("Lolipop", "http://www.icons101.com/icon_ico/id_83012/Android_50_Lollipop.ico");
        hash_files_maps.put("Mashmallow", "https://assets.materialup.com/uploads/80a76de8-da1e-4622-b75e-8b58b5b6aeb2/oZK2sRk95CaKTqkyrIOODEdM73JWHzWIbn1FsMHxOENqJeMffuaCwQQFThkdXil3g5Q=w300");
        hash_files_maps.put("Nougat", "https://www.naijaparole.com/wp-content/uploads/2016/06/nougat-660x330.png");

        for (String name : hash_files_maps.keySet()){
            TextSliderView sliderView = new TextSliderView(getActivity());
            sliderView.image(hash_files_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.FitCenterCrop)
                    .setOnSliderClickListener(this);
            sliderLayout.addSlider(sliderView);

            /*.description(name)
            *  .bundle(new Bundle())
                    .getBundle().putString("extra", name);*/
        }
       /* sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
       *//* sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);*//*
        sliderLayout.setCustomAnimation(new DescriptionAnimation());*/
        sliderLayout.setDuration(5000);
        sliderLayout.addOnPageChangeListener(this);
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
    public void onSliderClick(BaseSliderView slider) {
        //Toast.makeText(getActivity(), slider.getBundle().get("extra")+"", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.venues){
            Intent newVenueIntent = new Intent(getContext(), VenuesActivity.class);
            startActivity(newVenueIntent);
        }
        if (view.getId() == R.id.store){
            Intent newStoreIntent = new Intent(getContext(), StoreActivity.class);
            startActivity(newStoreIntent);
        }
    }
}
