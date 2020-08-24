package com.birthdaynote.library.scoket;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;


import com.birthdaynote.library.thread.NewThreadHandler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteScoket extends NewThreadHandler {
    private static final String TAG = "WriteScoket";
    private static final String SEND_DATA_KEY = "send_data";
    private static final int SEND_DATA = 0x21;

    private BufferedOutputStream bufferedOutputStream;

    public WriteScoket() {
        super("WriteScoket_thread");
    }

    /**
     * 通过scoket设置输入流
     *
     * @author SnapeYang
     * created at 2020/8/21 9:55
     */
    void setOutputStream(OutputStream outputStream) {
        this.bufferedOutputStream = new BufferedOutputStream(outputStream);
    }

    /**
     * 发送数据
     *
     * @param data 数据
     */
    public void send(final byte[] data) {
        Message obtain = Message.obtain();
        obtain.what = SEND_DATA;
        Bundle bundle = new Bundle();
        bundle.putByteArray(SEND_DATA_KEY, data);
        obtain.setData(bundle);
        sendMessage(obtain);
    }

    private void writeData(byte[] data) {
        if (ChatScoketClient.sharedCenter().isConnected()) {
            try {
                bufferedOutputStream.write(data);
                bufferedOutputStream.flush();
                Log.i(TAG, "发送成功");
            } catch (IOException e) {
                e.printStackTrace();
                Log.i(TAG, "发送失败");
            }
        } else {
//                    connect();
        }
    }

    public void close() {
        try {
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufferedOutputStream = null;
        }
    }

    @Override
    protected void working(Message message) {
        switch (message.what) {
            case SEND_DATA:
                byte[] byteArray = message.getData().getByteArray(SEND_DATA_KEY);
                writeData(byteArray);
                break;
        }
    }
}
