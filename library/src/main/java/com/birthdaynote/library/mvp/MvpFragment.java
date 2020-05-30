package com.birthdaynote.library.mvp;

import android.os.Bundle;
import android.util.Log;

import com.birthdaynote.library.app.BaseFragment;
import com.birthdaynote.library.data.entity.MvpData;
import com.birthdaynote.library.mvp.even.EvenConstants;
import com.birthdaynote.library.mvp.even.EvenInterface;
import com.birthdaynote.library.mvp.factory.PtrFactory;
import com.birthdaynote.library.mvp.factory.PtrFactoryInterface;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public abstract class MvpFragment<P extends PresenterInterface, E extends EvenInterface> extends BaseFragment implements ViewInterface<E> {
    private P mPtr;
    private Subject<E> mEven;
    private Disposable mDisposable;
    private BindLiveData mBindLiveData;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindLiveData = new BindLiveData();
        bindPtr();
        bindBaseEven();
    }

    private void bindBaseEven() {
        mBindLiveData.bindLiveData(EvenConstants.START_ACTIVIT_PTR_TAG, new Observer<MvpData>() {
            @Override
            public void onChanged(MvpData baseDataList) {
                Class className = baseDataList.getmClassName();
                Bundle bundle = baseDataList.getmData();
                startActivity(className, bundle);
            }
        });
        mBindLiveData.bindLiveData(EvenConstants.START_CANONICAL_PTR_TAG, new Observer<MvpData>() {
            @Override
            public void onChanged(MvpData mvpData) {
                String fragmentName = mvpData.getmFragmentName();
                Bundle bundle = mvpData.getmData();
                startContainerActivity(fragmentName, bundle);
            }
        });
        mBindLiveData.bindLiveData(EvenConstants.REQUEST_PERMISSIONS, new Observer<MvpData>() {
            @Override
            public void onChanged(MvpData mvpData) {
                String[] permissions = mvpData.getPermissions();
                requestPermissions(permissions);
            }
        });

        mBindLiveData.bindLiveData(EvenConstants.FINISH_ACTIVITY_PTR_TAG, new Observer<MvpData>() {
            @Override
            public void onChanged(MvpData mvpData) {
                getActivity().finish();
            }
        });

    }

    @Override
    public void onFailurePermissions(String permission) {
        super.onFailurePermissions(permission);
        if (mPtr != null) {
            mPtr.onFailurePermissions(permission);
        }
    }

    @Override
    public void onSuccessPermissions(String permission) {
        super.onSuccessPermissions(permission);
        if (mPtr != null) {
            mPtr.onSuccessPermissions(permission);
        }
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
        if (mPtr != null) {
            getLifecycle().addObserver(mPtr);
            mEven = newSubscriber();
            mDisposable = mEven.subscribe(mPtr);
        }
    }

    @Override
    public void unBindPtr() {
        if (mPtr != null) {
            getLifecycle().removeObserver(mPtr);
            mPtr = null;
        }
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    protected void bindLiveData(String tag, Observer observer) {
        mPtr.bindViewLiveData(this, tag, observer);
    }

    protected Subject<E> newSubscriber() {
        PublishSubject<E> objectPublishSubject = PublishSubject.create();
        return objectPublishSubject;
    }

    @Override
    public void sendEven(E even) {
        mEven.onNext(even);
    }

    protected BindLiveData sendEvenBindData(E even) {
        sendEven(even);
        return mBindLiveData;
    }

    protected class BindLiveData {
        public void bindLiveData(String tag, Observer observer) {
            if (mPtr != null) {
                mPtr.bindViewLiveData(MvpFragment.this, tag, observer);
            }
        }
    }

    protected PtrFactoryInterface getPtrFactory() {
        return PtrFactory.getFactory();
    }

}
