package com.jisajoy.socialloginexample;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jaison Joy on 2/8/2018.
 */

public class AppManager {
    private SharedPreferences mSharedPreference;
    private static AppManager mAppManager;
    private Context mContext;
    private final static String PREF_TAG = "spsettings";
    public static final String PREF_USER_NAME = "user_name";
    public static final String PREF_USER_EMAIL = "user_email";
    public static final String PREF_USER_MOBILENO = "user_mobileno";
    public static final String PREF_USER_ID = "user_id";
    public static final String PREF_PROFILE_PIC = "profile_pic";
    public static final String PREF_LASTNNAME = "last_name";
    public static final String PREF_FIRSTNAME = "first_name";
    public static final String PREF_PHONE_NUMBER = "phone_number";

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

    public void saveUserName(String useName) {
        SharedPreferences.Editor e = mSharedPreference.edit();
        e.putString(PREF_USER_NAME, useName);
        e.commit();
    }

    public String getUserName() {
        return mSharedPreference.getString(PREF_USER_NAME, "");
    }

    public void saveEmailId(String emailid) {
        SharedPreferences.Editor e = mSharedPreference.edit();
        e.putString(PREF_USER_EMAIL, emailid);
        e.commit();
    }

    public String getEmailid() {
        return mSharedPreference.getString(PREF_USER_EMAIL, "");
    }


    public void saveMobileNo(String mobileNo) {
        SharedPreferences.Editor e = mSharedPreference.edit();
        e.putString(PREF_USER_MOBILENO, mobileNo);
        e.commit();
    }

    public String getMobileNo() {
        return mSharedPreference.getString(PREF_USER_MOBILENO, "");
    }


    public void saveUserId(String loginId) {
        SharedPreferences.Editor e = mSharedPreference.edit();
        e.putString(PREF_USER_ID, loginId);
        e.commit();
    }

    public String getUserId() {
        return mSharedPreference.getString(PREF_USER_ID, "");
    }


    public void saveProfilePic(String profilePic) {
        SharedPreferences.Editor e = mSharedPreference.edit();
        e.putString(PREF_PROFILE_PIC, profilePic);
        e.commit();
    }

    public String getProfilePic() {
        return mSharedPreference.getString(PREF_PROFILE_PIC, "");
    }



    public void saveLastName(String lastName) {
        SharedPreferences.Editor e = mSharedPreference.edit();
        e.putString(PREF_LASTNNAME, lastName);
        e.commit();
    }

    public String getLastName() {
        return mSharedPreference.getString(PREF_LASTNNAME, "");
    }

    public void saveFirstName(String lastName) {
        SharedPreferences.Editor e = mSharedPreference.edit();
        e.putString(PREF_FIRSTNAME, lastName);
        e.commit();
    }

    public String getFirstName() {
        return mSharedPreference.getString(PREF_FIRSTNAME, "");
    }


    public void savePhoneNumber(String lastName) {
        SharedPreferences.Editor e = mSharedPreference.edit();
        e.putString(PREF_PHONE_NUMBER, lastName);
        e.commit();
    }

    public String getPhoneNumber() {
        return mSharedPreference.getString(PREF_PHONE_NUMBER, "");
    }

}
