package com.birthdaynote.library.widget.loading;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/11
 * Time: 16:11
 */
public class LoadingDilog extends Dialog {
    public LoadingDilog(@NonNull Context context) {
        super(context);
    }

    public LoadingDilog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoadingDilog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
    }

    private void initView() {
    }
}
