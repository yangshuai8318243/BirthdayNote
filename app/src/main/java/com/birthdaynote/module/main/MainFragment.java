package com.birthdaynote.module.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.birthdaynote.R;
import com.birthdaynote.app.BDFragment;
import com.birthdaynote.library.data.entity.BaseData;
import com.facebook.drawee.view.SimpleDraweeView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment extends BDFragment<MainPtr, MainEven> {
    @BindView(R.id.back_btn)
    Button mButton;

    @BindView(R.id.main_image)
    ImageView mImageView;

    @BindView(R.id.weather_image)
    SimpleDraweeView weather_image;

    @BindView(R.id.weather_layout)
    RelativeLayout weather_layout;

    @BindView(R.id.city_name)
    TextView city_name;

    @BindView(R.id.temperature_name)
    TextView temperature_name;

    @BindView(R.id.max_temperature_name)
    TextView max_temperature_name;

    @BindView(R.id.quality_name)
    TextView quality_name;

    @BindView(R.id.sunset_name)
    TextView sunset_name;

    @BindView(R.id.sunrise_name)
    TextView sunrise_name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.main_fm, container, false);
        bindView(inflate);
        return inflate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLiveData();
    }

    private void initLiveData() {
        initUpdateTimer();
        initWeatherDataOk();
    }

    private void initUpdateTimer() {
        bindLiveData(MainEven.MAIN_UPDATE_TIMER_TEXT, (Observer<Integer>) integer -> {
            String text = String.valueOf(integer)+"秒";
            mButton.setText(text);
        });
    }

    private void initWeatherDataOk() {
        bindLiveData(MainEven.MAIN_GET_WEATHER_DATA, (Observer<BaseData>) baseData -> {

            if (baseData.getIsOk()) {
                String city = baseData.getData("city");
                String weather = baseData.getData("weather");
                String temp = baseData.getData("temp");
                String temphigh = baseData.getData("temphigh");
                String templow = baseData.getData("templow");
                String sunrise = baseData.getData("sunrise");
                String sunset = baseData.getData("sunset");
                String quality = baseData.getData("quality");
                city_name.setText(city);
                temperature_name.setText(temp);
                max_temperature_name.setText(temphigh);
                quality_name.setText(quality);
                sunset_name.setText(sunset);
                sunrise_name.setText(sunrise);
                int imageId = 0;
                if (weather.contains("晴")) {
                    imageId = R.mipmap.weather_qingtian;
                } else if (weather.contains("暴雪")) {
                    imageId = R.mipmap.weather_baoxue;
                } else if (weather.contains("暴雨")) {
                    imageId = R.mipmap.weather_baoyu;
                } else if (weather.contains("冰雹")) {
                    imageId = R.mipmap.weather_bingbao;
                } else if (weather.contains("大风")) {
                    imageId = R.mipmap.weather_dafeng;
                } else if (weather.contains("大雪")) {
                    imageId = R.mipmap.weather_daxue;
                } else if (weather.contains("大雨")) {
                    imageId = R.mipmap.weather_dayu;
                } else if (weather.contains("多云")) {
                    imageId = R.mipmap.weather_duoyun;
                } else if (weather.contains("雷阵雨")) {
                    imageId = R.mipmap.weather_leizhenyu;
                } else if (weather.contains("雾")) {
                    imageId = R.mipmap.weather_wu;
                } else if (weather.contains("雾霾")) {
                    imageId = R.mipmap.weather_wumai;
                } else if (weather.contains("小雪")) {
                    imageId = R.mipmap.weather_xiaoxue;
                } else if (weather.contains("小雨")) {
                    imageId = R.mipmap.weather_xiaoyu;
                } else if (weather.contains("扬沙")) {
                    imageId = R.mipmap.weather_yangsha;
                } else if (weather.contains("雨夹雪")) {
                    imageId = R.mipmap.weather_yujiaxue;
                } else if (weather.contains("中雪")) {
                    imageId = R.mipmap.weather_zhongxue;
                } else if (weather.contains("中雨")) {
                    imageId = R.mipmap.weather_zhongyu;
                } else {
                    imageId = R.mipmap.weather_qingtianbai;
                }
                weather_image.setImageResource(imageId);
                weather_layout.setVisibility(View.VISIBLE);
                mImageView.setVisibility(View.GONE);
            }
        });
    }

    @OnClick(R.id.back_btn)
    protected void onClick() {
        MainEven mainEven = new MainEven();
        mainEven.setTag(MainEven.MAIN_GET_WEATHER_DATA);
        sendEven(mainEven);
    }


    @Override
    protected MainPtr initPtr() {
        return getPtrFactory().newPtr(MainPtr.class, this);
    }
}
