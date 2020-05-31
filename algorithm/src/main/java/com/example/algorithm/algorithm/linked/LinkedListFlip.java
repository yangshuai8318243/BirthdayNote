package com.example.algorithm.algorithm.linked;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.algorithm.AlgorithmBaseFragment;

public class LinkedListFlip extends AlgorithmBaseFragment {

    private SingleLinkedList singleLinkedList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addLinke();
    }

    @Override
    protected void run() {
        printSLinke();
    }

    private void printSLinke() {
        String old = singleLinkedList.toString();
        singleLinkedList.reserveLink();
        String newStr = singleLinkedList.toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("old data："+"\n")
                .append(old + "\n")
                .append("new Data：" + "\n")
                .append(newStr + "\n");

        textView.setText(stringBuilder.toString());
    }

    private void addLinke() {
        singleLinkedList = new SingleLinkedList();
        for (int i = 0; i < 10; i++) {
            int test = i + 1;
            singleLinkedList.addNode(new Node(test, "name：" + test, "Node" + test));
        }
    }

}
