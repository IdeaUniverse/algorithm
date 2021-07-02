package common;

import algorithm.dataStructure.tree.BinaryTree;

import java.util.*;
import java.util.function.Function;

public class Utils {

    public static int[] generateIntArray(int size) {
        return generateIntArray(size, 100);
    }

    public static int[] generateIntArray(int size, int bound) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new Random().nextInt(bound);
        }
        return arr;
    }

    public static int[] generateNoRepeatIntArray(int size, int bound){
        if(size > bound)
            throw new IllegalArgumentException("Size must be greater than bound");
        Set<Integer> set = new HashSet<>(size);
        while (set.size() < size){
            set.add(new Random().nextInt(bound));
        }
        return set.stream().mapToInt(i -> i).toArray();
    }

    public static int[] generateIntRangeArray(int start, int end){
        int length = end - start;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = start + i;
        }
        return arr;
    }

    public static int[] array(int ...items){
        return items;
    }

    public static String generateRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static int generateRandomInteger(int start, int end){
        return start + new Random().nextInt(end - start);
    }

    public static BinaryTree<Integer> generateCompleteBinaryTree(Integer[] arr){
        BinaryTree<Integer> bt = new BinaryTree<>();
        for (int v : arr)
            bt.insert(v);
        return bt;
    }

    public static void batchTest(Test test, int total){
        int success = 0;
        for (int i = 1; i <= total; i++) {
            System.out.println("\n(" + i + ")");
            boolean result = test.test();
            print("【验证】", result);
            success += result ? 1 : 0;
        }
        Utils.print("\n--------------------\n总计:", total, ", 正确:", success);
    }

    public static boolean isSameArray(int[] arr, int[] arr1){
        if(arr == null || arr1 == null || arr.length != arr1.length)
            return false;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != arr1[i])
                return false;
        }
        return true;
    }

    public static void print(Object ...args){
        StringBuilder result = new StringBuilder();
        Function<Object, String> toString = o -> {
            if(o instanceof int[])
                return Arrays.toString((int[]) o);
            if(o instanceof Integer[])
                return Arrays.toString((Integer[]) o);
            if(o instanceof String[])
                return Arrays.toString((String[]) o);
            return o.toString();
        };

        for (Object arg : args) {
            result.append(toString.apply(arg));
            result.append(" ");
        }
        System.out.println(result);
    }
}
