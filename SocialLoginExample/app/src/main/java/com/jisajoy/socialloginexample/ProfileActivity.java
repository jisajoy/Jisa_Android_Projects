package com.jisajoy.socialloginexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends BaseActivity {

    @BindView(R.id.profile_image)
    ImageView profileImage;

    @BindView(R.id.name)
    TextView mName;

    @BindView(R.id.email)
    TextView mEmail;

    @BindView(R.id.user_id)
    TextView mUserId;

    AppManager mAppManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setUnBinder(ButterKnife.bind(this));
        mAppManager = AppManager.getInstance(this);
        if (!mAppManager.getProfilePic().trim().isEmpty()) {
            Picasso.with(this).load(mAppManager.getProfilePic()).into(profileImage);
        }
        mName.setText(mAppManager.getUserName());
        mEmail.setText(mAppManager.getEmailid());
        mUserId.setText(mAppManager.getUserId());
    }

    @OnClick(R.id.logout_btn)
    void logoutUser(){
        mAppManager.saveEmailId("");
        mAppManager.saveProfilePic("");
        mAppManager.saveUserName("");
        mAppManager.saveUserId("");
        Intent newMainActivity = new Intent(this, MainActivity.class);
        startActivity(newMainActivity);
        finish();
    }
}
