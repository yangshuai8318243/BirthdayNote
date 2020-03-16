package com.birthdaynote.library.data.local;

public interface LocalDataManager {
    <D> D getData(String url);
    <D> void saveData(String url,D data);
}
