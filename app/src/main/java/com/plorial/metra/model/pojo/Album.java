package com.plorial.metra.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

/**
 * Created by plorial on 1/7/17.
 */

public class Album implements Parcelable {

    private String name;
    private String date;
    private Map<String, String> photos;

    public Album() {}

    protected Album(Parcel in) {
        name = in.readString();
        date = in.readString();
        photos = in.readHashMap(String.class.getClassLoader());
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, String> getPhotos() {
        return photos;
    }

    public void setPhotos(Map<String, String> photos) {
        this.photos = photos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(date);
        dest.writeMap(photos);
    }
}
