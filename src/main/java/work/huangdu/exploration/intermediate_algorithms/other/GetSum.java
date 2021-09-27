package work.huangdu.exploration.intermediate_algorithms.other;

/**
 * 两整数之和
 * 不使用运算符 + 和 - ，计算两整数 a 、b 之和。
 * 示例 1:
 * 输入: a = 1, b = 2
 * 输出: 3
 * 示例 2:
 * 输入: a = -2, b = 3
 * 输出: 1
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/7/22 0:22
 */
public class GetSum {
    /*public int getSum(int a, int b) {
        return a + b;
    }*/
    public int getSum(int a, int b) {
        int shift = 0, add = 0, result = 0;
        while (shift < 32 && (a != 0 || b != 0 || add != 0)) {
            int bita = a & 1, bitb = b & 1;
            if (bita == 0 && bitb == 0) {
                if (add != 0) {
                    result |= (1 << shift);
                    add = 0;
                }
            } else if (bita == 1 && bitb == 1) {
                if (add != 0) {
                    result |= (1 << shift);
                }
                add = 1;
            } else {
                if (add == 0) {
                    result |= (1 << shift);
                }
            }
            a >>= 1;
            b >>= 1;
            shift++;
        }
        return result;
    }

    public int getSum2(int a, int b) {
        int sum = 0;
        int mask = 1;
        // 是否进位
        boolean carry = false;
        while (mask != 0) {
            int aBit = a & mask;
            int bBit = b & mask;
            if (aBit == 0 && bBit == 0) {
                if (carry) {
                    sum |= mask;
                    carry = false;
                }
            } else if (aBit == 0 || bBit == 0) {
                if (!carry)
                    sum |= mask;
            } else {
                if (carry)
                    sum |= mask;
                carry = true;
            }
            mask <<= 1;
        }
        return sum;
    }
}
