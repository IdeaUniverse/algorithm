package leetcode;

import common.Test;
import common.Utils;

import java.util.Random;

/**
 * https://leetcode-cn.com/problems/longest-common-prefix/
 */
public class Q14 implements Test {

    private String longestCommonPrefix(String[] arr){
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].length() < minLength){
                minLength = arr[i].length();
            }
        }
        StringBuilder lcp = new StringBuilder(minLength);
        for (int i = 0; i < minLength; i++) {
            if(arr[0].length() - 1 < i) break;
            char c = arr[0].charAt(i);
            boolean flag = true;
            for (int j = 1; j < arr.length; j++) {
                if(arr[j].charAt(i) != c) {
                    flag = false;
                    break;
                }
            }
            if (flag) lcp.append(c);
        }
        return lcp.toString();
    }

    private int lcp(String[] arr){
        return longestCommonPrefix(arr).length();
    }

    @Override
    public boolean test() {
        String cp = Utils.generateRandomString(5);
        int num = 10;
        String[] arr = new String[num];
        System.out.println("字符串数组");
        for (int i = 0; i < num; i++) {
            arr[i] = cp + Utils.generateRandomString(new Random().nextInt(5));
        }
        Utils.print("正确lcp", cp, "正确lcp长度", cp.length());
        String lcp = longestCommonPrefix(arr);
        boolean result = cp.equals(lcp);
        Utils.print("计算所得lcp", lcp, "计算所得lcp长度", lcp.length());
        return result;
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q14(),100);
    }
}
