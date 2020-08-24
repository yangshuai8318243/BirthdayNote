package com.example.algorithm.evenTest;

import android.util.Log;

import com.example.algorithm.AlgorithmBaseFragment;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class EventTestFragment extends AlgorithmBaseFragment {
    @Override
    protected void run() {
        subjectTest();
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
                Log.e(TAG, "------data----11111------->" );
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
