package leetcode;

import common.Test;
import common.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * https://leetcode-cn.com/problems/combinations/solution/zu-he-by-leetcode-solution/
 */
public class Q77 implements Test {

    private List<List<Integer>> combine1(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backTracking(n, k, 1, result, new ArrayList<>());
        return result;
    }

    /**
     * https://www.bilibili.com/video/BV1xa411A76q?p=14a
     * @param start 每次递归从哪里开始
     * @param result 最终结果 result = [list,list,list...]
     * @param list 每一个组合
     *                      *(递归起点)
     *                /     /       \       \
     *                1     2       3       4
     *              / | \   /\      \
     *             2  3  4  3 4     4
     *  假设 n = 4, k = 2
     *  初始 result 和 list 都为空, 记为递归起点
     *  start = 1， 把 1 加入 list, 然后开启第 ① 轮递归，start = 2
     *  把 2 加入 list， 然后又开启第 ② 轮递归，start = 3        ①③④⑤
     *  此时 list = [1,2], list.size() = k， 把 list(copy) 加入 result，然后终止本轮递归，回到 ② 末尾
     *  此时 list 删掉最后一个元素 2，变为 list = [1]，第 ② 轮递归执行完毕，回到 ① 末尾
     *  此时 list 删掉最后一个元素 1，变为 list = []， 第 ① 轮递归执行完毕，回到递归起点
     *  此时 list = [], start = 2
     *  ...
     */
    private void backTracking(int n, int k, int start,
                               List<List<Integer>> result, List<Integer> list){
        if(list.size() == k){   //
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            backTracking(n, k, i + 1, result, list);
            if(!list.isEmpty()){
                list.remove(list.size() - 1);
            }
        }
    }

    /*以下为官方解法*/
    List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    private List<List<Integer>> combine2(int n, int k) {
        dfs(1, n, k);
        return ans;
    }
    private void dfs(int cur, int n, int k) {
        // 剪枝：temp 长度加上区间 [cur, n] 的长度小于 k，不可能构造出长度为 k 的 temp
        if (temp.size() + (n - cur + 1) < k) {
            return;
        }
        // 记录合法的答案
        if (temp.size() == k) {
            ans.add(new ArrayList<Integer>(temp));
            return;
        }
        // 考虑选择当前位置
        temp.add(cur);
        dfs(cur + 1, n, k);
        temp.remove(temp.size() - 1);
        // 考虑不选择当前位置
        dfs(cur + 1, n, k);
    }
    /*以上为官方解法*/

    @Override
    public boolean test() {
        int n = Utils.generateRandomInteger(1, 10);
        int k = Utils.generateRandomInteger(1, n + 1); //n > 1 ? Utils.generateRandomInteger(1, n) : 1;
        List<List<Integer>> result1 = combine1(n,k);
        List<List<Integer>> result2 = new Q77().combine2(n,k);  // 官方方法是有状态的
        Utils.print("n =", n, "k =",k);
        Utils.print("官方答案", result2);
        Utils.print("我的答案", result1);
        if(result1.size() != result2.size()){
            return false;
        }
        for (int i = 0; i < result1.size(); i++) {
            List<Integer> list1 = result1.get(i);
            List<Integer> list2 = result2.get(i);
            if(!Utils.isSameArray(
                    list1.stream().mapToInt(Integer::intValue).toArray(),
                    list2.stream().mapToInt(Integer::intValue).toArray())
            ){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
         Utils.batchTest(new Q77(), 100);
    }
}
