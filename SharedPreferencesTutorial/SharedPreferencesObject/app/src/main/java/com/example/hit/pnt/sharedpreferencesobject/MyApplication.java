package com.example.hit.pnt.sharedpreferencesobject;

import android.app.Application;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DataLocalManager.init(getApplicationContext());
    }
}
