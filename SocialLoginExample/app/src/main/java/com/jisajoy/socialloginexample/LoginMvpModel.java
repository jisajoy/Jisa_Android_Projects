package com.jisajoy.socialloginexample;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class LoginMvpModel implements LoginMvpPresenter{

    private static final String TAG = "LoginMvpPresenter";
    AppManager mAppManager;
Context mContext;
    @Inject
    public LoginMvpModel() {
    }

    @Override
    public void onServerLoginClick(String email, String password) {
        if (email.trim().isEmpty() & password.trim().isEmpty()){
            Toast.makeText(mContext, "Enter UserName and password", Toast.LENGTH_SHORT).show();
        }else if (email.trim().isEmpty()){
            Toast.makeText(mContext, "Enter UserName", Toast.LENGTH_SHORT).show();
        }else if (password.trim().isEmpty()){
            Toast.makeText(mContext, "Enter password", Toast.LENGTH_SHORT).show();
        }else {
            mAppManager.saveUserName("Server Login");
            mAppManager.saveEmailId(email);
            mAppManager.saveUserId("1100");
            mAppManager.saveProfilePic("");
            Toast.makeText(mContext, "Success Server Login", Toast.LENGTH_SHORT).show();
            Intent newProfileIntent = new Intent(mContext, ProfileActivity.class);
            mContext.startActivity(newProfileIntent);
        }

    }

    @Override
    public void onGoogleLoginClick(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateGoogleLoginUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateGoogleLoginUI(null);
        }
    }

    @Override
    public void onFacebookLoginClick(FirebaseUser user) {
        mAppManager.saveUserName(user.getDisplayName());
        mAppManager.saveProfilePic(user.getPhotoUrl()+"");
        mAppManager.saveEmailId(user.getEmail());
        mAppManager.saveUserId(user.getProviderId());
        Toast.makeText(mContext, "Success Facebook Login", Toast.LENGTH_SHORT).show();
        Intent newProfileIntent = new Intent(mContext, ProfileActivity.class);
        mContext.startActivity(newProfileIntent);
    }

    @Override
    public void setContext(Context context) {
        mContext = context;
        mAppManager = AppManager.getInstance(context);
    }


    private void updateGoogleLoginUI(GoogleSignInAccount currentUser) {

mAppManager.saveUserName(currentUser.getDisplayName());
mAppManager.saveProfilePic(currentUser.getPhotoUrl()+"");
mAppManager.saveEmailId(currentUser.getEmail());
mAppManager.saveUserId(currentUser.getId());
        Toast.makeText(mContext, "Success Google Login", Toast.LENGTH_SHORT).show();
        Intent newProfileIntent = new Intent(mContext, ProfileActivity.class);
        mContext.startActivity(newProfileIntent);
    }

    }
