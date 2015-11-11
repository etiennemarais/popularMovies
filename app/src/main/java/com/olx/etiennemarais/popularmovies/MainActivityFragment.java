package com.olx.etiennemarais.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class MainActivityFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final static String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private MoviesAdapter movieAdapter;
    private Api movieDBApi;
    private List<Movie> moviesList;
    private Context context;
    private String currentOrder;

    public MainActivityFragment() {
        setHasOptionsMenu(true);
    }

    public static Fragment newInstance() {
        return new MainActivityFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        registerSharedPrefsListener();
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

    private void registerSharedPrefsListener() {
        PreferenceManager
            .getDefaultSharedPreferences(getActivity().getBaseContext())
            .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // Update posters list on sort order changes
        if (isAdded() && getString(R.string.pref_sortorder_key).equals(key)) {
            updateSortOrder();
        }
    }

    private void updateSortOrder() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.context);
        String orderSetting = prefs.getString(
            getString(R.string.pref_sortorder_key), getString(R.string.pref_sortorder_mostPopular)
        );

        if (orderSetting.equals(getString(R.string.pref_sortorder_highestRated))) {
            currentOrder = Movie.ORDER_RATING_DESC;
            setAppTitleToHighestRated();
        } else {
            // Defaults to most popular anyway
            currentOrder = Movie.ORDER_POPULARITY_DESC;
            setAppTitleToPopular();
        }

        updateMoviesFromApi(currentOrder);
    }

    private void setupGridviewWithAdapter(View rootView) {
        movieAdapter = new MoviesAdapter(this.context, moviesList);
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

    private void setAppTitleToHighestRated() {
        getActivity().setTitle(R.string.title_activity_movie_list_rating);
    }

    private void setupRestAdapter() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setConverter(Util.getGsonConverter())
                .setLogLevel(RestAdapter.LogLevel.FULL)
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
