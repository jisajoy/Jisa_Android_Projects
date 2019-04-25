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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mysports.R;
import com.mysports.utilities.AppManager;


public class GuestLoginFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextInputLayout mMobileMaterial;
    TextInputLayout mOTPMaterial;
    AppCompatEditText mMobile;
    AppCompatEditText mOTP;
    RelativeLayout mGuestLoginBtn;
AppManager mAppManager;
    public GuestLoginFragment() {
        // Required empty public constructor
    }


    public static GuestLoginFragment newInstance(String param1, String param2) {
        GuestLoginFragment fragment = new GuestLoginFragment();
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
        return inflater.inflate(R.layout.fragment_guest_login, container, false);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAppManager = AppManager.getInstance(getActivity());
        mMobileMaterial = view.findViewById(R.id.mobile_material);
        mOTPMaterial = view.findViewById(R.id.otp_material);
        mMobile = view.findViewById(R.id.registered_mobile);
        mOTP = view.findViewById(R.id.otp);
        mGuestLoginBtn = view.findViewById(R.id.guest_login_btn);
        mGuestLoginBtn.setOnClickListener(this);

        ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xffE1E1E3});//hexadecimal number 0xAARRBBGG

        mMobile.setSupportBackgroundTintList(csl);// need to unit test
        mMobile.addTextChangedListener(new MyTextWatcher(mMobile));

        mOTP.setSupportBackgroundTintList(csl);
        mOTP.addTextChangedListener(new MyTextWatcher(mOTP));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.guest_login_btn) {
            if (mMobile.getText().toString().length() == 0 && mOTP.getText().toString().length() == 0) {
                Toast.makeText(getActivity(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();
            } else if (mMobile.getText().toString().length() == 0) {
                if (!validateMobile()) ;
            } else if (mOTP.getText().toString().length() == 0) {
                if (!validateOTP()) ;
            } else {
                mAppManager.saveIsLoggedIn(true);
                Toast.makeText(getActivity(), "Logged In", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        }
    }

    private boolean validateMobile() {
        if (mMobile.getText().toString().length() == 0) {
            mMobileMaterial.setError("Enter a Mobile Number");
            requestFocus(mMobile);
            return false;
        } else {
            mMobileMaterial.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateOTP() {
        if (mOTP.getText().toString().length() == 0) {
            mOTPMaterial.setError("Enter a OTP");
            requestFocus(mOTP);
            return false;
        } else {
            mOTPMaterial.setErrorEnabled(false);
            return true;
        }
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public class MyTextWatcher implements TextWatcher {

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
                case R.id.registered_mobile:
                    validateMobile();
                    break;
                case R.id.otp:
                    validateOTP();
                    break;
            }
        }
    }
}
