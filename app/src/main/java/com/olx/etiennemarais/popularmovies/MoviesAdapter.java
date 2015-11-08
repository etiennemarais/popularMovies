package com.olx.etiennemarais.popularmovies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.olx.etiennemarais.popularmovies.Movies.Movie;

import java.util.List;

public class MoviesAdapter extends ArrayAdapter<Movie>
{
    public MoviesAdapter(Activity context, List<Movie> movies)
    {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Movie movie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_flavor,
                    parent,
                    false
            );
        }

        TextView title = (TextView) convertView.findViewById(R.id.movieTitle);
        title.setText(movie.title);

        return convertView;
    }
}
