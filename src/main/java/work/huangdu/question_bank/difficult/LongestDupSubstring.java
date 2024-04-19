package work.huangdu.question_bank.difficult;

import java.util.HashSet;
import java.util.Set;

/**
 * 1044. 最长重复子串
 * 给你一个字符串 s ，考虑其所有 重复子串 ：即，s 的连续子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。
 * 返回 任意一个 可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。
 * 示例 1：
 * 输入：s = "banana"
 * 输出："ana"
 * 示例 2：
 * 输入：s = "abcd"
 * 输出：""
 * 提示：
 * 2 <= s.length <= 3 * 10^4
 * s 由小写英文字母组成
 *
 * @author huangdu
 * @version 2021/12/23
 */
public class LongestDupSubstring {
    // TODO 需要复习 Rabin-Karp 字符串编码
    public String longestDupSubstring(String s) {
        int n = s.length(), a1 = 26, a2 = 27, mod1 = 1000000007, mod2 = 1000000009;
        // 先对所有字符进行编码
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = s.charAt(i) - 'a';
        }
        // 二分查找的范围是[1, n-1]
        int l = 1, r = n - 1;
        int length = 0, start = -1;
        while (l <= r) {
            int m = l + (r - l + 1) / 2;
            int idx = check(arr, m, a1, a2, mod1, mod2);
            if (idx != -1) {
                // 有重复子串，移动左边界
                l = m + 1;
                length = m;
                start = idx;
            } else {
                // 无重复子串，移动右边界
                r = m - 1;
            }
        }
        return start != -1 ? s.substring(start, start + length) : "";
    }

    public int check(int[] arr, int m, int a1, int a2, int mod1, int mod2) {
        int n = arr.length;
        long aL1 = pow(a1, m, mod1);
        long aL2 = pow(a2, m, mod2);
        long h1 = 0, h2 = 0;
        for (int i = 0; i < m; ++i) {
            h1 = (h1 * a1 % mod1 + arr[i]) % mod1;
            h2 = (h2 * a2 % mod2 + arr[i]) % mod2;
        }
        // 存储一个编码组合是否出现过
        Set<Long> seen = new HashSet<>();
        seen.add(h1 * mod2 + h2);
        for (int start = 1; start <= n - m; ++start) {
            h1 = (h1 * a1 % mod1 - arr[start - 1] * aL1 % mod1 + arr[start + m - 1]) % mod1;
            h2 = (h2 * a2 % mod2 - arr[start - 1] * aL2 % mod2 + arr[start + m - 1]) % mod2;
            if (h1 < 0) {h1 += mod1;}
            if (h2 < 0) {h2 += mod2;}
            // 如果重复，则返回重复串的起点
            if (!seen.add(h1 * mod2 + h2)) {return start;}
        }
        // 没有重复，则返回-1
        return -1;
    }

    /**
     * 求a^n mod mod
     */
    public long pow(long a, long n, long mod) {
        long res = 1;
        long x = a;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = res * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return res;
    }
}
