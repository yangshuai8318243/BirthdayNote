package com.example.algorithm;

import android.graphics.Bitmap;
import android.os.Binder;

public class ImageBinder extends Binder {
    private Bitmap bitmap;

    public ImageBinder(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
