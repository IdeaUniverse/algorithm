package leetcode;

import common.Test;
import common.Utils;

import java.util.Arrays;

/**
 * 最大连续 1 的个数
 * https://leetcode-cn.com/problems/max-consecutive-ones/
 */
public class Q485 implements Test {

    /**
     * 循环算法
     * @param arr
     * @return
     */
    private int findMaxConsecutiveOnesLoop(int[] arr){
        if(arr == null) return 0;
        int maxRepeat = 0, currentRepeat = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != 1){
                currentRepeat = 0;
            }else {
                currentRepeat++;
                if(currentRepeat > maxRepeat)
                        maxRepeat = currentRepeat;
            }
        }
        return maxRepeat;
    }

    /**
     * 动态规划
     */
    private static int findMaxConsecutiveOnesDynamicProgramming(int[] arr){
        if (arr == null) return 0;
        int maxRepeat = 0;
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] == 1) {
                arr[i] = arr[i - 1] + 1;
                maxRepeat = Math.max(maxRepeat, arr[i]);
            } else
                arr[i] = 0;
        }
        return maxRepeat;
    }

    @Override
    public boolean test() {
        int[] arr = Utils.generateIntArray(10, 2);
//        arr = new int[]{1,1,0,1,1,1};
        Utils.print("原数组", Arrays.toString(arr));
        int result1 = findMaxConsecutiveOnesLoop(arr);
        int result2 = findMaxConsecutiveOnesDynamicProgramming(arr);
        boolean result = result1 == result2;
        Utils.print("循环算法结果: ", result1, "递归算法结果: ", result2);
        return result;
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q485(), 100);
    }
}
