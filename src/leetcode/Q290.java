package leetcode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/word-pattern/
 */
public class Q290 {

    private static boolean check(String pattern, String str){
        String[] words = str.split(" ");
        if(pattern.length() != words.length) return false;

        Map<Character, Integer> map = new HashMap<>(pattern.length());

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if(!map.containsKey(c))
                map.put(c, map.size());
        }

        Set<String> set = new HashSet<>(words.length);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if(!word.equals(words[map.get(pattern.charAt(i))])) return false;
            set.add(word);
        }
        return set.size() == map.keySet().size();
    }

    public static void main(String[] args) {
        System.out.println(check("abba", "dog cat cat dog"));
        System.out.println(check("abba", "dog cat cat fish"));
        System.out.println(check("aaaa", "dog cat cat dog"));
        System.out.println(check("abba", "dog dog dog dog"));
        System.out.println(check("aaaa", "b b b b"));
    }
}
