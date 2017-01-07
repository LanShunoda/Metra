package com.plorial.metra.view;

import com.plorial.metra.model.pojo.Album;

import java.util.List;

/**
 * Created by plorial on 1/7/17.
 */

public interface AlbumsView extends View {
    void showAlbums(List<Album> albums);
}
