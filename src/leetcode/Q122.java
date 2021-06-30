package leetcode;

import common.Test;
import common.Utils;

/**
 * 买卖股票的最佳时机 II
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class Q122 implements Test {

    public int maxProfit1(int[] prices){
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            if(diff > 0)
                profit += diff;
        }
        return profit;
    }

    public int maxProfit2(int[] prices) {
        int buyPrice = prices[0];   // 假设第一天买入
        int profit = 0;
        int currentPrice, prePrice = 0;
        for (int i = 0; i < prices.length; i++) {
            currentPrice = prices[i];
            if(i > 0) prePrice = prices[i - 1];
            if(currentPrice < prePrice){ // 今天比昨天降价了
                profit += prePrice - buyPrice; // 以昨天价格卖出
                buyPrice = currentPrice;    // 以今天价格买入
            }else if(i == prices.length - 1){// 或者价格越来越高直到最后一天
                profit += currentPrice - buyPrice; // 以最后天价格卖出
            }
        }
        return profit;
    }

    /**
     * leetcode
     */
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        int dp0 = 0, dp1 = -prices[0];
        for (int i = 1; i < n; ++i) {
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            int newDp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;
    }

    @Override
    public boolean test() {
        int[] arr = Utils.generateNoRepeatIntArray(15, 50);
        Utils.print("股票价格", arr);
        int result1 = maxProfit1(arr);
        int result2 = maxProfit2(arr);
        int result3 = maxProfit3(arr);
        Utils.print("profit", result1, result2, result3);
        return result1 == result2 && result2 == result3;
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q122(), 100);
    }
}
