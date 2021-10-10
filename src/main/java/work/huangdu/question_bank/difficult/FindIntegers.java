package work.huangdu.question_bank.difficult;

/**
 * 600. 不含连续1的非负整数
 * 给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含 连续的1 的个数。
 * 示例 1:
 * 输入: 5
 * 输出: 5
 * 解释:
 * 下面是带有相应二进制表示的非负整数<= 5：
 * 0 : 0
 * 1 : 1
 * 2 : 10
 * 3 : 11
 * 4 : 100
 * 5 : 101
 * 其中，只有整数3违反规则（有两个连续的1），其他5个满足规则。
 * 说明: 1 <= n <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/9/11
 */
public class FindIntegers {
    // TODO  复制粘贴需要复习
    public int findIntegers(int n) {
        // 预处理第 i 层满二叉树的路径数量
        int[] dp = new int[31];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < 31; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        // pre 记录上一层的根节点值，res 记录最终路径数
        int pre = 0, res = 0;
        for (int i = 29; i >= 0; --i) {
            int val = 1 << i;
            // if 语句判断 当前子树是否有右子树
            if ((n & val) != 0) {
                // 有右子树
                n -= val;
                res += dp[i + 1]; // 先将左子树（满二叉树）的路径加到结果中

                // 处理右子树
                if (pre == 1) {
                    // 上一层为 1，之后要处理的右子树根节点肯定也为 1
                    // 此时连续两个 1，不满足题意，直接退出
                    break;
                }
                // 标记当前根节点为 1
                pre = 1;
            } else {
                // 无右子树，此时不能保证左子树是否为满二叉树，所以要在下一层再继续判断
                pre = 0;
            }
            if (i == 0) {
                ++res;
            }
        }
        return res;
    }
}
