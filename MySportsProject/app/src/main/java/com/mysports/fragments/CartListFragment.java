package com.mysports.fragments;

import android.content.Context;
import android.net.Uri;
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
import android.widget.LinearLayout;

import com.mysports.R;
import com.mysports.adapter.CartRecycleView;
import com.mysports.adapter.VenueCartAdapter;
import com.mysports.bean.CartBean;
import com.mysports.bean.VenueCartBean;

import java.util.ArrayList;


public class CartListFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView mVenueRV;
    RecyclerView mStoreRV;
    ArrayList<VenueCartBean> mVenueCartList;
    ArrayList<CartBean> mCartList;
    LinearLayout mProceedToCheckout;

    public CartListFragment() {
        // Required empty public constructor
    }

    public static CartListFragment newInstance(String param1, String param2) {
        CartListFragment fragment = new CartListFragment();
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
        return inflater.inflate(R.layout.fragment_cart_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mStoreRV = view.findViewById(R.id.store_list);
        mVenueRV = view.findViewById(R.id.venue_list);
        mProceedToCheckout = view.findViewById(R.id.proceed_to_checkout);
        mProceedToCheckout.setOnClickListener(this);
        getVenueCartDetails();
        VenueCartAdapter venueCartAdapter = new VenueCartAdapter(getActivity(), mVenueCartList);
        mVenueRV.setAdapter(venueCartAdapter);
        mVenueRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        getStoreCartDetails();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mStoreRV.setLayoutManager(layoutManager);

        CartRecycleView cartRecycleView = new CartRecycleView(getActivity(), mCartList);
        mStoreRV.setAdapter(cartRecycleView);
    }

    private void getStoreCartDetails() {
        mCartList = new ArrayList<>();
        String[] cartProductName = {"Mizuno Women's Wave Sayonara Running Sneakers 8KN-31504 Pink running UK 7.5", "Mizuno Women's Wave Sayonara Running Sneakers 8KN-31504 Pink running UK 7.5", "Mizuno Women's Wave Sayonara Running Sneakers 8KN-31504 Pink running UK 7.5"};
        int[] cartProductImage = {R.drawable.game_logo, R.drawable.game_logo, R.drawable.game_logo};
        String[] cartQuantity = {"Qty:1", "Qty:2", "Qty:1"};
        String[] cartProductType = {"369 AED", "69 AED", "369 AED"};
        for (int i = 0; i < cartProductName.length; i++) {
            CartBean cartBean = new CartBean();
            cartBean.setmCartProductName(cartProductName[i]);
            cartBean.setmImageLogo(cartProductImage[i]);
            cartBean.setmQuantity(cartQuantity[i]);
            cartBean.setmProductType(cartProductType[i]);
            mCartList.add(cartBean);
        }
    }

    private void getVenueCartDetails() {
        mVenueCartList = new ArrayList<>();
        String[] venueName = new String[]{"Play To win Batminton Court", "Play To win Batminton Court"};
        String[] venueData = new String[]{"2018-09-03", "2018-09-03"};
        String[] venueTime = new String[]{"07:00Am-10:00Am", "07:00Am-10:00Am"};
        String[] venueSection = new String[]{"60", "60"};
        String[] priceRate = new String[]{"50.00AED", "50.00AED"};

        for (int i = 0; i < venueName.length; i++) {
            VenueCartBean venueCartBean = new VenueCartBean();
            venueCartBean.setmVenueName(venueName[i]);
            venueCartBean.setmVenueDate(venueData[i]);
            venueCartBean.setmVenueTime(venueTime[i]);
            venueCartBean.setmSession(venueSection[i]);
            venueCartBean.setmVenuePrice(priceRate[i]);
            mVenueCartList.add(venueCartBean);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.proceed_to_checkout) {
            CartCheckOutFragment cartCheckOutFragment = new CartCheckOutFragment();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.cart_holder, cartCheckOutFragment, "fragment_cart_checkout_tag");
            fragmentTransaction.addToBackStack("fragment_cart_checkout_backstack");
            fragmentTransaction.commit();
        }
    }
}
