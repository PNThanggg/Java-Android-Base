package com.example.pnt.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnt.android.newsapp.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private Context context;
    private List<NewsHeadlines> headlines;
    private SelectListener listener;

    public NewsAdapter() {
    }

    public NewsAdapter(Context context, List<NewsHeadlines> headlines) {
        this.context = context;
        this.headlines = headlines;
    }

    public NewsAdapter(Context context, List<NewsHeadlines> headlines, SelectListener listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.text_title.setText(headlines.get(position).getTitle());
        holder.text_source.setText(headlines.get(position).getSource().getName());

        if (headlines.get(position).getUrlToImage() != null) {
            Picasso.get()
                    .load(headlines.get(position).getUrlToImage())
                    .into(holder.img_headline);
        }

        holder.cardView.setOnClickListener(view -> {
            listener.OnNewsClicked(headlines.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return headlines == null ? 0 : headlines.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView text_title, text_source;
        ImageView img_headline;
        CardView cardView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            text_title = itemView.findViewById(R.id.text_title);
            text_source = itemView.findViewById(R.id.text_source);
            img_headline = itemView.findViewById(R.id.img_headline);
            cardView = itemView.findViewById(R.id.main_container);
        }
    }
}
