package com.plorial.metra.model;

import android.graphics.Bitmap;
import android.net.Uri;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import durdinapps.rxfirebase2.RxFirebaseStorage;
import io.reactivex.Observable;

/**
 * Created by plorial on 1/9/17.
 */

public class PhotosModelImpl implements PhotosModel {

    private final static String FIREBASE_BUCKET = "gs://metra-16a9c.appspot.com";

    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private String albumName;

    public PhotosModelImpl(String albumName) {
        this.albumName = albumName;
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReferenceFromUrl(FIREBASE_BUCKET);
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public Observable<UploadTask.TaskSnapshot> uploadPhoto(Uri uri) {
        return RxFirebaseStorage.putFile(storageReference.child("albums/" + albumName + "/" + getFileNameFromUri(uri)), uri);
    }

    @Override
    public void addPhotoToDatabase(String url, String name) {
        name = name.replace(".", "_");
        databaseReference.child("albums/" + albumName + "/photos/" + name).setValue(url);
    }

    @Override
    public Uri saveBitmapToFile(Bitmap bitmap, String dir) {
        File storeDir = new File(dir);
        if (!storeDir.exists()){
            storeDir.mkdirs();
        }

        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        String mImageName="MI_"+ timeStamp +".jpg";
        File mediaFile = new File(storeDir.getPath() + File.separator + mImageName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mediaFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Uri.fromFile(mediaFile);
    }

    private static String getFileNameFromUri(Uri uri){
        File tempFile = new File(uri.getPath());
        return tempFile.getName();
    }
}
