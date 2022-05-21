package com.example.hit.pnt.bottomappbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.hit.pnt.bottomappbar.adapter.MenuAdapter;
import com.example.hit.pnt.bottomappbar.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.navigation.setBackground(null);

        MenuAdapter adapter = new MenuAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        binding.viewpager.setAdapter(adapter);

        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                        binding.navigation.getMenu().findItem(R.id.account).setChecked(true);
                        break;

                    case 2:
                        binding.navigation.getMenu().findItem(R.id.help).setChecked(true);
                        break;

                    case 3:
                        binding.navigation.getMenu().findItem(R.id.exit).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        binding.viewpager.setCurrentItem(0);
                        break;

                    case R.id.account:
                        binding.viewpager.setCurrentItem(1);
                        break;

                    case R.id.help:
                        binding.viewpager.setCurrentItem(3);
                        break;

                    case R.id.exit:
                        binding.viewpager.setCurrentItem(4);
                        break;
                }

                return true;
            }
        });

        binding.actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}