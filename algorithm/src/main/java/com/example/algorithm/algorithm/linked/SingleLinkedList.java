package com.example.algorithm.algorithm.linked;

import androidx.annotation.NonNull;

public class SingleLinkedList {
    //先初始化一个头结点，头结点不能动
    private Node head = new Node(0, "", "");


    /**
     * 反转链表，在反转指针钱一定要保存下个结点的指针
     */
    public void reserveLink() {
        Node curNode = head;//头结点
        Node preNode = null;//前一个结点
        while (curNode != null) {
            Node nextNode = curNode.next;//保留下一个结点
            curNode.next = preNode;//指针反转
            preNode = curNode;//前结点后移
            curNode = nextNode;//当前结点后移
        }
        head = preNode;
    }


    /**
     * 链表添加结点:
     * 找到链表的末尾结点，把新添加的数据作为末尾结点的后续结点
     *
     * @param newNode
     */
    public void addNode(Node newNode) {
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    /**
     * 寻找单链表的中间结点：
     * 方法一、先求出链表的长度，再遍历1/2链表长度，寻找出链表的中间结点
     * 方法二、：
     * 用两个指针遍历链表，一个快指针、一个慢指针，
     * 快指针每次向前移动2个结点，慢指针一次向前移动一个结点，
     * 当快指针移动到链表的末尾，慢指针所在的位置即为中间结点所在的位置
     */
    public Node findMiddleNode() {
        Node slowPoint = head;
        Node quickPoint = head;
        //quickPoint.next == null是链表结点个数为奇数时，快指针已经走到最后了
        //quickPoint.next.next == null是链表结点数为偶数时，快指针已经走到倒数第二个结点了
        //链表结点个数为奇数时,返回的是中间结点；链表结点个数为偶数时，返回的是中间两个结点中的前一个
        while (quickPoint.next != null && quickPoint.next.next != null) {
            slowPoint = slowPoint.next;
            quickPoint = quickPoint.next.next;
        }
        return slowPoint;
    }

    /**
     * 链表删除结点:
     * 把要删除结点的前结点指向要删除结点的后结点，即直接跳过待删除结点
     *
     * @param index
     * @return
     */
    public boolean deleteNode(int index) {
        if (index < 1 || index > length()) {//待删除结点不存在
            return false;
        }
        if (index == 1) {//删除头结点
            head = head.next;
            return true;
        }
        Node preNode = head;
        Node curNode = preNode.next;
        int i = 1;
        while (curNode != null) {
            if (i == index) {//寻找到待删除结点
                preNode.next = curNode.next;//待删除结点的前结点指向待删除结点的后结点
                return true;
            }
            //当先结点和前结点同时向后移
            preNode = preNode.next;
            curNode = curNode.next;
            i++;
        }
        return true;
    }

    /**
     * 求链表的长度
     *
     * @return
     */
    public int length() {
        int length = 0;
        Node curNode = head;
        while (curNode != null) {
            length++;
            curNode = curNode.next;
        }
        return length;
    }


    @NonNull
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node curNode = head;
        while (curNode != null) {
            stringBuilder.append(curNode.toString());
            stringBuilder.append("\n");
            curNode = curNode.next;
        }
        return stringBuilder.toString();
    }
}
