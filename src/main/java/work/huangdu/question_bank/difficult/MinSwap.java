package work.huangdu.question_bank.difficult;

/**
 * 801. 使序列递增的最小交换次数
 * 我们有两个长度相等且不为空的整型数组 nums1 和 nums2 。在一次操作中，我们可以交换 nums1[i] 和 nums2[i]的元素。
 * 例如，如果 nums1 = [1,2,3,8] ， nums2 =[5,6,7,4] ，你可以交换 i = 3 处的元素，得到 nums1 =[1,2,3,4] 和 nums2 =[5,6,7,8] 。
 * 返回 使 nums1 和 nums2 严格递增 所需操作的最小次数 。
 * 数组 arr 严格递增 且  arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1] 。
 * 注意：
 * 用例保证可以实现操作。
 * 示例 1:
 * 输入: nums1 = [1,3,5,4], nums2 = [1,2,3,7]
 * 输出: 1
 * 解释:
 * 交换 A[3] 和 B[3] 后，两个数组如下:
 * A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
 * 两个数组均为严格递增的。
 * 示例 2:
 * 输入: nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9]
 * 输出: 1
 * 提示:
 * 2 <= nums1.length <= 10^5
 * nums2.length == nums1.length
 * 0 <= nums1[i], nums2[i] <= 2 * 10^5
 *
 * @author huangdu
 * @version 2022/10/10
 */
public class MinSwap {
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int dp0 = 0, dp1 = 1;
        for (int i = 1; i < n; i++) {
            int pre1 = nums1[i - 1], cur1 = nums1[i], pre2 = nums2[i - 1], cur2 = nums2[i];
            boolean condition1 = pre1 < cur1 && pre2 < cur2, condition2 = pre2 < cur1 && pre1 < cur2;
            int newDp0, newDp1;
            if (condition1 && condition2) {
                newDp0 = Math.min(dp0, dp1);
                newDp1 = Math.min(dp0, dp1) + 1;
            } else if (condition1) {
                newDp0 = dp0;
                newDp1 = dp1 + 1;
            } else {
                newDp0 = dp1;
                newDp1 = dp0 + 1;
            }
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return Math.min(dp0, dp1);
    }

    public int minSwap2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            int pre1 = nums1[i - 1], cur1 = nums1[i], pre2 = nums2[i - 1], cur2 = nums2[i];
            boolean condition1 = pre1 < cur1 && pre2 < cur2, condition2 = pre2 < cur1 && pre1 < cur2;
            if (condition1 && condition2) {
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + 1;
            } else if (condition1) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + 1;
            } else {
                dp[i][0] = dp[i - 1][1];
                dp[i][1] = dp[i - 1][0] + 1;
            }
        }
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }
}
