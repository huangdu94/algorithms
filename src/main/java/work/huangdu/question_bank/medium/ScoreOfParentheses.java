package work.huangdu.question_bank.medium;

/**
 * 856. 括号的分数
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 * 示例 1：
 * 输入： "()"
 * 输出： 1
 * 示例 2：
 * 输入： "(())"
 * 输出： 2
 * 示例 3：
 * 输入： "()()"
 * 输出： 2
 * 示例 4：
 * 输入： "(()(()))"
 * 输出： 6
 * 提示：
 * S 是平衡括号字符串，且只含有 ( 和 ) 。
 * 2 <= S.length <= 50
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/10/9
 */
public class ScoreOfParentheses {

    public int scoreOfParentheses(String s) {
        return split(s);
    }

    private int split(String s) {
        int n = s.length(), left = 0, pre = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                left--;
            }
            if (left == 0) {
                sum += multiply(s.substring(pre, i + 1));
                pre = i + 1;
            }
        }
        return sum;
    }

    private int multiply(String s) {
        if (s.length() == 2) {return 1;}
        return 2 * split(s.substring(1, s.length() - 1));
    }

    public static void main(String[] args) {
        ScoreOfParentheses sop = new ScoreOfParentheses();
        System.out.println(sop.scoreOfParentheses("(((())))"));
    }
}
