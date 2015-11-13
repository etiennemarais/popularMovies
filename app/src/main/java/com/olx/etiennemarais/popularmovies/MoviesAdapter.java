package com.olx.etiennemarais.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.olx.etiennemarais.popularmovies.Movies.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends ArrayAdapter<Movie> {

    public MoviesAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                R.layout.movie_list_item,
                parent,
                false
            );
        }

        Movie movie = getItem(position);

        ImageView posterImage = (ImageView) convertView.findViewById(R.id.movie_card_backdrop_image_view);
        Picasso.with(parent.getContext())
            .load(movie.getBackdropPath())
            .into(posterImage);

        TextView title = (TextView) convertView.findViewById(R.id.movie_card_title_text_view);
        title.setText(movie.title);

        return convertView;
    }
}
