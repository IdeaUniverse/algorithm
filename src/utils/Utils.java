package utils;

import java.util.Arrays;
import java.util.Random;

public class Utils {

    public static int[] generateIntArray(int size){
        return generateIntArray(size, 100);
    }

    public static int[] generateIntArray(int size, int bound) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new Random().nextInt(bound);
        }
        return arr;
    }

    public static void statistics(int total, int success){
        System.out.println("总计：" + total + ", 正确：" + success);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(generateIntArray(10)));
    }
}
