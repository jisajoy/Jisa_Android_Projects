package com.mysports.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mysports.R;
import com.mysports.custom.MyArrayAdapter;
import com.mysports.utilities.AppManager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextInputLayout mInputName;
    TextInputLayout mInputMobile;
    TextInputLayout mInputEmail;
    TextInputLayout mInputPassword;
    TextInputLayout mInputConfirmPassword;

    AppCompatEditText mName;
    AppCompatEditText mMobile;
    AppCompatEditText mEmail;
    AppCompatEditText mPassword;
    AppCompatEditText mConfirmPassword;

    RelativeLayout mRegisterUser;
    AppCompatSpinner mInterestInSports;
    ArrayList<String> mSportsList;
AppManager mAppManager;
    public RegistrationFragment() {
        // Required empty public constructor
    }

    public static RegistrationFragment newInstance(String param1, String param2) {
        RegistrationFragment fragment = new RegistrationFragment();
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
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAppManager = AppManager.getInstance(getActivity());
        mInputName = view.findViewById(R.id.input_layout_name);
        mInputMobile = view.findViewById(R.id.input_layout_mobile);
        mInputEmail = view.findViewById(R.id.input_layout_email);
        mInputPassword = view.findViewById(R.id.input_layout_password);
        mInputConfirmPassword = view.findViewById(R.id.input_layout_confirm_password);

        mName = view.findViewById(R.id.input_name);
        mMobile = view.findViewById(R.id.input_mobile);
        mEmail = view.findViewById(R.id.input_email);
        mPassword = view.findViewById(R.id.input_password);
        mConfirmPassword = view.findViewById(R.id.input_confirm_password);

        mRegisterUser = view.findViewById(R.id.register_user);
        mRegisterUser.setOnClickListener(this);

        mInterestInSports = view.findViewById(R.id.spinner_sports_interest);

        mSportsList = new ArrayList<>();
        mSportsList.add("Interested Sports");
        mSportsList.add("FootBall");
        mSportsList.add("Batminton");
        mSportsList.add("FootBall");
        mSportsList.add("BasketBall");
        seSportsListData(mSportsList, mInterestInSports);
    }

    private void seSportsListData(ArrayList<String> mSportsList, AppCompatSpinner mInterestInSports) {
        MyArrayAdapter arrayAdapterTitle = new MyArrayAdapter(getActivity(), R.layout.layout_drop_title_black, mSportsList, -1);
        arrayAdapterTitle.setDropDownViewResource(R.layout.layout_drop_list);
        mInterestInSports.setAdapter(arrayAdapterTitle);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register_user) {
            if (mName.getText().toString().length() == 0 && mMobile.getText().toString().length() == 0 && mEmail.getText().toString().length() == 0 &&
                    mPassword.getText().toString().length() == 0 && mConfirmPassword.getText().toString().length() == 0) {
                Toast.makeText(getActivity(), "All Fields Mandatory", Toast.LENGTH_SHORT).show();
            } else if (mName.getText().toString().length() == 0) {
                if (!validateName()) ;
            } else if (mMobile.getText().toString().length() == 0) {
                if (!validateMobile()) ;
            } else if (mEmail.getText().toString().length() == 0) {
                if (!validateEmail()) ;
            } else if (!validEmail(mEmail.getText().toString())) {
                if (!validateEmailPattern()) ;
            } else if (mPassword.getText().toString().length() == 0) {
                if (!validatePassword()) ;
            } else if (mConfirmPassword.getText().toString().length() == 0) {
                if (!validateConfirmPassword()) ;
            } else {
                mAppManager.saveIsLoggedIn(true);
                Toast.makeText(getActivity(), "SuccessFully Registered", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        }
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validateName() {
        if (mName.getText().toString().length() == 0) {
            mInputName.setError("Enter Your Name");
            requestFocus(mName);
            return false;
        } else {
            mInputName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMobile() {
        if (mMobile.getText().toString().length() == 0) {
            mInputMobile.setError("Enter Your Mobile Number");
            requestFocus(mMobile);
            return false;
        } else {
            mInputMobile.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {
        if (mEmail.getText().toString().length() == 0) {
            mInputEmail.setError("Enter Your Email Address");
            requestFocus(mEmail);
            return false;
        } else {
            mInputEmail.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmailPattern() {
        if (!validEmail(mEmail.getText().toString())) {
            mInputEmail.setError("Enter a Valid Email Address");
            requestFocus(mEmail);
            return false;
        } else {
            mInputEmail.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validatePassword() {
        if (mPassword.getText().toString().length() == 0) {
            mInputPassword.setError("Enter Your Password");
            requestFocus(mPassword);
            return false;
        } else {
            mInputPassword.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateConfirmPassword() {
        if (mConfirmPassword.getText().toString().length() == 0) {
            mInputConfirmPassword.setError("Confirm Your Password");
            requestFocus(mConfirmPassword);
            return false;
        } else {
            mInputConfirmPassword.setErrorEnabled(false);
        }
        return true;
    }
}
