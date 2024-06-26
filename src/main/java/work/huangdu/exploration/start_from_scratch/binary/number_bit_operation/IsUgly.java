package work.huangdu.exploration.start_from_scratch.binary.number_bit_operation;

/**
 * 263. 丑数
 * 编写一个程序判断给定的数是否为丑数。
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 * 示例 1:
 * 输入: 6
 * 输出: true
 * 解释: 6 = 2 × 3
 * 示例 2:
 * 输入: 8
 * 输出: true
 * 解释: 8 = 2 × 2 × 2
 * 示例 3:
 * 输入: 14
 * 输出: false
 * 解释: 14 不是丑数，因为它包含了另外一个质因数 7。
 * 说明：
 * 1 是丑数。
 * 输入不会超过 32 位有符号整数的范围: [−2^31,  2^31 − 1]。
 *
 * @author huangdu
 * @version 2020/8/16 0:40
 */
public class IsUgly {
    public boolean isUgly(int num) {
        if (num <= 0) { return false; }
        while ((num & 1) == 0) { num >>= 1; }
        while (num % 3 == 0) { num /= 3; }
        while (num % 5 == 0) { num /= 5; }
        return num == 1;
    }

    public static void main(String[] args) {
        System.out.println(new IsUgly().isUgly(Integer.MIN_VALUE));
    }
}
