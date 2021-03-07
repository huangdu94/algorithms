package work.huangdu.exploration.advanced_algorithms.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. 分割回文串
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 * 示例:
 * 输入:"aab"
 * 输出:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/8/13 13:59
 */
public class Partition {
    private final List<List<String>> res = new ArrayList<>();
    private final List<String> combination = new ArrayList<>();

    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) { return res; }
        backtrack(s, 0);
        return res;
    }

    /**
     * 回溯方法
     *
     * @param s     字符串
     * @param start 当前位置
     */
    private void backtrack(String s, int start) {
        for (int end = start + 1; end <= s.length(); end++) {
            if (isPlalindrome(s, start, end - 1)) {
                combination.add(s.substring(start, end));
                if (end == s.length()) {
                    res.add(new ArrayList<>(combination));
                } else {
                    backtrack(s, end);
                }
                combination.remove(combination.size() - 1);
            }
        }
    }

    /**
     * 判断字符串[i,j]子串是否为回文串
     *
     * @param s 字符串
     * @param i 子串开始
     * @param j 子串结尾
     * @return 是否为回文串
     */
    private boolean isPlalindrome(String s, int i, int j) {
        while (i < j) { if (s.charAt(i++) != s.charAt(j--)) { return false; } }
        return true;
    }
}

/**
 * 备忘录版本
 */
class Solution {
    public List<List<String>> partition(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        List<List<String>> results = new ArrayList<>();
        List<String> result = new ArrayList<>();
        int[][] memo = new int[n][n];
        recall(results, result, chars, 0, n, memo);
        return results;
    }

    private void recall(List<List<String>> results, List<String> result, char[] chars, int head, int n, int[][] memo) {
        if (head == n) {
            results.add(new ArrayList<>(result));
            return;
        }
        // 单个字符的一定满足
        result.add(new String(chars, head, 1));
        recall(results, result, chars, head + 1, n, memo);
        result.remove(result.size() - 1);
        // 多个字符的判断是否为回文
        for (int tail = head + 1; tail < n; tail++) {
            if (isPalindromic(chars, head, tail, memo)) {
                result.add(new String(chars, head, tail - head + 1));
                recall(results, result, chars, tail + 1, n, memo);
                result.remove(result.size() - 1);
            }
        }
    }

    private boolean isPalindromic(char[] chars, int head, int tail, int[][] memo) {
        if (memo[head][tail] == 0) {
            int i = head, j = tail;
            while (i < j) {
                if (chars[i++] != chars[j--]) {
                    memo[head][tail] = -1;
                    return false;
                }
            }
            memo[head][tail] = 1;
            return true;
        }
        return memo[head][tail] == 1;
    }
}

/**
 * 无备忘录版本
 */
class Solution2 {
    public List<List<String>> partition(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        List<List<String>> results = new ArrayList<>();
        List<String> result = new ArrayList<>();
        recall(results, result, chars, 0, n);
        return results;
    }

    private void recall(List<List<String>> results, List<String> result, char[] chars, int head, int n) {
        if (head == n) {
            results.add(new ArrayList<>(result));
            return;
        }
        // 单个字符的一定满足
        result.add(new String(chars, head, 1));
        recall(results, result, chars, head + 1, n);
        result.remove(result.size() - 1);
        // 多个字符的判断是否为回文
        for (int tail = head + 1; tail < n; tail++) {
            if (isPalindromic(chars, head, tail)) {
                result.add(new String(chars, head, tail - head + 1));
                recall(results, result, chars, tail + 1, n);
                result.remove(result.size() - 1);
            }
        }
    }

    private boolean isPalindromic(char[] chars, int head, int tail) {
        while (head < tail) {
            if (chars[head++] != chars[tail--]) {
                return false;
            }
        }
        return true;
    }
}