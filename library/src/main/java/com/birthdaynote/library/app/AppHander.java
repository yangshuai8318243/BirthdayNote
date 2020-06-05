package com.birthdaynote.library.app;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;

public class AppHander<T> extends Handler {
    private WeakReference<T> obj;
    private AppHanderListener listener;

    public AppHander(T objSource) {
        this.obj = new WeakReference<>(objSource);
    }

    public void setListener(AppHanderListener listener) {
        listener.setObj(obj);
        this.listener = listener;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if (listener != null) {
            listener.handleMessage(msg);
        }
    }

    public static abstract class AppHanderListener<O> {
        protected WeakReference<O> soc;

        public void setObj(WeakReference<O> obj) {
            this.soc = obj;
        }

        public abstract void handleMessage(Message msg);
    }

}
