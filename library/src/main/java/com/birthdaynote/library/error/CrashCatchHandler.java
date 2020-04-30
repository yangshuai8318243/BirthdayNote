package com.birthdaynote.library.error;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.birthdaynote.library.mvp.factory.PtrFactory;

import androidx.annotation.NonNull;

public class CrashCatchHandler implements Thread.UncaughtExceptionHandler {

    public static final String TAG = "CrashCatchHandler";
    private static CrashCatchHandler crashHandler = BuildeCrashCatchHandler.sCrashCatchHandler;
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultCaughtExceptionHandler;

    public static CrashCatchHandler getInstance() {
        return crashHandler;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        //获取默认的系统异常捕获器
        mDefaultCaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        //把当前的crash捕获器设置成默认的crash捕获器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private static class BuildeCrashCatchHandler {
        private static CrashCatchHandler sCrashCatchHandler = new CrashCatchHandler();
    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        if (!handleException(throwable) && mDefaultCaughtExceptionHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultCaughtExceptionHandler.uncaughtException(thread, throwable);
        }else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : "+ e);
            }
//            //退出程序
//            AppUtil.restarteApp(mContext);
        }
    }

    /**
     * 自定义错误处理
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        final String msg = ex.getLocalizedMessage();
        if (msg == null) {
            return false;
        }

        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,程序出现异常！", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        return true;
    }

}
