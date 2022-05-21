package com.example.hit.pnt.viewpager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MoviesViewHolder> {

    private final List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoviesViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_movie,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        holder.setMovie(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MoviesViewHolder extends RecyclerView.ViewHolder {
        private final RoundedImageView imagePoster;
        private final TextView textName, textCategory, textReleaseDate;
        private final RatingBar ratingBar;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            imagePoster = itemView.findViewById(R.id.imagePoster);
            textName = itemView.findViewById(R.id.textName);
            textCategory = itemView.findViewById(R.id.textCategory);
            textReleaseDate = itemView.findViewById(R.id.textReleaseDate);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }

        void setMovie(Movie movie) {
            imagePoster.setImageResource(movie.getPoster());
            textName.setText(movie.getName());
            textCategory.setText(movie.getCategory());
            textReleaseDate.setText(movie.getReleaseDate());
            ratingBar.setRating(movie.getRating());
        }
    }
}
