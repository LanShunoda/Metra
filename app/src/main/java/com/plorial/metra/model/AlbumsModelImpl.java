package com.plorial.metra.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.plorial.metra.model.pojo.Album;

import java.util.Date;
import java.util.List;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;

/**
 * Created by plorial on 1/7/17.
 */

public class AlbumsModelImpl implements AlbumsModel {

    private DatabaseReference databaseReference;

    public AlbumsModelImpl() {
        this.databaseReference = databaseReference = FirebaseDatabase.getInstance().getReference();;
    }

    @Override
    public io.reactivex.Observable<List<Album>> getAlbums() {
        return RxFirebaseDatabase.observeValueEvent(databaseReference.child("albums"), DataSnapshotMapper.listOf(Album.class));
    }

    @Override
    public void addAlbum(String name) {
        databaseReference.child("albums/" + name + "/date").setValue(new Date().toString());
        databaseReference.child("albums/" + name + "/name").setValue(name);
    }
}
