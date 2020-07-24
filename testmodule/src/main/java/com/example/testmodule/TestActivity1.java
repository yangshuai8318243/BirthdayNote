package com.example.testmodule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.birthdaynote.library.app.BaseApp;
import com.birthdaynote.library.mvp.MvpViewActivity;
import com.birthdaynote.library.mvp.PresenterInterface;
import com.birthdaynote.library.util.PrintUtils;
import com.birthdaynote.library.util.RxUtils;
import com.birthdaynote.library.util.constant.RouterConstants;
import com.example.testmodule.hook.FixInstrumentation;
import com.example.testmodule.rx.TestRxJava;
import com.sankuai.waimai.router.annotation.RouterUri;

import java.lang.reflect.InvocationTargetException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;

@RouterUri(path = RouterConstants.TEST_ACTIVITY1)
public class TestActivity1 extends MvpViewActivity {

    RelativeLayout relativeLayout;

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_module_im_activity_instant_messaging_main);
        final TestLeak testLeak = TestLeak.getInstance(this);
        relativeLayout = findViewById(R.id.test_layout_content);
        Button button = findViewById(R.id.add_view_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testLeak.fly();
                addView();
            }
        });
        TextView textView = findViewById(R.id.name);
        textView.setText(getClass().getSimpleName());
        Button srBtn = findViewById(R.id.start_serv);
        srBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TestService.class);
                Bundle bundle = new Bundle();
                bundle.putString("key", "--------------1-------------");
                intent.putExtra("testData", bundle);
                Log.e(TestActivity1.this.getClass().getName(), "-----------startService----------------");
                startService(intent);
            }
        });
        PrintUtils.printProcess(getClass().getName());
        try {
            FixInstrumentation.hookInstrumentation();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void addView() {
        String s = "3371 8010 0100 1778 12";

        new Observable<View>() {
            @Override
            protected void subscribeActual(Observer<? super View> observer) {
                View inflate = LayoutInflater.from(BaseApp.getInstance()).inflate(R.layout.test_module_test_layout, null);
                observer.onNext(inflate);
            }
        }.compose(RxUtils.schedulersTransformer()).subscribe(new Consumer<View>() {
            @Override
            public void accept(View view) throws Exception {
                relativeLayout.addView(view);
            }
        });
        AsyncLayoutInflater asyncLayoutInflater = new AsyncLayoutInflater(getApplicationContext());
        asyncLayoutInflater.inflate(R.layout.test_module_test_layout, null, new AsyncLayoutInflater.OnInflateFinishedListener() {
            @Override
            public void onInflateFinished(@NonNull View view, int resid, @Nullable ViewGroup parent) {

            }
        });

        new TestRxJava().testMap();
    }

    @Override
    protected PresenterInterface initPtr() {
        return null;
    }
}
