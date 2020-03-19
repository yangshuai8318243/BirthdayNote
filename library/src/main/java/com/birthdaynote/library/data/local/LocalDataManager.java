package com.birthdaynote.library.data.local;

public interface LocalDataManager {
    <D> D getData(String ket,String type);
    <D> void saveData(String url,String type,D data);
}
