package com.mysports.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mysports.R;
import com.mysports.fragments.GuestLoginFragment;
import com.mysports.fragments.LoginFragment;
import com.mysports.fragments.MyAccountFragment;
import com.mysports.fragments.RegistrationFragment;
import com.mysports.utilities.AppManager;

public class MyAccountActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar mMyAccountToolBar;
    TextView mToolbarHeading;
    ImageView mBackImage;
    RelativeLayout mUserLogin;
    RelativeLayout mRegisterNew;
    RelativeLayout mGuestLogin;
    RelativeLayout mHomeLayout;
    ImageView mBackBtn;
    AppManager mAppManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppManager = AppManager.getInstance(this);
        setContentView(R.layout.activity_my_account);
        mMyAccountToolBar = findViewById(R.id.toolbar_myaccount);
        mToolbarHeading = mMyAccountToolBar.findViewById(R.id.toolbar_heading);
        mBackImage = mMyAccountToolBar.findViewById(R.id.back_arrow);
        mBackImage.setOnClickListener(this);
        mToolbarHeading.setText("My Account");
        mUserLogin = findViewById(R.id.login);
        mUserLogin.setOnClickListener(this);
        mRegisterNew = findViewById(R.id.register_now);
        mRegisterNew.setOnClickListener(this);
        mGuestLogin = findViewById(R.id.guest_login);
        mGuestLogin.setOnClickListener(this);
        mHomeLayout = findViewById(R.id.login_home_layout);
        mBackBtn = findViewById(R.id.back_arrow);
        mBackBtn.setOnClickListener(this);

        if (mAppManager.getIsLoggedIn()) {
            mToolbarHeading.setText("My Account");
            MyAccountFragment myAccountFragment = new MyAccountFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.myaccount_ui_holder, myAccountFragment, "fragment_myaccount_tag");
            fragmentTransaction.show(myAccountFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            mToolbarHeading.setText("Login");
            LoginFragment loginFragment = new LoginFragment();
            currentLoginFrgament(loginFragment, "fragment_login_tag", "fragment_login_backstack");
        }

        if (v.getId() == R.id.register_now) {
            mToolbarHeading.setText("Register");
            RegistrationFragment registrationFragment = new RegistrationFragment();
            currentLoginFrgament(registrationFragment, "fragment_registration_tag", "fragment_register_now_backstack");
        }

        if (v.getId() == R.id.guest_login) {
            mToolbarHeading.setText("Guest Login");
            GuestLoginFragment guestLoginFragment = new GuestLoginFragment();
            currentLoginFrgament(guestLoginFragment, "fragment_guest_login_tag", "fragment_guest_login_backstack");
        }

        if (v.getId() == R.id.back_arrow) {
            LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag("fragment_login_tag");
            RegistrationFragment registrationFragment = (RegistrationFragment) getSupportFragmentManager().findFragmentByTag("fragment_registration_tag");
            GuestLoginFragment guestLoginFragment = (GuestLoginFragment) getSupportFragmentManager().findFragmentByTag("fragment_guest_login_tag");
            if (loginFragment != null && loginFragment.isVisible()) {
                backButtonPress("fragment_login_backstack");
            }
            if (registrationFragment != null && registrationFragment.isVisible()) {
                backButtonPress("fragment_register_now_backstack");
            }
            if (guestLoginFragment != null && guestLoginFragment.isVisible()) {
                backButtonPress("fragment_guest_login_backstack");
            }
        }
    }

    public void currentLoginFrgament(Fragment loginFrgament, String mFragmentTAG, String mFragmentBackstack) {
        mHomeLayout.setVisibility(View.GONE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.myaccount_ui_holder, loginFrgament, mFragmentTAG);
        fragmentTransaction.addToBackStack(mFragmentBackstack);
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.commit();
    }

    public void backButtonPress(String backstackCall) {
        mHomeLayout.setVisibility(View.VISIBLE);
        mToolbarHeading.setText("My Account");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(backstackCall, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mHomeLayout.setVisibility(View.VISIBLE);
        mToolbarHeading.setText("My Account");
    }
}
