package com.birthdaynote.library.util;

import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.birthdaynote.library.app.BaseApp;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class KeyboardUtils {
    public static void showKeyboard(EditText editText) {
        if (null == editText) {
            return;
        }
        InputMethodManager manager = ((InputMethodManager) BaseApp.getInstance().getSystemService(INPUT_METHOD_SERVICE));
        if (manager != null){
            manager.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
            manager.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
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
}
