package com.olx.etiennemarais.popularmovies.Util;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            .registerTypeAdapter(Date.class, new JsonDeserializer() {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                @Override
                public Date deserialize(final JsonElement json,
                    final Type typeOfT,
                    final JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return df.parse(json.getAsString());
                        } catch (ParseException e) {
                            return null;
                        }
                    }
            }).create();
    }

    private Util() {
        // Fully static API, do not allow creating new instances of this class.
    }
}
