package com.example.algorithm.algorithm.tree;

/**
 * 二叉树的节点数据结构
 */
public class TreeNode {
    int key = 0;
    String data = null;
    boolean isVisted = false;
    TreeNode leftChild = null;
    TreeNode rightChild = null;

    public TreeNode() {
    }

    /**
     * @param key  层序编码
     * @param data 数据域
     */
    public TreeNode(int key, String data) {
        this.key = key;
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "key=" + key +
                ", data='" + data + '\'' +
                ", isVisted=" + isVisted +
                ", \nleftChild=" + leftChild +
                ", \nrightChild=" + rightChild +
                '}' + "\n";
    }
}
