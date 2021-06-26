package leetcode;

import common.Test;
import common.Utils;
import java.util.Random;

public class Q231 implements Test {

    /**
     * 位运算
     * 如果一个数 n 是 2 的整数次幂，则 n & (n - 1) == 0
     * https://www.bilibili.com/video/BV1xa411A76q?p=25
     */
    public boolean isPowerOfTwo1(int n) {
        return (n & (n - 1)) == 0;
    }

    /**
     * 二分查找
     */
    public boolean isPowerOfTwo2(int n) {
        int l = 0, r = n;
        while (l <= r){
            int mid = (l + r) / 2;
            double powMid = Math.pow(2, mid);
            if(powMid == n)
                return true;
            else if(powMid > n)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return false;
    }

    @Override
    public boolean test() {
        boolean result = new Random().nextBoolean();
        int n = 0;
        if(result){
            int x = Utils.generateRandomInteger(0, 15);
            n = (int) Math.pow(2, x);
        }else {
            n = Utils.generateRandomInteger(2, 1000);
            if (n % 2 == 0){
                n++;    // 用奇数来代替非2的整数次幂
            }
        }

        boolean result1 = isPowerOfTwo1(n);
        boolean result2 = isPowerOfTwo2(n);
        Utils.print(n, (result1 ? "是" : "不是") + "2的整数次幂");
        Utils.print(n, (result2 ? "是" : "不是") + "2的整数次幂");
        return result1 == result && result2 == result;
    }


    public static void main(String[] args) {
        Utils.batchTest(new Q231(), 100);
    }
}
