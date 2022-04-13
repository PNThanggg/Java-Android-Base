package com.codeinger.wavebottomnavigationview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.simform.custombottomnavigation.SSCustomBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    private SSCustomBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new SSCustomBottomNavigation.Model(1,R.drawable.home,"Home"));
        bottomNavigation.add(new SSCustomBottomNavigation.Model(2,R.drawable.subscriptions,"Subscriptions"));
        bottomNavigation.add(new SSCustomBottomNavigation.Model(3,R.drawable.search,"Search"));
        bottomNavigation.add(new SSCustomBottomNavigation.Model(4,R.drawable.library,"Library"));
        bottomNavigation.add(new SSCustomBottomNavigation.Model(5,R.drawable.person,"Profile"));

        bottomNavigation.setCount(2,"11");
        bottomNavigation.show(1,true);
        replace(new HomeFragment(),"Home");

        bottomNavigation.setOnClickMenuListener(new Function1<SSCustomBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(SSCustomBottomNavigation.Model model) {

                switch (model.getId()){
                    case 1:
                        replace(new HomeFragment(),"Home");
                        break;

                    case 2:
                        replace(new SubscriptionFragment(),"Subscription");
                        break;

                    case 3:
                        replace(new SearchFragment(),"Search");
                        break;

                    case 4:
                        replace(new LibraryFragment(),"Library");
                        break;

                    case 5:
                        replace(new ProfileFragment(),"Profile");
                        break;
                }

                return null;
            }
        });

    }

    private void replace(Fragment fragment,String backStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.addToBackStack(backStack);
        transaction.commit();
    }
}