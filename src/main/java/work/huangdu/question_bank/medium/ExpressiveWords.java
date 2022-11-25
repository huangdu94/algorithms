package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 809. 情感丰富的文字
 * 有时候人们会用重复写一些字母来表示额外的感受，比如 "hello" -> "heeellooo", "hi" -> "hiii"。我们将相邻字母都相同的一串字符定义为相同字母组，例如："h", "eee", "ll", "ooo"。
 * 对于一个给定的字符串 S ，如果另一个单词能够通过将一些字母组扩张从而使其和 S 相同，我们将这个单词定义为可扩张的（stretchy）。扩张操作定义如下：选择一个字母组（包含字母 c ），然后往其中添加相同的字母 c 使其长度达到 3 或以上。
 * 例如，以 "hello" 为例，我们可以对字母组 "o" 扩张得到 "hellooo"，但是无法以同样的方法得到 "helloo" 因为字母组 "oo" 长度小于 3。此外，我们可以进行另一种扩张 "ll" -> "lllll" 以获得 "helllllooo"。如果 s = "helllllooo"，那么查询词 "hello" 是可扩张的，因为可以对它执行这两种扩张操作使得 query = "hello" -> "hellooo" -> "helllllooo"
 * = s。
 * 输入一组查询单词，输出其中可扩张的单词数量。
 * 示例：
 * 输入：
 * s = "heeellooo"
 * words = ["hello", "hi", "helo"]
 * 输出：1
 * 解释：
 * 我们能通过扩张 "hello" 的 "e" 和 "o" 来得到 "heeellooo"。
 * 我们不能通过扩张 "helo" 来得到 "heeellooo" 因为 "ll" 的长度小于 3 。
 * 提示：
 * 1 <= s.length, words.length <= 100
 * 1 <= words[i].length <= 100
 * s 和所有在 words 中的单词都只由小写字母组成。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/11/25
 */
public class ExpressiveWords {
    public int expressiveWords(String s, String[] words) {
        List<Character> charList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        char ch = s.charAt(0);
        int cnt = 1;
        for (int i = 1, n = s.length(); i < n; i++) {
            char cur = s.charAt(i);
            if (cur == ch) {
                cnt++;
            } else {
                charList.add(ch);
                countList.add(cnt);
                ch = cur;
                cnt = 1;
            }
        }
        charList.add(ch);
        countList.add(cnt);
        int ans = 0;
        for (String word : words) {
            ch = word.charAt(0);
            cnt = 1;
            int idx = 0;
            boolean flag = true;
            for (int i = 1, len = word.length(); i < len; i++) {
                char cur = word.charAt(i);
                if (cur == ch) {
                    cnt++;
                } else {
                    if (idx > charList.size() - 1 || ch != charList.get(idx) || cnt > countList.get(idx) || cnt < countList.get(idx) && countList.get(idx) < 3) {
                        flag = false;
                        break;
                    }
                    idx++;
                    ch = cur;
                    cnt = 1;
                }
            }
            if (flag && idx == charList.size() - 1 && ch == charList.get(idx) && (cnt == countList.get(idx) || cnt < countList.get(idx) && countList.get(idx) >= 3)) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ExpressiveWords ew = new ExpressiveWords();
        System.out.println(ew.expressiveWords("heeellooo", new String[] {"heeelloooworld"}));
    }
}
