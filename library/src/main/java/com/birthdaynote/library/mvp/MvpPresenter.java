package com.birthdaynote.library.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.Map;

public abstract class MvpPresenter<V extends ViewInterface, E extends EvenInterface, M extends ModelInterface> implements PresenterInterface<E> {

    protected static String TAG = "";
    protected M mModel;
    protected WeakReference<V> mView;
    private Map<String, LiveData> mLiveDataMap;


    public MvpPresenter(V view) {
        this.mView = new WeakReference<>(view);
        this.mModel = bindModel();
        this.mLiveDataMap = addLiveData();
        TAG = getClass().getName();
    }

    protected abstract Map<String, LiveData> addLiveData();


    @Override
    public void bindViewLiveData(LifecycleOwner owner, String tag, Observer observer) {
        Log.e(TAG,"----------bindViewLiveData------>"+tag);

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
        mModel.onCleared();
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
