package com.example.hit.pnt.sharedpreferencesobject;

import android.content.Context;

import com.google.gson.Gson;

public class DataLocalManager {
    private static final String PREF_FIRST_INSTALL = "PREF_FIRST_INSTALL";
    private static final String PREF_OBJECT_USERS = "PREF_NAME_USERS";

    private static DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;

    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }

    public static DataLocalManager getInstance() {
        if (instance == null) {
            instance = new DataLocalManager();
        }

        return instance;
    }

    public static void setUser(User user) {
        Gson gson = new Gson();
        String strJsonUser = gson.toJson(user);


        DataLocalManager.getInstance().mySharedPreferences.putStringValue(PREF_OBJECT_USERS, strJsonUser);
    }

    public static User getUser() {
        String strJsonUser = DataLocalManager.getInstance().mySharedPreferences.getStringValue(PREF_OBJECT_USERS);
        Gson gson = new Gson();

        return gson.fromJson(strJsonUser, User.class);
    }
}
