package leetcode;

import common.Test;
import common.Utils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/remove-element/
 */
public class Q27 implements Test {

    private int removeElement(int[] nums, int val){
        int i = 0, j = nums.length - 1;
        while (i <= j){
            if(nums[i] == val){
                nums[i] = nums[j--];
                continue;
            }
            i++;
        }
        return i;
    }

    private int[] generateArray(int size, int val, int num){
        int[] nums = new int[size];
        for (int i = 0; i < nums.length; i++) {
            if(i < size - num) nums[i] = i;
            else {
                nums[i] = val;
            }
        }
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }


    public static void main(String[] args) {
        Utils.batchTest(new Q27(),100);
    }

    @Override
    public boolean test() {
        int size = 10;
        int val = 18; // val 要大于 size
        int num = 4;
        int[] array = generateArray(size, val, num);
        int[] array1 = Utils.generateIntRangeArray(0, size - num);
        Utils.print("原数组", Arrays.toString(array), "重复元素", num + "个" + val, "验证数组", Arrays.toString(array1));
        int newLength = removeElement(array, val);
        Utils.print("删除重复元素后的数组", Arrays.toString(array), "新长度", newLength);
        int[] array2 = Arrays.stream(Arrays.copyOf(array, newLength)).sorted().toArray();
        Utils.print("截取并排序后的数组", Arrays.toString(array2));
        boolean result = Utils.isSameIntArray(array1, array2);
        Utils.print("验证结果", result);
        return result;
    }
}
