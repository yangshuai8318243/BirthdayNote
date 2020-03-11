package com.birthdaynote.library.mvp;

public interface PtrFactoryInterface {
    <P extends PresenterInterface>  P  newPtr(Class<P> ptr);
}
