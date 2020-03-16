package com.birthdaynote.library.mvp;

import android.os.Bundle;

import com.birthdaynote.library.app.BaseFragment;
import com.birthdaynote.library.mvp.factory.PtrFactory;
import com.birthdaynote.library.mvp.factory.PtrFactoryInterface;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public abstract class MvpFragment<P extends PresenterInterface,E extends EvenInterface> extends BaseFragment implements ViewInterface<E>  {
    protected static String TAG = "";
    private P mPtr;
    private Subject<E> mEven;
    private Disposable mDisposable;
    private BindLiveData mBindLiveData;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        TAG = this.getClass().getName();
        super.onCreate(savedInstanceState);
        mBindLiveData = new BindLiveData();
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

    protected void bindLiveData(String tag, Observer observer) {
        mPtr.bindViewLiveData(this, tag, observer);
    }

    protected Subject<E> newSubscriber(){
        PublishSubject<E> objectPublishSubject = PublishSubject.create();
        return objectPublishSubject;
    }

    @Override
    public void sendEven(E even) {
        mEven.onNext(even);
    }

    protected BindLiveData sendEvenBindData(E even){
        sendEven(even);
        return mBindLiveData;
    }

    protected class BindLiveData{
        public void bindLiveData(String tag, Observer observer) {
            mPtr.bindViewLiveData(MvpFragment.this, tag, observer);
        }
    }

    protected PtrFactoryInterface getPtrFactory(){
        return PtrFactory.getFactory();
    }

}
