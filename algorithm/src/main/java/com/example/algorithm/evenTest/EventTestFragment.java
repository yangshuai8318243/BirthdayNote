package com.example.algorithm.evenTest;

import android.net.Uri;
import android.util.Log;

import com.example.algorithm.AlgorithmBaseFragment;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class EventTestFragment extends AlgorithmBaseFragment {
    public static final String UFILE_Scheme = "ufile";
    public static final String Host_Scheme = "/ufile.app";

    @Override
    protected void run() {
//        subjectTest();
        testGosn();
    }

    private void testGosn() {

        String url = "ufile://ufile.app?id=10943&uid=111&isopen=好好";
        //将String类型的地址转变为URI类型
//        Uri uri = Uri.parse(url);
        String toString = new Uri.Builder()
                .appendEncodedPath(Host_Scheme)
                .scheme(UFILE_Scheme)
                .appendQueryParameter("id", "11233")
                .appendQueryParameter("isopen", "是的 的")
                .build().toString();
        Uri uri = Uri.parse(toString);
        Log.e(TAG, "------data----getHost------->" + uri.toString());
        Log.e(TAG, "------data----getHost------->" + uri.getHost());
        Log.e(TAG, "------data----getAuthority------->" + uri.getAuthority());
        Log.e(TAG, "------data----getFragment------->" + uri.getFragment());
        Log.e(TAG, "------data----getQuery------->" + uri.getQuery());
        Log.e(TAG, "------data----getScheme------->" + uri.getScheme());
        Log.e(TAG, "------data----getQueryParameter------->" + uri.getQueryParameter("id"));
        Log.e(TAG, "------data----getQueryParameter------->" + uri.getQueryParameter("isopen"));
    }

    private void obTest() {
        Observable<String> stringObservable = new Observable<String>() {
            @Override
            protected void subscribeActual(Observer observer) {
                observer.onNext("1111");
                observer.onNext("1");
            }
        }.filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                Log.e(TAG, "------data----11111------->");
                if (s == "1") {
                    return true;
                }
                return false;
            }
        });


        stringObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "------data----------->" + s);
            }
        });
    }

    private void subjectTest() {
        Subject objectSubject = PublishSubject.create().toSerialized();
        objectSubject.ofType(EvetA.class);


        objectSubject.filter(new Predicate<Object>() {
            @Override
            public boolean test(Object o) throws Exception {
                Log.e(TAG, "------1111111----------->");
                if (o instanceof EvetA) {
                    EvetA evetA = (EvetA) o;
                    if (evetA.getTag() == "1") {
                        return true;
                    }
                }
                return false;
            }
        }).subscribe(new Consumer<EvetA>() {
            @Override
            public void accept(EvetA evetA) throws Exception {
                Log.e(TAG, "----------------->" + evetA.getTag());
            }
        });


        EvetA evetA = new EvetA();
        evetA.setTag("111");
        objectSubject.onNext(evetA);
        evetA = new EvetA();
        evetA.setTag("1");
        objectSubject.onNext(evetA);

//        EventB eventB = new EventB();
//        objectSubject.onNext(eventB);

    }
}
