package com.example.algorithm.algorithm.tree;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.algorithm.AlgorithmBaseFragment;
import com.example.algorithm.R;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

public class TreeAlgorithm extends AlgorithmBaseFragment {
    @Override
    protected void run() {
        TreeNode treeNode = new TreeNode();
        BinaryTree.createBinTree(treeNode);
        int size = height(treeNode);
        BinaryTree bt = new BinaryTree();
//        bt.createBinTree(bt.getRoot());
//        Log.e(TAG, "----------bt----size---->\n" + bt.height());
        TreeNode treeNode1 = viewG2Tree((ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.test_layout_hight, null));
        size = height(treeNode1);

        Log.e(TAG, "----------size-------->\n" + size);

    }


    /**
     * 翻转一个二叉树
     *
     * @param treeNode
     */
    private void Mirror(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        //先翻转
        swap(treeNode);
        //再深入
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

    /**
     * 查一个二叉树的高度
     *
     * @param treeNode
     * @return
     */
    private int height(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        } else {
            int leftSize = height(treeNode.leftChild) + 1;
            int rightSize = height(treeNode.rightChild) + 1;
            return Math.max(leftSize, rightSize);
        }

    }

    /**
     * 将一个viewgroup的深度转换为二叉树的深度
     *
     * @param viewGroup
     * @return
     */
    private TreeNode viewG2Tree(ViewGroup viewGroup) {
        int vgHight = getVGHight(viewGroup);
        TreeNode treeNodeRoot = new TreeNode();
        TreeNode treeNode = null;
        for (int i = 1; i < vgHight; i++) {
            if (treeNode == null) {
                treeNode = treeNodeRoot;
            }
            treeNode.rightChild = new TreeNode();
            treeNode.leftChild = new TreeNode();
            treeNode = treeNode.leftChild;
        }

        return treeNodeRoot;
    }

    private int getVGHight(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        int max = 1;
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                ViewGroup cV = (ViewGroup) childAt;
                int i1 = getVGHight(cV);
                max = Math.max(i1, max);
            }
        }
        return max + 1;
    }

    /**
     * 计算二叉树的宽度
     *
     * @param treeNode
     * @return
     */
    public static int maxWidth(TreeNode treeNode) {
        if (treeNode == null) return 0;

        Queue<TreeNode> queue = new ArrayDeque<>();

        int maxWidth = 0;
        queue.add(treeNode);

        while (true) {
            int size = queue.size();
            if (size == 0) break;
            while (size > 0) {
                TreeNode poll = queue.poll();
                size--;
                if (poll.leftChild != null) {
                    queue.add(poll.leftChild);
                }
                if (poll.rightChild != null) {
                    queue.add(treeNode.rightChild);
                }
            }

            maxWidth = Math.max(maxWidth, queue.size());
        }
        return maxWidth;
    }

    public static int maxTerrWith(TreeNode treeNode) {
        if (treeNode == null) return 0;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.add(treeNode);
        int max = 0;
        while (true) {
            int size = deque.size();
            if (size == 0) break;
            while (size > 0) {
                TreeNode poll = deque.poll();
                size--;
                if (poll.leftChild != null) {
                    deque.add(poll.leftChild);
                }
                if (poll.rightChild != null) {
                    deque.add(poll.rightChild);
                }
            }

            max = Math.max(max, deque.size());
        }
        return max;
    }

    /**
     * 找到一条树的路径为指定值的和
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (root == null) {
            return list;
        }
        List<Integer> temp = new ArrayList<Integer>();
        getPass(root, sum, list, temp, 0);
        return list;

    }

    /**
     * 递归方法，找到路径
     *
     * @param root
     * @param sum
     * @param list
     * @param temp
     * @param add
     */
    public void getPass(TreeNode root, int sum, List<List<Integer>> list, List<Integer> temp, int add) {
		/*如果是负数呢
		 * if(add>sum){
			return;
		}*/
        if (root.leftChild == null && root.rightChild == null) {
            if (add + root.key == sum) {
                temp.add(root.key);
                list.add(new ArrayList<Integer>(temp));
                temp.remove(temp.size() - 1);
            }
            return;
        } else {
            if (root.leftChild != null) {
                temp.add(root.key);
                getPass(root.leftChild, sum, list, temp, add + root.key);
                temp.remove(temp.size() - 1);
            }
            if (root.rightChild != null) {
                temp.add(root.key);
                getPass(root.rightChild, sum, list, temp, add + root.key);
                temp.remove(temp.size() - 1);
            }
        }
    }

}
