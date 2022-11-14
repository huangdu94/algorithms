package work.huangdu.question_bank.medium;

/**
 * 790. 多米诺和托米诺平铺
 * 有两种形状的瓷砖：一种是 2 x 1 的多米诺形，另一种是形如 "L" 的托米诺形。两种形状都可以旋转。
 * 给定整数 n ，返回可以平铺 2 x n 的面板的方法的数量。返回对 109 + 7 取模 的值。
 * 平铺指的是每个正方形都必须有瓷砖覆盖。两个平铺不同，当且仅当面板上有四个方向上的相邻单元中的两个，使得恰好有一个平铺有一个瓷砖占据两个正方形。
 * 示例 1:
 * 输入: n = 3
 * 输出: 5
 * 解释: 五种不同的方法如上所示。
 * 示例 2:
 * 输入: n = 1
 * 输出: 1
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2022/11/12
 */
public class NumTilings {
    public int numTilings(int n) {
        final int mod = (int)(1e9 + 7);
        // dp[i][j] 表示第i列为j种状态
        // j: 0 全空 1 下空 2 上空 3 全满
        int dp0 = 0, dp1 = 0, dp2 = 0, dp3 = 1;
        for (int i = 1; i <= n; i++) {
            int newDp0 = dp3;
            int newDp1 = (dp0 + dp2) % mod;
            int newDp2 = (dp0 + dp1) % mod;
            int newDp3 = (((dp0 + dp1) % mod + dp2) % mod + dp3) % mod;
            dp0 = newDp0;
            dp1 = newDp1;
            dp2 = newDp2;
            dp3 = newDp3;
        }
        return dp3;
    }

    public static void main(String[] args) {
        NumTilings nt = new NumTilings();
        System.out.println(nt.numTilings(3));
    }
}
