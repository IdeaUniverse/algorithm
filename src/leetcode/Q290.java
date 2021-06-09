package leetcode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/word-pattern/
 */
public class Q290 {

    private static boolean wordPattern(String pattern, String str){
        String[] words = str.split(" ");
        if(pattern.length() != words.length) return false;
        Map<String, String> map1 = new HashMap<>(pattern.length());
        Map<String, String> map2 = new HashMap<>(pattern.length());
        for (int i = 0; i < pattern.length(); i++) {
            String word = words[i];
            String pat = String.valueOf(pattern.charAt(i));
            map1.put(pat, word);
            map2.put(word, pat);
        }
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String pat = map2.get(word);
            if(!word.equals(map1.get(pat)))
                return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            String pat = String.valueOf(pattern.charAt(i));
            String word = map1.get(pat);
            if(!pat.equals(map2.get(word)))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(wordPattern("abba", "dog cat cat dog"));
        System.out.println(wordPattern("abba", "dog cat cat fish"));
        System.out.println(wordPattern("aaaa", "dog cat cat dog"));
        System.out.println(wordPattern("abba", "dog dog dog dog"));
        System.out.println(wordPattern("aaaa", "b b b b"));
        System.out.println(wordPattern("aaaa", "dog cat cat dog"));
    }
}
