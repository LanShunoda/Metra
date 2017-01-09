package com.plorial.metra.model;

import android.graphics.Bitmap;
import android.net.Uri;

import com.google.firebase.storage.UploadTask;

import io.reactivex.Observable;

/**
 * Created by plorial on 1/9/17.
 */

public interface PhotosModel {
    Observable<UploadTask.TaskSnapshot> uploadPhoto(Uri uri);
    void addPhotoToDatabase(String url, String name);
    Uri saveBitmapToFile(Bitmap bitmap, String dir);
    void deletePhoto(String name);
}
