package com.birthdaynote.library.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;


import com.birthdaynote.library.log.AppLog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/8/29
 * Time: 16:00
 */
public class BitmapUtils {

    public static final String TAG = "BitmapUtils";

    /**
     * 质量压缩
     * 设置bitmap options属性，降低图片的质量，像素不会减少
     * 第一个参数为需要压缩的bitmap图片对象，第二个参数为压缩后图片保存的位置
     * 设置options 属性0-100，来实现压缩（因为png是无损压缩，所以该属性对png是无效的）
     *
     * @param bmp
     * @param file
     * @param quality 0-100 100为不压缩
     */
    public static void qualityCompress(Bitmap bmp, File file, int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        bmp.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        fileOutput(baos.toByteArray(), file);
    }

    /**
     * 尺寸压缩（通过缩放图片像素来减少图片占用内存大小）
     *
     * @param bmp
     * @param file
     * @param ratio 尺寸压缩倍数,值越大，图片尺寸越小
     */
    public static void sizeCompress(Bitmap bmp, File file, int ratio) {
        // 压缩Bitmap到对应尺寸
        Bitmap result = Bitmap.createBitmap(bmp.getWidth() / ratio, bmp.getHeight() / ratio, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Rect rect = new Rect(0, 0, bmp.getWidth() / ratio, bmp.getHeight() / ratio);
        canvas.drawBitmap(bmp, null, rect, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        result.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] bytes = baos.toByteArray();

        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        AppLog.i(TAG, "============》", bitmap.getWidth(), bitmap.getHeight());

        fileOutput(baos.toByteArray(), file);
    }

    /**
     * 尺寸压缩（通过缩放图片像素来减少图片占用内存大小）
     *
     * @param bmp   直接返回bitmap
     * @param ratio
     * @return
     */
    public static Bitmap sizeCompress(Bitmap bmp, int ratio) {
        // 压缩Bitmap到对应尺寸
        Bitmap result = Bitmap.createBitmap(bmp.getWidth() / ratio, bmp.getHeight() / ratio, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Rect rect = new Rect(0, 0, bmp.getWidth() / ratio, bmp.getHeight() / ratio);
        canvas.drawBitmap(bmp, null, rect, null);
        return result;
    }


    public static void sizeCompress(File bmpFile, File file, int ratio) {
        // 压缩Bitmap到对应尺寸
        Bitmap bitmap = BitmapFactory.decodeFile(bmpFile.getPath() + File.separatorChar + bmpFile.getName());
        sizeCompress(bitmap, file, ratio);
    }


    /**
     * 采样率压缩（设置图片的采样率，降低图片像素）
     *
     * @param filePath
     * @param inSampleSize 数值越高，图片像素越低 2的幂
     */
    public static byte[] samplingRateCompress(String filePath, int inSampleSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
//          options.inJustDecodeBounds = true;//为true的时候不会真正加载图片，而是得到图片的宽高信息。
        //采样率
        options.inSampleSize = inSampleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 获得一个Bitmap大小
     *
     * @param bitmap
     * @return
     */
    public static int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {    //API 19
            return bitmap.getAllocationByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
    }


    public static void samplingRateCompress(String filePath, File file, int inSampleSize) {
        byte[] data = samplingRateCompress(filePath, inSampleSize);
        fileOutput(data, file);
    }


    public static void fileOutput(byte[] bytes, File file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtil.closeOutput(fos);
        }
    }

}
