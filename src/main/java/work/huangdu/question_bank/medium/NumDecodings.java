package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * 示例 1：
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2：
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * 示例 3：
 * 输入：s = "0"
 * 输出：0
 * 解释：没有字符映射到以 0 开头的数字。
 * 含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
 * 由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
 * 示例 4：
 * 输入：s = "06"
 * 输出：0
 * 解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。
 * 提示：
 * 1 <= s.length <= 100
 * s 只包含数字，并且可能包含前导零。
 *
 * @author huangdu
 * @version 2021/4/21
 */
public class NumDecodings {
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0') {
                dp[i + 1] += dp[i];
                if (i + 1 < n && (s.charAt(i) < '2' || s.charAt(i) == '2' && s.charAt(i + 1) <= '6')) {
                    dp[i + 2] += dp[i];
                }
            }
        }
        return dp[n];
    }

    public int numDecodings2(String s) {
        int n = s.length();
        Queue<Integer> queue = new ArrayDeque<>();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        queue.offer(0);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur != n && s.charAt(cur) != '0') {
                if (dp[cur + 1] == 0) {
                    queue.offer(cur + 1);
                }
                dp[cur + 1] += dp[cur];
                if (cur + 1 < n && (s.charAt(cur) < '2' || s.charAt(cur) == '2' && s.charAt(cur + 1) <= '6')) {
                    if (dp[cur + 2] == 0) {
                        queue.offer(cur + 2);
                    }
                    dp[cur + 2] += dp[cur];
                }
            }
        }
        return dp[n];
    }

    public int numDecodings3(String s) {
        int n = s.length();
        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> visited = new HashMap<>();
        queue.offer(0);
        visited.put(0, 1);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur != n && s.charAt(cur) != '0') {
                if (!visited.containsKey(cur + 1)) {
                    queue.offer(cur + 1);
                }
                visited.put(cur + 1, visited.getOrDefault(cur + 1, 0) + visited.get(cur));
                if (cur + 1 < n && (s.charAt(cur) < '2' || s.charAt(cur) == '2' && s.charAt(cur + 1) <= '6')) {
                    if (!visited.containsKey(cur + 2)) {
                        queue.offer(cur + 2);
                    }
                    visited.put(cur + 2, visited.getOrDefault(cur + 2, 0) + visited.get(cur));
                }
            }
        }
        return visited.getOrDefault(n, 0);
    }

    public static void main(String[] args) {
        NumDecodings nd = new NumDecodings();
        System.out.println(nd.numDecodings("3123124247982348792347"));
        System.out.println(nd.numDecodings2("3123124247982348792347"));
    }
}
