package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 902. 最大为 N 的数字组合
 * 给定一个按 非递减顺序 排列的数字数组 digits 。你可以用任意次数 digits[i] 来写的数字。例如，如果 digits = ['1','3','5']，我们可以写数字，如 '13', '551', 和 '1351315'。
 * 返回 可以生成的小于或等于给定整数 n 的正整数的个数 。
 * 示例 1：
 * 输入：digits = ["1","3","5","7"], n = 100
 * 输出：20
 * 解释：
 * 可写出的 20 个数字是：
 * 1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
 * 示例 2：
 * 输入：digits = ["1","4","9"], n = 1000000000
 * 输出：29523
 * 解释：
 * 我们可以写 3 个一位数字，9 个两位数字，27 个三位数字，
 * 81 个四位数字，243 个五位数字，729 个六位数字，
 * 2187 个七位数字，6561 个八位数字和 19683 个九位数字。
 * 总共，可以使用D中的数字写出 29523 个整数。
 * 示例 3:
 * 输入：digits = ["7"], n = 8
 * 输出：1
 * 提示：
 * 1 <= digits.length <= 9
 * digits[i].length == 1
 * digits[i] 是从 '1' 到 '9' 的数
 * digits 中的所有值都 不同
 * digits 按 非递减顺序 排列
 * 1 <= n <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/10/21
 */
public class AtMostNGivenDigitSet {
    public int atMostNGivenDigitSet(String[] digits, int n) {
        // 1. 看看n是几位数，并存下n的每一位数字
        List<Integer> numList = new ArrayList<>(10);
        List<Integer> digitList = new ArrayList<>(digits.length);
        while (n > 0) {
            numList.add(n % 10);
            n /= 10;
        }
        Collections.reverse(numList);
        for (String digit : digits) {
            digitList.add(digit.charAt(0) - '0');
        }
        // 2. 小于n位数的话，随便生成
        int numBit = numList.size(), digitListSize = digitList.size(), ans = 0;
        for (int i = 1; i < numBit; i++) {
            ans += Math.pow(digitListSize, i);
        }
        // 3. 等于n位的话需要注意：
        // 3.1 从高位开始，如果高位小于n该位数字，则后面随便生成
        // 3.2 如果高位等于n该位数字，则后面再继续讨论
        for (int i = 0; i < numBit; i++) {
            // 找到有多少个数字小于n当前数字
            int cnt = 0;
            boolean contain = false;
            for (int digit : digitList) {
                if (digit >= numList.get(i)) {
                    contain = digit == numList.get(i);
                    break;
                }
                cnt++;
            }
            if (cnt > 0) {
                ans += cnt * Math.pow(digitListSize, numBit - i - 1);
            }
            if (i == numBit - 1 && contain) {ans++;}
            if (!contain) {break;}
        }
        return ans;
    }

    public static void main(String[] args) {
        AtMostNGivenDigitSet amngds = new AtMostNGivenDigitSet();
        System.out.println(amngds.atMostNGivenDigitSet(new String[] {"1", "4", "9"}, 1000000000));
        System.out.println(amngds.atMostNGivenDigitSet(new String[] {"1", "3", "5", "7"}, 100));
        System.out.println(amngds.atMostNGivenDigitSet(new String[] {"3", "4", "8"}, 4));
        System.out.println(amngds.atMostNGivenDigitSet(new String[] {"1", "2", "3", "4", "5", "6", "7", "9"}, 1));
        System.out.println(amngds.atMostNGivenDigitSet(new String[] {"5", "7", "8"}, 59));
    }
}
