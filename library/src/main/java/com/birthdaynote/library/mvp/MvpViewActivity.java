package com.birthdaynote.library.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.birthdaynote.library.app.BaseActivity;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public abstract class MvpViewActivity<P extends PresenterInterface,E extends EvenInterface> extends BaseActivity implements ViewInterface<E>{

    private P mPtr;
    private Subject<E> mEven;
    private Disposable mDisposable;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPtr();
        bindPtr();
    }

    @Override
    protected void onDestroy() {
        unBindPtr();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    protected abstract P initPtr();

    protected P coreatePtr(Class<P> pClass){
        try {
            return  pClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Cannot create an instance of " + pClass, e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot create an instance of " + pClass, e);
        }
    };

    @Override
    public void bindPtr() {
        mPtr = initPtr();
        getLifecycle().addObserver(mPtr);
        mEven = newSubscriber();
        mDisposable = mEven.subscribe(mPtr);
    }

    @Override
    public void unBindPtr() {
        getLifecycle().removeObserver(mPtr);
        mPtr = null;
        mDisposable.dispose();
    }

    protected Subject<E> newSubscriber(){
        PublishSubject<E> objectPublishSubject = PublishSubject.create();
        return objectPublishSubject;
    }

    @Override
    public void sendEven(E even) {
        mEven.onNext(even);
    }


}
