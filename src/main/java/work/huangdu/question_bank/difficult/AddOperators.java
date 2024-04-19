package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 282. 给表达式添加运算符
 * 给定一个仅包含数字 0-9 的字符串 num 和一个目标值整数 target ，在 num 的数字之间添加 二元 运算符（不是一元）+、- 或 * ，返回所有能够得到目标值的表达式。
 * 示例 1:
 * 输入: num = "123", target = 6
 * 输出: ["1+2+3", "1*2*3"]
 * 示例 2:
 * 输入: num = "232", target = 8
 * 输出: ["2*3+2", "2+3*2"]
 * 示例 3:
 * 输入: num = "105", target = 5
 * 输出: ["1*0+5","10-5"]
 * 示例 4:
 * 输入: num = "00", target = 0
 * 输出: ["0+0", "0-0", "0*0"]
 * 示例 5:
 * 输入: num = "3456237490", target = 9191
 * 输出: []
 * 提示：
 * 1 <= num.length <= 10
 * num 仅含数字
 * -2^31 <= target <= 2^31 - 1
 *
 * @author huangdu
 * @version 2021/10/18
 */
public class AddOperators {
    private int target;
    private int n;
    private String num;
    private char[] chars;
    private List<String> ans;

    public List<String> addOperators(String num, int target) {
        this.target = target;
        this.n = num.length();
        this.num = num;
        this.chars = num.toCharArray();
        this.ans = new ArrayList<>();
        backtrack(0, new StringBuilder());
        return ans;
    }

    private void backtrack(int cur, StringBuilder sb) {
        if (cur == n) {
            if (calculate(sb.toString()) == target) {
                ans.add(sb.toString());
            }
            return;
        }
        char c = chars[cur];
        if (c == '0') {
            sb.append(c);
            addOperator(cur + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        } else {
            for (int next = cur + 1; next <= n; next++) {
                sb.append(num, cur, next);
                addOperator(next, sb);
                sb.delete(sb.length() - (next - cur), sb.length());
            }
        }
    }

    private void addOperator(int next, StringBuilder sb) {
        if (next == n) {
            backtrack(next, sb);
        } else {
            for (char operator : new char[] {'+', '-', '*'}) {
                sb.append(operator);
                backtrack(next, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        AddOperators ao = new AddOperators();
        System.out.println(ao.addOperators("2147483648", -2147483648));
    }

    private static long calculate(String s) {
        Stack<Long> numStack = new Stack<>();
        int n = s.length();
        char sign = '+';
        long num = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (isDigit(c)) {num = num * 10 + (c - '0');}
            if ((!isDigit(c) && c != ' ') || i == n - 1) {
                switch (sign) {
                    case '+':
                        numStack.push(num);
                        break;
                    case '-':
                        numStack.push(-num);
                        break;
                    case '*':
                        numStack.push(numStack.pop() * num);
                        break;
                    case '/':
                        numStack.push(numStack.pop() / num);
                        break;
                }
                sign = c;
                num = 0;
            }
        }
        long result = 0;
        while (!numStack.empty()) {
            result += numStack.pop();
        }
        return result;
    }

    private static boolean isDigit(char c) {
        return c <= '9' && c >= '0';
    }
}

class AddOperators2 {
    private int n;
    private String num;
    private int target;
    private List<String> ans;

    public List<String> addOperators(String num, int target) {
        this.n = num.length();
        this.num = num;
        this.target = target;
        this.ans = new ArrayList<>();
        StringBuffer expr = new StringBuffer();
        backtrack(expr, 0, 0, 0);
        return ans;
    }

    public void backtrack(StringBuffer expr, int i, long res, long mul) {
        if (i == n) {
            if (res == target) {
                ans.add(expr.toString());
            }
            return;
        }
        int signIndex = expr.length();
        if (i > 0) {
            expr.append(0); // 占位，下面填充符号
        }
        long val = 0;
        // 枚举截取的数字长度（取多少位），注意数字可以是单个 0 但不能有前导零
        for (int j = i; j < n && (j == i || num.charAt(i) != '0'); ++j) {
            val = val * 10 + num.charAt(j) - '0';
            expr.append(num.charAt(j));
            if (i == 0) { // 表达式开头不能添加符号
                backtrack(expr, j + 1, val, val);
            } else { // 枚举符号
                expr.setCharAt(signIndex, '+');
                backtrack(expr, j + 1, res + val, val);
                expr.setCharAt(signIndex, '-');
                backtrack(expr, j + 1, res - val, -val);
                expr.setCharAt(signIndex, '*');
                backtrack(expr, j + 1, res - mul + mul * val, mul * val);
            }
        }
        expr.setLength(signIndex);
    }
}