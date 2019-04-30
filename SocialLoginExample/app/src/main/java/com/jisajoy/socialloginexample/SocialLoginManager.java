package com.jisajoy.socialloginexample;

import android.content.Context;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class SocialLoginManager {

    private static final String TAG = "login manager";

    private LoginMvpPresenter loginMvpPresenter;

    @Inject
    public SocialLoginManager(LoginMvpPresenter loginMvpPresenter) {
        this.loginMvpPresenter = loginMvpPresenter;
    }

    void serverLogin(String email, String password){
        loginMvpPresenter.onServerLoginClick(email, password);
    }

    void facebookLogin(FirebaseUser user){
        loginMvpPresenter.onFacebookLoginClick(user);
    }

    void googleLogin(Task<GoogleSignInAccount> completedTask){
        loginMvpPresenter.onGoogleLoginClick(completedTask);
    }

    void setContext(Context context){
        loginMvpPresenter.setContext(context);
    }
}
