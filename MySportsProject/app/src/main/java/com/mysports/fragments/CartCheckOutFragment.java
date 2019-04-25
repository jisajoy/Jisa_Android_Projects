package com.mysports.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mysports.R;
import com.mysports.activity.VenueProviderActivity;
import com.mysports.adapter.ShippingDetailAdapter;
import com.mysports.bean.ShippingDetails;
import com.mysports.utilities.MyApplication;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartCheckOutFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView mShippingDetailsRV;
    ArrayList<ShippingDetails> mAddressList;
    RelativeLayout mShippingDetails;
    public static boolean shippingDetailsView;
    RelativeLayout mNewShippingAddress;
    public static boolean newShippingAddressView;
    LinearLayout mNewAddressLayout;
    RelativeLayout mSaveNewAddress;
    public static boolean newEditaddress;
    TextInputLayout mInputLayoutName;
    TextInputLayout mInputLayoutMobile;
    TextInputLayout mInputLayoutAddress;
    TextInputLayout mInputLayoutCity;
    TextInputLayout mInputLayoutArea;
    TextInputLayout mInputLayoutCountry;

    AppCompatEditText mInputName;
    AppCompatEditText mInputMobile;
    AppCompatEditText mInputAddress;
    AppCompatEditText mInputCity;
    AppCompatEditText mInputArea;
    AppCompatEditText mInputCountry;


    LinearLayout mEditAddress;
    RelativeLayout mEditAddressLayout;
    RecyclerView mEditAddressRV;

    public CartCheckOutFragment() {
        // Required empty public constructor
    }

    public static CartCheckOutFragment newInstance(String param1, String param2) {
        CartCheckOutFragment fragment = new CartCheckOutFragment();
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
        return inflater.inflate(R.layout.fragment_cart_check_out, container, false);
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shippingDetailsView = true;
        newShippingAddressView = false;
        newEditaddress = false;
        ColorStateList colorSlate = new ColorStateList(new int[][]{new int[0]}, new int[]{0xffE1E1E3});//hexadecimal number 0xAARRBBGG
        mInputLayoutName = view.findViewById(R.id.input_layout_name);
        mInputLayoutMobile = view.findViewById(R.id.input_layout_mobile);
        mInputLayoutAddress = view.findViewById(R.id.input_layout_address);
        mInputLayoutCity = view.findViewById(R.id.input_layout_city);
        mInputLayoutArea = view.findViewById(R.id.input_layout_area);
        mInputLayoutCountry = view.findViewById(R.id.input_layout_country);

        mInputName = view.findViewById(R.id.input_name);
        mInputName.setSupportBackgroundTintList(colorSlate);// need to unit test in kitkat and lower version OS
        mInputName.addTextChangedListener(new MyTextWatcher(mInputName));

        mInputMobile = view.findViewById(R.id.input_mobile);
        mInputMobile.setSupportBackgroundTintList(colorSlate);
        mInputMobile.addTextChangedListener(new MyTextWatcher(mInputMobile));

        mInputAddress = view.findViewById(R.id.input_address);
        mInputAddress.setSupportBackgroundTintList(colorSlate);
        mInputAddress.addTextChangedListener(new MyTextWatcher(mInputAddress));

        mInputCity = view.findViewById(R.id.input_city);
        mInputCity.setSupportBackgroundTintList(colorSlate);
        mInputCity.addTextChangedListener(new MyTextWatcher(mInputCity));

        mInputArea = view.findViewById(R.id.input_area);
        mInputArea.setSupportBackgroundTintList(colorSlate);
        mInputArea.addTextChangedListener(new MyTextWatcher(mInputArea));

        mInputCountry = view.findViewById(R.id.input_country);
        mInputCountry.setSupportBackgroundTintList(colorSlate);
        mInputCountry.addTextChangedListener(new MyTextWatcher(mInputCountry));

        mEditAddress = view.findViewById(R.id.edit_address);
        mEditAddress.setVisibility(View.GONE);

        mEditAddressLayout = view.findViewById(R.id.edit_address_layout);
        mEditAddressLayout.setOnClickListener(this);

        mEditAddressRV = view.findViewById(R.id.edit_address_list);
        mNewAddressLayout = view.findViewById(R.id.new_address_layout);
        mNewAddressLayout.setVisibility(View.GONE);
        mNewShippingAddress = view.findViewById(R.id.add_new_address);
        mNewShippingAddress.setOnClickListener(this);
        mShippingDetailsRV = view.findViewById(R.id.shipping_details_rv);
        mShippingDetails = view.findViewById(R.id.shipping_details);
        mShippingDetails.setOnClickListener(this);
        shippingDetails();
        ShippingDetailAdapter shippingDetailAdapter = new ShippingDetailAdapter(getActivity(), mAddressList);
        mShippingDetailsRV.setAdapter(shippingDetailAdapter);
        mShippingDetailsRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSaveNewAddress = view.findViewById(R.id.add_new_address_btn);
        mSaveNewAddress.setOnClickListener(this);
    }

    private void shippingDetails() {
        mAddressList = new ArrayList<>();
        String[] personName = new String[]{"faisal", "faisal"};
        String[] mobileNo = new String[]{"+91 7736569688", "+91 7736569688"};
        String[] streetAddress = new String[]{"Saraya avanue, Gharhood, office no: #304", "Saraya avanue, Gharhood, office no: #304"};
        String[] city = new String[]{"Dubai", "Dubai"};
        String[] area = new String[]{"Al Karama", "Al Karama"};
        String[] country = new String[]{"UAE", "UAE"};
        Boolean[] activeAddress = new Boolean[]{true, false};
        for (int i = 0; i < personName.length; i++) {
            ShippingDetails shippingDetails = new ShippingDetails();
            shippingDetails.setPersonAddress(streetAddress[i]);
            shippingDetails.setPersonArea(area[i]);
            shippingDetails.setPersonCity(city[i]);
            shippingDetails.setPersonCountry(country[i]);
            shippingDetails.setPersonMobileNo(mobileNo[i]);
            shippingDetails.setPersonName(personName[i]);
            shippingDetails.setAddressNo((i + 1) + "");
            shippingDetails.setAddressActive(activeAddress[i]);
            mAddressList.add(shippingDetails);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.shipping_details) {// do not disturb
            if (shippingDetailsView) {
                shippingDetailsView = false;
                mShippingDetailsRV.setVisibility(View.GONE);
            } else {
                if (newShippingAddressView) {
                    newShippingAddressView = true;
                    mNewAddressLayout.setVisibility(View.GONE);
                }
                if (newEditaddress) {
                    newEditaddress = true;
                    mEditAddress.setVisibility(View.GONE);
                }
                shippingDetailsView = true;
                mShippingDetailsRV.setVisibility(View.VISIBLE);
            }
        }
        if (v.getId() == R.id.add_new_address) {
            if (newShippingAddressView) {
                newShippingAddressView = false;
                mNewAddressLayout.setVisibility(View.GONE);
            } else {
                if (shippingDetailsView) {
                    shippingDetailsView = true;
                    mShippingDetailsRV.setVisibility(View.GONE);
                }
                if (newEditaddress) {
                    newEditaddress = true;
                    mEditAddress.setVisibility(View.GONE);
                }
                newShippingAddressView = true;
                mNewAddressLayout.setVisibility(View.VISIBLE);
            }
        }

        if (v.getId() == R.id.edit_address_layout) {
            if (newEditaddress) {
                if (shippingDetailsView) {
                    shippingDetailsView = true;
                    mShippingDetailsRV.setVisibility(View.GONE);
                } else {
                    if (newShippingAddressView) {
                        newShippingAddressView = true;
                        mNewAddressLayout.setVisibility(View.GONE);
                    }
                }
                newEditaddress = false;
                mEditAddress.setVisibility(View.VISIBLE);
            } else {
                newEditaddress = true;
                mEditAddress.setVisibility(View.GONE);
            }
        }
        if (v.getId() == R.id.add_new_address_btn) {
            if (mInputName.getText().toString().length() == 0 && mInputMobile.getText().toString().length() == 0 && mInputAddress.getText().toString().length() == 0 &&
                    mInputCity.getText().toString().length() == 0 && mInputArea.getText().toString().length() == 0 && mInputCountry.getText().toString().length() == 0) {
                Toast.makeText(getActivity(), "Please Fill all Fields", Toast.LENGTH_SHORT).show();
            } else if (mInputName.getText().toString().length() == 0) {
                if (!validateName()) ;
            } else if (mInputMobile.getText().toString().length() == 0) {
                if (!validateMobile()) ;
            } else if (mInputAddress.getText().toString().length() == 0) {
                if (!validateInputAddress()) ;
            } else if (mInputCity.getText().toString().length() == 0) {
                if (validateCity()) ;
            } else if (mInputArea.getText().toString().length() == 0) {
                if (!validateArea()) ;
            } else if (mInputCountry.getText().toString().length() == 0) {
                if (!validateCountry()) ;
            } else {
                Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
            }
        }

        if (v.getId() == R.id.edit_address_layout) {

        }
    }

    private boolean validateName() {
        if (mInputName.getText().toString().length() == 0) {
            mInputLayoutName.setError("Please Enter Name");
            requestFocus(mInputName);
            return false;
        } else {
            mInputLayoutName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMobile() {
        if (mInputMobile.getText().toString().trim().length() == 0) {
            mInputLayoutMobile.setError("Please Enter Mobile");
            requestFocus(mInputMobile);
            return false;
        } else {
            mInputLayoutMobile.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateInputAddress() {
        if (mInputAddress.getText().toString().trim().length() == 0) {
            mInputLayoutAddress.setError("Please Enter Address");
            requestFocus(mInputAddress);
            return false;
        } else {
            mInputLayoutAddress.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateCity() {
        if (mInputCity.getText().toString().length() == 0) {
            mInputLayoutCity.setError("Please Enter City");
            requestFocus(mInputCity);
            return false;
        } else {
            mInputLayoutCity.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateArea() {
        if (mInputArea.getText().toString().length() == 0) {
            mInputLayoutArea.setError("Please Enter Area");
            requestFocus(mInputArea);
            return false;
        } else {
            mInputLayoutArea.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateCountry() {
        if (mInputCountry.getText().toString().length() == 0) {
            mInputLayoutCountry.setError("Please Enter Country");
            requestFocus(mInputCountry);
            return false;
        } else {
            mInputLayoutCountry.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_mobile:
                    validateMobile();
                    break;
                case R.id.input_address:
                    validateInputAddress();
                    break;
                case R.id.input_area:
                    validateArea();
                    break;
                case R.id.input_city:
                    validateCity();
                    break;
                case R.id.input_country:
                    validateCountry();
                    break;
                default:
                    //nothing
            }
        }
    }

}
