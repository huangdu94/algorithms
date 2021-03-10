package work.huangdu.question_bank.difficult;

/**
 * 224. 基本计算器
 * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 * 示例 1：
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 * 提示：
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/3/10
 */
public class Calculate {
    private int n;
    private int i;
    private char[] chars;

    public int calculate(String s) {
        this.n = s.length();
        this.i = 0;
        this.chars = s.toCharArray();
        return calculate();
    }

    private int calculate() {
        int sum = 0, num = 0;
        char op = '+';
        while (i < n) {
            char c = chars[i++];
            if (c != ' ') {
                if (c == ')') { return compute(sum, num, op);}
                if (c == '(') {
                    sum = compute(sum, calculate(), op);
                } else if (Character.isDigit(c)) {
                    num = num * 10 + (c - '0');
                }
                if (c == '+' || c == '-') {
                    sum = compute(sum, num, op);
                    num = 0;
                    op = c;
                }
            }
        }
        return compute(sum, num, op);
    }

    private int compute(int sum, int num, char op) {
        switch (op) {
            case '+':
                return sum + num;
            case '-':
                return sum - num;
        }
        return 0;
    }

    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        System.out.println(calculate.calculate(" 2-1 + 2 "));
    }
}
