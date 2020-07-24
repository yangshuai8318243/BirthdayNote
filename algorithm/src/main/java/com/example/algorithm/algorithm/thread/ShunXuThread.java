package com.example.algorithm.algorithm.thread;

import android.app.IntentService;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.algorithm.AlgorithmBaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.locks.ReentrantLock;

import androidx.annotation.NonNull;


public class ShunXuThread extends AlgorithmBaseFragment {
    int index = 0;
    Object o = new Object();

    @Override
    protected void run() {
//        fun1();
//        fun2();
//        fun3();
        fun4();
//        Hashtable<String,String>  hashtable = new Hashtable<>();
//        hashtable.put("","");
//        HashMap<String, String> stringStringHashMap = new HashMap<>();
//        stringStringHashMap.put("","");
//
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add()
    }

    private void testReentrantLock(){
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
    }


    private void fun4(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG,"------thread1----------------->");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG,"------thread2----------------->");

            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG,"------thread3----------------->");

            }
        });

        try {
            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            thread3.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




    }

    /**
     * 锁的方式实现
     */
    private void fun1() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (index < 100) {
                    synchronized (o) {
                        if (index % 2 == 0) {
                            printData(index);
                            index = index + 1;
                            o.notifyAll();
                        } else {
                            try {
                                o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (index < 100) {
                    synchronized (o) {
                        if (index % 2 == 1) {
                            printData(index);
                            index = index + 1;
                            o.notifyAll();
                        } else {
                            try {
                                o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        });

        thread.setName("thread1");
        thread.start();
        thread1.setName("thread2");
        thread1.start();
    }

    private static void printData(int data) {
        String name = Thread.currentThread().getName();
        Log.e("thread :" + name, data + "");
    }

    /**
     * join方式实现
     */
    private void fun2() {
        while (index < 100) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    printData(index);
                    index++;
                }
            });
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    printData(index);
                    index++;
                }
            });
            thread.setName("thread1");
            thread1.setName("thread2");
            try {
                thread.start();
                thread.join();
                thread1.start();
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Handler实现
     */

    private void fun3() {
        Run run1 = new Run("Run 11", 0);
        Run run2 = new Run("Run 22", 1);
        run1.setOtherHandler(run2.getSelfHandler());
        run2.setOtherHandler(run1.getSelfHandler());

        run1.start();
    }

    private static class Run {
        public static final int IS_OK = 1;
        public static final int STOP = 2;
        public static final int STAR = 3;
        private final HandlerThread handlerThread;

        private int index;
        private Handler otherHandler;
        private Handler selfHandler;

        public void setOtherHandler(Handler otherHandler) {
            this.otherHandler = otherHandler;
        }

        public Handler getSelfHandler() {
            return selfHandler;
        }

        public Run(String name, int index) {
            this.index = index;
            handlerThread = new HandlerThread(name);
            handlerThread.start();
            selfHandler = new Handler(handlerThread.getLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    switch (msg.what) {
                        case IS_OK:
                            break;
                        case STOP:
                            handlerThread.quit();
                            break;
                        case STAR:
                            if (loop())
                                sendOtherEmptyMessage(STAR);
                            break;
                    }
                }
            };
        }

        private void sendOtherEmptyMessage(int w) {
            otherHandler.sendEmptyMessage(w);
        }

        private boolean loop() {
            if (index < 100) {
                printData(index);
                index = index + 2;
                return true;
            }
            sendOtherEmptyMessage(STOP);
            handlerThread.quit();
            return false;

        }

        public void start() {
            selfHandler.sendEmptyMessage(STAR);
        }

    }


}
