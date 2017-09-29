package com.abhishek.prefwathcer;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

/**
 * Created by abhishek on 29/9/17.
 */

class UserPref extends BaseSharedPreference {

    private static final String PREF_NAME = "pref_user";
    private static final String KEY_NAME = "name";

    private static UserPref pref;

    private UserPref(SharedPreferences preference) {
        super(preference);
    }

    static UserPref getInstance(Context context) {
        if (pref == null) {
            pref = new UserPref(context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE));
        }
        return pref;
    }

    void setName(String name) {
        getEditor().putString(KEY_NAME, name).commit();
    }

    @Nullable
    public String getName() {
        return getPrefs().getString(KEY_NAME, null);
    }
}

