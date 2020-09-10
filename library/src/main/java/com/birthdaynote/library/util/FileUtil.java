package com.birthdaynote.library.util;

import android.app.Application;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;


import com.birthdaynote.library.log.AppLog;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileUtil {
    // 用于获取APP的所在包目录
    public static String s_packageName;
    //来获得当前应用程序对应的apk文件的路径
    public static String s_packageCodePath;
    // 获取该程序的安装包路径
    public static String s_packageResourcePath;
    // 获得根目录/data
    public static String s_DataDirectory = Environment.getDataDirectory().getPath();

    //获得缓存目录/cache
    public static String s_DownloadCacheDirectory = Environment.getDownloadCacheDirectory().getPath();
    //获得SD卡目录/mnt/sdcard
    public static String s_ExternalStorageDirectory = Environment.getExternalStorageDirectory().getPath();
    // 获得系统目录/system
    public static String s_RootDirectoryy = Environment.getRootDirectory().getPath();


    /**
     * 初始化获得部分路径
     *
     * @param application
     */
    public static void init(Application application) {
        s_packageName = application.getPackageName();
        s_packageCodePath = application.getPackageCodePath();
        s_packageResourcePath = application.getPackageResourcePath();
    }


    //检查SDCard存在并且可以读写
    public static boolean isSDCardState() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 判断文件是否已经存在
     *
     * @param fileName 要检查的文件名
     * @return boolean, true表示存在，false表示不存在
     */
    public static boolean isFileExist(String fileName) {
        File file = new File("绝对路径" + fileName);
        return file.exists();
    }

    /**
     * 新建目录
     *
     * @param path 目录的绝对路径
     * @return 创建成功则返回true
     */
    public static boolean createFolder(String path) {
        File file = new File(path);
        return file.mkdir();
    }

    /**
     * 创建文件
     *
     * @param path     文件所在目录的目录名
     * @param fileName 文件名
     * @return 文件新建成功则返回true
     */
    public static boolean createFile(String path, String fileName) {
        File file = new File(path + File.separator + fileName);
        if (file.exists()) {
            return false;
        } else {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 删除单个文件
     *
     * @param path     文件所在的绝对路径
     * @param fileName 文件名
     * @return 删除成功则返回true
     */
    public static boolean deleteFile(String path, String fileName) {
        File file = new File(path + File.separator + fileName);
        return file.exists() && file.delete();
    }

    /**
     * 删除一个目录（可以是非空目录）
     *
     * @param dir 目录绝对路径
     */
    public static boolean deleteDirection(File dir) {
        if (dir == null || !dir.exists() || dir.isFile()) {
            return false;
        }
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                deleteDirection(file);//递归
            }
        }
        dir.delete();
        return true;
    }

    /**
     * 将字符串写入文件
     *
     * @param text     写入的字符串
     * @param fileStr  文件的绝对路径
     * @param isAppend true从尾部写入，false从头覆盖写入
     */
    public static void writeFile(String text, String fileStr, boolean isAppend) {
        try {
            File file = new File(fileStr);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream f = new FileOutputStream(fileStr, isAppend);
            f.write(text.getBytes());
            f.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 拷贝文件
     *
     * @param srcPath 绝对路径
     * @param destDir 目标文件所在目录
     * @return boolean true拷贝成功
     */
    public static boolean copyFile(String srcPath, String destDir) {
        boolean flag = false;
        File srcFile = new File(srcPath); // 源文件
        if (!srcFile.exists()) {
            AppLog.i("FileUtils is copyFile：", "源文件不存在");
            return false;
        }
        // 获取待复制文件的文件名
        String fileName = srcPath.substring(srcPath.lastIndexOf(File.separator));
        String destPath = destDir + fileName;
        if (destPath.equals(srcPath)) {
            AppLog.i("FileUtils is copyFile：", "源文件路径和目标文件路径重复");
            return false;
        }
        File destFile = new File(destPath); // 目标文件
        if (destFile.exists() && destFile.isFile()) {
            AppLog.i("FileUtils is copyFile：", "该路径下已经有一个同名文件");
            return false;
        }
        File destFileDir = new File(destDir);
        destFileDir.mkdirs();
        try {
            FileInputStream fis = new FileInputStream(srcPath);
            FileOutputStream fos = new FileOutputStream(destFile);
            byte[] buf = new byte[1024];
            int c;
            while ((c = fis.read(buf)) != -1) {
                fos.write(buf, 0, c);
            }
            fis.close();
            fos.close();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 重命名文件
     *
     * @param oldPath 旧文件的绝对路径
     * @param newPath 新文件的绝对路径
     * @return 文件重命名成功则返回true
     */
    public static boolean renameTo(String oldPath, String newPath) {
        if (oldPath.equals(newPath)) {
            AppLog.i("FileUtils is renameTo：", "文件重命名失败：新旧文件名绝对路径相同");
            return false;
        }
        File oldFile = new File(oldPath);
        File newFile = new File(newPath);

        return oldFile.renameTo(newFile);
    }

    /**
     * 计算某个文件的大小
     *
     * @param path 文件的绝对路径
     * @return 文件大小
     */
    public static long getFileSize(String path) {
        File file = new File(path);
        return file.length();
    }

    /**
     * 计算某个文件夹的大小
     *
     * @param file 目录所在绝对路径
     * @return 文件夹的大小
     */
    public static double getDirSize(File file) {
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                double size = 0;
                for (File f : children)
                    size += getDirSize(f);
                return size;
            } else {//如果是文件则直接返回其大小,以“兆”为单位
                return (double) file.length() / 1024 / 1024;
            }
        } else {
            return 0.0;
        }
    }

    /**
     * 获取某个路径下的文件列表
     *
     * @param path 文件路径
     * @return 文件列表File[] files
     */
    public static File[] getFileList(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                return files;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 计算某个目录包含的文件数量
     *
     * @param path 目录的绝对路径
     * @return 文件数量
     */
    public static int getFileCount(String path) {
        File directory = new File(path);
        File[] files = directory.listFiles();
        return files.length;
    }

    /**
     * 获取SDCard 总容量大小(MB)
     *
     * @param path 目录的绝对路径
     * @return 总容量大小
     */
    public long getSDCardTotal(String path) {

        if (null != path && path.equals("")) {

            StatFs statfs = new StatFs(path);
            //获取SDCard的Block总数
            long totalBlocks = statfs.getBlockCount();
            //获取每个block的大小
            long blockSize = statfs.getBlockSize();
            //计算SDCard 总容量大小MB
            return totalBlocks * blockSize / 1024 / 1024;

        } else {
            return 0;
        }
    }

    /**
     * 获取SDCard 可用容量大小(MB)
     *
     * @param path 目录的绝对路径
     * @return 可用容量大小
     */
    public long getSDCardFree(String path) {

        if (null != path && path.equals("")) {

            StatFs statfs = new StatFs(path);
            //获取SDCard的Block可用数
            long availaBlocks = statfs.getAvailableBlocks();
            //获取每个block的大小
            long blockSize = statfs.getBlockSize();
            //计算SDCard 可用容量大小MB
            return availaBlocks * blockSize / 1024 / 1024;

        } else {
            return 0;
        }
    }

    public static boolean isImageFile(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        String reg = ".+(\\.jpeg|\\.jpg|\\.gif|\\.bmp|\\.png).*";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(url.toLowerCase());
        return matcher.find();
    }

    public static boolean isVideoFile(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        String reg = ".+(\\.avi|\\.wmv|\\.mpeg|\\.mp4|\\.mov|\\.mkv|\\.flv|\\.f4v|\\.m4v|\\.rmvb|\\.rm|\\.rmvb|\\.3gp|\\.dat|\\.ts|\\.mts|\\.vob).*";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(url.toLowerCase());
        return matcher.find();
    }

    public static boolean isUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        String reg = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        return url.matches(reg);
    }

    public static byte[] getFileByte(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            return null;
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            in.close();
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
