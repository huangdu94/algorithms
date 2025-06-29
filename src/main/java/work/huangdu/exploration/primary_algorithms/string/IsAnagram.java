package work.huangdu.exploration.primary_algorithms.string;

/**
 * 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 * @author huangdu
 * @version 2020/7/26 17:01
 */
public class IsAnagram {
    // 如果是unicode字符的话，使用HashMap
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] sRecode = new int[26];
        int[] tRecode = new int[26];
        for (int i = 0; i < s.length(); i++) {
            sRecode[s.charAt(i) - 'a']++;
            tRecode[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (sRecode[i] != tRecode[i])
                return false;
        }
        return true;
    }

    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        int len = s.length();
        int[] count = new int[26];
        for (int i = 0; i < len; i++) {
            int si = s.charAt(i) - 'a';
            int ti = t.charAt(i) - 'a';
            if (si != ti) {
                count[si]++;
                count[ti]--;
            }
        }
        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int mask = 0;
        int[] count = new int[26];
        for (int i = 0, n = s.length(); i < n; i++) {
            int sidx = s.charAt(i) - 'a', tidx = t.charAt(i) - 'a';
            if (sidx == tidx) {
                continue;
            }
            if (count[sidx] == 0) {
                mask |= 1 << sidx;
            } else if (count[sidx] == -1) {
                mask ^= 1 << sidx;
            }
            if (count[tidx] == 0) {
                mask |= 1 << tidx;
            } else if (count[tidx] == 1) {
                mask ^= 1 << tidx;
            }
            count[sidx]++;
            count[tidx]--;
        }
        return mask == 0;
    }
}
