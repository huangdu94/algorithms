package work.huangdu.question_bank.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 01.02. 判定是否互为字符重排
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 * 示例 1：
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 * 示例 2：
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 * 说明：
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 *
 * @author huangdu
 * @version 2022/9/27
 */
public class CheckPermutation {
    public boolean checkPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {return false;}
        int n = s1.length();
        Map<Character, Integer> freqs = new HashMap<>(n << 1);
        for (int i = 0; i < n; i++) {
            if (freqs.merge(s1.charAt(i), 1, Integer::sum) == 0) {freqs.remove(s1.charAt(i));}
            if (freqs.merge(s2.charAt(i), -1, Integer::sum) == 0) {freqs.remove(s2.charAt(i));}
        }
        return freqs.isEmpty();
    }
}
