package com.birthdaynote.library.mvp;

public class PtrFactory implements PtrFactoryInterface {

    public static PtrFactory getFactory() {
        return BuildePtrFactory.sPtrFactory;
    }

    @Override
    public <P extends PresenterInterface> P newPtr(Class<P> ptr) {
        try {
            return (P) ptr.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("please inherit PresenterInterface");
    }

    private static class BuildePtrFactory {
        private static PtrFactory sPtrFactory = new PtrFactory();
    }

    private PtrFactory() {
    }



}
