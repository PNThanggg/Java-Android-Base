package com.example.hit.pnt.sharedpreferences;

import android.app.Application;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DataLocalManager.init(getApplicationContext());
        if(!DataLocalManager.getFirstInstalled()) {
            Toast.makeText(getApplicationContext(), "First installed app", Toast.LENGTH_LONG).show();
            DataLocalManager.setFirstInstalled(true);
        }

        Set<String> nameUsers = new HashSet<>();

        nameUsers.add("Abc");
        nameUsers.add("Abc1");
        nameUsers.add("Abc2");

        DataLocalManager.setNameUsers(nameUsers);
    }
}
