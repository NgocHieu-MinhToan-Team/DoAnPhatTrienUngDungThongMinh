package com.example.pepperluchapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private static final String PREF_NAME = "androidhive-welcome";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static PrefManager mInstance;
    SharedPreferences mPref;
    SharedPreferences.Editor mEditor;
    Context mContext;
    int PRIVATE_MODE = 0;

    public PrefManager(Context context) {
        this.mContext = context;
        mPref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        mEditor = mPref.edit();
    }

    public static PrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new PrefManager(context);
        }
        return mInstance;
    }

    public void setFirstimeLaunch(boolean isFirstTime) {
        mEditor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        mEditor.apply();
    }

    public boolean isFirstTimeLaunch() {
        return mPref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}

