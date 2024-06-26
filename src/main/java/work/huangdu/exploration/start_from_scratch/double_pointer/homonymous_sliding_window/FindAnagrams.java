package work.huangdu.exploration.start_from_scratch.double_pointer.homonymous_sliding_window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * 说明：
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * 输出:
 * [0, 6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 * 示例 2:
 * 输入:
 * s: "abab" p: "ab"
 * 输出:
 * [0, 1, 2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 *
 * @author huangdu
 * @version 2020/12/15 16:29
 */
public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) return new ArrayList<>();
        int pn = p.length(), sn = s.length();
        int[] pCounts = new int[26], sCounts = new int[26];
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < pn; i++) {
            pCounts[p.charAt(i) - 'a']++;
            sCounts[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i + pn <= sn; i++) {
            if (Arrays.equals(sCounts, pCounts)) {
                result.add(i);
            }
            if (i + pn < sn) {
                sCounts[s.charAt(i) - 'a']--;
                sCounts[s.charAt(i + pn) - 'a']++;
            }
        }
        return result;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        if (s.length() < p.length()) return new ArrayList<>();
        int pn = p.length(), sn = s.length(), left = 0;
        int[] pCounts = new int[26], sCounts = new int[26];
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < pn; i++) {
            pCounts[p.charAt(i) - 'a']++;
        }
        for (int right = 0; right < sn; right++) {
            int c = s.charAt(right) - 'a';
            if (++sCounts[c] > pCounts[c]) {
                int temp;
                while ((temp = s.charAt(left) - 'a') != c) {
                    sCounts[temp]--;
                    left++;
                }
                sCounts[temp]--;
                left++;
            }
            if (right - left + 1 == pn) {
                result.add(left);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FindAnagrams fa = new FindAnagrams();
        System.out.println(fa.findAnagrams2("cbaebabacd", "abc"));
    }

    public List<Integer> findAnagrams3(String s, String p) {
        int n = s.length(), m = p.length();
        if (n < m) {return new ArrayList<>(0); }
        List<Integer> ans = new ArrayList<>();
        int[] countS = init(s, m), countP = init(p, m);
        if (Arrays.equals(countS, countP)) {ans.add(0);}
        for (int i = 1; i <= n - m; i++) {
            countS[s.charAt(i - 1) - 'a']--;
            countS[s.charAt(i + m - 1) - 'a']++;
            if (Arrays.equals(countS, countP)) {ans.add(i);}
        }
        return ans;
    }

    private int[] init(String str, int end) {
        int[] count = new int[26];
        for (int i = 0; i < end; i++) {
            count[str.charAt(i) - 'a']++;
        }
        return count;
    }
}
