package com.birthdaynote.module.common;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.birthdaynote.R;

public class CommonActionBar {
    private TextView titleView;
    private Button callBackBtn;

    private CommonActionBar() {
    }

    public static CommonActionBar bindView(View view) {
        View root = view.findViewById(R.id.common_action_bar_layout);
        if (root == null) return null;
        CommonActionBar commonActionBar = new CommonActionBar();
        commonActionBar.initView(root);
        return commonActionBar;
    }

    private void initView(View rootView) {
        titleView = rootView.findViewById(R.id.common_title_text);
        callBackBtn = rootView.findViewById(R.id.common_back_btn);
    }

    public void setTitle(String title) {
        titleView.setText(title);
    }

    public void setCallBack(View.OnClickListener callBack) {
        callBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.onClick(callBackBtn);
                }
            }
        });
    }

}
