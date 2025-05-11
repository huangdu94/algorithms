package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 784. 字母大小写全排列
 * 给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
 * 返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
 * 示例 1：
 * 输入：s = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 * 示例 2:
 * 输入: s = "3z4"
 * 输出: ["3z4","3Z4"]
 * 提示:
 * 1 <= s.length <= 12
 * s 由小写英文字母、大写英文字母和数字组成
 *
 * @author huangdu
 * @version 2022/10/31
 */
public class LetterCasePermutation {
    public List<String> letterCasePermutation(String s) {
        int n = s.length(), i = 0;
        while (i < n && !Character.isLetter(s.charAt(i))) {i++;}
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(s);
        while (i < n) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                String str = queue.poll();
                char[] chars = str.toCharArray();
                chars[i] = Character.isLowerCase(chars[i]) ? Character.toUpperCase(chars[i]) : Character.toLowerCase(chars[i]);
                queue.offer(str);
                queue.offer(new String(chars));
            }
            i++;
            while (i < n && !Character.isLetter(s.charAt(i))) {i++;}
        }
        return new ArrayList<>(queue);
    }
}
