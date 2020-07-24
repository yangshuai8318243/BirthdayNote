package com.birthdaynote.library.mvp;

import com.birthdaynote.library.data.entity.BaseData;

public abstract class MvpModel<D extends BaseData> implements ModelInterface {
    protected static String TAG = "";

    public MvpModel() {
        TAG = getClass().getName();
//        WorkManager.initialize(BaseApp.getInstance(), new Configuration.Builder().build());
//        WorkManager instance = WorkManager.getInstance(BaseApp.getInstance());
//        instance.enqueue(new WorkRequest() {
//            @NonNull
//            @Override
//            public UUID getId() {
//                return super.getId();
//            }
//        });
    }


}
