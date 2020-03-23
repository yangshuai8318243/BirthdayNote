package com.birthdaynote.library.data.local;

public interface LocalDataManager {
    <D> D getData(String key,String type,Class<D> dClass);
    <D> void saveData(String key,String type,D data);
}
