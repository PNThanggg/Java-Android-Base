package com.example.hit.pnt.sharedpreferencesobject;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    public static final String MY_SHARED_PREFERENCES = "MY_SHARED_PREFERENCES";
    private Context context;

    public MySharedPreferences(Context context) {
        this.context = context;
    }

    public void putBooleanValue(String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBooleanValue(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(key, false);
    }

    public void putStringValue(String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringValue(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);

        return sharedPreferences.getString(key, "");
    }
}
