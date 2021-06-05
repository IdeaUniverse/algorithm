package leetcode;

import common.Test;
import common.Utils;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * https://leetcode-cn.com/problems/add-two-numbers/
 */
public class Q2 implements Test {

    private static List<Integer> add (List<Integer> num1, List<Integer> num2) {
        if(num1.size() < num2.size()){
            List<Integer> t = num1;
            num1 = num2;
            num2 = t;
        }
        for (int i = num2.size(); i < num1.size(); i++) {
            num2.add(0);
        }
        List<Integer> result = new LinkedList<>();
        boolean promote = false;
        for (int i = 0; i < num1.size(); i++) {
            int sum = num1.get(i) + num2.get(i);
            if(promote) sum++;
            promote = sum > 9;
            if(promote){
                result.add(sum - 10);
            }else {
                result.add(sum);
            }
        }
        if (promote) result.add(1);
        return result;
    }

    public static boolean validate(){
        int r1 = 0,r2 = 0;
        do {
            r1 = new Random().nextInt(10);
        }while (r1 <= 0);
        do {
            r2 = new Random().nextInt(10);
        }while (r2 <= 0);
        int[] array1 = Utils.generateIntArray(r1, 10);
        int[] array2 = Utils.generateIntArray(r2, 10);

        int number1 = arrayToInt(array1);
        int number2 = arrayToInt(array2);

        List<Integer> num1 = new LinkedList<>();
        List<Integer> num2 = new LinkedList<>();
        for (int i = 0; i < array1.length; i++) {
            num1.add(array1[i]);
        }
        for (int i = 0; i < array2.length; i++) {
            num2.add(array2[i]);
        }
        List<Integer> resultList = add(num1, num2);
        int[] result = resultList.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(num1 + " + " + num2 + " = " + resultList);
        int realResult = number1 + number2;
        boolean check = realResult == arrayToInt(result);
        System.out.println("real: " + number1 + " + " + number2 + " = " + realResult);
        return check;
    }

    private static int arrayToInt(int[] array){
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result += Math.pow(10, i) * array[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q2(),100);
    }

    @Override
    public boolean test() {
        return validate();
    }
}
