package com.olx.etiennemarais.popularmovies.Api;

import com.olx.etiennemarais.popularmovies.Secrets;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface Api {
    String API_ENDPOINT = Secrets.API_URL;
    String API_KEY = Secrets.API_KEY;

    @GET("/discover/movie?api_key=" + API_KEY)
    void getMovies(@Query("sort_by") String order, Callback<MoviesResponse> cb);
}
