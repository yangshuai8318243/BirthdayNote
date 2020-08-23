package com.birthdaynote.module.birthday.show;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.birthdaynote.R;
import com.birthdaynote.app.BDActivity;
import com.birthdaynote.library.mvp.PresenterInterface;
import com.birthdaynote.library.util.KeyboardChangeListener;
import com.birthdaynote.library.util.KeyboardUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.Unbinder;

public class ShowEdActivity extends BDActivity {

    @BindView(R.id.message_ed)
    EditText message_ed;

    @BindView(R.id.biaoq_btn)
    Button biaoq_btn;

    @BindView(R.id.biaoqin_content_ll)
    LinearLayout biaoqin_content_ll;

    private int softKeyboardHeight = 0;
    private boolean isShowKeyboard;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KeyboardChangeListener keyboardChangeListener = new KeyboardChangeListener(this);
        keyboardChangeListener.setKeyBoardListener((isShow, keyboardHeight) -> {
            Log.e(TAG, "------onKeyboardChange----isShow---->" + isShow);
            Log.e(TAG, "------onKeyboardChange----keyboardHeight---->" + keyboardHeight);

            if (softKeyboardHeight == 0) {
                softKeyboardHeight = keyboardHeight;
            }
            ShowEdActivity.this.isShowKeyboard = isShow;
            if (isShow) {
                shwoCotentLl();
            }
        });
    }

    @OnFocusChange(R.id.message_ed)
    public void OnFocusChange(EditText editText) {
        Log.e(TAG, "-------OnFocusChange----------------->" + editText.getFocusable());
    }

    @OnClick(R.id.biaoq_btn)
    public void shwoBiaoqin() {
        Log.e(TAG, "------isShowKeyboard-------->" + isShowKeyboard);
        if (isShowKeyboard) {
            KeyboardUtils.hideKeyboard(message_ed);
        } else {
            KeyboardUtils.showKeyboard(message_ed);
        }
    }

    private void shwoCotentLl() {
        if (softKeyboardHeight > 0) {
            ViewGroup.LayoutParams layoutParams = biaoqin_content_ll.getLayoutParams();
            Log.e(TAG, "------shwoBiaoqin----height---->" + layoutParams.height);
            Log.e(TAG, "------shwoBiaoqin------width-->" + layoutParams.width);

            layoutParams.height = softKeyboardHeight;
            biaoqin_content_ll.setLayoutParams(layoutParams);
            biaoqin_content_ll.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected Unbinder binderView() {
        return ButterKnife.bind(this);
    }

    @Override
    protected int getActivityViewId() {
        return R.layout.birthday_show_ac_layout;
    }


    @Override
    protected PresenterInterface initPtr() {
        return null;
    }
}
