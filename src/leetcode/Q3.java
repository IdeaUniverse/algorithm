package leetcode;

import common.Test;
import common.Utils;
import java.util.*;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class Q3 implements Test {

    private static String maxSubString(String str){
        if(str == null || "".equals(str)){
            return "";
        }
        StringBuilder result = new StringBuilder(str.length());
        List<Character> charSet = new ArrayList<>(str.length());
        String maxSub = "";
        for (int i = 0; i + charSet.size() < str.length(); i++) {
            if (i > 0) charSet.remove(0);
            for (int j = i + charSet.size(); j < str.length(); j++) {
                if(!charSet.contains(str.charAt(j))){
                    charSet.add(str.charAt(j));
                }else {
                    break;
                }
            }
            result.delete(0, result.length());
            charSet.forEach(result::append);
            String sub = String.join(",", result);
            if(sub.length() > maxSub.length()) maxSub = sub;
        }
        return maxSub;
    }

    private static int maxSubStringLength(String str){
        String result = maxSubString(str);
        return result.length();
    }

    /**
     * 验证是否包含于原字符串，且无重复，暂未验证是否是最长
     * @param original
     * @param sub
     * @return
     */
    private static boolean validate(String original, String sub){
        Set<Character> set = new HashSet<>(sub.length());
        for (int i = 0; i < sub.length(); i++) {
            set.add(sub.charAt(i));
        }
        return original.contains(sub) && sub.length() == set.size();
    }

    @Override
    public boolean test() {
        String str = Utils.generateRandomString(10);
//        str = "Yo0995Mu5Z";
        String result = maxSubString(str);
        System.out.println("原字符串：" + str + " 最长子串：" + result + " 子串长度：" + result.length());
        return validate(str, result);
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q3(),100);
    }
}
