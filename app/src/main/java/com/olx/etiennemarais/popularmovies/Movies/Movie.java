package com.olx.etiennemarais.popularmovies.Movies;

public class Movie {
    public static final String ORDER_POPULARITY_DESC = "popularity.desc";
    public static final String ORDER_RATING_DESC = "vote_average.desc";
    private static final String BASE_IMAGE_PATH = "http://image.tmdb.org/t/p/";
    private static final String BACKDROP_DEFAULT_SIZE = "w1280";
    private static final String COVER_DEFAULT_SIZE = "w185";

    public String title;
    public int externalId;
    public String originalTitle;
    public String backdropPath;
    public String posterPath;
    public String overview;
    public float popularity;
    public float voteAverage;
    //public Date releaseDate;
    public boolean isFavorite;

    public String getPosterPath() {
        return BASE_IMAGE_PATH + COVER_DEFAULT_SIZE + posterPath;
    }

    public String getBackdropPath() {
        return BASE_IMAGE_PATH + BACKDROP_DEFAULT_SIZE + backdropPath;
    }
}
