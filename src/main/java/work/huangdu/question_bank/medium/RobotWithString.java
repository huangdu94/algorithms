package work.huangdu.question_bank.medium;

/**
 * 2434. 使用机器人打印字典序最小的字符串
 * RobotWithString
 *
 * @author huangdu
 * @version 2025/6/6
 */
public class RobotWithString {
    /**
     * 思路：这其实就是一个压栈，出栈的过程
     * 1. 遍历一遍s，找出每个index后面最小的字母（不包含当前位置）
     * 2. 再遍历一遍s，如果当前字母小于等于之后最小的字母直接出栈（重复此过程，直到栈顶字母大于之后最小的字母或栈为空）
     * 3. 如果当前字母大于之后最小的字母压栈
     * 4. 字符串遍历完后，栈中元素依次出栈
     */
    public String robotWithString(String s) {
        int n = s.length(), top = 0, pos = 0;
        // 复用ans，先用来存后面的最小字母，然后用来存答案
        char[] ans = new char[n], stack = new char[n];
        ans[n - 1] = 'z';
        for (int i = n - 2; i >= 0; i--) {
            char ch = s.charAt(i + 1);
            ans[i] = ch > ans[i + 1] ? ans[i + 1] : ch;
        }
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i), min = ans[i];
            if (ch <= min) {
                ans[pos++] = ch;
                while (top > 0 && stack[top - 1] <= min) {
                    ans[pos++] = stack[--top];
                }
            } else {
                stack[top++] = ch;
            }
        }
        while (top > 0) {
            ans[pos++] = stack[--top];
        }
        return new String(ans);
    }
}
