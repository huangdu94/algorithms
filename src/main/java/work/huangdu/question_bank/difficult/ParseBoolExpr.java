package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.List;

/**
 * 1106. 解析布尔表达式
 * 给你一个以字符串形式表述的 布尔表达式（boolean） expression，返回该式的运算结果。
 * 有效的表达式需遵循以下约定：
 * "t"，运算结果为 True
 * "f"，运算结果为 False
 * "!(expr)"，运算过程为对内部表达式 expr 进行逻辑 非的运算（NOT）
 * "&(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 与的运算（AND）
 * "|(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 或的运算（OR）
 * 示例 1：
 * 输入：expression = "!(f)"
 * 输出：true
 * 示例 2：
 * 输入：expression = "|(f,t)"
 * 输出：true
 * 示例 3：
 * 输入：expression = "&(t,f)"
 * 输出：false
 * 示例 4：
 * 输入：expression = "|(&(t,f,t),!(t))"
 * 输出：false
 * 提示：
 * 1 <= expression.length <= 20000
 * expression[i] 由 {'(', ')', '&', '|', '!', 't', 'f', ','} 中的字符组成。
 * expression 是以上述形式给出的有效表达式，表示一个布尔值。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/11/8
 */
public class ParseBoolExpr {
    public boolean parseBoolExpr(String expression) {
        if (expression.length() == 1) {return "t".equals(expression);}
        char headChar = expression.charAt(0);
        expression = expression.substring(2, expression.length() - 1);
        if (headChar == '!') {return !parseBoolExpr(expression);}
        List<String> subExpressionList = new ArrayList<>();
        int left = 0, pre = 0;
        for (int i = 0, n = expression.length(); i < n; i++) {
            if (expression.charAt(i) == '(') {left++;}
            if (expression.charAt(i) == ')') {left--;}
            if (expression.charAt(i) == ',' && left == 0) {
                subExpressionList.add(expression.substring(pre, i));
                pre = i + 1;
            }
        }
        subExpressionList.add(expression.substring(pre));
        if (headChar == '&') {
            boolean result = true;
            for (String subExpression : subExpressionList) {
                result &= parseBoolExpr(subExpression);
            }
            return result;
        }
        boolean result = false;
        for (String subExpression : subExpressionList) {
            result |= parseBoolExpr(subExpression);
        }
        return result;
    }

    public static void main(String[] args) {
        ParseBoolExpr pbe = new ParseBoolExpr();
        System.out.println(pbe.parseBoolExpr("&(|(f))"));
    }
}
