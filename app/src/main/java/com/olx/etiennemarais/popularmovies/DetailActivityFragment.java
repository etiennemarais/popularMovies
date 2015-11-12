package com.olx.etiennemarais.popularmovies;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olx.etiennemarais.popularmovies.Api.Api;
import com.olx.etiennemarais.popularmovies.Util.Util;

import retrofit.RestAdapter;

public class DetailActivityFragment extends Fragment {
    private static final String LOG_TAG = DetailActivityFragment.class.getSimpleName();
    private Context context;
    private Api movieDBApi;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        setupRestAdapter();

        return rootView;
    }

    private void setupRestAdapter() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setConverter(Util.getGsonConverter())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Api.API_ENDPOINT)
                .build();

        movieDBApi = restAdapter.create(Api.class);
    }
}
