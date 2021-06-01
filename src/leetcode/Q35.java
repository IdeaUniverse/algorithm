package leetcode;

import common.Test;
import common.Utils;

import java.util.Arrays;
import java.util.Random;

/**
 * https://leetcode-cn.com/problems/search-insert-position/
 */
public class Q35 implements Test {

    /**
     * 假设数组递增排序
     * 递归解决
     */
    private int searchInsertRecursively(int[] arr, int target){
        if(arr.length == 0) return 0;
        return searchIndex(arr, target, 0, arr.length - 1);
    }

    /**
     * 递归解决
     */
    private int searchIndex(int[] arr, int target, int start, int end){
        if(start >= end) { // 其实这里只剩一个元素了
            if (target < arr[start]) return start;
            else if(target > arr[start]) return end + 1;
        }
        int middle = (start + end) / 2;
        if(target == arr[middle]) {
            return middle;
        }else if(target < arr[middle]) {
            return searchIndex(arr, target, start, middle - 1);
        }else {
            return searchIndex(arr, target, middle + 1, end);
        }
    }

    /**
     * 循环解决
     */
    private int searchInsertLoop(int[] arr, int target){
        int left = 0, right = arr.length - 1, middle = 0;
        while (left <= right){
            middle = (left + right) / 2;
            if(target == arr[middle]) return middle;
            if(target < arr[middle]) right = middle - 1;
            else left = middle + 1;
        }
        return target < arr[middle] ? middle : middle + 1;
    }

    @Override
    public boolean test() {
        int arrSize = Utils.generateRandomInteger(1, 20);
        int[] arr = Utils.generateNoRepeatIntArray(arrSize, 100);
        Arrays.sort(arr);
        int index = new Random().nextInt(arr.length + 1);
        int target;
        if(index == arr.length) target = 200;
        else target = arr[index];
        Utils.print("原数组:", Arrays.toString(arr), "数组长度:", arr.length, "目标值:", target, "目标索引:", index);
        int result1 = searchInsertRecursively(arr, target);
        int result2 = searchInsertLoop(arr, target);
        boolean flag = result1 == result2 && result2 == index;
        Utils.print("递归算法结果:", result1, "循环算法结果:", result2, "验证:", flag);
        return flag;
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q35(), 100);
    }
}
