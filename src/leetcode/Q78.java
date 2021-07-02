package leetcode;

import common.Test;
import common.Utils;

import java.util.*;

/**
 * 子集
 * https://leetcode-cn.com/problems/subsets/
 */
public class Q78 implements Test {

    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        if(nums.length == 1){
            resultList = new ArrayList<>();
            List<Integer> l0 = new ArrayList<>();
            List<Integer> l1 = new ArrayList<>();
            l1.add(nums[0]);
            resultList.add(l0);
            resultList.add(l1);
            return resultList;
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int[] arr = newArrayLessThanIndex(nums, i);
            List<List<Integer>> rList = subsets(arr);
            for (List<Integer> list : rList) {
                list.add(num);
                resultList.add(list);
            }
        }
        resultList.add(new ArrayList<>()); // 补一个空集
        return resultList;
    }

    private int[] newArrayLessThanIndex(int[] a, int index){
        int[] b = new int[index];
        int j = 0;
        for (int i = 0; i < index; i++) {
                b[j++] = a[i];
        }
        return b;
    }

    /**
     * leetcode
     */
    public List<List<Integer>> subsets1(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }

    @Override
    public boolean test() {
        int size = Utils.generateRandomInteger(1, 6) ; // 结果是指数量级的，所以size不要超过8
        int[] arr = Utils.generateIntRangeArray(1, size);
        List<List<Integer>> list = subsets(arr);
        List<List<Integer>> list1 = subsets1(arr);
        Utils.print("原数组", arr);
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
        // 重置
        t.clear();
        ans.clear();
        return result;
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q78(), 100);
    }
}
