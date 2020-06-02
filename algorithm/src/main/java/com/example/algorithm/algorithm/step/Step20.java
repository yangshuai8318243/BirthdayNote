package com.example.algorithm.algorithm.step;

import com.example.algorithm.AlgorithmBaseFragment;

import java.util.Arrays;

/**
 * 我们可以用 2*1 的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用 n 个 2*1 的小矩形无重叠地覆盖一个 2*n 的大矩形，总共有多少种方法？
 * <p>
 * 总共有20级台阶，每次只能上一级或者是两级，问有多少种方法可以上去
 * <p>
 * 以上两个是相同的类型问题
 */
public class Step20 extends AlgorithmBaseFragment {
    @Override
    protected void run() {
//        int test = test(20);
        int test = text1(20);
        int test1 = JumpFloorII1(20);

        textView.setText("当20台阶方法为：" + test + "\n" + test1);
    }

    private int fuind(int da) {
        if (da == 1) {
            return 1;
        }
        if (da == 2) {
            return 2;
        }
        if (da > 2)
            return fuind(da - 1) + fuind(da - 2);

        return -1;
    }

    /**
     * 总共有20级台阶，每次只能上一级或者是两级，问有多少种方法可以上去
     * 第 n 级台阶可以从第抄 n-1 和 第 n-2 阶迈上来2113
     * 所以假设迈到5261第n级可以有 a(n) 种方法
     * 则 a(n)=a(n-1)+a(n-2)
     * 而 第一阶 1种
     * 第二阶 2种
     * 所以从第n阶开始递归到1或者2就可以计算出
     *
     * @param n
     * @return
     */
    private int test(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        if (n > 2) {
            return test(n - 1) + test(n - 2);
        }

        return -1;
    }

    /**
     * 递归是将一个问题划分成多个子问题求解，
     * 动态规划也是如此，
     * 但是动态规划会把子问题的解缓存起来，
     * 从而避免重复求解子问题。
     *
     * @param n
     * @return
     */
    public int Fibonacci(int n) {
        if (n <= 1)
            return n;
        int[] fib = new int[n + 1];
        fib[1] = 1;
        for (int i = 2; i <= n; i++)
            fib[i] = fib[i - 1] + fib[i - 2];
        return fib[n];
    }


    /**
     * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级... 它也可以跳上 n 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * 跳上 n-1 级台阶，可以从 n-2 级跳 1 级上去，也可以从 n-3 级跳 2 级上去...，那么
     * f(n-1) = f(n-2) + f(n-3) + ... + f(0)
     * 同样，跳上 n 级台阶，可以从 n-1 级跳 1 级上去，也可以从 n-2 级跳 2 级上去... ，那么
     * f(n) = f(n-1) + f(n-2) + ... + f(0)
     * f(n) - f(n-1) = f(n-1)
     * f(n) = 2f(n-1)
     *
     * @param n
     * @return
     */
    private int text1(int n) {
        if (n == 1) {
            return 1;
        }
        return 2 * text1(n - 1);
    }

    public int JumpFloorII1(int target) {
        return (int) Math.pow(2, target - 1);
    }

    public int JumpFloorII2(int target) {
        int[] dp = new int[target];
        Arrays.fill(dp, 1);
        for (int i = 1; i < target; i++)
            for (int j = 0; j < i; j++)
                dp[i] += dp[j];
        return dp[target - 1];
    }
}
