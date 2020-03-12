package com.birthdaynote.library.mvp;

public interface PtrFactoryInterface {
    <P extends PresenterInterface,V extends ViewInterface>  P  newPtr(Class<P> ptr,V viewInterface);
}
