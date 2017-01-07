package com.plorial.metra.presenter;

import com.plorial.metra.model.AlbumsModel;
import com.plorial.metra.model.AlbumsModelImpl;
import com.plorial.metra.model.pojo.Album;
import com.plorial.metra.view.AlbumsView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by plorial on 1/7/17.
 */

public class AlbumsListPresenter extends BasePresenter {

    private AlbumsView view;
    private AlbumsModel model;

    public AlbumsListPresenter(AlbumsView view) {
        this.view = view;
        model = new AlbumsModelImpl();
    }

    public void loadAlbums(){
        Observable<List<Album>> albumsObservable = model.getAlbums();
        Disposable disposable = albumsObservable.subscribe(new Consumer<List<Album>>() {
            @Override
            public void accept(List<Album> albums) throws Exception {
                view.showAlbums(albums);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                view.showError(throwable.getMessage());
            }
        });
        addDisposable(disposable);
    }

    public void onAlbumClick(Album album){

    }

    public void addAlbum(String name){
        model.addAlbum(name);
    }
}
