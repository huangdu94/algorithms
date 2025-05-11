package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 2864. 最大二进制奇数
 * 给你一个 二进制 字符串 s ，其中至少包含一个 '1' 。
 * 你必须按某种方式 重新排列 字符串中的位，使得到的二进制数字是可以由该组合生成的 最大二进制奇数 。
 * 以字符串形式，表示并返回可以由给定组合生成的最大二进制奇数。
 * 注意 返回的结果字符串 可以 含前导零。
 * 示例 1：
 * 输入：s = "010"
 * 输出："001"
 * 解释：因为字符串 s 中仅有一个 '1' ，其必须出现在最后一位上。所以答案是 "001" 。
 * 示例 2：
 * 输入：s = "0101"
 * 输出："1001"
 * 解释：其中一个 '1' 必须出现在最后一位上。而由剩下的数字可以生产的最大数字是 "100" 。所以答案是 "1001" 。
 * 提示：
 * 1 <= s.length <= 100
 * s 仅由 '0' 和 '1' 组成
 * s 中至少包含一个 '1'
 *
 * @author huangdu
 */
public class MaximumOddBinaryNumber {
    public String maximumOddBinaryNumber(String s) {
        int n = s.length(), oneCount = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (chars[i] == '1') {oneCount++;}
        }
        Arrays.fill(chars, '0');
        chars[n - 1] = '1';
        int i = 0;
        while (oneCount > 1) {
            chars[i++] = '1';
            oneCount--;
        }
        return new String(chars);
    }
}
