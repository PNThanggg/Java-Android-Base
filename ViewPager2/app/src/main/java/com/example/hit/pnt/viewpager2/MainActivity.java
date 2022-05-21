package com.example.hit.pnt.viewpager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.VibrationEffect;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // add thư viện: Gradle Scripts -> build.gradle(Module)
    //     // Rounded ImageView
    //    implementation 'com.makeramen:roundedimageview:2.3.0'
    //
    //    // Smooth Bottom Bar
    //    implementation 'com.github.ibrahimsn98:SmoothBottomBar:1.7.8'
    //
    //    // Scalable Size Unit
    //    implementation 'com.intuit.sdp:sdp-android:1.0.6'
    //    implementation 'com.intuit.ssp:ssp-android:1.0.6'

    // thêm vào Gradle Scripts -> settings.gradle -> dependencyResolutionManagement -> repositories
    // ->  maven { url 'https://jitpack.io' }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupMoviesViewPager();
    }

    private void setupMoviesViewPager() {
        ViewPager2 viewPager2 = findViewById(R.id.moviesViewPager);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.setAdapter(new MovieAdapter(getMovies()));
    }

    private List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();

        Movie passengers = new Movie();
        passengers.setPoster(R.drawable.passenger);
        passengers.setName("Passengers");
        passengers.setCategory("Science Fiction");
        passengers.setReleaseDate("December 22, 2016");
        passengers.setRating(4.6f);
        movies.add(passengers);

        Movie sasuke = new Movie();
        sasuke.setPoster(R.drawable.abc);
        sasuke.setName("Sasuke");
        sasuke.setCategory("Science Fiction");
        sasuke.setReleaseDate("December 22, 2016");
        sasuke.setRating(2.5f);
        movies.add(sasuke);

        Movie martian = new Movie();
        martian.setPoster(R.drawable.martitan);
        martian.setName("Martian");
        martian.setCategory("Science Fiction");
        martian.setReleaseDate("December 22, 2016");
        martian.setRating(3.4f);
        movies.add(martian);

        Movie tomorrow_war = new Movie();
        tomorrow_war.setPoster(R.drawable.the_tomorrow_war);
        tomorrow_war.setName("The tomorrow war");
        tomorrow_war.setCategory("Science Fiction");
        tomorrow_war.setReleaseDate("December 22, 2016");
        tomorrow_war.setRating(4.9f);
        movies.add(tomorrow_war);

        Movie tomorrow_war1 = new Movie();
        tomorrow_war1.setPoster(R.drawable.the_tomorrow_war);
        tomorrow_war1.setName("The tomorrow war");
        tomorrow_war1.setCategory("Science Fiction");
        tomorrow_war1.setReleaseDate("December 22, 2016");
        tomorrow_war1.setRating(4.9f);
        movies.add(tomorrow_war1);

        Movie tomorrow_war2 = new Movie();
        tomorrow_war2.setPoster(R.drawable.the_tomorrow_war);
        tomorrow_war2.setName("The tomorrow war");
        tomorrow_war2.setCategory("Science Fiction");
        tomorrow_war2.setReleaseDate("December 22, 2016");
        tomorrow_war2.setRating(4.9f);
        movies.add(tomorrow_war2);

        return movies;
    }
}