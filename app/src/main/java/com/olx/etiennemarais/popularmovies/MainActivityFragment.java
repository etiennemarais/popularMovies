package com.olx.etiennemarais.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.olx.etiennemarais.popularmovies.Api.Api;
import com.olx.etiennemarais.popularmovies.Api.MoviesResponse;
import com.olx.etiennemarais.popularmovies.Movies.Movie;
import com.olx.etiennemarais.popularmovies.Util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivityFragment extends Fragment {
    private final static String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private MoviesAdapter movieAdapter;
    private Api movieDBApi;
    private List<Movie> moviesList;

    public MainActivityFragment() {
        setHasOptionsMenu(true);
    }

    public static Fragment newInstance() {
        return new MainActivityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        setupRestAdapter();
        initializeMoviesListIfNull();

        setupGridviewWithAdapter(rootView);

        setAppTitleToPopular();

        if (moviesList.isEmpty()) {
            Log.i(LOG_TAG, "Movies list is empty");
            updateMoviesFromApi(Movie.ORDER_POPULARITY_DESC);
        }

        return rootView;
    }

    private void setupGridviewWithAdapter(View rootView) {
        movieAdapter = new MoviesAdapter(getActivity(), moviesList);
        GridView gridView = (GridView) rootView.findViewById(R.id.moviesGridView);
        gridView.setAdapter(movieAdapter);
    }

    private void initializeMoviesListIfNull() {
        if (moviesList == null) {
            moviesList = new ArrayList<Movie>();
        }
    }

    private void setAppTitleToPopular() {
        getActivity().setTitle(R.string.title_activity_movie_list_popular);
    }

    private void setupRestAdapter() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setConverter(Util.getGsonConverter())
                .setEndpoint(Api.API_ENDPOINT)
                .build();

        movieDBApi = restAdapter.create(Api.class);
    }

    private void updateMoviesFromApi(String order) {
        movieDBApi.getMovies(order, new Callback<MoviesResponse>() {
            @Override
            public void success(MoviesResponse responseObject, Response response) {
                Log.i(LOG_TAG, "Successfully called the API");
                moviesList = responseObject.results;
                movieAdapter.clear();
                movieAdapter.addAll(moviesList);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(LOG_TAG, "An error occurred when fetching movies: " + error.getMessage());
                System.err.println("ERROR: " + error.getMessage());
            }
        });
    }
}
