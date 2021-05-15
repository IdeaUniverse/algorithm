package leetcode;

import common.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Q20 implements Test {

    private Map<Character, Character> map;
    {
        map = new HashMap<>(6);
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
    }

    private boolean check(String str){
        if(str == null || str.length() % 2 == 1) return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '[' || c == '{')
                stack.push(c);
            else if(stack.pop() != map.get(c)){
                return false;
            }
        }
        return stack.isEmpty();
    }

    @Override
    public boolean test() {
        System.out.println(check("()"));
        System.out.println(check("()[]{}"));
        System.out.println(check( "(]"));
        System.out.println(check( "([)]"));
        System.out.println(check("{[]}"));
        System.out.println(check("{[[]}"));
        System.out.println(check(""));
        System.out.println(check(null));
        return false;
    }

    public static void main(String[] args) {
        new Q20().test();
    }
}
