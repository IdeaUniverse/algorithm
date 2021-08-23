package leetcode;

import common.Test;
import common.Utils;

/**
 * 268. 丢失的数字
 * https://leetcode-cn.com/problems/missing-number/
 */
public class Q268 implements Test {

    private int missingNumber(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int total = (nums.length + 1) * nums.length / 2;
        return total - sum;
    }

    @Override
    public boolean test() {
        int size = Utils.generateRandomInteger(1, 20);
        int r = Utils.generateRandomInteger(0, size + 1);
        int[] nums = new int[size];
        int i = 0;
        while (i < size){
            if(i < r){
                nums[i] = i;
            }else if(i >= r){
                nums[i] = i + 1;
            }
            i++;
        }
        Utils.print("原数组", nums, "缺失", r);
        int result = missingNumber(nums);
        Utils.print("计算缺失数", result);
        return result == r;
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q268(), 1000);
    }
}
