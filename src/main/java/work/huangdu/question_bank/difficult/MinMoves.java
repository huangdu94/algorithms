package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.List;

/**
 * 1703. 得到连续 K 个 1 的最少相邻交换次数
 * 给你一个整数数组 nums 和一个整数 k 。 nums 仅包含 0 和 1 。每一次移动，你可以选择 相邻 两个数字并将它们交换。
 * 请你返回使 nums 中包含 k 个 连续 1 的 最少 交换次数。
 * 示例 1：
 * 输入：nums = [1,0,0,1,0,1], k = 2
 * 输出：1
 * 解释：在第一次操作时，nums 可以变成 [1,0,0,0,1,1] 得到连续两个 1 。
 * 示例 2：
 * 输入：nums = [1,0,0,0,0,0,1,1], k = 3
 * 输出：5
 * 解释：通过 5 次操作，最左边的 1 可以移到右边直到 nums 变为 [0,0,0,0,0,1,1,1] 。
 * 示例 3：
 * 输入：nums = [1,1,0,1], k = 2
 * 输出：0
 * 解释：nums 已经有连续 2 个 1 了。
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums[i] 要么是 0 ，要么是 1 。
 * 1 <= k <= sum(nums)
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2022/12/20
 */
public class MinMoves {
    public int minMoves(int[] nums, int k) {
        List<Integer> zeroCountList = initZeroCountList(nums);
        int move = computeFirstMove(k, zeroCountList), n = zeroCountList.size();
        if (k - 1 == n) {
            return move;
        }
        int ans = move;
        int[] zeroCountPrefixSum = initZeroCountPrefixSum(n, zeroCountList);
        for (int left = 1; left + k - 2 < n; left++) {
            move = move - (zeroCountPrefixSum[k / 2 + left - 1] - zeroCountPrefixSum[left - 1]) + (zeroCountPrefixSum[k + left - 1] - zeroCountPrefixSum[k / 2 + left - 1 + k % 2]);
            ans = Math.min(ans, move);
        }
        return ans;
    }

    private List<Integer> initZeroCountList(int[] nums) {
        List<Integer> zeroCountList = new ArrayList<>();
        int n = nums.length, i = 0;
        while (i < n && nums[i] == 0) {
            i++;
        }
        while (i < n) {
            int pre = ++i;
            while (i < n && nums[i] == 0) {
                i++;
            }
            if (i < n) {
                zeroCountList.add(i - pre);
            }
        }
        return zeroCountList;
    }

    private int computeFirstMove(int k, List<Integer> zeroCountList) {
        int move = 0;
        for (int left = 0; left < k - 1; left++) {
            int moveCount;
            if (left < (k - 1) / 2) {
                moveCount = left + 1;
            } else if (left == (k - 1) / 2) {
                moveCount = left + (k - 1) % 2;
            } else {
                moveCount = (k - 1) - left;
            }
            move += zeroCountList.get(left) * moveCount;
        }
        return move;
    }

    private int[] initZeroCountPrefixSum(int n, List<Integer> zeroCountList) {
        int[] zeroCountPrefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            zeroCountPrefixSum[i + 1] = zeroCountPrefixSum[i] + zeroCountList.get(i);
        }
        return zeroCountPrefixSum;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 1, 0, 0, 1, 0, 0, 0};
        int k = 3;
        MinMoves mm = new MinMoves();
        System.out.println(mm.minMoves(nums, k));
    }
}
