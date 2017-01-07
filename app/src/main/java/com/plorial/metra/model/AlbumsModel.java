package com.plorial.metra.model;

import android.database.Observable;

import com.plorial.metra.model.pojo.Album;

import java.util.List;

/**
 * Created by plorial on 1/7/17.
 */

public interface AlbumsModel {
    io.reactivex.Observable<List<Album>> getAlbums();
    void addAlbum(String name);
}
