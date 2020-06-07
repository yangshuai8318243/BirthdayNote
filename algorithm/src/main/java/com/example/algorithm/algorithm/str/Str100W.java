package com.example.algorithm.algorithm.str;

import android.util.Log;

import com.example.algorithm.AlgorithmBaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Str100W extends AlgorithmBaseFragment {

    @Override
    protected void run() {
//        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
//        Collection<Object> values = objectObjectHashMap.values();
//        List<Collection<Object>> collections = Arrays.asList(values);
//        Collections.sort(collections, new Comparator<Collection<Object>>() {
//            @Override
//            public int compare(Collection<Object> o1, Collection<Object> o2) {
//
//                return 0;
//            }
//        });
//        test1();
//        test2();
        StrAdd strAdd = new StrAdd();
        String s = strAdd.bigNumAdd("192382489312", "431094");
        int i = 123 & 321;
//        textView.setText((123&321));
        new TreeSet<Integer>().add(1);
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("","");
        stringStringHashMap.get("");
    }

    private void test1() {
        String[] data = {"paper", "ckup", "book", "cup", "pen", "book", "aaaa", "bbbb", "cccc", "dddd"};
        String[] src = new String[1000];
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            int i2 = random.nextInt(data.length - 1);
            src[i] = data[i2];
        }

        List maxStr = FindMaxCountWord.findMaxStr(src, 3);

        Object[] objects = maxStr.toArray();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("max str")
                .append(Arrays.toString(objects));
        String s = stringBuilder.toString();
        textView.setText(s);
    }

    private void test2() {
        List<FindMaxCountWord.StrData> stus = new ArrayList<>();
        FindMaxCountWord.StrData stu1 = new FindMaxCountWord.StrData("张三", 10);
        FindMaxCountWord.StrData stu2 = new FindMaxCountWord.StrData("李四", 20);
        FindMaxCountWord.StrData stu3 = new FindMaxCountWord.StrData("王五", 5);

        stus.add(stu1);
        stus.add(stu2);
        stus.add(stu3);

        //对users按年龄进行排序
        Collections.sort(stus, new Comparator<FindMaxCountWord.StrData>() {

            @Override
            public int compare(FindMaxCountWord.StrData o1, FindMaxCountWord.StrData o2) {
                // 升序
                return o1.content - o2.content;
            }
        });

        Log.e(getClass().getSimpleName(), Arrays.toString(stus.toArray()));
    }

    private void test() {
        int[] k = new int[127];
        String s = k.toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Arrays.toString(k));
        for (int i = 0; i < 127; i++) {
            k[i]++;
        }
        stringBuilder.append('\n');
        stringBuilder.append(Arrays.toString(k));

        textView.setText(stringBuilder.toString());

    }
}
