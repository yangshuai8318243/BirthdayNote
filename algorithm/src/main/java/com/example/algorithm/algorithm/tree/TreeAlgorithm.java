package com.example.algorithm.algorithm.tree;

import android.util.Log;

import com.example.algorithm.AlgorithmBaseFragment;

import java.util.TreeMap;

public class TreeAlgorithm extends AlgorithmBaseFragment {
    @Override
    protected void run() {
        TreeNode treeNode = new TreeNode();
        BinaryTree.createBinTree(treeNode);
        int size = height(treeNode);
        BinaryTree bt = new BinaryTree();
        bt.createBinTree(bt.getRoot());
        Log.e(TAG, "----------bt----size---->\n" + bt.height());
        Log.e(TAG, "----------size-------->\n" + size);
    }

    private void Mirror(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        swap(treeNode);
        Mirror(treeNode.leftChild);
        Mirror(treeNode.rightChild);

    }

    private void swap(TreeNode treeNode) {
        TreeNode leftChild = treeNode.leftChild;
        treeNode.leftChild = treeNode.rightChild;
        treeNode.rightChild = leftChild;
    }


    public int TreeDepth(TreeNode root) {
        return height(root);
    }

    private int height(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }else {
            int leftSize = height(treeNode.leftChild) + 1;
            int rightSize = height(treeNode.rightChild) + 1;
            return Math.max(leftSize, rightSize);
        }

    }
}
