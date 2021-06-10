package common;

import java.util.*;

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
        Set<Integer> set = new HashSet<>(size);
        while (set.size() < size){
            set.add(new Random().nextInt(bound));
        }
        return set.stream().mapToInt(i -> i).toArray();
    }

    public static int[] generateIntRangeArray(int start, int end){
        int[] arr = new int[end - start];
        for (int i = start; i < end; i++) {
            arr[i] = i;
        }
        return arr;
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

    public static void batchTest(Test test, int total){
        int success = 0;
        for (int i = 0; i < total; i++) {
            System.out.println("\n(" + (i + 1) + ")");
            boolean result = test.test();
            print("【验证】", result);
            success += result ? 1 : 0;
        }
        System.out.println("\n-------------------\n总计: " + total + ", 正确: " + success);
    }

    public static boolean isSameIntArray(int[] arr, int[] arr1){
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
        for (Object arg : args) {
            result.append(arg);
            result.append(" ");
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        char c = '8';
        System.out.println((int) c);
        System.out.println(Character.getNumericValue('A'));
        System.out.println(Character.getNumericValue('^'));
    }
}
