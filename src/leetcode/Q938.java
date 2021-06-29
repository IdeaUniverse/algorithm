package leetcode;


import algorithm.dataStructure.tree.BinarySearchTree;
import algorithm.dataStructure.tree.TreeNode;
import common.Test;
import common.Utils;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Q938 implements Test {

    public int rangeSumBST(BinarySearchTree<Integer> bst, int low, int high) {
        TreeNode<Integer> current = bst.root;
        if(current == null) return 0;
        Queue<TreeNode<Integer>> queue = new ConcurrentLinkedQueue<>();
        queue.add(current);
        int sum = 0;
        while (queue.size() > 0){
            current = queue.poll();
            if(current.data >= low && current.data <= high)
                sum += current.data;
            if(current.leftChild != null)
                queue.add(current.leftChild);
            if(current.rightChild != null)
                queue.add(current.rightChild);
        }
        return sum;
    }

    @Override
    public boolean test() {
        int[] arr = Utils.generateNoRepeatIntArray(10, 20);
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int sum = 0;
        int lowIndex = Utils.generateRandomInteger(0, arr.length);
        int highIndex = Utils.generateRandomInteger(0, arr.length);
        int high = Math.max(arr[lowIndex], arr[highIndex]);
        int low = Math.min(arr[lowIndex], arr[highIndex]);

        for (int v : arr){
            bst.insert(v);
            if(v >= low && v <= high)
                sum += v;
        }
        int result = rangeSumBST(bst, low, high);
        Utils.print("原数组", Arrays.toString(arr));
        Utils.print("low =", low, "high =", high, "计算答案", result,"正确答案", sum);
        return result == sum;
    }


    public static void main(String[] args) {
        Utils.batchTest(new Q938(), 100);
    }
}