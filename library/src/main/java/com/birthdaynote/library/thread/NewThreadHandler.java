package com.birthdaynote.library.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

public abstract class NewThreadHandler {

    private final HandlerThread handlerThread;
    private final HandlerTool handlerTool;

    public NewThreadHandler(String name) {
        handlerThread = new HandlerThread(name);
        handlerThread.start();
        handlerTool = new HandlerTool(handlerThread.getLooper(), this);
    }

    protected abstract void working(Message message);

    public void clean() {
        handlerThread.interrupt();
    }

    public final boolean post(@NonNull Runnable r) {
        return handlerTool.post(r);
    }

    public final boolean postAtTime(@NonNull Runnable r, long time) {
        return handlerTool.postAtTime(r, time);
    }

    public final boolean sendMessage(@NonNull Message msg) {
        return handlerTool.sendMessage(msg);
    }

    public final boolean sendEmptyMessage(int what) {
        return handlerTool.sendEmptyMessage(what);
    }

    public final boolean sendEmptyMessageAtTime(int what, long time) {
        return handlerTool.sendEmptyMessageAtTime(what, time);
    }

    private static class HandlerTool extends Handler {
        private NewThreadHandler newThreadHandler;

        public HandlerTool(NewThreadHandler threadHandler) {
            this.newThreadHandler = threadHandler;
        }

        public HandlerTool(@NonNull Looper looper, NewThreadHandler threadHandler) {
            super(looper);
            this.newThreadHandler = threadHandler;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            newThreadHandler.working(msg);
        }
    }
}
