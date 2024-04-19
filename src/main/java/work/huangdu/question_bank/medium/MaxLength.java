package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1239. 串联字符串的最大长度
 * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 * 请返回所有可行解 s 中最长长度。
 * 示例 1：
 * 输入：arr = ["un","iq","ue"]
 * 输出：4
 * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
 * 示例 2：
 * 输入：arr = ["cha","r","act","ers"]
 * 输出：6
 * 解释：可能的解答有 "chaers" 和 "acters"。
 * 示例 3：
 * 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
 * 输出：26
 * 提示：
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] 中只含有小写英文字母
 *
 * @author huangdu
 * @version 2021/6/19
 */
public class MaxLength {
    private int n;
    private List<Set<Character>> list;
    private Set<Character> selected;
    private int max;

    public int maxLength(List<String> arr) {
        this.list = new ArrayList<>(arr.size());
        int remain = 0;
        for (String str : arr) {
            int len = str.length();
            Set<Character> set = new HashSet<>(len);
            for (int i = 0; i < len; i++) {
                if (!set.add(str.charAt(i))) {
                    set = null;
                    break;
                }
            }
            if (set != null) {
                remain += set.size();
                list.add(set);
            }
        }
        this.n = list.size();
        this.selected = new HashSet<>(26);
        this.max = 0;
        backtrack(0, remain);
        return max;
    }

    private void backtrack(int i, int remain) {
        if (i == n) {
            max = Math.max(max, selected.size());
            return;
        }
        if (selected.size() + remain < max) {
            return;
        }
        Set<Character> bak = selected;
        Set<Character> cur = list.get(i);
        Set<Character> union = new HashSet<>(selected);
        union.addAll(cur);
        if (union.size() == cur.size() + selected.size()) {
            selected = union;
            backtrack(i + 1, remain - cur.size());
        }
        selected = bak;
        backtrack(i + 1, remain - cur.size());
    }

    static class Solution {
        private int n;
        private List<Integer> list;
        private int max;

        public int maxLength(List<String> arr) {
            this.list = new ArrayList<>(arr.size());
            int remain = 0;
            for (String str : arr) {
                int bits = 0;
                for (int i = 0, len = str.length(); i < len; i++) {
                    int bit = 1 << (str.charAt(i) - 'a');
                    if ((bits & bit) != 0) {
                        bits = 0;
                        break;
                    }
                    bits |= bit;
                }
                if (bits != 0) {
                    remain += Integer.bitCount(bits);
                    list.add(bits);
                }
            }
            this.n = list.size();
            this.max = 0;
            backtrack(0, 0, remain);
            return max;
        }

        private void backtrack(int i, int selected, int remain) {
            int selectedCount = Integer.bitCount(selected);
            if (i == n) {
                if (max < selectedCount) {max = selectedCount;}
                return;
            }
            if (selectedCount + remain < max) {
                return;
            }
            int cur = list.get(i), curCount = Integer.bitCount(cur);
            if ((selected & cur) == 0) {
                backtrack(i + 1, selected | cur, remain - curCount);
            }
            backtrack(i + 1, selected, remain - curCount);
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            List<String> arr = Arrays.asList("ab", "ba", "cd", "dc", "ef", "fe", "gh", "hg", "ij", "ji", "kl", "lk", "mn", "nm", "op", "po");
            System.out.println(solution.maxLength(arr));
        }
    }
}
