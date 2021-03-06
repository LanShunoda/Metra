package com.plorial.metra.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.plorial.metra.model.pojo.Album;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Observable;

/**
 * Created by plorial on 1/7/17.
 */

public class AlbumsModelImpl implements AlbumsModel {

    private DatabaseReference databaseReference;

    public AlbumsModelImpl() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public Observable<List<Album>> getAlbums() {
        return RxFirebaseDatabase.observeValueEvent(databaseReference.child("albums"), DataSnapshotMapper.listOf(Album.class));
    }

    @Override
    public void addAlbum(String name) {
        databaseReference.child("albums/" + name + "/date").setValue(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        databaseReference.child("albums/" + name + "/name").setValue(name);
    }
}
