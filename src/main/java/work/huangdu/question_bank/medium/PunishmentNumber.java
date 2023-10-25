package work.huangdu.question_bank.medium;

/**
 * 2698. 求一个整数的惩罚数
 * 给你一个正整数 n ，请你返回 n 的 惩罚数 。
 * n 的 惩罚数 定义为所有满足以下条件 i 的数的平方和：
 * 1 <= i <= n
 * i * i 的十进制表示的字符串可以分割成若干连续子字符串，且这些子字符串对应的整数值之和等于 i 。
 * 示例 1：
 * 输入：n = 10
 * 输出：182
 * 解释：总共有 3 个整数 i 满足要求：
 * - 1 ，因为 1 * 1 = 1
 * - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
 * - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
 * 因此，10 的惩罚数为 1 + 81 + 100 = 182
 * 示例 2：
 * 输入：n = 37
 * 输出：1478
 * 解释：总共有 4 个整数 i 满足要求：
 * - 1 ，因为 1 * 1 = 1
 * - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
 * - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
 * - 36 ，因为 36 * 36 = 1296 ，且 1296 可以分割成 1 + 29 + 6 。
 * 因此，37 的惩罚数为 1 + 81 + 100 + 1296 = 1478
 * 提示：
 * 1 <= n <= 1000
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class PunishmentNumber {
    private final int[] table = {1, 9, 10, 36, 45, 55, 82, 91, 99, 100, 235, 297, 369, 370, 379, 414, 657, 675, 703, 756, 792, 909, 918, 945, 964, 990, 991, 999, 1000};

    public int punishmentNumber(int n) {
        int ans = 0;
        for (int num : table) {
            if (num > n) {break;}
            ans += num * num;
        }
        return ans;
    }

    public int punishmentNumber2(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int len = len(i * i);
            int[] digits = digits(i * i, len);
            for (int part = 1; part <= len; part++) {
                if (dfs(digits, len, 0, part, i)) {
                    ans += i * i;
                    break;
                }
            }
        }
        return ans;
    }

    private int len(int num) {
        int n = 0;
        while (num > 0) {
            num /= 10;
            n++;
        }
        return n;
    }

    private int[] digits(int num, int n) {
        int[] digits = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            digits[i] = num % 10;
            num /= 10;
        }
        return digits;
    }

    private boolean dfs(int[] digits, int n, int cur, int part, int target) {
        if (part == 1) {return compute(digits, cur, n) == target;}
        if (n - cur < part) {return false;}
        for (int i = cur + 1; i < n; i++) {
            if (dfs(digits, n, i, part - 1, target - compute(digits, cur, i))) {return true;}
        }
        return false;
    }

    private int compute(int[] digits, int start, int end) {
        int num = 0;
        for (int i = start; i < end; i++) {
            num = num * 10 + digits[i];
        }
        return num;
    }
}
