package com.example.algorithm.algorithm.array;

import android.util.Log;

import com.example.algorithm.AlgorithmBaseFragment;

import java.util.Arrays;

public class ArrayNew extends AlgorithmBaseFragment {
    @Override
    protected void run() {
        int[] intsA = new int[5];
        for (int i = 0; i < 5; i++) {
            intsA[i] = i + 1;
        }

        int[] intsB = multiply(intsA);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("intsA data:")
                .append("\n")
                .append(Arrays.toString(intsA))
                .append("\nintsB data:\n")
                .append(Arrays.toString(intsB));

        textView.setText(stringBuilder.toString());

    }

    private void test(int[] ints) {
        if (ints == null) {
            return;
        }

        int[] data = new int[ints.length];
        for (int i = 0, index = 1; i < ints.length; index *= ints[i], i++) {
            data[i] = index;
        }

        for (int i = ints.length - 1, index = 1; i > 0; index *= ints[i], i--) {
            data[i] *= index;
        }

    }

    /**
     * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
     * （注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
     * 因为在循环中定义变量，所以在i++之前相乘则，始终 B[i] 为 A 0到I-1的乘机，反向同理
     *
     * @param A
     * @return d
     */
    private int[] multiply(int[] A) {
        int n = A.length;
        int[] B = new int[n];
        for (int i = 0, product = 1; i < n; product *= A[i], i++) {
            /* 从左往右累乘 */
            B[i] = product;
            Log.e(TAG, "----111---11-->" + product);
            Log.e(TAG, "----111---22-->" + A[i]);
        }

        for (int i = n - 1, product = 1; i >= 0; product *= A[i], i--) {
            /* 从右往左累乘 */
            B[i] *= product;
            Log.e(TAG, "----222---11-->" + product);
            Log.e(TAG, "----222---22-->" + A[i]);
        }
        return B;

    }

}
