package work.huangdu.exploration.start_from_scratch.string.number_transform_string;

/**
 * 640. 求解方程
 * 求解一个给定的方程，将x以字符串"x=#value"的形式返回。该方程仅包含'+'，'-'操作，变量 x 和其对应系数。
 * 如果方程没有解，请返回“No solution”。
 * 如果方程有无限解，则返回“Infinite solutions”。
 * 如果方程中只有一个解，要保证返回值 x 是一个整数。
 * 示例 1：
 * 输入: "x+5-3+x=6+x-2"
 * 输出: "x=2"
 * 示例 2:
 * 输入: "x=x"
 * 输出: "Infinite solutions"
 * 示例 3:
 * 输入: "2x=x"
 * 输出: "x=0"
 * 示例 4:
 * 输入: "2x+3x-6x=x+2"
 * 输出: "x=-1"
 * 示例 5:
 * 输入: "x=x+2"
 * 输出: "No solution"
 *
 * @author huangdu
 * @version 2020/9/27 12:57
 */
public class SolveEquation {
    class Solution {
        private static final String NO_SOLUTION = "No solution";
        private static final String INFINITE_SOLUTIONS = "Infinite solutions";
        private static final String X_EQUAL = "x=";

        public String solveEquation(String equation) {
            int equalSignIndex = equation.indexOf('=');
            int[] left = handle(equation.substring(0, equalSignIndex)), right = handle(equation.substring(equalSignIndex + 1));
            if (left[0] == right[0]) {return left[1] == right[1] ? INFINITE_SOLUTIONS : NO_SOLUTION;}
            return X_EQUAL.concat(Integer.toString((right[1] - left[1]) / (left[0] - right[0])));
        }

        private int[] handle(String expression) {
            int xCoefficient = 0, constant = 0, symbol = 1, current = 0;
            boolean flag = false;
            for (int i = 0, n = expression.length(); i < n; i++) {
                char ch = expression.charAt(i);
                if ('0' <= ch && ch <= '9') {
                    current = current * 10 + (ch - '0');
                    flag = true;
                    continue;
                }
                if (ch == 'x') {
                    xCoefficient += symbol * (flag ? current : 1);
                } else {
                    constant += symbol * current;
                    symbol = ch == '-' ? -1 : 1;
                }
                current = 0;
                flag = false;
            }
            constant += symbol * current;
            return new int[] {xCoefficient, constant};
        }
    }

    public String solveEquation(String equation) {
        int equalSignIndex = equation.indexOf('=');
        int[] left = compute(equation.substring(0, equalSignIndex));
        int[] right = compute(equation.substring(equalSignIndex + 1));
        int x = left[0] - right[0];
        int num = right[1] - left[1];
        if (x == 0) {
            if (num == 0) {return "Infinite solutions";}
            return "No solution";
        }
        return "x=".concat(Integer.toString(num / x));
    }

    private int[] compute(String expression) {
        int xSum = 0, numSum = 0, num = 0;
        char sign = '+';
        boolean numFlag = false;
        for (int i = 0, len = expression.length(); i < len; i++) {
            char c = expression.charAt(i);
            if (c <= '9' && c >= '0') {
                num = num * 10 + (c - '0');
                numFlag = true;
            }
            if (c == 'x') {
                if (!numFlag) {num = 1;}
                switch (sign) {
                    case '+':
                        xSum += num;
                        break;
                    case '-':
                        xSum -= num;
                        break;
                }
                if (i < len - 1) {
                    sign = expression.charAt(++i);
                    num = 0;
                    numFlag = false;
                }
            } else if (c > '9' || c < '0' || i == len - 1) {
                switch (sign) {
                    case '+':
                        numSum += num;
                        break;
                    case '-':
                        numSum += -num;
                        break;
                }
                sign = c;
                num = 0;
                numFlag = false;
            }
        }
        return new int[] {xSum, numSum};
    }

    public static void main(String[] args) {
        SolveEquation solve = new SolveEquation();
        String equation = "x+5-3+x=6+x-2";
        System.out.println(solve.solveEquation(equation));
    }
}
