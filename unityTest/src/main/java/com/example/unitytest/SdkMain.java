package com.example.unitytest;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/27
 * Time: 15:31
 */
public class SdkMain {

    /**
     * unity项目启动时的的上下文
     */
    private Activity _unityActivity;
    /**
     * 获取unity项目的上下文
     * @return
     */
    Activity getActivity(){
        if(null == _unityActivity) {
            try {
                Class<?> classtype = Class.forName("com.unity3d.player.UnityPlayer");
                Activity activity = (Activity) classtype.getDeclaredField("currentActivity").get(classtype);
                _unityActivity = activity;
            } catch (ClassNotFoundException e) {

            } catch (IllegalAccessException e) {

            } catch (NoSuchFieldException e) {

            }
        }
        return _unityActivity;
    }


    public void initChatSdk(String data) {
        Log.e(getClass().getSimpleName(), "------------------>" + data);
        Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
        sendUnity();
    }

    public void sendUnity() {
        UnityPlayer.UnitySendMessage("GameObject", "messageFromChatSdk", "android sdk");
    }
}
