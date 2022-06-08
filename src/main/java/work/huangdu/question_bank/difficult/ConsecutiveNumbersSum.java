package work.huangdu.question_bank.difficult;

/**
 * 829. 连续整数求和
 * 给定一个正整数 n，返回 连续正整数满足所有数字之和为 n 的组数 。
 * 示例 1:
 * 输入: n = 5
 * 输出: 2
 * 解释: 5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。
 * 示例 2:
 * 输入: n = 9
 * 输出: 3
 * 解释: 9 = 4 + 5 = 2 + 3 + 4
 * 示例 3:
 * 输入: n = 15
 * 输出: 4
 * 解释: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 * 提示:
 * 1 <= n <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/6/6
 */
public class ConsecutiveNumbersSum {
    /**
     * a a+1 a+2 ... a+k-1
     * (2a+k-1)*k/2 = n              2*n-k*k+k = 2ak
     * 2a = 2*n/k-(k-1)
     * a=n/k-(k-1)/2
     * n/k-(k-1)/2>=1
     * n-(k-1)*k/2>=k
     * 2n>=2k+(k-1)*k
     * 2n >= k*k+k
     */
    public int consecutiveNumbersSum(int n) {
        int ans = 0;
        for (int k = 1; k * k + k <= 2 * n; k++) {
            if ((2 * n - k * k - k) % (2 * k) == 0 && (2 * n - k * k - k) / (2 * k) + k - 1 <= n) {
                ans++;
            }
        }
        return ans;
    }
}
