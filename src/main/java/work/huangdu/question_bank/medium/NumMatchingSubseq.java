package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 792. 匹配子序列的单词数
 * 给定字符串 s 和字符串数组 words, 返回  words[i] 中是s的子序列的单词个数 。
 * 字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
 * 例如， “ace” 是 “abcde” 的子序列。
 * 示例 1:
 * 输入: s = "abcde", words = ["a","bb","acd","ace"]
 * 输出: 3
 * 解释: 有三个是 s 的子序列的单词: "a", "acd", "ace"。
 * Example 2:
 * 输入: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * 输出: 2
 * 提示:
 * 1 <= s.length <= 5 * 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * words[i]和 s 都只由小写字母组成。
 *
 * @author huangdu
 * @version 2022/11/17
 */
public class NumMatchingSubseq {
    public int numMatchingSubseq(String s, String[] words) {
        Queue<int[]>[] queues = new Queue[26];
        for (int i = 0; i < 26; i++) {
            queues[i] = new ArrayDeque<>();
        }
        for (int i = 0, n = words.length; i < n; i++) {
            queues[words[i].charAt(0) - 'a'].offer(new int[] {i, 0});
        }
        int ans = 0;
        for (int i = 0, n = s.length(); i < n; i++) {
            char c = s.charAt(i);
            Queue<int[]> queue = queues[c - 'a'];
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] data = queue.poll();
                if (data[1] + 1 == words[data[0]].length()) {
                    ans++;
                    continue;
                }
                queues[words[data[0]].charAt(data[1] + 1) - 'a'].offer(new int[] {data[0], data[1] + 1});
            }
        }
        return ans;
    }

    public int numMatchingSubseq2(String s, String[] words) {
        List<Integer>[] indexs = new List[26];
        for (int i = 0; i < 26; i++) {
            indexs[i] = new ArrayList<>();
        }
        for (int i = 0, n = s.length(); i < n; i++) {
            indexs[s.charAt(i) - 'a'].add(i);
        }
        int ans = 0;
        for (String word : words) {
            int n = word.length(), curIdx = -1, i = 0;
            while (i < n) {
                char c = word.charAt(i);
                List<Integer> idxList = indexs[c - 'a'];
                if (idxList.isEmpty()) {break;}
                int left = 0, right = idxList.size() - 1;
                while (left < right) {
                    int mid = left + (right - left >> 1);
                    if (idxList.get(mid) <= curIdx) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                if (idxList.get(left) <= curIdx) {break;}
                curIdx = idxList.get(left);
                i++;
            }
            if (i == n) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        NumMatchingSubseq nms = new NumMatchingSubseq();
        System.out.println(nms.numMatchingSubseq("abcde", new String[] {"a", "bb", "acd", "ace"}));
        System.out.println(nms.numMatchingSubseq2("abcde", new String[] {"a", "bb", "acd", "ace"}));
    }
}
