package com.birthdaynote.library.mvp;

public interface EvenInterface<E> {
    String getTag();
    void setTag(String tag);

    E getData();
}
