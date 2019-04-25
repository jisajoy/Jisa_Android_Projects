package com.jisajoy.socialloginexample;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public interface LoginMvpPresenter {
    void onServerLoginClick(String email, String password);

    void onGoogleLoginClick(Task<GoogleSignInAccount> completedTask);

    void onFacebookLoginClick(AccessToken accessToken, FirebaseAuth mAuth);
}
