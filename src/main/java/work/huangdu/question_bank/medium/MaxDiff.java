package work.huangdu.question_bank.medium;

import java.util.LinkedList;

/**
 * 1432. 改变一个整数能得到的最大差值
 * MaxDiff
 *
 * @author huangdu
 * @version 2025/6/15
 */
public class MaxDiff {
    /**
     * 最小值：
     * 1. 第一个数字如果不是1，直接改成1
     * 2. 第一个数字如果是1，就找除它以外第一个不是0的数字改成0
     * 最大值：
     * 1. 找到第一个不是9的数字改成9
     */
    public int maxDiff(int num) {
        LinkedList<Integer> nList = new LinkedList<>();
        while (num > 0) {
            nList.addFirst(num % 10);
            num /= 10;
        }
        int max = 0, min = 0, maxOld = -1, maxNew = 0, minOld = -1;
        if (nList.peek() != 1) {

        }
        boolean first = true;
        while (!nList.isEmpty()) {
            int n = nList.poll();
            if (minOld == -1 && n < 9) {
                minOld = n;
            }
            if (maxOld == -1) {
                if (first && n != 1) {
                    maxOld = n;
                    maxNew = 1;
                }
                if (!first && n > 1) {
                    maxOld = n;
                }
            }
            max = max * 10 + (n == minOld ? 9 : n);
            min = min * 10 + (n == maxOld ? maxNew : n);
            first = false;
        }
        return max - min;
    }
}
