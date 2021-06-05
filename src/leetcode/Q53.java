package leetcode;

import common.Test;
import common.Utils;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/maximum-subarray/
 */
public class Q53 implements Test {

    /**
     * 动态规划
     * @param nums
     * @return
     */
    public int maxSubArray1(int[] nums) {
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.max(nums[i - 1] + nums[i], nums[i]);
            sum = Math.max(sum, nums[i]);
        }
        return sum;
    }

    /**
     * 暴力法
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int sum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int s = 0;
            for (int j = i; j < nums.length; j++) {
                s += nums[j];
                sum = Math.max(sum, s);
            }
        }
        return sum;
    }

    /**
     * 随机生成正负数数组
     * @param size
     * @return
     */
    private int[] generateArray(int size){
        int n = 50;
        int[] arr = Utils.generateIntArray(size, 2 * n);
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= n;
        }
        return arr;
    }

    @Override
    public boolean test() {
        int[] nums = generateArray(3);
        //nums = new int[]{15, -24, 16};
        Utils.print("原数组", Arrays.toString(nums));
        int result2 = maxSubArray2(nums);
        int result1 = maxSubArray1(nums);   // 动态归划法会修改原数组，所以放在最后
        boolean result = result1 == result2;
        Utils.print("result2:", result2,  "result1:", result1);
        return result;
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q53(), 1);
    }
}
