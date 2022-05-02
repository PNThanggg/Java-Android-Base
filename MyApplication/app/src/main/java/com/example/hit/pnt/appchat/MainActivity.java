package com.example.hit.pnt.appchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // thêm thư viện
//    // Scalable size unit
//    implementation 'com.intuit.sdp:sdp-android:1.0.6'
//    implementation 'com.intuit.ssp:ssp-android:1.0.6'
//
//    //Rounded ImageView
//    implementation 'com.makeramen:roundedimageview:2.3.0'
    // ở mục build.gradle/android thêm
//    buildFeatures.dataBinding = true
//    buildFeatures.viewBinding = true

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
