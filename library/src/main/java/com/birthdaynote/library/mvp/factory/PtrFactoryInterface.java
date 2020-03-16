package com.birthdaynote.library.mvp.factory;

import com.birthdaynote.library.mvp.PresenterInterface;
import com.birthdaynote.library.mvp.ViewInterface;

public interface PtrFactoryInterface {
    <P extends PresenterInterface,V extends ViewInterface>  P  newPtr(Class<P> ptr, V viewInterface);
}
