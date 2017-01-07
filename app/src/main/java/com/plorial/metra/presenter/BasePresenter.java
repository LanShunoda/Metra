package com.plorial.metra.presenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by plorial on 1/7/17.
 */

public abstract class BasePresenter implements Presenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
    }
}
