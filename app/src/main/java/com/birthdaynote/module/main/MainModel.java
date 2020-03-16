package com.birthdaynote.module.main;

import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.mvp.MvpModel;

public class MainModel extends MvpModel<BaseData> {
    BaseData getImageData(){
        BaseData build = new BaseData.Builder()
                .mBoolean(false)
                .mStr("11111")
                .mDouble(1.0)
                .mInt(233)
                .mLong(1233L).build();
        build.getmSimpleMapData().put("xxxxx","dddddd");
        build.getmListData().saveData("key1",new BaseData.Builder().mStr("xxxxxx1111111xxxxxxxxxx").build());
        return build;
    }


    @Override
    public void onCleared() {

    }
}
