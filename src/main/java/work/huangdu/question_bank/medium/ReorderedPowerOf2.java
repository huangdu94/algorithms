package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 869. 重新排序得到 2 的幂
 * 给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 * 示例 1：
 * 输入：1
 * 输出：true
 * 示例 2：
 * 输入：10
 * 输出：false
 * 示例 3：
 * 输入：16
 * 输出：true
 * 示例 4：
 * 输入：24
 * 输出：false
 * 示例 5：
 * 输入：46
 * 输出：true
 * 提示：
 * 1 <= N <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/10/28
 */
public class ReorderedPowerOf2 {
    private final static Set<String> SET = new HashSet<>();

    static {
        for (int i = 1; i > 0; i <<= 1) {
            SET.add(convert(i));
        }
    }

    public boolean reorderedPowerOf2(int n) {
        return SET.contains(convert(n));
    }

    private static String convert(int num) {
        char[] chars = Integer.toString(num).toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
