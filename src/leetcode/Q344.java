package leetcode;

import common.Test;
import common.Utils;

import java.util.Arrays;

/**
 * 反转字符串
 * https://leetcode-cn.com/problems/reverse-string/
 */
public class Q344 implements Test {

    public void reverseString(char[] s) {
        int i = 0, j = s.length - 1;
        while (i < j){
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }

    @Override
    public boolean test() {
        String s = Utils.generateRandomString(10);
        char[] chars = s.toCharArray();
        Utils.print("原字符串", Arrays.toString(chars));
        reverseString(chars);
        Utils.print("反转字符串", Arrays.toString(chars));
        String answer = new StringBuilder(s).reverse().toString();
        return new String(chars).equals(answer);
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q344(), 100);
    }
}
