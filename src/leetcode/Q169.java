package leetcode;

import common.Test;
import common.Utils;

import java.util.Random;

/**
 * 多数元素
 * https://leetcode-cn.com/problems/majority-element/
 */
public class Q169 implements Test {

    /**
     * 摩尔投票法
     * @param nums
     * @return
     */
    // [17, 13, 10, 11, 9, 11, 15, 11, 12, 1]
    private int majorityElement(int[] nums) {
        int cnt = 0;
        int current = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == current)
                cnt++;
            else if(cnt == 0)
                current = nums[i];
            else
                cnt--;
        }
        return current;
    }

    /**
     * 分治法
     * @param nums
     * @return
     */
    private int majorityElement2(int[] nums, int start, int end){
        if(start == end)
            return nums[start];
        int half = (start + end) / 2;
        int leftMajority = majorityElement2(nums, start, half);
        int rightMajority = majorityElement2(nums, half + 1, end);
        if(leftMajority == rightMajority)
            return leftMajority;
        int leftMajorityCnt = 0, rightMajorityCnt = 0;
        for (int i = start; i <= end; i++ ) {
            if(nums[i] == leftMajority)
                leftMajorityCnt++;
            if(nums[i] == rightMajority)
                rightMajorityCnt++;
        }
        return leftMajorityCnt > rightMajorityCnt ? leftMajority : rightMajority;
    }

    /**
     * 生成符合条件的数组，众数个数 > 数组长度的一半
     * @param size
     * @param majority
     * @return
     */
    private int[] generateArray(int size, int majority){
        int[] nums = new int[size];
        int majorityCnt = Utils.generateRandomInteger(size / 2 + 1, size);
        int[] indexes = Utils.generateNoRepeatIntArray(majorityCnt, size);
        for (int index : indexes) {
            nums[index] = majority;
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != majority)
                nums[i] = Utils.generateRandomInteger(1,20);
        }
        return nums;
    }

    @Override
    public boolean test() {
        int majority = Utils.generateRandomInteger(1, 20);
        int[] nums = generateArray(10, majority);
        int majority1 = majorityElement(nums);
        int majority2 = majorityElement2(nums,0, nums.length - 1);
        Utils.print("原数组", nums, "众数", majority);
        Utils.print("计算结果", "众数1=", majority1, "众数2=", majority2);
        return majority1 == majority && majority2 == majority;
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q169(), 100);
    }
}
