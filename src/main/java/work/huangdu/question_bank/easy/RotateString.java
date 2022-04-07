package work.huangdu.question_bank.easy;

/**
 * 796. 旋转字符串
 * 给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
 * s 的 旋转操作 就是将 s 最左边的字符移动到最右边。
 * 例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。
 * 示例 1:
 * 输入: s = "abcde", goal = "cdeab"
 * 输出: true
 * 示例 2:
 * 输入: s = "abcde", goal = "abced"
 * 输出: false
 * 提示:
 * 1 <= s.length, goal.length <= 100
 * s 和 goal 由小写英文字母组成
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/4/7
 */
public class RotateString {
    // 有O(n)的算法
    public boolean rotateString(String s, String goal) {
        int n = s.length();
        if (n != goal.length()) {return false;}
        for (int i = 0; i < n; i++) {
            if (goal.startsWith(s.substring(i)) && goal.endsWith(s.substring(0, i))) {
                return true;
            }
        }
        return false;
    }
}
