package com.birthdaynote.library.mvp.factory;

import com.birthdaynote.library.mvp.ModelInterface;

public interface ModelFactoryInterface {
    <M extends ModelInterface>  M  newModel(Class<M> model);
}
