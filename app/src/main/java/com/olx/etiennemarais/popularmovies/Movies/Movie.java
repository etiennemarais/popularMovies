package com.olx.etiennemarais.popularmovies.Movies;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "movies")
public class Movie extends Model {
    public static final String ORDER_POPULARITY_DESC = "popularity.desc";
    public static final String ORDER_RATING_DESC = "vote_average.desc";

    @Column(name = "title")
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
