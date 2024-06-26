package work.huangdu.exploration.start_from_scratch.binary.number_bit_operation;

import java.util.Arrays;

/**
 * 670. 最大交换
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 * 示例 1 :
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 * 示例 2 :
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 * 注意:
 * 给定数字的范围是 [0, 10^8]
 *
 * @author huangdu
 * @version 2020/10/18 11:18
 */
public class MaximumSwap {
    public int maximumSwap(int num) {
        if (num < 10) {return num;}
        int len = 0, _num = num, ans = 0;
        while (_num > 0) {
            _num /= 10;
            len++;
        }
        _num = num;
        int[] digits = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            digits[i] = _num % 10;
            _num /= 10;
        }
        for (int i = 0; i < len; i++) {
            int first = digits[i], maxJ = i;
            for (int j = i + 1; j < len; j++) {
                if (first < digits[j] && digits[j] >= digits[maxJ]) {
                    maxJ = j;
                }
            }
            if (i != maxJ && first != digits[maxJ]) {
                int temp = digits[i];
                digits[i] = digits[maxJ];
                digits[maxJ] = temp;
                break;
            }
        }
        for (int i = 0; i < len; i++) {
            ans = ans * 10 + digits[i];
        }
        return ans;
    }

    public int maximumSwap2(int num) {
        if (num < 10) {return num;}
        int[] numBits = getNumBits(num);
        int[] sortedNumBits = Arrays.copyOf(numBits, numBits.length);
        Arrays.sort(sortedNumBits);
        int j = numBits.length - 1;
        while (j > 0 && numBits[j] == sortedNumBits[j]) {
            j--;
        }
        if (j == 0) {return num;}
        int i = 0;
        while (numBits[i] != sortedNumBits[j]) {
            i++;
        }
        swap(numBits, i, j);
        return getNum(numBits);
    }

    public int maximumSwap3(int num) {
        int digits = 0, idx = 0, n = num;
        while (n > 0) {
            n /= 10;
            digits++;
        }
        int[] numArray = new int[digits];
        n = num;
        while (n > 0) {
            numArray[idx++] = n % 10;
            n /= 10;
        }
        int[] sortNumArray = Arrays.copyOf(numArray, digits);
        Arrays.sort(sortNumArray);
        int needSwapIdx = digits;
        for (int i = digits - 1; i >= 0; i--) {
            if (numArray[i] != sortNumArray[i]) {
                needSwapIdx = i;
                break;
            }
        }
        if (needSwapIdx == digits) {return num;}
        for (int i = 0; i < digits; i++) {
            if (numArray[i] == sortNumArray[needSwapIdx]) {
                numArray[i] = numArray[needSwapIdx];
                numArray[needSwapIdx] = sortNumArray[needSwapIdx];
                break;
            }
        }
        int ans = 0;
        for (int i = digits - 1; i >= 0; i--) {
            ans = ans * 10 + numArray[i];
        }
        return ans;
    }

    private int[] getNumBits(int num) {
        int temp = num, i = 0, n = 0;
        while (temp != 0) {
            temp /= 10;
            n++;
        }
        int[] numBits = new int[n];
        temp = num;
        while (temp != 0) {
            numBits[i++] = temp % 10;
            temp /= 10;
        }
        return numBits;
    }

    private void swap(int[] numBits, int i, int j) {
        int temp = numBits[j];
        numBits[j] = numBits[i];
        numBits[i] = temp;
    }

    private int getNum(int[] numBits) {
        int res = 0;
        for (int k = numBits.length - 1; k >= 0; k--) {
            res = res * 10 + numBits[k];
        }
        return res;
    }
}
