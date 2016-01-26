package com.olx.etiennemarais.popularmovies.Movies;

import android.os.Parcel;
import android.os.Parcelable;

public class Video implements Parcelable {
    public static final String YOUTUBE_URL = "https://www.youtube.com/watch?v=";
    public String externalId;
    public String iso;
    public String youtubeKey;
    public String name;
    public String site;
    public String size;
    public String type;

    public String getVideoLink() {
        return YOUTUBE_URL + this.youtubeKey;
    }

    public Video() {}

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() {
        public Video createFromParcel(Parcel source) {
            return new Video(source);
        }

        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    protected Video(Parcel in) {
        this.externalId = in.readString();
        this.iso = in.readString();
        this.youtubeKey = in.readString();
        this.name = in.readString();
        this.site = in.readString();
        this.size = in.readString();
        this.type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.externalId);
        dest.writeString(this.iso);
        dest.writeString(this.youtubeKey);
        dest.writeString(this.name);
        dest.writeString(this.site);
        dest.writeString(this.size);
        dest.writeString(this.type);
    }
}
