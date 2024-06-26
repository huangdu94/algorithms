package work.huangdu.exploration.start_from_scratch.string.matching;

/**
 * 459. 重复的子字符串
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 * 示例 1:
 * 输入: "abab"
 * 输出: True
 * 解释: 可由子字符串 "ab" 重复两次构成。
 * 示例 2:
 * 输入: "aba"
 * 输出: False
 * 示例 3:
 * 输入: "abcabcabcabc"
 * 输出: True
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 *
 * @author huangdu
 * @version 2020/8/24 0:10
 */
public class RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern2(String s) {
        int len = s.length(), subLen;
        String pre, cur;
        boolean flag;
        // i表示假设字符串由子串重复两次构成
        for (int i = 2; i <= len; i++) {
            // 只有len是i的整数倍的时候才有继续判断的必要
            if (len % i == 0) {
                subLen = len / i;
                pre = s.substring(0, subLen);
                flag = true;
                for (int k = 2; k <= i; k++) {
                    cur = s.substring((k - 1) * subLen, k * subLen);
                    if (!pre.equals(cur)) {
                        flag = false;
                        break;
                    }
                }
                // 子串是否都相等
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        // i表示假设字符串由子串重复两次构成
        for (int i = 2; i <= len; i++) {
            // 只有len是i的整数倍的时候才有继续判断的必要
            if (len % i == 0) {
                if (equals(chars, len, len / i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean equals(char[] chars, int len, int subLen) {
        char first;
        for (int i = 0; i < subLen; i++) {
            first = chars[i];
            for (int j = i; j < len; j += subLen) {
                if (chars[j] != first) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean repeatedSubstringPattern3(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                boolean flag = true;
                for (int j = i; j < n; j++) {
                    if (chars[j] != chars[j - i]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "abab";
        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern3(s));
    }
}
