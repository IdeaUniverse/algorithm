package leetcode;

import common.Test;
import common.Utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 存在重复元素
 * https://leetcode-cn.com/problems/contains-duplicate/
 */
public class Q217 implements Test {

    public boolean containsDuplicate1(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if(!set.add(nums[i]))
                return true;
        }
        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == nums[i + 1])
                return true;
        }
        return false;
    }

    @Override
    public boolean test() {
        int[] nums = Utils.generateIntArray(10, 8);
        Utils.print("原数组", Arrays.toString(nums));
        // containsDuplicate2 会给原数组排序
        boolean result = containsDuplicate1(nums) == containsDuplicate2(nums);
        Utils.print("排序后", Arrays.toString(nums));
        return result;
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q217(), 100);
    }
}
