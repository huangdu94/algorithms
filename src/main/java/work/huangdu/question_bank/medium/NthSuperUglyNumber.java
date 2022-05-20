package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 313. 超级丑数
 * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
 * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
 * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
 * 示例 1：
 * 输入：n = 12, primes = [2,7,13,19]
 * 输出：32
 * 解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
 * 示例 2：
 * 输入：n = 1, primes = [2,3,5]
 * 输出：1
 * 解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
 * 提示：
 * 1 <= n <= 10^6
 * 1 <= primes.length <= 100
 * 2 <= primes[i] <= 1000
 * 题目数据 保证 primes[i] 是一个质数
 * primes 中的所有值都 互不相同 ，且按 递增顺序 排列
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/8/9
 */
public class NthSuperUglyNumber {
    // TODO 需要复习
    // 最小堆 mn*log(mn) 超时
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1) {
            return 1;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        Set<Integer> seen = new HashSet<>();
        for (int prime : primes) {
            minHeap.offer(prime);
        }
        while (true) {
            int min = minHeap.remove();
            if (--n == 1) {
                return min;
            }
            for (int prime : primes) {
                long num = (long) prime * min;
                if (num <= Integer.MAX_VALUE) {
                    if (seen.add((int) num)) {
                        minHeap.offer((int) num);
                    }
                }
            }
        }
    }

    // 动态规划
    public int nthSuperUglyNumber2(int n, int[] primes) {
        if (n == 1) {
            return 1;
        }
        int k = 1, len = primes.length;
        int[] dp = new int[n + 1];
        dp[k] = 1;
        int[] pointers = new int[len];
        Arrays.fill(pointers, 1);
        while (++k <= n) {
            long min = Integer.MAX_VALUE;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                long next = (long) dp[pointers[i]] * primes[i];
                if (min > next) {
                    min = next;
                    list.clear();
                }
                if (min == next) {
                    list.add(i);
                }
            }
            for (int index : list) {
                pointers[index]++;
            }
            dp[k] = (int) min;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        NthSuperUglyNumber nsun = new NthSuperUglyNumber();
        System.out.println(nsun.nthSuperUglyNumber2(100000, new int[]{7, 19, 29, 37, 41, 47, 53, 59, 61, 79, 83, 89, 101, 103, 109, 127, 131, 137, 139, 157, 167, 179, 181, 199, 211, 229, 233, 239, 241, 251}) == 1092889481);
    }
}