package com.mysports.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jaison Joy on 4/10/2018.
 */

public class AppManager {
    private SharedPreferences mSharedPreference;
    private static AppManager mAppManager;
    private Context mContext;
    private static final String PREF_TAG = "sp_settings";
    private static final String PREF_ISLOGGEDIN = "logged_in";

    private AppManager(Context context) {
        this.mContext = context;
        mSharedPreference = mContext.getSharedPreferences(PREF_TAG, Context.MODE_PRIVATE);
    }

    public static synchronized AppManager getInstance(Context context) {
        if (mAppManager == null)
            mAppManager = new AppManager(context);
        return mAppManager;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void saveIsLoggedIn(Boolean isLoggedIn) {
        SharedPreferences.Editor e = mSharedPreference.edit();
        e.putBoolean(PREF_ISLOGGEDIN, isLoggedIn);
        e.commit();
    }

    public Boolean getIsLoggedIn() {
        return mSharedPreference.getBoolean(PREF_ISLOGGEDIN, false);
    }
}
