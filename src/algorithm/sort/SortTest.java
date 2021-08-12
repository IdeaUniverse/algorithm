package algorithm.sort;

import common.Utils;

public class SortTest {

    public static void main(String[] args) {
        int[] array = Utils.array(3,8,5,2,6);
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : array) {
            if(i > max) max = i;
            else if(i < min) min = i;
        }

        int [] container = new int[max - min + 1];
        for (int i : array) {
            container[i - min] = i;
        }
        for (int i : container) {
            if(i > 0)
                System.out.println(i);
        }
    }
}
