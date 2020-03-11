package com.birthdaynote.library.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.birthdaynote.library.app.BaseActivity;
import com.birthdaynote.library.app.BaseFragment;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public abstract class MvpFragment<P extends PresenterInterface,E extends EvenInterface> extends BaseFragment implements ViewInterface<E>  {
    protected static String TAG = "";
    private P mPtr;
    private Subject<E> mEven;
    private Disposable mDisposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        TAG = this.getClass().getName();
        super.onCreate(savedInstanceState);
        bindPtr();
    }


    @Override
    public void onDestroy() {
        unBindPtr();
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    protected abstract P initPtr();


    @Override
    public void bindPtr() {
        mPtr = initPtr();
        if (mPtr != null){
            getLifecycle().addObserver(mPtr);
            mEven = newSubscriber();
            mDisposable = mEven.subscribe(mPtr);
        }
    }

    @Override
    public void unBindPtr() {
        if (mPtr != null){
            getLifecycle().removeObserver(mPtr);
            mPtr = null;
        }
        if (mDisposable!=null) {
            mDisposable.dispose();
        }
    }

    protected Subject<E> newSubscriber(){
        PublishSubject<E> objectPublishSubject = PublishSubject.create();
        return objectPublishSubject;
    }

    @Override
    public void sendEven(E even) {
        mEven.onNext(even);
    }

    protected PtrFactoryInterface getPtrFactory(){
        return PtrFactory.getFactory();
    }

}
