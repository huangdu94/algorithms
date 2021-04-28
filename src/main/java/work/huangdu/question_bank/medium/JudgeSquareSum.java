package work.huangdu.question_bank.medium;

/**
 * 633. 平方数之和
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 * 示例 1：
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 * 示例 2：
 * 输入：c = 3
 * 输出：false
 * 示例 3：
 * 输入：c = 4
 * 输出：true
 * 示例 4：
 * 输入：c = 2
 * 输出：true
 * 示例 5：
 * 输入：c = 1
 * 输出：true
 * 提示：
 * 0 <= c <= 2^31 - 1
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/4/28
 */
public class JudgeSquareSum {
    public boolean judgeSquareSum(int c) {
        for (long i = 0; i * i <= c; i++) {
            double result = Math.sqrt(c - i * i);
            if ((result - (int)result) < 1e-6) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        JudgeSquareSum jss = new JudgeSquareSum();
        System.out.println(jss.judgeSquareSum(0));
    }
}
