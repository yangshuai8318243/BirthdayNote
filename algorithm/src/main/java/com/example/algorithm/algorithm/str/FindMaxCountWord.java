package com.example.algorithm.algorithm.str;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindMaxCountWord {

    static class StrData {
        String name;
        int content;

        public StrData(String name, int content) {
            this.name = name;
            this.content = content;
        }

        @Override
        public String toString() {
            return "StrData{" +
                    "name='" + name + '\'' +
                    ", content=" + content +
                    '}';
        }
    }

    public static List findMaxStr(String[] strings, int top) {
        HashMap<String, Integer> tongji = new HashMap<>();
//        HashMap<Integer, String> top10 = new HashMap<>();
        List<StrData> top10 = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            Integer integer = tongji.get(string);
            if (integer == null) {
                tongji.put(string, 1);
                integer = 1;
            } else {
                integer = integer + 1;
                tongji.put(string, integer);
            }
            StrData strData = new StrData(string, integer);
            Log.e("allMap", "---------->" + strData);

            addList(top10, strData, top);
        }
        Object[] objects = tongji.values().toArray();
        Log.e("allMap", "---------->" + tongji);
        return top10;
    }


    public static void addList(List<StrData> top10, StrData strData, int top) {
        Log.e("addList", "---------->" + strData);
        Log.e("addList", "---------->" + top10.size());

        if (top10.size() < top) {
            top10.add(strData);
        } else {
            for (int i = 0; i < top10.size(); i++) {
                StrData item = top10.get(i);
                if (item.content < strData.content) {
                    top10.remove(i);
                    Log.e("addList", "---------->" + strData);
                    top10.add(i, strData);
                    break;
                }
            }
//            StrData strDataSrc = top10.get(0);
//            if (strData.content > strDataSrc.content) {
//                if (strData.name.equals(strDataSrc.name)) {
//                    strDataSrc.content = strData.content;
//                } else {
//                    if (top10.size() >= top) {
//                        top10.remove(top - 1);
//                    }
//                    top10.add(0, strData);
//                }
//            }
        }

        Collections.sort(top10, new Comparator<StrData>() {
            @Override
            public int compare(StrData o1, StrData o2) {
                return o2.content - o1.content;
            }
        });

    }


    public List<Integer> solutionByHeap(int[] input, int k) {
        List<Integer> list = new ArrayList<>();
        if (k > input.length || k == 0) {
            return list;
        }
        Queue<Integer> queue = new PriorityQueue<>();
        for (int num : input) {
            if (queue.size() < k) {
                queue.add(num);
            } else if (queue.peek() < num) {
                queue.poll();
                queue.add(num);
            }
        }
        while (k-- > 0) {
            list.add(queue.poll());
        }
        return list;
    }


    public static void test1() {
        FindMaxCountWord trie = new FindMaxCountWord();


    }


    public static void compute(String str) {
        int[] k = new int[127];
        for (int i = 0; i < str.length(); i++) {
            k[str.charAt(i)]++;

        }
        int max = k[0];
        for (int i = 0; i < 127; i++) {
            if (k[i] > max)
                max = k[i];
        }
        for (int i = 0; i < 127; i++) {
            if (k[i] == max)
                System.out.println((char) i + "(" + k[i] + "次)");
        }
    }

    public void test() {
        int[] a = new int[]{0, 9, 8};
        Sort(a, a.length - 1);
        for (int i : a) {
            System.out.println(i);
        }
    }

    public static void MaxHeapify(int[] a, int index, int size) {
        int l = 2 * index;
        int r = 2 * index + 1;
        int largest = index;
        if (l <= size && a[l] > a[index]) {
            largest = l;
        }
        if (r <= size && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != index) {
            int temp = a[largest];
            a[largest] = a[index];
            a[index] = temp;
            MaxHeapify(a, largest, size);
        }
    }

    public static void HeapBuild(int[] a, int size) {
        for (int i = size / 2; i >= 1; i--) {
            MaxHeapify(a, i, size);
        }
    }

    public static void Sort(int[] a, int size) {
        HeapBuild(a, size);
        for (int i = size; i >= 2; i--) {
            int temp = a[i];
            a[i] = a[1];
            a[1] = temp;
            MaxHeapify(a, 1, i - 1);
        }
    }

}
