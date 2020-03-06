package com.birthdaynote.library.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

public abstract class MvpPresenter<V extends ViewInterface,E extends EvenInterface,M extends ModelInterface> implements PresenterInterface<E> {


    private V mView;
    private M mModel;

    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {

    }

    @Override
    public void onCreate() {
        mModel = bindModel();
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

    protected M coreateModel(Class<M> mClass){
        try {
            return  mClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Cannot create an instance of " + mClass, e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot create an instance of " + mClass, e);
        }
    };


    protected  void bindView(V view){
        mView = view;
    };

    @Override
    public void accept(E e) throws Exception {

    }
}
