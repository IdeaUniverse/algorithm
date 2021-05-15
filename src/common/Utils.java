package common;

import java.util.Random;

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

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static void batchTest(int total, Test test){
        int success = 0;
        for (int i = 0; i < total; i++) {
            success += test.test() ? 1 : 0;
        }
        System.out.println("\n总计：" + total + ", 正确：" + success);
    }

    public static void main(String[] args) {
        char c = '8';
        System.out.println((int) c);
        System.out.println(Character.getNumericValue('A'));
        System.out.println(Character.getNumericValue('^'));
    }
}
