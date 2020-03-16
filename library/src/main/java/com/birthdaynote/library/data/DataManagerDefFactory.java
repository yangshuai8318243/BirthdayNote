package com.birthdaynote.library.data;

import com.birthdaynote.library.data.local.RoomLocalManager;
import com.birthdaynote.library.data.net.OkNetManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class DataManagerDefFactory implements DataManagerFactoryInterface<DataManager> {
    private OkHttpClient mOkHttpClient;
    private Headers mHeader;
    private MediaType mMediaType;

    private DataManagerDefFactory(Builder builder) {
        mOkHttpClient = builder.mOkHttpClient;
        mHeader = builder.mHeader;
        mMediaType = builder.mMediaType;
    }

    @Override
    public DataManager createDataManager(Class<DataManager> dataManagerClass) {
        try {
            Constructor<DataManager> constructor = dataManagerClass.getConstructor(OkNetManager.class, RoomLocalManager.class);

            OkNetManager okNetManager = new OkNetManager(mOkHttpClient, mHeader, mMediaType);
            RoomLocalManager roomLocalManager = new RoomLocalManager();

            return constructor.newInstance(okNetManager, roomLocalManager);
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
        private OkHttpClient mOkHttpClient;
        private Headers mHeader;
        private MediaType mMediaType;

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

        public Builder mMediaType(MediaType val) {
            mMediaType = val;
            return this;
        }

        public DataManagerDefFactory build() {
            return new DataManagerDefFactory(this);
        }
    }
}
