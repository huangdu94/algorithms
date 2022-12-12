package work.huangdu.question_bank.medium;

/**
 * 1781. 所有子字符串美丽值之和
 * 一个字符串的 美丽值 定义为：出现频率最高字符与出现频率最低字符的出现次数之差。
 * 比方说，"abaacc" 的美丽值为 3 - 1 = 2 。
 * 给你一个字符串 s ，请你返回它所有子字符串的 美丽值 之和。
 * 示例 1：
 * 输入：s = "aabcb"
 * 输出：5
 * 解释：美丽值不为零的字符串包括 ["aab","aabc","aabcb","abcb","bcb"] ，每一个字符串的美丽值都为 1 。
 * 示例 2：
 * 输入：s = "aabcbaa"
 * 输出：17
 * 提示：
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/12/12
 */
public class BeautySum {
    public int beautySum(String s) {
        int n = s.length(), ans = 0;
        for (int k = 1; k <= n; k++) {
            int[] counts = new int[26];
            for (int i = 0; i < k; i++) {
                counts[s.charAt(i) - 'a']++;
            }
            ans += beauty(counts);
            for (int i = k; i < n; i++) {
                counts[s.charAt(i - k) - 'a']--;
                counts[s.charAt(i) - 'a']++;
                ans += beauty(counts);
            }
        }
        return ans;
    }

    private int beauty(int[] counts) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int count : counts) {
            if (count == 0) {continue;}
            max = Math.max(max, count);
            min = Math.min(min, count);
        }
        return max - min;
    }

    public int beautySum2(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int[] counts = new int[26];
                int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
                for (int k = i; k <= j; k++) {
                    counts[s.charAt(k) - 'a']++;
                }
                for (int count : counts) {
                    if (count == 0) {continue;}
                    max = Math.max(max, count);
                    min = Math.min(min, count);
                }
                ans += max - min;
            }
        }
        return ans;
    }
}
