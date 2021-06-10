package leetcode;

import common.Test;
import common.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * https://leetcode-cn.com/problems/two-sum
 */
public class Q1 implements Test {

    public static int[] twoSum(int[] array, int sum){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            map.put(array[i], i);
        }
        for (int i = 0; i < array.length; i++) {
            int diff = sum - array[i];
            if(map.containsKey(diff)){
                return new int[]{map.get(diff), i};
            }
        }
        return new int[]{};
    }

    @Override
    public boolean test() {
        int[] array = Utils.generateIntArray(10);
        int idx1 = new Random().nextInt(array.length);
        int idx2;
        do {
            idx2 = new Random().nextInt(array.length);
        } while (idx2 == idx1);
        int sum = array[idx1] + array[idx2];
        System.out.println("original array: " + Arrays.toString(array));
        System.out.println("sum = " + sum + ", idx1 = " + idx1 + " idx2 = " + idx2);
        int[] result = twoSum(array, sum);
        System.out.println("result = " + Arrays.toString(result));
        int a = array[result[0]];
        int b = array[result[1]];
        System.out.println(a + " + " + b + " = " + (a + b));
        return a + b == sum;
    }


    public static void main(String[] args) {
        Utils.batchTest(new Q1(), 100);
    }
}
