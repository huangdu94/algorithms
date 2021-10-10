package work.huangdu.question_bank.medium;

/**
 * 650. 只有两个键的键盘
 * 最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：
 * Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
 * Paste（粘贴）：粘贴 上一次 复制的字符。
 * 给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。
 * 示例 1：
 * 输入：3
 * 输出：3
 * 解释：
 * 最初, 只有一个字符 'A'。
 * 第 1 步, 使用 Copy All 操作。
 * 第 2 步, 使用 Paste 操作来获得 'AA'。
 * 第 3 步, 使用 Paste 操作来获得 'AAA'。
 * 示例 2：
 * 输入：n = 1
 * 输出：0
 * 提示：
 * 1 <= n <= 1000
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/9/19
 */
public class MinSteps {
    private int minStep;

    public int minSteps2(int n) {
        if (n == 1) {return 0;}
        minStep = n;
        dfs(n, 1, 0, 0);
        return minStep;
    }

    private void dfs(int n, int text, int paste, int step) {
        if (text > n || paste > n) {return;}
        if (step >= minStep) {return;}
        if (text == n) {
            minStep = step;
            return;
        }
        dfs(n, text, text, step + 1);
        if (paste > 0) {
            dfs(n, text + paste, paste, step + 1);
        }
    }

    // TODO 需要复习
    public int minSteps(int n) {
        int[] f = new int[n + 1];
        for (int i = 2; i <= n; ++i) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; ++j) {
                if (i % j == 0) {
                    f[i] = Math.min(f[i], f[j] + i / j);
                    f[i] = Math.min(f[i], f[i / j] + j);
                }
            }
        }
        return f[n];
    }

    public static void main(String[] args) {
        MinSteps ms = new MinSteps();
        System.out.println(ms.minSteps(5));
    }
}
