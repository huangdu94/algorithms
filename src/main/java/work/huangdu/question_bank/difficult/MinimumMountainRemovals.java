package work.huangdu.question_bank.difficult;

/**
 * 1671. 得到山形数组的最少删除次数
 * 我们定义 arr 是 山形数组 当且仅当它满足：
 * arr.length >= 3
 * 存在某个下标 i （从 0 开始） 满足 0 < i < arr.length - 1 且：
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * 给你整数数组 nums ，请你返回将 nums 变成 山形状数组 的 最少 删除次数。
 * 示例 1：
 * 输入：nums = [1,3,1]
 * 输出：0
 * 解释：数组本身就是山形数组，所以我们不需要删除任何元素。
 * 示例 2：
 * 输入：nums = [2,1,1,5,6,2,3,1]
 * 输出：3
 * 解释：一种方法是将下标为 0，1 和 5 的元素删除，剩余元素为 [1,5,6,3,1] ，是山形数组。
 * 提示：
 * 3 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^9
 * 题目保证 nums 删除一些元素后一定能得到山形数组。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class MinimumMountainRemovals {
    // LIS 问题
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length, len = 0;
        int[] left = new int[n], right = new int[n], lis = new int[n];
        lis[0] = nums[0];
        for (int i = 1; i < n; i++) {len = lis(nums, i, len, lis, left);}
        // 从右到左再来一次
        len = 0;
        lis[0] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {len = lis(nums, i, len, lis, right);}
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            if (left[i] != 0 && right[i] != 0) {
                ans = Math.max(ans, left[i] + right[i]);
            }
        }
        return n - ans - 1;
    }

    private int lis(int[] nums, int i, int len, int[] lis, int[] dp) {
        int num = nums[i];
        if (num >= lis[len]) {
            if (num > lis[len]) {lis[++len] = num;}
            dp[i] = len;
        } else {
            // 二分查找找到num>lis[i-1],num<=lis[i]那这次这个值就是i
            int l = -1, r = len + 1;
            while (l + 1 < r) {
                int m = l + (r - l >> 1);
                if (num > lis[m]) {
                    l = m;
                } else {
                    r = m;
                }
            }
            lis[r] = num;
            dp[i] = r;
        }
        return len;
    }

    public static void main(String[] args) {
        MinimumMountainRemovals mmr = new MinimumMountainRemovals();
        System.out.println(mmr.minimumMountainRemovals(new int[] {100, 92, 89, 77, 74, 66, 64, 66, 64}));
    }
}
