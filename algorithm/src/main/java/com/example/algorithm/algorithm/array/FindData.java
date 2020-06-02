package com.example.algorithm.algorithm.array;

import com.example.algorithm.AlgorithmBaseFragment;

import java.util.Arrays;

public class FindData extends AlgorithmBaseFragment {
    @Override
    protected void run() {
        int[] rIntArray = getRIntArray(20);
        ArraySort.sortMaoPao(rIntArray);
        int nextInt = random.nextInt(20);

        int i = erFenFind(rIntArray, nextInt);

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

}
