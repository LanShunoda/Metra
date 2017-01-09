package com.plorial.metra.view;

import com.plorial.metra.model.pojo.Album;

/**
 * Created by plorial on 1/8/17.
 */

public interface PhotosView extends View {
    Album getAlbum();
    void updatePhotos();
}
