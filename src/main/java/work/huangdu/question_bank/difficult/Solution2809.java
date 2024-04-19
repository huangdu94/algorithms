package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 2809. 使数组和小于等于 x 的最少时间
 * 给你两个长度相等下标从 0 开始的整数数组 nums1 和 nums2 。每一秒，对于所有下标 0 <= i < nums1.length ，nums1[i] 的值都增加 nums2[i] 。操作 完成后 ，你可以进行如下操作：
 * 选择任一满足 0 <= i < nums1.length 的下标 i ，并使 nums1[i] = 0 。
 * 同时给你一个整数 x 。
 * 请你返回使 nums1 中所有元素之和 小于等于 x 所需要的 最少 时间，如果无法实现，那么返回 -1 。
 * 示例 1：
 * 输入：nums1 = [1,2,3], nums2 = [1,2,3], x = 4
 * 输出：3
 * 解释：
 * 第 1 秒，我们对 i = 0 进行操作，得到 nums1 = [0,2+2,3+3] = [0,4,6] 。
 * 第 2 秒，我们对 i = 1 进行操作，得到 nums1 = [0+1,0,6+3] = [1,0,9] 。
 * 第 3 秒，我们对 i = 2 进行操作，得到 nums1 = [1+1,0+2,0] = [2,2,0] 。
 * 现在 nums1 的和为 4 。不存在更少次数的操作，所以我们返回 3 。
 * 示例 2：
 * 输入：nums1 = [1,2,3], nums2 = [3,3,3], x = 4
 * 输出：-1
 * 解释：不管如何操作，nums1 的和总是会超过 x 。
 * 提示：
 *
 * 1 <= nums1.length <= 10^3
 * 1 <= nums1[i] <= 10^3
 * 0 <= nums2[i] <= 10^3
 * nums1.length == nums2.length
 * 0 <= x <= 10^6
 *
 * @author huangdu
 */
public class Solution2809 {
    /*
        1. 每个i最多操作一次即可，因为如果你操作两次，就说明前面一次操作没有任何意义可以去掉，反而更好
        2. 对于一批需要操作的i，我们应该对其按nums2进行升序排序，nums2更大的后操作，收益最大
        3. 如果一直不操作的话，nums1的和是s1，nums2的和是s2，那第k轮后，和的值就是s1 + s2 * k
     */
    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size(), s1 = 0, s2 = 0;
        List<Integer> idxs = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            s1 += nums1.get(i);
            s2 += nums2.get(i);
            idxs.add(i);
        }
        idxs.sort(Comparator.comparingInt(nums2::get));
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int idx = idxs.get(i - 1), num1 = nums1.get(idx), num2 = nums2.get(idx);
            for (int j = i; j > 0; j--) {
                dp[j] = Math.max(dp[j], dp[j - 1] + num1 + num2 * j);
            }
        }
        for (int k = 0; k <= n; k++) {
            if (s1 + s2 * k - dp[k] <= x) {
                return k;
            }
        }
        return -1;
    }
}
