package work.huangdu.exploration.start_from_scratch.double_pointer.homonymous_sliding_window;

/**
 * 424. 替换后的最长重复字符
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 * 注意:
 * 字符串长度 和 k 不会超过 10^4。
 * 示例 1:
 * 输入:
 * s = "ABAB", k = 2
 * 输出:
 * 4
 * 解释:
 * 用两个'A'替换为两个'B',反之亦然。
 * 示例 2:
 * 输入:
 * s = "AABABBA", k = 1
 * 输出:
 * 4
 * 解释:
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 *
 * @author huangdu
 * @version 2020/12/18 12:58
 */
public class CharacterReplacement {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        if (n <= k) return n;
        int max = 0, result = k + 1, start = 0;
        int[] counts = new int[26];
        char[] chars = s.toCharArray();
        for (int end = 0; end < n; end++) {
            max = Math.max(max, ++counts[chars[end] - 'A']);
            while (end - start + 1 > k + max) {
                int count = counts[chars[start++] - 'A']--;
                if (count == max) {
                    max = getMax(counts);
                }
            }
            result = Math.max(result, end - start + 1);
        }
        return result;
    }

    private int getMax(int[] counts) {
        int max = 0;
        for (int count : counts) {
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    public int characterReplacement1(String s, int k) {
        // 假设s不为null且k不为负数
        // n为0时，此判断条件成立，n>=1、27、53...时，最大的字母至少为1+n/27个
        // if (k >= n - 1 - n / 27) return n;
        int n = s.length(), maxLen = 0, maxCount = 0, l = 0, r = 0;
        int[] counts = new int[26];
        while (r < n) {
            while (r <= n && maxCount + k >= r - l) {
                if (r < n) {
                    maxCount = Math.max(maxCount, ++counts[s.charAt(r) - 'A']);
                }
                r++;
            }
            maxLen = Math.max(maxLen, r - l - 1);
            while (maxCount + k < r - l) {
                counts[s.charAt(l++) - 'A']--;
            }
        }
        return maxLen;
    }

    public int characterReplacement2(String s, int k) {
        int n = s.length(), maxLen = 0, maxCount = 0, l = 0, r = 0;
        int[] counts = new int[26];
        while (r < n) {
            maxCount = Math.max(maxCount, ++counts[s.charAt(r++) - 'A']);
            while (maxCount + k < r - l) {
                counts[s.charAt(l++) - 'A']--;
            }
            maxLen = Math.max(maxLen, r - l);
        }
        return maxLen;
    }

    public int characterReplacement3(String s, int k) {
        int n = s.length(), maxCount = 0, l = 0, r = 0;
        int[] counts = new int[26];
        while (r < n) {
            maxCount = Math.max(maxCount, ++counts[s.charAt(r++) - 'A']);
            if (maxCount + k < r - l) {
                counts[s.charAt(l++) - 'A']--;
            }
        }
        return r - l;
    }

    public static void main(String[] args) {
        CharacterReplacement cp = new CharacterReplacement();
        String s = "AAAA";
        int k = 0;
        int result = cp.characterReplacement(s, k);
        System.out.println(result);
    }
}
