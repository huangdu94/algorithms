package work.huangdu.exploration.start_from_scratch.graph_search.dfs;

/**
 * 397. 整数替换
 * 给定一个正整数 n ，你可以做如下操作：
 * 如果 n 是偶数，则用 n / 2替换 n 。
 * 如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。
 * n 变为 1 所需的最小替换次数是多少？
 * 示例 1：
 * 输入：n = 8
 * 输出：3
 * 解释：8 -> 4 -> 2 -> 1
 * 示例 2：
 * 输入：n = 7
 * 输出：4
 * 解释：7 -> 8 -> 4 -> 2 -> 1
 * 或 7 -> 6 -> 3 -> 2 -> 1
 * 示例 3：
 * 输入：n = 4
 * 输出：2
 * 提示：
 * 1 <= n <= 2^31 - 1
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/3/23
 */
public class IntegerReplacement {

    private int min = Integer.MAX_VALUE;

    public int integerReplacement(int n) {
        dfs(n, 0);
        return min;
    }

    private void dfs2(long num, int count) {
        if (num == 1) {
            if (count < min) {
                min = count;
            }
            return;
        }
        // 剪枝1
        if (count >= min) {return;}
        if ((num & 1) == 0) {
            dfs(num >> 1, count + 1);
        } else {
            long small = num - 1, big = num + 1;
            // 剪枝2
            if ((small & (small - 1)) == 0) {
                dfs(small, count + 1);
            } else if ((big & (big - 1)) == 0) {
                dfs(big, count + 1);
            } else {
                dfs(small, count + 1);
                dfs(big, count + 1);
            }
        }
    }

    private void dfs(long num, int count) {
        if (num == 1) {
            if (count < min) {
                min = count;
            }
            return;
        }
        // 剪枝1
        if (count >= min) {return;}
        if ((num & 1) == 0) {
            dfs(num >> 1, count + 1);
        } else {
            dfs(num - 1, count + 1);
            dfs(num + 1, count + 1);
        }
    }

    public static void main(String[] args) {
        IntegerReplacement ir = new IntegerReplacement();
        System.out.println(ir.integerReplacement(2147483647));
    }
}
