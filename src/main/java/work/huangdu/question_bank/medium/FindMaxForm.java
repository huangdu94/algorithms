package work.huangdu.question_bank.medium;

/**
 * 474. 一和零
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 * 示例 1：
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 * 示例 2：
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 * 提示：
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/6/7
 */
public class FindMaxForm {
    // TODO 需要复习
    public int findMaxForm2(String[] strs, int m, int n) {
        int size = strs.length;
        int[][][] dp = new int[size + 1][m + 1][n + 1];
        for (int i = 1; i <= size; i++) {
            String str = strs[i - 1];
            int zeroCount = getZeroCount(str), oneCount = str.length() - zeroCount;
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j >= zeroCount && k >= oneCount) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeroCount][k - oneCount] + 1);
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }
        return dp[size][m][n];
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int size = strs.length;
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int zeroCount = getZeroCount(str), oneCount = str.length() - zeroCount;
            for (int j = m; j >= zeroCount; j--) {
                for (int k = n; k >= oneCount; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zeroCount][k - oneCount] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private int getZeroCount(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '0') {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        FindMaxForm fmf = new FindMaxForm();
        System.out.println(fmf.findMaxForm(strs, m, n));
    }
}
