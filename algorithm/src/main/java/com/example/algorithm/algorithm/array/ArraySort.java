package com.example.algorithm.algorithm.array;

import com.example.algorithm.AlgorithmBaseFragment;

import java.util.Arrays;
import java.util.Random;

public class ArraySort extends AlgorithmBaseFragment {
    @Override
    protected void run() {
        int[] scr = new int[20];
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int i2 = random.nextInt(scr.length - 1);
            scr[i] = i2;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("src array:\n");
        stringBuilder.append(Arrays.toString(scr));

//        sortMaoPao(scr);
        scr = mergeSort(scr);

        stringBuilder.append("sort array:\n");
        stringBuilder.append(Arrays.toString(scr));

        textView.setText(stringBuilder.toString());
    }

    /**
     * 归并排序
     *
     * @param ints
     * @return
     */
    public static int[] mergeSort(int[] ints) {
        if (ints.length < 2) {
            return ints;
        }
        int i = ints.length / 2;
        int[] left = Arrays.copyOfRange(ints, 0, i);
        int[] right = Arrays.copyOfRange(ints, i, ints.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 归并排序——将两段排序好的数组结合成一个排序数组
     *
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, l = 0, r = 0; index < result.length; index++) {
            if (l >= left.length)
                result[index] = right[r++];
            else if (r >= right.length)
                result[index] = left[l++];
            else if (left[l] > right[r])
                result[index] = right[r++];
            else
                result[index] = left[l++];
        }
        return result;
    }

    /**
     * 冒泡排序
     *
     * @param ints
     */
    public static void sortMaoPao(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length - 1; j++) {
                if (ints[j + 1] < ints[j]) {
                    int temp = ints[j + 1];
                    ints[j + 1] = ints[j];
                    ints[j] = temp;
                }
            }
        }
    }


}
