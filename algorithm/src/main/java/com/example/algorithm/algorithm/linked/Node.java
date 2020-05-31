package com.example.algorithm.algorithm.linked;

public class Node {
    int o;
    String name;
    String linkeName;
    Node next;

    public Node(int o) {
        this(o, "", "");
    }

    public Node(int o, String name, String linkeName) {
        this.o = o;
        this.name = name;
        this.linkeName = linkeName;
    }


    @Override
    public String toString() {
        return "Node{" +
                "o=" + o +
                ", name='" + name + '\'' +
                ", linkeName='" + linkeName + '\'' +
                '}';
    }
}
