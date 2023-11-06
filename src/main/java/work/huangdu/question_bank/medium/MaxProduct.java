package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 318. 最大单词长度乘积
 * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 * 示例 1:
 * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "xtfn"。
 * 示例 2:
 * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 * 示例 3:
 * 输入: ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 * 提示：
 * <p>
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] 仅包含小写字母
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/11/17
 */
public class MaxProduct {
    public int maxProduct(String[] words) {
        int n = words.length, max = 0;
        int[] maskCache = new int[n];
        Arrays.sort(words, Comparator.comparingInt(String::length));
        for (int i = n - 1; i >= 1; i--) {
            int n1 = words[i].length();
            if (n1 * n1 < max) {
                break;
            }
            int mask1 = maskCache[i] == 0 ? convert(words[i]) : maskCache[i];
            for (int j = i - 1; j >= 0; j--) {
                int n2 = words[j].length();
                if (n1 * n2 < max) {
                    break;
                }
                int mask2 = maskCache[j] == 0 ? maskCache[j] = convert(words[j]) : maskCache[j];
                if ((mask1 & mask2) == 0) {
                    max = n1 * n2;
                }
            }
        }
        return max;
    }

    private int convert(String word) {
        int mask = 0;
        for (int i = 0, n = word.length(); mask != 0X3FFFFFF && i < n; i++) {
            mask |= (1 << (word.charAt(i) - 'a'));
        }
        return mask;
    }

    static class Solution2 {
        public int maxProduct(String[] words) {
            int n = words.length, max = 0;
            Map<String, Integer> cache = new HashMap<>(n);
            Arrays.sort(words, Comparator.comparingInt(String::length));
            for (int i = n - 1; i >= 1; i--) {
                int n1 = words[i].length();
                if (n1 * n1 < max) {
                    break;
                }
                Integer word1 = cache.getOrDefault(words[i], convert(words[i]));
                for (int j = i - 1; j >= 0; j--) {
                    int n2 = words[j].length();
                    if (n1 * n2 < max) {
                        break;
                    }
                    Integer word2 = cache.computeIfAbsent(words[j], this::convert);
                    if (compare(word1, word2)) {
                        max = n1 * n2;
                    }
                }
            }
            return max;
        }

        private int convert(String word) {
            int mask = 0;
            for (int i = 0, n = word.length(); mask != 0X3FFFFFF && i < n; i++) {
                int index = word.charAt(i) - 'a';
                mask |= (1 << index);
            }
            return mask;
        }

        private boolean compare(int word1, int word2) {
            while (word1 != 0 && word2 != 0) {
                if ((word1 & 1) == 1 && (word2 & 1) == 1) {
                    return false;
                }
                word1 >>= 1;
                word2 >>= 1;
            }
            return true;
        }
    }

    static class Solution1 {
        public int maxProduct(String[] words) {
            int n = words.length, max = 0;
            Map<String, boolean[]> cache = new HashMap<>(n);
            Arrays.sort(words, Comparator.comparingInt(String::length));
            for (int i = n - 1; i >= 1; i--) {
                int n1 = words[i].length();
                if (n1 * n1 < max) {
                    break;
                }
                boolean[] word1 = cache.getOrDefault(words[i], convert(words[i]));
                for (int j = i - 1; j >= 0; j--) {
                    int n2 = words[j].length();
                    if (n1 * n2 < max) {
                        break;
                    }
                    boolean[] word2 = cache.computeIfAbsent(words[j], this::convert);
                    if (compare(word1, word2)) {
                        max = n1 * n2;
                    }
                }
            }
            return max;
        }

        private boolean[] convert(String word) {
            int count = 0;
            boolean[] booleanArray = new boolean[26];
            for (int i = 0, n = word.length(); count < 26 && i < n; i++) {
                int index = word.charAt(i) - 'a';
                if (!booleanArray[index]) {
                    booleanArray[index] = true;
                    count++;
                }
            }
            return booleanArray;
        }

        private boolean compare(boolean[] word1, boolean[] word2) {
            for (int i = 0; i < 26; i++) {
                if (word1[i] && word2[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        String[] words = {"eae", "ea", "aaf", "bda", "fcf", "dc", "ac", "ce", "cefde", "dabae"};
        MaxProduct mp = new MaxProduct();
        System.out.println(mp.maxProduct(words));
    }

    public int maxProduct2(String[] words) {
        int n = words.length;
        int[] preproccess = new int[n];
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int temp = 0;
            for (int j = 0, len = word.length(); j < len; j++) {
                int idx = word.charAt(j) - 'a';
                temp |= 1 << idx;
            }
            preproccess[i] = temp;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((preproccess[i] & preproccess[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }
}
