package com.example.hit.pnt.sharedpreferences;

import android.content.Context;

import java.util.Set;

public class DataLocalManager {
    private static final String PREF_FIRST_INSTALL = "PREF_FIRST_INSTALL";
    private static final String PREF_NAME_USERS = "PREF_NAME_USERS";

    private static DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;

    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }

    public static DataLocalManager getInstance() {
        if(instance == null) {
            instance = new DataLocalManager();
        }

        return instance;
    }

    public static void setFirstInstalled(boolean isFirst) {
        DataLocalManager.getInstance().mySharedPreferences.putBooleanValue(PREF_FIRST_INSTALL, isFirst);
    }

    public static boolean getFirstInstalled() {
        return DataLocalManager.getInstance().mySharedPreferences.getBooleanValue(PREF_FIRST_INSTALL);
    }

    public static void setNameUsers(Set<String> nameUsers) {
        DataLocalManager.getInstance().mySharedPreferences.putStringSetValue(PREF_NAME_USERS, nameUsers);
    }

    public static Set<String> getNameUsers() {
        return DataLocalManager.getInstance().mySharedPreferences.getStringSetValue(PREF_NAME_USERS);
    }
}
