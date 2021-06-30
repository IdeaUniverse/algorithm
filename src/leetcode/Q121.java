package leetcode;

import common.Test;
import common.Utils;

import java.util.Arrays;

/**
 * 买卖股票的最佳时机
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 */
public class Q121 implements Test {

    /**
     * 理解：
     * 每前进一天，有两种选择，如果今天价格更低，则买入价格更新为今天；
     * 否则，如果今天卖出利润更大，则在今天卖出
     */
    public int maxProfit(int[] prices) {
        int buyPrice = Integer.MAX_VALUE;
        int profit = 0;
        for (int price : prices){
            if(price < buyPrice){
                buyPrice = price;
            }else if(price - buyPrice > profit){
                profit = price - buyPrice;
            }
        }
        return profit;
    }

    /**
     * leetcode 暴力法
     */
    public int maxProfit1(int[] prices) {
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit) {
                    maxprofit = profit;
                }
            }
        }
        return maxprofit;
    }

    @Override
    public boolean test() {
        int[] arr = Utils.generateIntArray(20, 100);
        Utils.print("股票价格", Arrays.toString(arr));
        return maxProfit(arr) == maxProfit1(arr);
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q121(), 100);
    }
}
