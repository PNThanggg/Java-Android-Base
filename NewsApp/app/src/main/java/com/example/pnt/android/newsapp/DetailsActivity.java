package com.example.pnt.android.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pnt.android.newsapp.Models.NewsHeadlines;
import com.example.pnt.android.newsapp.databinding.ActivityDetailsBinding;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;
    NewsHeadlines headlines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        headlines = (NewsHeadlines) getIntent().getSerializableExtra("data");

        binding.textDetailTitle.setText(headlines.getTitle());
        binding.textDetailAuthor.setText(headlines.getAuthor());
        binding.textDetailTime.setText(headlines.getPublishedAt());
        binding.textDetailDetail.setText(headlines.getDescription());
        binding.textDetailContent.setText(headlines.getContent());

        Picasso.get().load(headlines.getUrlToImage()).into(binding.imageDetailNews);
    }
}