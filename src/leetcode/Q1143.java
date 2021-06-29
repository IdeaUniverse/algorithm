package leetcode;

import common.Test;
import common.Utils;

/**
 * 最长公共子序列
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 */
public class Q1143 implements Test {

    private final int UP_LEFT = 1;
    private final int UP = 2;
    private final int LEFT = 3;

    public int longestCommonSubsequence(String text1, String text2) {
        return lcs(text1,text2).length();
    }

    private Record[][] getRecords(String x, String y){
        if(x == null || x.isEmpty() || y == null || y.isEmpty()){
            return null;
        }
        Record[][] records = new Record[x.length() + 1][y.length() + 1];
        for (int i = 0; i < records.length; i++) {
            for (int j = 0; j < records[i].length; j++) {
                records[i][j] = new Record();
            }
        }
        for (int i = 1; i < x.length() + 1; i++) {
            for (int j = 1; j < y.length() + 1; j++) {
                char xc = x.charAt(i - 1); char yc = y.charAt(j - 1);
                if (xc == yc) {
                    records[i][j].value = records[i - 1][j - 1].value + 1;
                    records[i][j].direction = UP_LEFT;
                } else if (records[i - 1][j].value >= records[i][j - 1].value) {
                    records[i][j].value = records[i - 1][j].value;
                    records[i][j].direction = UP;
                } else {
                    records[i][j].value = records[i][j - 1].value;
                    records[i][j].direction = LEFT;
                }
            }
        }
        return records;
    }

    public String lcs(String x, String y){
        Record[][] records = getRecords(x, y);
        StringBuilder sb = new StringBuilder(Math.max(x.length(), y.length()));
        getLCS(x, y, records, x.length(), y.length(), sb);
        return sb.toString();
    }

    private void getLCS(String x, String y, Record[][] records, int i, int j, StringBuilder sb){
        if(i == 0 || j == 0) return;
        Record end = records[i][j];
        if(end.direction == UP_LEFT){
            getLCS(x, y, records, i - 1, j - 1, sb);
            sb.append(x.charAt(i - 1));
        } else if(end.direction == UP)
            getLCS(x, y, records, i - 1, j, sb);
        else
            getLCS(x, y, records, i, j - 1, sb);
    }

    @Override
    public boolean test() {
        String x = Utils.generateRandomString(15);
        String y = Utils.generateRandomString(15);
        String lcs = lcs(x, y);
        Utils.print("x字符串", x);
        Utils.print("y字符串", y);
        Utils.print("lcs", lcs);
        int result1 = lcs.length();
        Record[][] records = getRecords(x, y);
        int result2 = records[x.length()][y.length()].value;
        return result1 == result2;
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q1143(), 100);
    }
}

class Record {
    public int value;   // lcs长度
    public int direction = -1;
}