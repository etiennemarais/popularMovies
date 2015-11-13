package com.olx.etiennemarais.popularmovies.Util;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.converter.GsonConverter;

public class Util {
    private static GsonConverter sGsonConverter;

    public static GsonConverter getGsonConverter() {
        if (sGsonConverter != null) return sGsonConverter;

        sGsonConverter = new GsonConverter(buildGsonWithSettings());
        return sGsonConverter;
    }

    @NonNull
    private static Gson buildGsonWithSettings() {
        return new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    }

    private Util() {
        // Fully static API, do not allow creating new instances of this class.
    }
}
