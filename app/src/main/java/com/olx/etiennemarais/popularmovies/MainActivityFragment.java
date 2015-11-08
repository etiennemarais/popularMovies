package com.olx.etiennemarais.popularmovies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.olx.etiennemarais.popularmovies.Movies.Movie;

import java.util.Arrays;

public class MainActivityFragment extends Fragment
{
    private MoviesAdapter movieAdapter;

    Movie[] movies = {
        new Movie("Bladerunner"),
        new Movie("Star wars"),
        new Movie("Lord of the Rings 1"),
        new Movie("Lord of the Rings 2"),
        new Movie("Lord of the Rings 3"),
        new Movie("The Hobbit"),
        new Movie("The Matrix"),
        new Movie("The Haunting"),
    };

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        movieAdapter = new MoviesAdapter(getActivity(), Arrays.asList(movies));

        GridView gridView = (GridView) rootView.findViewById(R.id.moviesGridView);
        gridView.setAdapter(movieAdapter);

        return rootView;
    }
}
