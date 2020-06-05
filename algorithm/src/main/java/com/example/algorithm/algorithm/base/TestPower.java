package com.example.algorithm.algorithm.base;

import android.util.Log;

import com.example.algorithm.AlgorithmBaseFragment;

public class TestPower extends AlgorithmBaseFragment {
    @Override
    protected void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PrintInt printInt = new PrintInt();
//                printInt.print1ToMaxOfNDigits(12);
                Log.e(TAG, (5 / 2) + "");
                Log.e(TAG, (3 / 2) + "");
                Log.e(TAG, (7 / 2) + "");

            }
        }).start();
    }

    /**
     * 给定一个 double 类型的浮点数 base 和 int 类型的整数 exponent，求 base 的 exponent 次方。
     *
     * @param base
     * @param exponent
     * @return
     */
    public double Power(double base, int exponent) {
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;
        boolean isNegative = false;
        if (exponent < 0) {
            exponent = -exponent;
            isNegative = true;
        }
        double pow = Power(base * base, exponent / 2);
        if (exponent % 2 != 0)
            pow = pow * base;
        return isNegative ? 1 / pow : pow;
    }


}
