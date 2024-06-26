package work.huangdu.exploration.start_from_scratch.binary.number_bit_operation;

/**
 * 400. 第N个数字
 * 在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 个数字。
 * 注意:
 * n 是正数且在32位整数范围内 ( n < 2^31)。
 * 示例 1:
 * 输入:
 * 3
 * 输出:
 * 3
 * 示例 2:
 * 输入:
 * 11
 * 输出:
 * 0
 * 说明:
 * 第11个数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是0，它是10的一部分。
 *
 * @author huangdu
 * @version 2020/10/19 15:45
 */
public class FindNthDigit {
    public static void main(String[] args) {
        FindNthDigit findNthDigit = new FindNthDigit();
        System.out.println(findNthDigit.findNthDigit(1000000000));
    }

    // 1位的数9个
    // 2位的数90个
    // 3位的数900个...
    // TODO 代码可优化的地方很多
    public int findNthDigit(int n) {
        int digits = 0;
        long count = 0, base = 9;
        while (count <= n) {
            count += base * ++digits;
            base *= 10;
        }
        count -= base / 10 * digits;
        int remain = (int)(n - count);
        int number = (int)Math.pow(10, digits - 1) + (remain - 1) / digits;
        int bit = remain % digits;
        if (remain == 0) {
            number--;
            digits--;
        }
        return number / (int)Math.pow(10, bit == 0 ? 0 : digits - bit) % 10;
    }

    public int findNthDigit2(int n) {
        int count = 9, bits = 1;
        while (count != 900000000 && n > count * bits) {
            n -= (count * bits);
            count *= 10;
            bits++;
        }
        int targetNum = count / 9 + --n / bits, targetBit = n % bits + 1;
        while (targetBit < bits) {
            targetNum /= 10;
            targetBit++;
        }
        return targetNum % 10;
    }
}
