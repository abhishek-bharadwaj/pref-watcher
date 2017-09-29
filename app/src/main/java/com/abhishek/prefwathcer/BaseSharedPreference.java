package com.abhishek.prefwathcer;

import android.content.SharedPreferences;

public abstract class BaseSharedPreference {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public BaseSharedPreference(SharedPreferences preference) {
        prefs = preference;
        editor = prefs.edit();
    }

    public SharedPreferences getPrefs() {
        return prefs;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public void clear() {
        // Clean up the entire profile
        editor.clear().commit();
    }

    public void commit() {
        commit(editor);
    }

    private void commit(SharedPreferences.Editor editor) {
        editor.apply();
    }
}
