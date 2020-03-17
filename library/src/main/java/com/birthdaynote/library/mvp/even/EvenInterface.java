package com.birthdaynote.library.mvp.even;

public interface EvenInterface<E> {
    String getTag();
    void setTag(String tag);

    E getData();
}
