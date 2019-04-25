package com.jisajoy.socialloginexample;

import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;

public class BaseActivity extends AppCompatActivity {

    private Unbinder mUnBinder;


    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }
}
