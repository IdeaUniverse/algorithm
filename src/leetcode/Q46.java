package leetcode;

import common.Test;
import common.Utils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 全排列
 * https://leetcode-cn.com/problems/permutations/
 */
public class Q46 implements Test {

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list;
        List<List<Integer>> resultList;
        if(nums.length < 2) {
            list = Arrays.stream(nums).boxed().collect(Collectors.toList());
            resultList = new ArrayList<>(nums.length);
            resultList.add(list);
            return resultList;
        }
        resultList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int[] arr = newArrayWithoutIndex(nums, i);
            List<List<Integer>> rList = permute(arr);   // 排序一个序列时，先去掉其中一个元素，然后做剩下元素的全排列，然后再把这个元素添加到返回的全排列的末尾
            for (List<Integer> rl : rList) {
                rl.add(nums[i]);
                resultList.add(rl);
            }
        }
        return resultList;
    }

    private int[] newArrayWithoutIndex(int[] a, int index){
        int[] b = new int[a.length - 1];
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if(i != index)
                b[j++] = a[i];
        }
        return b;
    }

    /**
     * leetcode
     */
    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }


    @Override
    public boolean test() {
        int size = Utils.generateRandomInteger(1, 6) ; // 结果是阶乘量级的，所以size不要超过8
        int[] nums = Utils.generateIntRangeArray(0, size);
        List<List<Integer>> list = permute(nums);
        List<List<Integer>> list1 = permute1(nums);
        Utils.print("原数组", nums);
        Utils.print("计算结果", list);
        Utils.print("官方结果", list1);
        boolean result = list.size() == list1.size();
        Set<String> set = new HashSet<>();
        for (List<Integer> l : list) {
            set.add(l.toString());
        }
        for (List<Integer> l1 : list1) {
            if(!set.contains(l1.toString()))
                return false;
        }
        return result;
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q46(), 100);
    }
}
