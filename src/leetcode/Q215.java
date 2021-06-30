package leetcode;

import common.Test;
import common.Utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 数组中的第K个最大元素
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 */
public class Q215 implements Test {

    /**
     * 最小堆
     * https://www.bilibili.com/video/BV1xa411A76q?p=23
     */
    private int[] topK(int[] arr, int k){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k);
        int[] result = new int[k];
        for (int i : arr) {
            priorityQueue.add(i);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        for (int i = 0; i < k; i++) {
            result[k - 1 - i] = priorityQueue.poll();
        }
        return result;
    }

    @Override
    public boolean test() {
        final int ARR_SIZE = 20;
        int[] arr = Utils.generateIntArray(ARR_SIZE, ARR_SIZE);
        final int k = Utils.generateRandomInteger(1, 5);

        Integer[] sortedArr = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(sortedArr, Collections.reverseOrder());
        
        Utils.print("原数组", Arrays.toString(arr));
        Utils.print("排序后", Arrays.toString(sortedArr));
        int[] computedTopKs = topK(arr, k);
        Utils.print("计算得到的前",k,"大的数是", Arrays.toString(computedTopKs));
        int topK = sortedArr[k - 1];
        int computedTopK = computedTopKs[k - 1];
        Utils.print("第",k,"大的元素是", computedTopK);
        return topK == computedTopK;
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q215(), 100);
    }
}
