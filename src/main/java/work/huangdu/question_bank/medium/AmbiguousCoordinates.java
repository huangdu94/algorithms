package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 816. 模糊坐标
 * 我们有一些二维坐标，如 "(1, 3)" 或 "(2, 0.5)"，然后我们移除所有逗号，小数点和空格，得到一个字符串S。返回所有可能的原始字符串到一个列表中。
 * 原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。
 * 最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。
 * 示例 1:
 * 输入: "(123)"
 * 输出: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
 * 示例 2:
 * 输入: "(00011)"
 * 输出:  ["(0.001, 1)", "(0, 0.011)"]
 * 解释:
 * 0.0, 00, 0001 或 00.01 是不被允许的。
 * 示例 3:
 * 输入: "(0123)"
 * 输出: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
 * 示例 4:
 * 输入: "(100)"
 * 输出: [(10, 0)]
 * 解释:
 * 1.0 是不被允许的。
 * 提示:
 * 4 <= S.length <= 12.
 * S[0] = "(", S[S.length - 1] = ")", 且字符串 S 中的其他元素都是数字。
 *
 * @author huangdu
 * @version 2022/11/7
 */
public class AmbiguousCoordinates {
    public List<String> ambiguousCoordinates(String s) {
        List<String> ans = new ArrayList<>();
        String numStr = s.substring(1, s.length() - 1);
        int n = numStr.length();
        for (int i = 1; i < n; i++) {
            String left = numStr.substring(0, i), right = numStr.substring(i);
            if (validOverall(left) && validOverall(right)) {
                List<String> xList = new ArrayList<>(), yList = new ArrayList<>();
                if (validPart(left)) {xList.add(left);}
                for (int j = 1, m = left.length(); j < m; j++) {
                    String leftLeft = left.substring(0, j), leftRight = left.substring(j);
                    if (validPart(leftLeft, leftRight)) {
                        xList.add(leftLeft.concat(".").concat(leftRight));
                    }
                }
                if (validPart(right)) {yList.add(right);}
                for (int j = 1, m = right.length(); j < m; j++) {
                    String rightLeft = right.substring(0, j), rightRight = right.substring(j);
                    if (validPart(rightLeft, rightRight)) {
                        yList.add(rightLeft.concat(".").concat(rightRight));
                    }
                }
                for (String x : xList) {
                    for (String y : yList) {
                        ans.add("(".concat(x).concat(", ").concat(y).concat(")"));
                    }
                }
            }
        }
        return ans;
    }

    private boolean validOverall(String num) {
        if (num.length() == 1) {return true;}
        return num.charAt(0) != '0' || num.charAt(num.length() - 1) != '0';
    }

    private boolean validPart(String num) {
        if (num.length() == 1) {return true;}
        return num.charAt(0) != '0';
    }

    private boolean validPart(String left, String right) {
        if (left.charAt(0) == '0' && left.length() > 1) {return false;}
        return right.charAt(right.length() - 1) != '0';
    }

    public static void main(String[] args) {

    }
}
