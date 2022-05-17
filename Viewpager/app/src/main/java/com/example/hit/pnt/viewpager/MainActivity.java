package com.example.hit.pnt.viewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.hit.pnt.viewpager.adapter.Adapter;
import com.example.hit.pnt.viewpager.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Adapter adapter = new Adapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        binding.viewPager.setAdapter(adapter);

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        binding.navigation.getMenu().findItem(R.id.home).setChecked(true);
                        break;

                    case 1:
                        binding.navigation.getMenu().findItem(R.id.cart).setChecked(true);
                        break;

                    case 2:
                        binding.navigation.getMenu().findItem(R.id.notification).setChecked(true);
                        break;

                    case 3:
                        binding.navigation.getMenu().findItem(R.id.person).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        binding.viewPager.setCurrentItem(0);
                        break;

                    case R.id.cart:
                        binding.viewPager.setCurrentItem(1);
                        break;

                    case R.id.notification:
                        binding.viewPager.setCurrentItem(2);
                        break;

                    case R.id.person:
                        binding.viewPager.setCurrentItem(3);
                        break;
                }

                return true;
            }
        });
    }
}