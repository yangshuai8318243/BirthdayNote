package com.example.algorithm.file;

import com.birthdaynote.library.data.net.OkNetManager;
import com.birthdaynote.library.log.AppLog;
import com.birthdaynote.library.util.FileUtil;
import com.birthdaynote.library.util.StringUitls;
import com.example.algorithm.AlgorithmBaseFragment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/17
 * Time: 9:40
 */
public class FileDataTest extends AlgorithmBaseFragment {
    @Override
    protected void run() {
        fileUploadTest();
    }

    private void fileUploadTest() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        OkNetManager.get().initOkClient(okHttpClient);

        OkNetManager.get().requestSyn("http://gok-rescy.mjyx.com/shenzhen/test_biaoqin_01.zip", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                String folderPath = FileUtil.s_ExternalStorageDirectory + File.separator + "algorithm";
                AppLog.e(TAG, "--------folderPath--------->" + folderPath);
                String zipFilePath = folderPath + File.separator + "NetBiaoqin.zip";
                FileUtil.createFolder(folderPath);
                FileUtil.createFile(folderPath, "NetBiaoqin.zip");
                boolean b = FileUtil.copyFile(inputStream, zipFilePath);
                String fileMD5 = FileUtil.getFileMD5(new File(zipFilePath)).toUpperCase();
                AppLog.e(TAG, "-------fileMD5--------", fileMD5);
                if (b) {
                    FileUtil.unZip(new File(zipFilePath), folderPath);
                    String urlFileName = StringUitls.getUrlFileName("http://gok-rescy.mjyx.com/shenzhen/test.zip", "zip");
                    String fileNameNoEx = FileUtil.getFileNameNoEx(urlFileName);
                    String configPath = folderPath + File.separator + fileNameNoEx + File.separator + "config.json";
                    byte[] fileByte = FileUtil.getFileByte(configPath);
                    String json = new String(fileByte);
                    AppLog.e(TAG, "--------->", json);
                }
            }
        });
    }

    private void fileTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream open = getContext().getAssets().open("shieldwords.txt");
                    List<String> inputStreamToList = FileUtil.getInputStreamToList(open);
                    long l = System.currentTimeMillis();
                    String str = "evanixwindycity风城";
                    for (String ke : inputStreamToList) {
                        str = str.replace(ke, "***");
                    }
                    AppLog.i(TAG, "时间为：" + (System.currentTimeMillis() - l), str);
                    AppLog.i(TAG, "时间为：" + (System.currentTimeMillis() - l), (100 / 3));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
