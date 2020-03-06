package com.birthdaynote.library.mvp;

public interface ViewInterface <E extends EvenInterface>{
    void bindPtr();
    void unBindPtr();
    void sendEven(E even);
}
