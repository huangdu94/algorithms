package work.huangdu.specific;

import java.util.Arrays;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class Solution {
    private static final int BASE = 1000000007;
    private int min_sum;
    private int max_sum;
    private int[][] memo;

    public int count(String num1, String num2, int min_sum, int max_sum) {
        this.min_sum = min_sum;
        this.max_sum = max_sum;
        this.memo = new int[Math.max(num1.length(), num2.length())][max_sum + 1];
        init(memo);
        int right = f(num2.toCharArray(), 0, 0, true);
        init(memo);
        int left = f(num1.toCharArray(), 0, 0, true);
        return (right - left + (check(num1) ? 1 : 0) + BASE) % BASE;
    }

    public int f(char[] nums, int digitSum, int i, boolean isLimit) {
        if (digitSum > max_sum) {return 0;}
        if (i == nums.length) {return digitSum >= min_sum ? 1 : 0;}
        if (!isLimit && memo[i][digitSum] != -1) {return memo[i][digitSum];}
        int ans = 0;
        int up = isLimit ? nums[i] - '0' : 9;
        for (int num = 0; num <= up; num++) {
            ans = (ans + f(nums, digitSum + num, i + 1, isLimit && num == up)) % BASE;
        }
        if (!isLimit) {
            memo[i][digitSum] = ans;
        }
        return ans;
    }

    private void init(int[][] memo) {
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
    }

    private boolean check(String num) {
        int sum = 0;
        for (int i = 0, n = num.length(); i < n; i++) {sum += num.charAt(i) - '0';}
        return sum >= min_sum && sum <= max_sum;
    }
}
