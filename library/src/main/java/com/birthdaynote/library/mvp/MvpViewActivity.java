package com.birthdaynote.library.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.birthdaynote.library.app.BaseActivity;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public abstract class MvpViewActivity<P extends PresenterInterface,E extends EvenInterface> extends BaseActivity implements ViewInterface<E>{
    protected static String TAG = "";
    private P mPtr;
    private Subject<E> mEven;
    private Disposable mDisposable;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        TAG = this.getClass().getName();
        super.onCreate(savedInstanceState);
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
        if (mEven != null) {
            mEven.onNext(even);
        }else {
            //没有对应的 事件发送器 检查是否有创建ptr对象
            Log.d(TAG,"There is no corresponding event sender to check if a ptr object has been created");
        }
    }

    protected PtrFactoryInterface getPtrFactory(){
        return PtrFactory.getFactory();
    }

}
