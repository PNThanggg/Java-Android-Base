package com.monstertechno.introslider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    int[] images = {R.drawable.slider_one, R.drawable.slider_two, R.drawable.slider_three};
    String[] header = {"Order Nearby", "Points", "Vouchers"};
    String[] description = {
            "Never missed our fun-Tastic products, find our nearest branch at your tips.",
            "Buy and rewards, collects your point to get the rewards.",
            "Convert your points to get the voucher convert to other Citra business never been easier"
    };
    ViewPagerAdapter adapter;

    CircleIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter(this, images,header,description);
        viewPager.setAdapter(adapter);

        indicator = findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
    }
}