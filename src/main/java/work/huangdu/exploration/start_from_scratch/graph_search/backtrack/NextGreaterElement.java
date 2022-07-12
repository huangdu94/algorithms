package work.huangdu.exploration.start_from_scratch.graph_search.backtrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 556. 下一个更大元素 III
 * 给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。
 * 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
 * 示例 1：
 * 输入：n = 12
 * 输出：21
 * 示例 2：
 * 输入：n = 21
 * 输出：-1
 * 提示：
 * 1 <= n <= 2^31 - 1
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/4/2
 */
public class NextGreaterElement {
    public int nextGreaterElement2(int n) {
        // 低位在前
        List<Integer> numList = new ArrayList<>(9);
        while (n > 0) {
            numList.add(n % 10);
            n /= 10;
        }
        // 从低位开始找第一个低位比高位大的地方，如果找完了都没有直接返回-1
        for (int i = 1; i < numList.size(); i++) {
            if (numList.get(i) < numList.get(i - 1)) {
                int cur = numList.get(i);
                for (int j = 0; j < i; j++) {
                    if (numList.get(j) > cur) {
                        Collections.swap(numList, i, j);
                        int left = 0, right = i - 1;
                        while (left < right) {
                            Collections.swap(numList, left++, right--);
                        }
                        long ans = 0;
                        for (int k = numList.size() - 1; k >= 0; k--) {
                            ans = ans * 10 + numList.get(k);
                            if (ans > Integer.MAX_VALUE) {return -1;}
                        }
                        return (int)ans;
                    }
                }
            }
        }
        return -1;
    }

    private int ans;
    private int n;
    private int size;
    private int[] nums;

    private static final int BOUND = Integer.MAX_VALUE / 10;

    public int nextGreaterElement(int n) {
        if (n < 10) { return -1; }
        this.ans = -1;
        this.n = n;
        this.size = 0;
        // 1. 计算位数
        while (n != 0) {
            size++;
            n /= 10;
        }
        this.nums = new int[size];
        // 2. 拆分数字
        int i = 0;
        n = this.n;
        while (n != 0) {
            nums[i++] = n % 10;
            n /= 10;
        }
        // 3. 回溯得到结果
        backtrack(0, 0);
        return ans;
    }

    private void backtrack(int number, int start) {
        if (start == size) {
            if (number > n && (ans == -1 || number < ans)) {
                ans = number;
            }
            return;
        }
        if (number > BOUND) {return;}
        number *= 10;
        for (int i = start; i < size; i++) {
            swap(start, i);
            backtrack(number + nums[start], start + 1);
            swap(start, i);
        }
    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int n = 101;
        System.out.println(new NextGreaterElement().nextGreaterElement(n));
    }
}
