package com.birthdaynote.library.scoket;


import android.os.Message;


import com.birthdaynote.library.log.AppLog;
import com.birthdaynote.library.thread.NewThreadHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ChatScoketClient extends NewThreadHandler {

    private static final String TAG = "ChatScoket";
    private static final int STRAT_CONNECT = 0x01;

    //    Socket
    private Socket socket;
    //    IP地址
    private static String s_ipAddress;
    //    端口号
    private static int s_port;
    //    线程
    private Thread thread;

    private ReadScoket readScoket;
    private WriteScoket writeScoket;

    public static ChatScoketClient sharedCenter() {
        return SharedChatScoket.S_CHAT_SCOKET_CLIENT;
    }

    @Override
    protected void working(Message message) {
        switch (message.what) {
            case STRAT_CONNECT:
                connect();
                break;
        }
    }

    private static class SharedChatScoket {
        private static final ChatScoketClient S_CHAT_SCOKET_CLIENT = new ChatScoketClient();
    }

    public ChatScoketClient() {
        super("ChatScoketClient_thread");
        this.readScoket = new ReadScoket();
        this.writeScoket = new WriteScoket();
    }

    public static void init(String ipAddress, int port) {
        s_ipAddress = ipAddress;
        s_port = port;
    }

    public static String getS_ipAddress() {
        return s_ipAddress;
    }

    public static int getS_port() {
        return s_port;
    }

    public ReadScoket getReadScoket() {
        return readScoket;
    }

    public WriteScoket getWriteScoket() {
        return writeScoket;
    }


    public void connectStart() {
        sendEmptyMessage(STRAT_CONNECT);
    }

    /**
     * 通过IP地址(域名)和端口进行连接
     */
    private void connect() {
        try {
            socket = new Socket(s_ipAddress, s_port);
            int sendBufferSize = socket.getSendBufferSize();
            socket.setSendBufferSize(1024 * 1024);
            // 发送数据包，默认为 false，即客户端发送数据采用 Nagle 算法；
            // 但是对于实时交互性高的程序，建议其改为 true，即关闭 Nagle 算法，客户端每发送一次数据，无论数据包大小都会将这些数据发送出去
            socket.setTcpNoDelay(true);
            // 设置输出流的发送缓冲区大小，默认是4KB，即4096字节
            socket.setSendBufferSize(4096);
            // 设置输入流的接收缓冲区大小，默认是4KB，即4096字节
            socket.setReceiveBufferSize(4096);
            // 作用：每隔一段时间检查服务器是否处于活动状态，如果服务器端长时间没响应，自动关闭客户端socket
            // 防止服务器端无效时，客户端长时间处于连接状态
            socket.setKeepAlive(true);
            //代表可以立即向服务器端发送单字节数据
            socket.setOOBInline(true);

            AppLog.i(TAG, "sendBufferSize  --->" + sendBufferSize);

            socket.setSoTimeout(5 * 1000);//设置超时时间
            if (isConnected()) {

                OutputStream outputStream = socket.getOutputStream();
                writeScoket.setOutputStream(outputStream);
                InputStream inputStream = socket.getInputStream();
                readScoket.setInputStream(inputStream);

                if (connectedCallback != null) {
                    connectedCallback.callback();
                }

                AppLog.i(TAG, "连接成功");
            } else {
                AppLog.i(TAG, "连接失败");
                if (disconnectedCallback != null) {
                    disconnectedCallback.callback(new IOException("连接失败"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            AppLog.e(TAG, "连接异常");
            if (disconnectedCallback != null) {
                disconnectedCallback.callback(e);
            }
        }
    }

    /**
     * 判断是否连接
     */
    public boolean isConnected() {
        if (socket == null) return false;
        return socket.isConnected();
    }

    /**
     * 连接回调
     * 非主线程回调
     */
    private OnServerConnectedCallbackBlock connectedCallback;
    /**
     * 断开连接回调(连接失败)
     * 非主线程回调
      */
    private OnServerDisconnectedCallbackBlock disconnectedCallback;


    /**
     * 回调声明
     */
    public interface OnServerConnectedCallbackBlock {
        void callback();
    }

    public interface OnServerDisconnectedCallbackBlock {
        void callback(IOException e);
    }

    public interface OnReceiveCallbackBlock {
        void callback(String receicedMessage);
    }

    public void setConnectedCallback(OnServerConnectedCallbackBlock connectedCallback) {
        this.connectedCallback = connectedCallback;
    }

    public void setDisconnectedCallback(OnServerDisconnectedCallbackBlock disconnectedCallback) {
        this.disconnectedCallback = disconnectedCallback;
    }


    /**
     * 移除回调
     */
    private void removeCallback() {
        connectedCallback = null;
        disconnectedCallback = null;
    }

    /**
     * 断开连接
     */
    public void disconnect() {
        if (isConnected()) {
            try {
                clean();
                if (readScoket != null) {
                    readScoket.close();
                }
                if (writeScoket != null) {
                    writeScoket.close();
                }

                socket.close();
                if (socket.isClosed()) {
                    if (disconnectedCallback != null) {
                        disconnectedCallback.callback(new IOException("断开连接"));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
