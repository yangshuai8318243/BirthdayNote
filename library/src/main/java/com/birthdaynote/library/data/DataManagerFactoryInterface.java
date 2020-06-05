package com.birthdaynote.library.data;

import com.birthdaynote.library.data.DataManager;

public interface DataManagerFactoryInterface {
    <M extends DataManager> M createDataManager(Class<M> mClass);
}
