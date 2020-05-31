package com.example.algorithm.algorithm.tack;

import java.util.Stack;

/**
 * in 栈用来处理入栈（push）操作，out 栈用来处理出栈（pop）操作。
 * 一个元素进入 in 栈之后，
 * 出栈的顺序被反转。
 * 当元素要出栈时，需要先进入 out 栈，此时元素出栈顺序再一次被反转，
 * 因此出栈顺序就和最开始入栈顺序是相同的，
 * 先进入的元素先退出，这就是队列的顺序
 */
public class Tack2Linked {
    Stack<Integer> in = new Stack<Integer>();
    Stack<Integer> out = new Stack<Integer>();

    public void put(int data) {
        in.push(data);
    }

    public int pop() {
        if (out.empty()) {
            while (!in.empty()) {
                Integer pop = in.pop();
                out.push(pop);
            }
        }

        return out.pop();
    }
}
