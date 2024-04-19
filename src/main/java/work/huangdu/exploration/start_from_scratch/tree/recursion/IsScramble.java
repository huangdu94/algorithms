package work.huangdu.exploration.start_from_scratch.tree.recursion;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 87. 扰乱字符串
 * 给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。
 * 下图是字符串 s1 = "great" 的一种可能的表示形式。
 * *     great
 * *    /    \
 * *   gr    eat
 * *  / \    /  \
 * * g   r  e   at
 * *            / \
 * *           a   t
 * 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。
 * 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。
 * *     rgeat
 * *    /    \
 * *   rg    eat
 * *  / \    /  \
 * * r   g  e   at
 * *            / \
 * *           a   t
 * 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。
 * 同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。
 * *     rgtae
 * *    /    \
 * *   rg    tae
 * *  / \    /  \
 * * r   g  ta  e
 * *        / \
 * *       t   a
 * 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。
 * 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。
 * 示例 1:
 * 输入: s1 = "great", s2 = "rgeat"
 * 输出: true
 * 示例 2:
 * 输入: s1 = "abcde", s2 = "caebd"
 * 输出: false
 *
 * @author huangdu
 * @version 2021/1/1 17:13
 */
public class IsScramble {

    public static void main(String[] args) {
        IsScramble solution = new IsScramble();
        long start = System.currentTimeMillis();
        System.out.println(solution.isScramble2("eebaacbcbcadaaedceaaacadccd",
            "eadcaacabaddaceacbceaabeccd"));
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) / 1000.0 + "秒.");
        System.out.println("Done!");
    }

    private static class Key {
        int l1;
        int l2;
        int r1;
        int r2;

        public Key(int l1, int l2, int r1, int r2) {
            this.l1 = l1;
            this.l2 = l2;
            this.r1 = r1;
            this.r2 = r2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }
            if (o == null || getClass() != o.getClass()) { return false; }
            Key key = (Key)o;
            return l1 == key.l1 &&
                l2 == key.l2 &&
                r1 == key.r1 &&
                r2 == key.r2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(l1, l2, r1, r2);
        }
    }

    private final Map<Key, Boolean> memo = new HashMap<>();

    public boolean isScramble2(String s1, String s2) {
        int n = s1.length();
        if (!isAnagram(s1, s2)) { return false;}
        if (n < 4) {return true;}
        return helper(s1.toCharArray(), 0, n, s2.toCharArray(), 0, n);
    }

    private boolean helper(char[] s1, int l1, int r1, char[] s2, int l2, int r2) {
        if (r1 - l1 < 4) {return true;}
        Key key = new Key(l1, l2, r1, r2);
        if (memo.containsKey(key)) { return memo.get(key); }
        int[] countHead = new int[26], countTail = new int[26];
        int zeroHead = 0, zeroTail = 0;
        for (int i = l1 + 1, j = l2 + 1; i < r1; i++, j++) {
            if (++countHead[s1[i - 1] - 'a'] == 0) {
                zeroHead--;
            } else if (countHead[s1[i - 1] - 'a'] == 1) {
                zeroHead++;
            }
            if (--countHead[s2[j - 1] - 'a'] == 0) {
                zeroHead--;
            } else if (countHead[s2[j - 1] - 'a'] == -1) {
                zeroHead++;
            }
            if (zeroHead == 0 && helper(s1, l1, i, s2, l2, j) && helper(s1, i, r1, s2, j, r2)) {
                memo.put(key, true);
                return true;
            }
            if (++countTail[s1[i - 1] - 'a'] == 0) {
                zeroTail--;
            } else if (countTail[s1[i - 1] - 'a'] == 1) {
                zeroTail++;
            }
            if (--countTail[s2[r2 - (j - l2)] - 'a'] == 0) {
                zeroTail--;
            } else if (countTail[s2[r2 - (j - l2)] - 'a'] == -1) {
                zeroTail++;
            }
            if (zeroTail == 0 && helper(s1, l1, i, s2, r2 - (j - l2), r2) && helper(s1, i, r1, s2, l2, r2 - (j - l2))) {
                memo.put(key, true);
                return true;
            }
        }
        memo.put(key, false);
        return false;
    }

    private boolean isAnagram(String s1, String s2) {
        int n = s1.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        if (n == 1 && s1.equals(s2)) { return true; }
        for (int i = 1; i < n; i++) {
            String left1 = s1.substring(0, i), right1 = s1.substring(i),
                left2head = s2.substring(0, i), left2tail = s2.substring(n - i);
            if (isAnagram(left1, left2head)) {
                if (isScramble(left1, left2head) && isScramble(right1, s2.substring(i))) {
                    return true;
                }
            }
            if (isAnagram(left1, left2tail)) {
                if (isScramble(left1, left2tail) && isScramble(right1, s2.substring(0, n - i))) {
                    return true;
                }
            }
        }
        return false;
    }
}
