package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 241. 为运算表达式设计优先级
 * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 10^4 。
 * 示例 1：
 * 输入：expression = "2-1-1"
 * 输出：[0,2]
 * 解释：
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * 示例 2：
 * 输入：expression = "2*3-4*5"
 * 输出：[-34,-14,-10,-10,10]
 * 解释：
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * 提示：
 * 1 <= expression.length <= 20
 * expression 由数字和算符 '+'、'-' 和 '*' 组成。
 * 输入表达式中的所有整数值在范围 [0, 99]
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/7/12
 */
public class DiffWaysToCompute {

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<Integer> diffWaysToCompute(String expression) {
        List elementList = new ArrayList();
        int number = 0;
        for (int i = 0, n = expression.length(); i < n; i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                number = number * 10 + (ch - '0');
            } else {
                elementList.add(number);
                elementList.add(ch);
                number = 0;
            }
        }
        elementList.add(number);
        int n = elementList.size();
        // 实际上只能用到四分之一，只是为了下标处理方便
        List<Integer>[][] dp = new List[n][n];
        for (int i = 0; i < n; i += 2) {
            for (int j = 0; j < n; j += 2) {
                dp[i][j] = new ArrayList();
            }
        }
        for (int i = 0; i < n; i += 2) {
            dp[i][i].add((Integer)elementList.get(i));
        }
        for (int len = 2; len < n; len += 2) {
            for (int left = 0; left + len < n; left += 2) {
                int right = left + len;
                for (int mid = left; mid < right; mid += 2) {
                    char op = (char)elementList.get(mid + 1);
                    for (int value1 : dp[left][mid]) {
                        for (int value2 : dp[mid + 2][right]) {
                            dp[left][right].add(compute(value1, op, value2));
                        }
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

    private int compute(int left, char operator, int right) {
        switch (operator) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            default:
                throw new RuntimeException("operator is not support.");
        }
    }

    public static void main(String[] args) {
        DiffWaysToCompute dwtc = new DiffWaysToCompute();
        String expression = "2*3-4*5";
        System.out.println(dwtc.diffWaysToCompute(expression));
    }

    class failSolution {
        // even idx: number
        // odd idx: operator
        private List elementList;
        private List<Integer> ans;

        public List<Integer> diffWaysToCompute(String expression) {
            init();
            parse(expression);
            backtrack();
            return ans;
        }

        private void init() {
            this.elementList = new ArrayList();
            this.ans = new ArrayList<>();
        }

        private void parse(String expression) {
            int n = expression.length(), number = 0;
            for (int i = 0; i < n; i++) {
                char ch = expression.charAt(i);
                if (Character.isDigit(ch)) {
                    number = number * 10 + (ch - '0');
                } else {
                    elementList.add(number);
                    elementList.add(ch);
                    number = 0;
                }
            }
            elementList.add(number);
        }

        private void backtrack() {
            int size = elementList.size();
            if (size == 1) {ans.addAll(elementList);}
            for (int idx = 1; idx < size; idx += 2) {
                char operator = (char)elementList.remove(idx);
                int right = (int)elementList.remove(idx);
                int oldLeft = (int)elementList.set(idx - 1, compute((int)elementList.get(idx - 1), operator, right));
                backtrack();
                elementList.set(idx - 1, oldLeft);
                elementList.add(idx, right);
                elementList.add(idx, operator);
            }
        }

        private int compute(int left, char operator, int right) {
            switch (operator) {
                case '+':
                    return left + right;
                case '-':
                    return left - right;
                case '*':
                    return left * right;
                default:
                    throw new RuntimeException("operator is not support.");
            }
        }
    }
}
