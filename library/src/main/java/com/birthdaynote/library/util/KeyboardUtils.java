package com.birthdaynote.library.util;

import android.app.Activity;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.birthdaynote.library.app.BaseApp;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class KeyboardUtils {
    private static int softKeyboardHeight = 0;

    public static void showKeyboard(EditText editText) {
        if (null == editText) {
            return;
        }
        InputMethodManager manager = ((InputMethodManager) BaseApp.getInstance().getSystemService(INPUT_METHOD_SERVICE));
        if (manager != null) {
            manager.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
            manager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    public static void hideKeyboard(EditText editText) {
        if (null == editText) {
            return;
        }
        InputMethodManager manager = (InputMethodManager) BaseApp.getInstance().getSystemService(INPUT_METHOD_SERVICE);
        if (manager != null) {
            manager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    /**
     * 底部虚拟按键栏的高度
     *
     * @return
     */
    public static int getSoftButtonsBarHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        //这个方法获取可能不是真实屏幕的高度
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        //获取当前屏幕的真实高度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        }
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }


    public static void initKeyboardListener(Activity activity) {
        KeyboardChangeListener keyboardChangeListener = new KeyboardChangeListener(activity);
        keyboardChangeListener.setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                if (softKeyboardHeight == 0) {
                    softKeyboardHeight = keyboardHeight;
                }
            }
        });
    }

    public static int getSoftKeyboardHeight() {
        return softKeyboardHeight;
    }
}
