package leetcode;

import common.Test;
import common.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 找到所有数组中消失的数字
 * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
 */
public class Q448 implements Test {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>(n);
        for (int x : nums)
            nums[(x - 1) % n] += n;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] <= n)
                list.add(i + 1);
        }
        return list;
    }

    @Override
    public boolean test() {
        int[] arr = Utils.generateIntArray(10, 10);
        for (int i = 0; i < arr.length; i++) {
            arr[i]++;
        }
        int[] arr1 = new int[arr.length];
        System.arraycopy(arr, 0, arr1,0, arr.length);
        Arrays.sort(arr1);

        Utils.print("原数组", arr);
        Utils.print("排序数组", arr1);
        List<Integer> list = findDisappearedNumbers(arr);
        Utils.print("处理后", arr);

        List<Integer> list1 = new ArrayList<>(arr.length);

        for (int i = 1; i <= arr1.length; i++) {
            int finalI = i;
            if(Arrays.stream(arr1).noneMatch(item -> item == finalI))
                list1.add(i);
        }
        Utils.print("lost", list1, list);
        return Utils.isSameArray(
                list.stream().mapToInt(i -> i).toArray(),
                list1.stream().mapToInt(i -> i).toArray());
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q448(), 100);
    }
}
