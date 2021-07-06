package algorithm.sort;

import common.Test;
import common.Utils;

import java.util.Arrays;
import java.util.Random;

public class QuickSort implements Test {

    public void sort(int[] array){
        sort(array, false);
    }

    public int[] sort(int[] array, boolean newArray){
        if(newArray){
            int[] arr = Arrays.copyOf(array, array.length);
            partition(arr, 0, arr.length - 1);
            return arr;
        }
        partition(array, 0, array.length - 1);
        return array;
    }

    private void partition(int[] arr, int left, int right){
        if(left >= right) return;
        int p = new Random().nextInt(right - left) + left;   // 空闲位置， l ~ r 之间的随机数
        swap(arr, left, p);
        int pivot = left, l = left, r = right;
        while (l < r){
            while (l < r && arr[r] >= arr[pivot]) r--;
            if(l < r){
                swap(arr, r, pivot);
                pivot = r;
            }

            while (l < r && arr[l] <= arr[pivot]) l++;
            if(l < r){
                swap(arr, l, pivot);
                pivot = l;
            }
        }
        partition(arr, left, pivot - 1);
        partition(arr, pivot + 1, right);
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Override
    public boolean test() {
        int[] array = Utils.generateIntArray(10);
        int[] newArray = new int[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        Utils.print("原数组", Arrays.toString(array), "数组拷贝" , Arrays.toString(newArray));
        sort(array);
        Arrays.sort(newArray);
        Utils.print("排序后的数组", Arrays.toString(array));
        Utils.print("java 内置排序后的数组", Arrays.toString(newArray));
        return Utils.isSameArray(array, newArray);
    }

    public static void main(String[] args) {
        Utils.batchTest(new QuickSort(), 100);
    }
}
