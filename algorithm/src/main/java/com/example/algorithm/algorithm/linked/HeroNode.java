package com.example.algorithm.algorithm.linked;

public class HeroNode {
    public int no;
    public String name;
    public String nikename;
    public HeroNode next;//指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nikename) {
        this.no = no;
        this.name = name;
        this.nikename = nikename;
    }
    //为了显示方便，我们重写一下tostring方法


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nikename='" + nikename + '\'' +

                '}';
    }
}
