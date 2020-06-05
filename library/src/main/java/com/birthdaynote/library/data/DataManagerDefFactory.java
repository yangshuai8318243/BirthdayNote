package com.birthdaynote.library.data;

import com.birthdaynote.library.data.local.LocalDataManager;
import com.birthdaynote.library.data.local.RoomLocalManager;
import com.birthdaynote.library.data.net.NetworkDataManager;
import com.birthdaynote.library.data.net.OkNetManager;
import com.birthdaynote.library.data.net.ReqestInterceptor;
import com.birthdaynote.library.data.net.ResponseInterceptor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;

public class DataManagerDefFactory implements DataManagerFactoryInterface {
    private OkHttpClient mOkHttpClient;
    private Headers mHeader;
    private String mMediaType;

    private DataManagerDefFactory(Builder builder) {
        mOkHttpClient = builder.mOkHttpClient;
        mHeader = builder.mHeader;
        mMediaType = builder.mMediaType;
    }

    @Override
    public <M extends DataManager> M createDataManager(Class<M> dataManagerClass) {
        try {
            Constructor<M> constructor = dataManagerClass.getConstructor(LocalDataManager.class, NetworkDataManager.class);

            OkNetManager okNetManager = new OkNetManager(mOkHttpClient, mHeader, mMediaType);
            RoomLocalManager roomLocalManager = new RoomLocalManager();

            return constructor.newInstance(roomLocalManager, okNetManager);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Check if OkNetManager or RoomLocalManager is configured correctly");
    }


    public static final class Builder {
        private OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new ReqestInterceptor())
                .addInterceptor(new ResponseInterceptor())
                .build();
        private Headers mHeader = new Headers.Builder().build();
        private String mMediaType = "text/x-markdown; charset=utf-8";

        public Builder() {
        }

        public Builder mOkHttpClient(OkHttpClient val) {
            mOkHttpClient = val;
            return this;
        }

        public Builder mHeader(Headers val) {
            mHeader = val;
            return this;
        }

        public Builder mMediaType(String val) {
            mMediaType = val;
            return this;
        }

        public DataManagerDefFactory build() {
            return new DataManagerDefFactory(this);
        }
    }
}
