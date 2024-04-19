package work.huangdu.question_bank.easy;

/**
 * 2129. 将标题首字母大写
 * 给你一个字符串 title ，它由单个空格连接一个或多个单词组成，每个单词都只包含英文字母。请你按以下规则将每个单词的首字母 大写 ：
 * 如果单词的长度为 1 或者 2 ，所有字母变成小写。
 * 否则，将单词首字母大写，剩余字母变成小写。
 * 请你返回 大写后 的 title 。
 * 示例 1：
 * 输入：title = "capiTalIze tHe titLe"
 * 输出："Capitalize The Title"
 * 解释：
 * 由于所有单词的长度都至少为 3 ，将每个单词首字母大写，剩余字母变为小写。
 * 示例 2：
 * 输入：title = "First leTTeR of EACH Word"
 * 输出："First Letter of Each Word"
 * 解释：
 * 单词 "of" 长度为 2 ，所以它保持完全小写。
 * 其他单词长度都至少为 3 ，所以其他单词首字母大写，剩余字母小写。
 * 示例 3：
 * 输入：title = "i lOve leetcode"
 * 输出："i Love Leetcode"
 * 解释：
 * 单词 "i" 长度为 1 ，所以它保留小写。
 * 其他单词长度都至少为 3 ，所以其他单词首字母大写，剩余字母小写。
 * 提示：
 * 1 <= title.length <= 100
 * title 由单个空格隔开的单词组成，且不含有任何前导或后缀空格。
 * 每个单词由大写和小写英文字母组成，且都是 非空 的。
 *
 * @author huangdu
 */
public class CapitalizeTitle {
    public String capitalizeTitle(String title) {
        int n = title.length();
        StringBuilder ans = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            char ch = title.charAt(i);
            if (ch == ' ') {
                ans.append(ch);
            } else if ((i == 0 || title.charAt(i - 1) == ' ') && (i < n - 2 && title.charAt(i + 1) != ' ' && title.charAt(i + 2) != ' ')) {
                ans.append(Character.toUpperCase(ch));
            } else {
                ans.append(Character.toLowerCase(ch));
            }
        }
        return ans.toString();
    }

    public String capitalizeTitle2(String title) {
        int n = title.length();
        StringBuilder ans = new StringBuilder(n);
        for (int i = 0, start = 0; i < n; i++) {
            if (i == n - 1 || title.charAt(i) == ' ') {
                int end = i == n - 1 ? i + 1 : i;
                if (end - start > 2) {
                    ans.append(Character.toUpperCase(title.charAt(start++)));
                }
                for (int j = start; j < end; j++) {
                    ans.append(Character.toLowerCase(title.charAt(j)));
                }
                if (i < n - 1) {ans.append(' ');}
                start = end + 1;
            }
        }
        return ans.toString();
    }
}
