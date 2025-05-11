package work.huangdu.question_bank.easy;

/**
 * 1047. 删除字符串中的所有相邻重复项
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * 示例：
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 * 提示：
 * 1 <= S.length <= 20000
 * S 仅由小写英文字母组成。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2021/3/9
 */
public class RemoveDuplicates {
    public String removeDuplicates2(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int left = i, right = i + 1;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            if (right - left - 1 > 0) {
                return removeDuplicates2(s.substring(0, left + 1).concat(s.substring(right)));
            }
        }
        return s;
    }

    // 栈
    public String removeDuplicates(String s) {
        if (s.length() == 1) { return s; }
        int top = 0;
        char[] stack = s.toCharArray();
        for (char c : stack) {
            if (top != 0 && stack[top - 1] == c) {
                top--;
            } else {
                stack[top++] = c;
            }
        }
        return new String(stack, 0, top);
    }
}
