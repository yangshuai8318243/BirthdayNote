package com.birthdaynote.library.scoket;

import android.os.Message;


import com.birthdaynote.library.log.AppLog;
import com.birthdaynote.library.thread.NewThreadHandler;
import com.birthdaynote.library.util.PrintUtils;

import java.io.IOException;
import java.io.InputStream;

public class ReadScoket extends NewThreadHandler {
    private static final String TAG = "ReadScoket";
    private static final int STRAT_RECEIVE = 0x11;
    private boolean isReceive = true;
    //    Socket输入流
    private InputStream inputStream;

    private ReadDataCallback readDataCallback;

    public ReadScoket() {
        super("ReadScoket_thread");
    }

    void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setReadDataCallback(ReadDataCallback readDataCallback) {
        this.readDataCallback = readDataCallback;
    }

    /**
     * 接收数据
     * created at 2020/8/21 10:20
     *
     * @author SnapeYang
     */
    private void receive() {
        if (ChatScoketClient.sharedCenter().isConnected() && isReceive) {
            try {
                //得到的是16进制数，需要进行解析
                byte[] bt = new byte[1024 * 1024];
                int read = inputStream.read(bt);
                if (read > 0) {
                    if (readDataCallback != null) {
                        readDataCallback.processData(bt);
                        AppLog.i(TAG, "接收成功");
                    } else {
                        AppLog.i(TAG, "没有设置数据接收回调");
                    }
                } else {
                    AppLog.i(TAG, "数据错误  read =" + read);
                }

            } catch (IOException e) {
                String errInfo = PrintUtils.errInfo(e);
                AppLog.i(TAG, "接收失败");
                AppLog.i(TAG, errInfo);
            } finally {
                sendEmptyMessage(STRAT_RECEIVE);
            }
        } else {
            AppLog.e(TAG, "链接断开");
        }
    }

    /**
     * 开始读取数据
     * created at 2020/8/21 16:43
     *
     * @author SnapeYang
     */
    public void receiveStrat() {
        sendEmptyMessage(STRAT_RECEIVE);
    }

    @Override
    protected void working(Message message) {
        switch (message.what) {
            case STRAT_RECEIVE:
                receive();
                break;
        }
    }


    public interface ReadDataCallback {
        /**
         * 异步处理
         * created at 2020/8/21 10:20
         *
         * @param data scoket中的数据
         * @author SnapeYang
         */
        boolean processData(byte[] data);
    }

    public void close() {
        try {
            isReceive = false;
            clean();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream = null;
        }
    }
}
