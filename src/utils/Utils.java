package utils;

import java.util.Arrays;
import java.util.Random;

public class Utils {

    public static int[] generateArray(int size){
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new Random().nextInt(100);
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(generateArray(10)));
    }
}
