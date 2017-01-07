package com.plorial.metra.model;

import com.plorial.metra.model.pojo.Album;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by plorial on 1/7/17.
 */

public interface AlbumsModel {
    Observable<List<Album>> getAlbums();
    void addAlbum(String name);
}
