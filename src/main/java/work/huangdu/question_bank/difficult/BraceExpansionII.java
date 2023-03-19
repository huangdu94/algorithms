package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 1096. 花括号展开 II
 * 如果你熟悉 Shell 编程，那么一定了解过花括号展开，它可以用来生成任意字符串。
 * 花括号展开的表达式可以看作一个由 花括号、逗号 和 小写英文字母 组成的字符串，定义下面几条语法规则：
 * 如果只给出单一的元素 x，那么表达式表示的字符串就只有 "x"。R(x) = {x}
 * 例如，表达式 "a" 表示字符串 "a"。
 * 而表达式 "w" 就表示字符串 "w"。
 * 当两个或多个表达式并列，以逗号分隔，我们取这些表达式中元素的并集。R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
 * 例如，表达式 "{a,b,c}" 表示字符串 "a","b","c"。
 * 而表达式 "{{a,b},{b,c}}" 也可以表示字符串 "a","b","c"。
 * 要是两个或多个表达式相接，中间没有隔开时，我们从这些表达式中各取一个元素依次连接形成字符串。R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)}
 * 例如，表达式 "{a,b}{c,d}" 表示字符串 "ac","ad","bc","bd"。
 * 表达式之间允许嵌套，单一元素与表达式的连接也是允许的。
 * 例如，表达式 "a{b,c,d}" 表示字符串 "ab","ac","ad"。
 * 例如，表达式 "a{b,c}{d,e}f{g,h}" 可以表示字符串 "abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"。
 * 给出表示基于给定语法规则的表达式 expression，返回它所表示的所有字符串组成的有序列表。
 * 假如你希望以「集合」的概念了解此题，也可以通过点击 “显示英文描述” 获取详情。
 * 示例 1：
 * 输入：expression = "{a,b}{c,{d,e}}"
 * 输出：["ac","ad","ae","bc","bd","be"]
 * 示例 2：
 * 输入：expression = "{{a,z},a{b,c},{ab,z}}"
 * 输出：["a","ab","ac","z"]
 * 解释：输出中 不应 出现重复的组合结果。
 * 提示：
 * 1 <= expression.length <= 60
 * expression[i] 由 '{'，'}'，',' 或小写英文字母组成
 * 给出的表达式 expression 用以表示一组基于题目描述中语法构造的字符串
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/3/18
 */
public class BraceExpansionII {
    public List<String> braceExpansionII(String expression) {
        List<String> ans = new ArrayList<>(compute(expression));
        ans.sort(String::compareTo);
        return ans;
    }

    private Set<String> compute(String expression) {
        String newExpression = removeBracket(expression);
        if (newExpression.length() == 1) {return new HashSet<>(Collections.singletonList(newExpression));}
        List<String> subExpressionList = newExpression.length() == expression.length() ? Collections.singletonList(newExpression) : splitCommon(newExpression);
        Set<String> set = new HashSet<>();
        for (String subExpression : subExpressionList) {
            set.addAll(splitBracket(subExpression));
        }
        return set;
    }

    private String removeBracket(String expression) {
        int n = expression.length();
        if (n == 1) {return expression;}
        int left = 0;
        for (int i = 0; i < n; i++) {
            if (expression.charAt(i) == '{') {
                left++;
            } else if (expression.charAt(i) == '}') {
                left--;
            }
            if (left == 0 && i != n - 1) {return expression;}
        }
        return expression.substring(1, n - 1);
    }

    private List<String> splitCommon(String expression) {
        int n = expression.length(), left = 0, pre = 0;
        List<String> subExpressionList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (expression.charAt(i) == '{') {
                left++;
            } else if (expression.charAt(i) == '}') {
                left--;
            } else if (left == 0 && expression.charAt(i) == ',') {
                subExpressionList.add(expression.substring(pre, i));
                pre = i + 1;
            }
        }
        subExpressionList.add(expression.substring(pre));
        return subExpressionList;
    }

    private Set<String> splitBracket(String expression) {
        int n = expression.length();
        if (n == 1) {return new HashSet<>(Collections.singletonList(expression));}
        int left = 0, pre = 0;
        Set<String> oldSet = null;
        for (int i = 0; i < n; i++) {
            if (expression.charAt(i) == '{') {
                left++;
            } else if (expression.charAt(i) == '}') {
                left--;
            }
            if (left == 0) {
                oldSet = multiply(oldSet, compute(expression.substring(pre, i + 1)));
                pre = i + 1;
            }
        }
        if (pre < n) {oldSet = multiply(oldSet, compute(expression.substring(pre)));}
        return oldSet;
    }

    private Set<String> multiply(Set<String> oldSet, Set<String> addSet) {
        Set<String> newSet = new HashSet<>();
        if (Objects.isNull(oldSet)) {
            newSet.addAll(addSet);
        } else {
            for (String old : oldSet) {
                for (String add : addSet) {
                    newSet.add(old.concat(add));
                }
            }
        }
        return newSet;
    }

    public static void main(String[] args) {
        BraceExpansionII be2 = new BraceExpansionII();
        System.out.println(be2.braceExpansionII("{{a,z},a{b,c},{ab,z}}"));
    }
}
