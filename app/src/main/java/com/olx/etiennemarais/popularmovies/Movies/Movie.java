package com.olx.etiennemarais.popularmovies.Movies;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Movie implements Parcelable {
    public static final String ORDER_POPULARITY_DESC = "popularity.desc";
    public static final String ORDER_RATING_DESC = "vote_average.desc";
    private static final String BASE_IMAGE_PATH = "http://image.tmdb.org/t/p/";
    private static final String BACKDROP_DEFAULT_SIZE = "w1280";
    private static final String COVER_DEFAULT_SIZE = "w185";
    public static final String MOVIE = "com.olx.etiennemarais.popularmovies.Movies.Movie";

    public String title;
    public int externalId;
    public String originalTitle;
    public String backdropPath;
    public String posterPath;
    public String overview;
    public float popularity;
    public float voteAverage;
    public String releaseDate;

    public String getPosterPath() {
        return BASE_IMAGE_PATH + COVER_DEFAULT_SIZE + posterPath;
    }

    public String getBackdropPath() {
        return BASE_IMAGE_PATH + BACKDROP_DEFAULT_SIZE + backdropPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.externalId);
        dest.writeString(this.originalTitle);
        dest.writeString(this.backdropPath);
        dest.writeString(this.posterPath);
        dest.writeString(this.overview);
        dest.writeFloat(this.popularity);
        dest.writeFloat(this.voteAverage);
        dest.writeString(this.releaseDate);
        //dest.writeLong(releaseDate != null ? releaseDate.getTime() : -1);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.externalId = in.readInt();
        this.originalTitle = in.readString();
        this.backdropPath = in.readString();
        this.posterPath = in.readString();
        this.overview = in.readString();
        this.popularity = in.readFloat();
        this.voteAverage = in.readFloat();
        //long tmpReleaseDate = in.readLong();
        this.releaseDate = in.readString();
        //this.releaseDate = tmpReleaseDate == -1 ? null : new Date(tmpReleaseDate);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
