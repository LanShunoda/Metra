package com.plorial.metra.presenter;

import android.graphics.Bitmap;
import android.net.Uri;

import com.google.firebase.storage.UploadTask;
import com.plorial.metra.model.PhotosModel;
import com.plorial.metra.model.PhotosModelImpl;
import com.plorial.metra.view.PhotosView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by plorial on 1/9/17.
 */

public class PhotosPresenter extends BasePresenter {

    private PhotosView photosView;
    private PhotosModel model;

    public PhotosPresenter(PhotosView photosView) {
        this.photosView = photosView;
        model = new PhotosModelImpl(photosView.getAlbum().getName());
    }

    public void addPhoto(Uri uri){
        Observable<UploadTask.TaskSnapshot> observable = model.uploadPhoto(uri);
        Disposable disposable = observable.subscribe(new Consumer<UploadTask.TaskSnapshot>() {
            @Override
            public void accept(UploadTask.TaskSnapshot taskSnapshot) throws Exception {
                String url = taskSnapshot.getDownloadUrl().toString();
                String name = taskSnapshot.getMetadata().getName();
                model.addPhotoToDatabase(url, name);
                photosView.getAlbum().getPhotos().put(name, url);
                photosView.updatePhotos();
            }
        }, new Consumer<Throwable>(){
            @Override
            public void accept(Throwable throwable) throws Exception {
                photosView.showError(throwable.getMessage());
            }
        });
        addDisposable(disposable);
    }

    public Uri saveBitmapToFile(Bitmap bitmap, String dir) {
        return model.saveBitmapToFile(bitmap, dir);
    }
}
