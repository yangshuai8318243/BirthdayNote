package com.example.algorithm.algorithm.array;

import com.example.algorithm.AlgorithmBaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindData extends AlgorithmBaseFragment {
    @Override
    protected void run() {
        int[] rIntArray = getRIntArray(20);
        ArraySort.sortMaoPao(rIntArray);
        int nextInt = random.nextInt(50);

//        int i = erFenFind(rIntArray, nextInt);
//        int i = findCloceNum(rIntArray, nextInt);
        List list = openDend();
        Object[] objects = list.toArray();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" src data:")
                .append(Arrays.toString(objects));
//                .append("\n")
//                .append("find data:")
//                .append(booleans)
//                .append("\n");
//                .append("index is:")
//                .append(i);
        textView.setText(stringBuilder.toString());


    }

    /**
     * 排序后的二维数组中查找数字
     * 该二维数组中的一个数，
     * 小于它的数一定在其左边，
     * 大于它的数一定在其下边。
     * 因此，从右上角开始查找，
     * 就可以根据 target 和当前元素的大小关系来缩小查找区间，
     * 当前元素的查找区间为左下角的所有元素。
     *
     * @param target
     * @param matrix
     * @return
     */
    public boolean Find(int target, int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int rows = matrix.length, cols = matrix[0].length;
        int r = 0, c = cols - 1; // 从右上角开始
        while (r <= rows - 1 && c >= 0) {
            if (target == matrix[r][c])
                return true;
            else if (target > matrix[r][c])
                r++;
            else
                c--;
        }
        return false;
    }


    private int erFenFind(int[] ints, int findData) {
        int starIndex = 0;
        int endIndex = ints.length - 1;
        if (ints != null) {
            int i = (starIndex + endIndex) / 2;
            while (starIndex <= endIndex) {
                int anInt = ints[i];
                if (anInt == findData) {
                    return i;
                } else if (anInt > findData) {
                    endIndex = i - 1;
                } else {
                    starIndex = i + 1;
                }
                i = (starIndex + endIndex) / 2;
            }

        }
        return -1;
    }


    /**
     * 从一个排好序的数组中从中间开始找，如果大于则将中间值左移，反之右移
     *
     * @param a
     * @param num
     * @return
     */
    public static int binarySearch(int[] a, int num) {
        if (a.length == 0) {
            return -1;
        }
        int startPosition = 0;
        int endPosition = a.length - 1;
        int midPosition = (startPosition + endPosition) / 2;
        while (startPosition <= endPosition) {
            if (a[midPosition] == num) {
                return midPosition;
            }
            if (a[midPosition] > num) {
                endPosition = midPosition - 1;
            }
            if (a[midPosition] < num) {
                startPosition = midPosition + 1;
            }
            midPosition = (startPosition + endPosition) / 2;
        }
        return -1;
    }

    /**
     * 找到最接近的index
     *
     * @param a
     * @param num
     * @return
     */
    public static int findCloceNum(int[] a, int num) {
        int length = a.length;
        int start = 0;
        int end = length - 1;
        int lastIndex = 0;
        int index = (start + end) / 2;

        while (start <= end) {
            int i = a[index];
            if (i > num) {
                end = index - 1;
            } else if (i < num) {
                start = index + 1;
            } else if (i == num) {
                return index;
            }
            lastIndex = index;
            index = (start + end) / 2;
        }
        if (lastIndex == a.length - 1) {
            return lastIndex;
        }
        int i = a[lastIndex];
        int i2 = a[lastIndex + 1];
        if (Math.abs(i - num) > Math.abs(i2 - num)) {
            return lastIndex + 1;
        } else {
            return lastIndex;
        }
    }


    /**
     * 找到没有重复的数字的index
     *
     * @param ints
     * @return
     */
    public static Map<Integer, Integer> findNotTowNum(int[] ints) {
        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        for (int i = 0; i < ints.length; i++) {
            int anInt = ints[i];
            Integer integer = integerIntegerHashMap.get(anInt);
            if (integer == null) {
                integerIntegerHashMap.put(anInt, i);
            } else {
                integerIntegerHashMap.remove(anInt);
            }
        }

//        Set<Integer> integers = integerIntegerHashMap.keySet();
//        for (Integer integer : integers) {
//            integerIntegerHashMap.get(integer);
//        }
//        Collection<Integer> values = integerIntegerHashMap.values();
//        return Collections.max(values);

        return integerIntegerHashMap;
    }

    /**
     * ● 1~100盏灯，都是亮的，第一次将能被1整除的数的灯按下，
     * 变暗，第二次将能被2整除的数的等按下，
     * 变亮，第三次将能被3整除的数的等按下，
     * 变暗…第100次将能被100整除的数的灯按下，问，最后有多少盏灯是亮的。
     * 数学接法
     *
     * @return
     */
    public List openDend() {
        int total = 0;        //统计最后还有几盏灯亮着
        int i = 1;

        while (i * i <= 100) {
            System.out.print(i * i + "\t");
            i++;
            total++;
        }

        System.out.println("\n最后还有" + total + "盏灯是亮着的");
        ArrayList<Integer> integers = new ArrayList<>();
        return integers;
    }

    /**
     * ● 1~100盏灯，都是亮的，第一次将能被1整除的数的灯按下，
     * 变暗，第二次将能被2整除的数的等按下，
     * 变亮，第三次将能被3整除的数的等按下，
     * 变暗…第100次将能被100整除的数的灯按下，问，最后有多少盏灯是亮的。
     * 全部遍历
     *
     * @return
     */
    public List openDend1() {
        boolean[] ints = new boolean[100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if ((j + 1) % (i + 1) == 0) {
                    ints[i] = true;
                } else {
                    ints[i] = false;
                }
            }
        }

        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (ints[i]) {
                integers.add(i);
            }
        }
        return integers;
    }
}
