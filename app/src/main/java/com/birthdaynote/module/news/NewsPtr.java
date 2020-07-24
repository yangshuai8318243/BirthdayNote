package com.birthdaynote.module.news;

import android.content.res.AssetManager;
import android.util.Log;

import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.data.entity.BaseDataList;
import com.birthdaynote.library.mvp.MvpPresenter;
import com.birthdaynote.library.util.RxUtils;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class NewsPtr extends MvpPresenter<NewsFragment, NewsEven, NewsModle> {

    private MediatorLiveData<BaseDataList> updateNewsData;

    public NewsPtr(NewsFragment view) {
        super(view);
    }

    @Override
    protected Map<String, LiveData> addLiveData() {
        HashMap<String, LiveData> dataHashMap = new HashMap<>();
        updateNewsData = new MediatorLiveData<>();
        dataHashMap.put(NewsEven.UPDATE_NEWS_DATA, updateNewsData);
        return dataHashMap;
    }

    @Override
    protected NewsModle bindModel() {
        return coreateModel(NewsModle.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }


    @Override
    public void accept(NewsEven newsEven) throws Exception {
        if (NewsEven.UPDATE_NEWS_DATA.equals(newsEven.getTag())) {
            BaseData data = newsEven.getData();
            boolean refresh = data.getData(NewsEven.UPDATE_NEWS_DATA_REFRESH_KEY);
            updateNewsData(refresh);
        }
    }

    private void updateNewsData(boolean refresh) {
        addSubscribe(new Observable<BaseDataList>() {
            @Override
            protected void subscribeActual(Observer<? super BaseDataList> observer) {
                BaseDataList newsData = mModel.getNewsData(refresh);

//                for (BaseData baseData : newsData.getDataList()) {
//                    Log.e(TAG, baseData.print(true, false));
//                }
                if (newsData.getOk()) {
                    observer.onNext(newsData);
                } else {
                    observer.onError(new RuntimeException(newsData.getMessage()));
                }
            }
        }.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends BaseDataList>>() {
            @Override
            public ObservableSource<? extends BaseDataList> apply(Throwable throwable) throws Exception {
                return null;
            }
        }).compose(RxUtils.schedulersTransformer()).subscribe(new Consumer<BaseDataList>() {
            @Override
            public void accept(BaseDataList baseDataList) throws Exception {
                updateNewsData.setValue(baseDataList);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }));

    }
}
