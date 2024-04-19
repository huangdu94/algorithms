package work.huangdu.question_bank.difficult;

import java.util.Arrays;

/**
 * 132. 分割回文串 II
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 * 返回符合要求的 最少分割次数 。
 * 示例 1：
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * 示例 2：
 * 输入：s = "a"
 * 输出：0
 * 示例 3：
 * 输入：s = "ab"
 * 输出：1
 * 提示：
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 *
 * @author huangdu
 * @version 2021/3/8
 */
public class MinCut {

    public int minCut(String s) {
        int n = s.length();
        if (n == 1) {return 0;}
        boolean[][] isPalindrome = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            isPalindrome[i][i] = true;
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                isPalindrome[i][j] = s.charAt(i) == s.charAt(j) && (i + 1 > j - 1 || isPalindrome[i + 1][j - 1]);
            }
        }
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int tail = 0; tail < n; tail++) {
            if (isPalindrome[0][tail]) {
                dp[tail] = 0;
            } else {
                for (int head = 0; head < tail; head++) {
                    if (isPalindrome[head + 1][tail]) {
                        dp[tail] = Math.min(dp[tail], dp[head] + 1);
                    }
                }
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        String s
            =
            "apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpbgdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftcucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggbfeebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtlaliyudpacctzizcftjlunlgnfwcqqxcqikocqffsjyurzwysfjmswvhbrmshjuzsgpwyubtfbnwajuvrfhlccvfwhxfqthkcwhatktymgxostjlztwdxritygbrbibdgkezvzajizxasjnrcjwzdfvdnwwqeyumkamhzoqhnqjfzwzbixclcxqrtniznemxeahfozp";
        MinCut mc = new MinCut();
        System.out.println(mc.minCut(s));
    }
}

// 回溯超时
class Solution {
    private int n;
    private char[] chars;
    private int min;
    private int[][] memo;

    public int minCut(String s) {
        n = s.length();
        chars = s.toCharArray();
        min = Integer.MAX_VALUE;
        memo = new int[n][n];
        recall(0, 0);
        return min;
    }

    private void recall(int head, int cut) {
        if (head == n) {
            min = cut - 1;
            return;
        }
        if (min <= cut) { return; }
        // 先大后小
        for (int tail = n - 1; tail > head; tail--) {
            if (isPalindrome(chars, head, tail)) {
                recall(tail + 1, cut + 1);
            }
        }
        recall(head + 1, cut + 1);
    }

    private boolean isPalindrome(char[] chars, int head, int tail) {
        if (memo[head][tail] != 0) { return memo[head][tail] == 1; }
        int head0 = head, tail0 = tail;
        while (head < tail) {
            if (chars[head++] != chars[tail--]) {
                memo[head0][tail0] = -1;
                return false;
            }
        }
        memo[head0][tail0] = 1;
        return true;
    }
}
