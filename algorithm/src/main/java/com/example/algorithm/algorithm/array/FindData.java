package com.example.algorithm.algorithm.array;

import com.example.algorithm.AlgorithmBaseFragment;

import java.util.Arrays;

public class FindData extends AlgorithmBaseFragment {
    @Override
    protected void run() {
        int[] rIntArray = getRIntArray(50);
        ArraySort.sortMaoPao(rIntArray);
        int nextInt = random.nextInt(50);

        int i = binarySearch(rIntArray, nextInt);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" src data:")
                .append(Arrays.toString(rIntArray))
                .append("\n")
                .append("find data:")
                .append(nextInt)
                .append("\n")
                .append("index is:")
                .append(i);
        textView.setText(stringBuilder.toString());


    }


    /**
     * 从一个排好序的数组中从中间开始找，如果大于则将中间值左移，反之右移
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

}
