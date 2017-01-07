package com.plorial.metra.model.pojo;

import java.util.Map;

/**
 * Created by plorial on 1/7/17.
 */

public class Album {

    private String name;
    private String date;
    private Map<String, String> photos;

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
}
