package work.huangdu.exploration.start_from_scratch.array.change_move;

/**
 * 453. 最小移动次数使数组元素相等
 * 给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动将会使 n - 1 个元素增加 1。
 * 示例:
 * 输入:
 * [1,2,3]
 * 输出:
 * 3
 * 解释:
 * 只需要3次移动（注意每次移动会增加两个元素的值）：
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 *
 * @author huangdu
 * @version 2020/9/15 12:31
 */
public class MinMoves {
    public int minMoves(int[] nums) {
        long sum = 0;
        int min = Integer.MAX_VALUE;
        for (int n : nums) {
            sum += n;
            if (n < min) {
                min = n;
            }
        }
        return (int) (sum - nums.length * min);
    }
}
