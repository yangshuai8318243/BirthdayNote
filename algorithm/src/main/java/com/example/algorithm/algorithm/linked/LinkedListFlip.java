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
        stringBuilder.append("old data：" + "\n")
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

    private void fanZhuanLinke(Node root) {
        Node head = root;
        Node preNode = null;

        while (head != null) {
            Node nextNode = head.next;
            head.next = preNode;
            preNode = head;
            head = nextNode;
        }

    }


    /**
     * 在 O(1) 时间内删除链表节点
     * 如果该节点不是尾节点，那么可以直接将下一个节点的值赋给该节点，
     * 然后令该节点指向下下个节点，再删除下一个节点，时间复杂度为 O(1)。
     * 否则，就需要先遍历链表，找到节点的前一个节点，然后让前一个节点指向 null，时间复杂度为 O(N)。
     *
     * @param head
     * @param tobeDelete
     * @return
     */
    public Node deleteNode(Node head, Node tobeDelete) {
        if (head == null || tobeDelete == null)
            return null;
        if (tobeDelete.next != null) {
            // 要删除的节点不是尾节点
            Node next = tobeDelete.next;
//            tobeDelete.val = next.val;
            tobeDelete.next = next.next;
        } else {
            if (head == tobeDelete)
                // 只有一个节点
                head = null;
            else {
                Node cur = head;
                while (cur.next != tobeDelete)
                    cur = cur.next;
                cur.next = null;
            }
        }
        return head;
    }

}
