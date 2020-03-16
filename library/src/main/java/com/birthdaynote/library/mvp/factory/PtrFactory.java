package com.birthdaynote.library.mvp.factory;

import android.util.Log;

import com.birthdaynote.library.mvp.PresenterInterface;
import com.birthdaynote.library.mvp.ViewInterface;

import java.lang.reflect.InvocationTargetException;

public class PtrFactory implements PtrFactoryInterface {

    public static PtrFactory getFactory() {
        return BuildePtrFactory.sPtrFactory;
    }

    @Override
    public <P extends PresenterInterface, V extends ViewInterface> P newPtr(Class<P> ptr, V viewInterface) {
        try {
            return (P) ptr.getConstructor(viewInterface.getClass()).newInstance(viewInterface);
        } catch (IllegalAccessException e) {
//            e.printStackTrace();
            Log.e("PtrFactory",e.getMessage());

        } catch (InstantiationException e) {
//            e.printStackTrace();
            Log.e("PtrFactory",e.getMessage());

        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
            Log.e("PtrFactory",e.getMessage());

        } catch (InvocationTargetException e) {
//            e.printStackTrace();
            Log.e("PtrFactory",e.getMessage());
        }
        throw new RuntimeException("please inherit PresenterInterface");
    }


    private static class BuildePtrFactory {
        private static PtrFactory sPtrFactory = new PtrFactory();
    }

    private PtrFactory() {
    }



}
