package work.huangdu.exploration.start_from_scratch.graph_search.baktrack;

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