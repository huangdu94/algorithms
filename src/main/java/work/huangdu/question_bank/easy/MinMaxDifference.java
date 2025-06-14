package work.huangdu.question_bank.easy;

import java.util.LinkedList;

/**
 * 2566. 替换一个数字后的最大差值
 * MinMaxDifference
 *
 * @author huangdu
 * @version 2025/6/14
 */
public class MinMaxDifference {
    /**
     * 1. min 将第一个数字替换成0
     * 2. max 将第一个不是9的数字替换成9
     */
    public int minMaxDifference(int num) {
        LinkedList<Integer> nList = new LinkedList<>();
        while (num > 0) {
            nList.addFirst(num % 10);
            num /= 10;
        }
        int max = 0, min = 0, big = nList.getFirst(), small = -1;
        while (!nList.isEmpty()) {
            int n = nList.poll();
            if (small == -1 && n != 9) {
                small = n;
            }
            max = max * 10 + (n == small ? 9 : n);
            min = min * 10 + (n == big ? 0 : n);
        }
        return max - min;
    }
}
