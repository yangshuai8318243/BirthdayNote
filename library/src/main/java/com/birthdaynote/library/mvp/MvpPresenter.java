package com.birthdaynote.library.mvp;


import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.Map;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class MvpPresenter<V extends ViewInterface, E extends EvenInterface, M extends ModelInterface> implements PresenterInterface<E> {

    protected static String TAG = "";
    protected M mModel;
    protected WeakReference<V> mView;
    private Map<String, LiveData> mLiveDataMap;
    //管理RxJava，主要针对RxJava异步操作造成的内存泄漏
    private CompositeDisposable mCompositeDisposable;

    public MvpPresenter(V view) {
        this.mView = new WeakReference<>(view);
        this.mModel = bindModel();
        this.mLiveDataMap = addLiveData();
        TAG = getClass().getName();
        mCompositeDisposable = new CompositeDisposable();
    }

    protected abstract Map<String, LiveData> addLiveData();

    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }


    @Override
    public void bindViewLiveData(LifecycleOwner owner, String tag, Observer observer) {
        Log.e(TAG, "----------bindViewLiveData------>" + tag);

        if (mLiveDataMap == null) {
            throw new RuntimeException("Please confirm if LiveData was created");
        } else {
            LiveData liveData = mLiveDataMap.get(tag);
            if (liveData == null) {
                throw new RuntimeException("Please confirm if LiveData was created");
            } else {
                liveData.observe(owner, observer);
            }
        }
    }

    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        if (mModel != null) {
            mModel.onCleared();
        }
        //ViewModel销毁时会执行，同时取消所有异步任务
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }


    @Override
    public void bindRxEven() {

    }

    @Override
    public void unBindRxEven() {

    }

    protected abstract M bindModel();

    protected M coreateModel(Class<M> mClass) {
        try {
            return mClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Cannot create an instance of " + mClass, e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot create an instance of " + mClass, e);
        }
    }

    ;

}
