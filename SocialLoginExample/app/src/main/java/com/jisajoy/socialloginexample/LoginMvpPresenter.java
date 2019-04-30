package com.jisajoy.socialloginexample;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

public interface LoginMvpPresenter {
    void onServerLoginClick(String email, String password);

    void onGoogleLoginClick(Task<GoogleSignInAccount> completedTask);

    void onFacebookLoginClick(FirebaseUser user);

    void setContext(Context context);
}
