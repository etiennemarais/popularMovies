package com.olx.etiennemarais.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.olx.etiennemarais.popularmovies.Movies.Movie;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class DetailActivityFragment extends Fragment {
    private static final String LOG_TAG = DetailActivityFragment.class.getSimpleName();
    private Context context;
    private Movie movie;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent intent = getActivity().getIntent();
        movie = intent.getParcelableExtra(Movie.MOVIE);

        setAppTitleToMovie();

        ImageView backdrop = (ImageView) view.findViewById(R.id.movie_detail_backdrop_image_view);
        Picasso.with(getActivity())
            .load(movie.getBackdropPath())
            .into(backdrop);

        ImageView poster = (ImageView) view.findViewById(R.id.movie_detail_poster_image_view);
        Picasso.with(getActivity())
            .load(movie.getPosterPath())
            .into(poster);

        TextView originalTitle = (TextView) view.findViewById(R.id.movie_detail_title_text_view);
        originalTitle.setText(movie.originalTitle);

        TextView synopsis = (TextView) view.findViewById(R.id.movie_detail_overview_text_view);
        synopsis.setText(movie.overview);

//        String releaseDate = DateFormat.getLongDateFormat(getActivity()).format(movie.releaseDate);
//        TextView movieReleaseDateLabel = (TextView) view.findViewById(R.id.movie_detail_release_date_text_view);
//        movieReleaseDateLabel.setText(releaseDate);

        return view;
    }

    private void setAppTitleToMovie() {
        getActivity().setTitle(movie.title);
    }
}
