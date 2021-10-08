package work.huangdu.exploration.start_from_scratch.double_pointer.homonymous_sliding_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 187. 重复的DNA序列
 * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
 * 示例 1：
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * 示例 2：
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 * 提示：
 * 0 <= s.length <= 10^5
 * s[i] 为 'A'、'C'、'G' 或 'T'
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/12/15 10:43
 */
public class FindRepeatedDnaSequences {
    private static final Map<Character, Integer> dictionary = new HashMap<>();

    static {
        dictionary.put('A', 1);
        dictionary.put('C', 2);
        dictionary.put('G', 3);
        dictionary.put('T', 4);
    }

    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        if (n < 10) {return new ArrayList<>();}
        Set<Integer> hashSet = new HashSet<>(n - 9);
        Set<Integer> exist = new HashSet<>(n / 2);
        List<String> result = new ArrayList<>();
        for (int i = 0; i + 9 < n; i++) {
            int hash = hash(s, i, i + 10);
            if (!hashSet.add(hash) && exist.add(hash)) {
                result.add(s.substring(i, i + 10));
            }
        }
        return result;
    }

    /**
     * 10位目标子串求hash值
     * A C G T
     * 1 2 3 4
     *
     * @param s     输入字符串
     * @param start 当前窗口开始
     * @param end   当前窗口结束
     * @return hash值
     */
    private int hash(String s, int start, int end) {
        int hash = 0, base = 1;
        for (int i = start; i < end; i++) {
            hash += dictionary.get(s.charAt(i)) * base;
            base *= 4;
        }
        return hash;
    }

    public List<String> findRepeatedDnaSequences2(String s) {
        int n = s.length();
        if (n < 10) {return new ArrayList<>();}
        char[] chars = s.toCharArray();
        int preHash = hash(chars);
        List<String> ans = new ArrayList<>();
        Set<Integer> hashSet = new HashSet<>(n - 9);
        Set<Integer> exist = new HashSet<>(n / 2);
        hashSet.add(preHash);
        for (int i = 1; i < n - 9; i++) {
            if (!hashSet.add(preHash = hash(preHash, chars[i - 1], chars[i + 9])) && exist.add(preHash)) {
                ans.add(new String(chars, i, 10));
            }
        }
        return ans;
    }

    private int hash(int preHash, char head, char tail) {
        return (preHash - index(head)) / 4 + index(tail) * (int)Math.pow(4, 9);
    }

    private int hash(char[] chars) {
        int hash = 0, base = 1;
        for (int i = 0; i < 10; i++) {
            hash += index(chars[i]) * base;
            base *= 4;
        }
        return hash;
    }

    private int index(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
            default:
                return -1;
        }
    }
}
