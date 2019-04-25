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
import android.support.v4.app.FragmentManager;
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
import com.mysports.activity.VenueProviderActivity;
import com.mysports.utilities.AppManager;


public class LoginFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextInputLayout mUserNameMaterial;
    TextInputLayout mPasswordMaterial;
    AppCompatEditText mUserName;
    AppCompatEditText mPassword;
    TextView mForgotPassword;
    RelativeLayout mLoginBtn;
AppManager mAppManager;
    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAppManager = AppManager.getInstance(getActivity());
        mUserNameMaterial = view.findViewById(R.id.user_name_material);
        mPasswordMaterial = view.findViewById(R.id.password_material);
        mUserName = view.findViewById(R.id.input_username);
        mPassword = view.findViewById(R.id.input_password);
        mForgotPassword = view.findViewById(R.id.forgot_password);
        mLoginBtn = view.findViewById(R.id.login_btn);
        mLoginBtn.setOnClickListener(this);

        ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xffE1E1E3});//hexadecimal number 0xAARRBBGG

        mUserName.setSupportBackgroundTintList(csl);// need to unit test
        mUserName.addTextChangedListener(new MyTextWatcher(mUserName));


        mPassword.setSupportBackgroundTintList(csl);
        mPassword.addTextChangedListener(new MyTextWatcher(mPassword));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_btn){
            if (mUserName.getText().toString().length() == 0 && mPassword.getText().toString().length() == 0) {
                Toast.makeText(getActivity(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();
            } else if (mUserName.getText().toString().length() == 0) {
                if (!validateUserName()) ;
            } else if (mPassword.getText().toString().length() == 0) {
                if (!validatePassword()) ;
            } else {
                mAppManager.saveIsLoggedIn(true);
                getActivity().finish();
                Toast.makeText(getActivity(), "Logged In", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateUserName() {
        if (mUserName.getText().toString().length() == 0) {
            mUserNameMaterial.setError("Enter a User Name");
            requestFocus(mUserName);
            return false;
        } else {
            mUserNameMaterial.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword() {
        if (mPassword.getText().toString().length() == 0) {
            mPasswordMaterial.setError("Enter a Password");
            requestFocus(mPassword);
            return false;
        } else {
            mPasswordMaterial.setErrorEnabled(false);
        }
        return true;
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
                case R.id.input_username:
                    validateUserName();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
            }
        }
    }

}
