package com.example.testmodule.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

public class LruCacheTest {
    private LruCache<String, Bitmap> lruCache;

    private LruCacheTest() {
        int max = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //最大内存的八分之一
        int size = max / 8;
        lruCache = new LruCache<String, Bitmap>(size) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }


    public void add(String key,Bitmap bitmap){
        lruCache.put(key,bitmap);
    }

    public Bitmap get(String key){
        return lruCache.get(key);
    }
}
