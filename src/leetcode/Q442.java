package leetcode;

import common.Test;
import common.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数组中重复的数据
 * https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/
 */
public class Q442 implements Test {

    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>(n);
        for (int i : nums) {
            nums[(i - 1) % n] += n;
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if(num > n * 2) //这里找到所有重复的； 题目要求找到同一个数出现两次的，则 num > n * 2 && num <= n * 3
                list.add(i + 1);
        }
        return list;
    }

    @Override
    public boolean test() {
        int[] arr = Utils.generateIntArray(10, 10);
        for (int i = 0; i < arr.length; i++) arr[i] += 1;
        Utils.print("原数组", arr);
        int[] arr1 = new int[arr.length];
        System.arraycopy(arr,0, arr1, 0, arr.length);
        List<Integer> list1 = findDuplicates(arr);
        Map<Integer, Integer> map = new HashMap<>(arr.length);
        for (int i : arr1) map.put(i, map.getOrDefault(i, 0) + 1);
        int[] realResult = map.keySet().stream().filter(i -> map.get(i) > 1).mapToInt(i -> i).toArray();
        Utils.print("结果", list1);
        Utils.print("真实结果", realResult);
        return Utils.isSameIntArray(list1.stream().mapToInt(i -> i).toArray(), realResult);
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q442(), 100);
    }
}
