package work.huangdu.question_bank.medium;

/**
 * 227. 基本计算器 II
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 整数除法仅保留整数部分。
 * 示例 1：
 * 输入：s = "3+2*2"
 * 输出：7
 * 示例 2：
 * 输入：s = " 3/2 "
 * 输出：1
 * 示例 3：
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 * 提示：
 * 1 <= s.length <= 3 * 105
 * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 * s 表示一个 有效表达式
 * 表达式中的所有整数都是非负整数，且在范围 [0, 23^1 - 1] 内
 * 题目数据保证答案是一个 32-bit 整数
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/3/11
 */
public class Calculate2 {
    public int calculate(String s) {
        int n = s.length(), top = 0, num = 0;
        int[] stack = new int[n / 2 + 1];
        char op = '+';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            }
            if (i == n - 1 || c == '+' || c == '-' || c == '*' || c == '/') {
                switch (op) {
                    case '+':
                        stack[top++] = num;
                        break;
                    case '-':
                        stack[top++] = -num;
                        break;
                    case '*':
                        stack[top - 1] *= num;
                        break;
                    case '/':
                        stack[top - 1] /= num;
                }
                num = 0;
                op = c;
            }
        }
        for (int i = 0; i < top; i++) {num += stack[i];}
        return num;
    }
}
