package com.birthdaynote.library.mvp;

import com.birthdaynote.library.mvp.even.EvenInterface;

public interface ViewInterface <E extends EvenInterface>{
    void bindPtr();
    void unBindPtr();
    void sendEven(E even);
}
