package com.jisajoy.socialloginexample;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Arrays;
import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    Button mGoogleLoginBtn;
    Button mFacebookLoginBtn;
    Button mTwitterLoginBtn;
    TextView mName;
    TextView mUserName;
    TextView mEmailId;
    TextView userId;
    TextView phoneNumber;
    ImageView mProfilePhoto;
    CallbackManager mCallbackManager;
    LoginManager loginManager;
    FirebaseAuth mAuth;

    LoginMvpPresenter mPresenter;

    @BindView(R.id.emailid_login)
    EditText mEmailEditText;

    @BindView(R.id.login_password)
    EditText mPasswordEditText;

    private static final int RC_SIGN_IN = 9001;
    public static final String TAG = "Social Login";
    private Collection<String> permissions = Arrays.asList("public_profile ", "email");


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       mAuth = FirebaseAuth.getInstance();
        setUnBinder(ButterKnife.bind(this));

       mPresenter = new MainActivity();
    }

    @OnClick(R.id.login_btn)
    void onServerLoginClick(View v) {// click to the login button
        mPresenter.onServerLoginClick(mEmailEditText.getText().toString(),
                mPasswordEditText.getText().toString());
    }


    @OnClick(R.id.google_login)
    void onGoogleLoginClick(View v) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @OnClick(R.id.facebook_login)
    void onFbLoginClick(View v) {
        mCallbackManager = CallbackManager.Factory.create();
        loginManager = LoginManager.getInstance();
        loginManager.logInWithReadPermissions(this, permissions);
        loginManager.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (loginResult != null) {
                    handleFacebookAccessToken(loginResult.getAccessToken());
                }
            }

            @Override
            public void onCancel() {
                //progressDialog.dismiss();
                // fbSignInListener.OnFbSignInComplete(null, "User cancelled.");
            }

            @Override
            public void onError(FacebookException exception) {
                if (exception instanceof FacebookAuthorizationException) {
                    if (AccessToken.getCurrentAccessToken() != null) {
                        LoginManager.getInstance().logOut();
                    }
                    //progressDialog.dismiss();
                }
                // fbSignInListener.OnFbSignInComplete(null, exception.getMessage());
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken accessToken) {
        Log.d(TAG, "handleFacebookAccessToken:" + accessToken);
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //progressDialog.dismiss();
                            UIwithFacebookLogin(user);

                        } else {
                            //progressDialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //  updateUIwithFacebookLogin(null);
                        }

                        // ...
                    }
                });
    }
    private void UIwithFacebookLogin(FirebaseUser user) {
        //  System.out.println("user_val" + mFbUserId);
        System.out.println("user_name" +user.getDisplayName());
        System.out.println("user_email" + user.getEmail());
        System.out.print("phone_number"+user.getPhoneNumber());
        System.out.println("photo_url"+user.getPhotoUrl());
        //MyApplication.faceBookEmailId=user.getEmail();
        //MyApplication.facebookUserName=user.getDisplayName();

//        Toast.makeText(this, ""+user.getEmail(), Toast.LENGTH_SHORT).show();
//        mFBemailId = user.getEmail();
//        mFBUserName = user.getDisplayName();



    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            UIwithFacebookLogin(currentUser);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("resultvalue", requestCode+" "+resultCode);
        if (resultCode == RESULT_OK) {
            // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
            if (requestCode == RC_SIGN_IN) {
                // The Task returned from this call is always completed, no need to attach
                // a listener.
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);
            }else {
                mCallbackManager.onActivityResult(requestCode, resultCode, data);
            }


        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }


    private void updateUI(GoogleSignInAccount currentUser) {
        //Picasso.with(this).load(currentUser.getPhotoUrl()).centerInside().into(mProfilePhoto);
        mName.setText(currentUser.getDisplayName());
        mUserName.setText(currentUser.getGivenName());
        mEmailId.setText(currentUser.getEmail());
        userId.setText(currentUser.getIdToken());
        phoneNumber.setText(currentUser.getServerAuthCode());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }
}
