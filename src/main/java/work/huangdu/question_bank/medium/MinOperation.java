package work.huangdu.question_bank.medium;

/**
 * 1775. 通过最少操作次数使数组的和相等
 * 给你两个长度可能不等的整数数组 nums1 和 nums2 。两个数组中的所有值都在 1 到 6 之间（包含 1 和 6）。
 * 每次操作中，你可以选择 任意 数组中的任意一个整数，将它变成 1 到 6 之间 任意 的值（包含 1 和 6）。
 * 请你返回使 nums1 中所有数的和与 nums2 中所有数的和相等的最少操作次数。如果无法使两个数组的和相等，请返回 -1 。
 * 示例 1：
 * 输入：nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
 * 输出：3
 * 解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
 * - 将 nums2[0] 变为 6 。 nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2] 。
 * - 将 nums1[5] 变为 1 。 nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2] 。
 * - 将 nums1[2] 变为 2 。 nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2] 。
 * 示例 2：
 * 输入：nums1 = [1,1,1,1,1,1,1], nums2 = [6]
 * 输出：-1
 * 解释：没有办法减少 nums1 的和或者增加 nums2 的和使二者相等。
 * 示例 3：
 * 输入：nums1 = [6,6], nums2 = [1]
 * 输出：3
 * 解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
 * - 将 nums1[0] 变为 2 。 nums1 = [2,6], nums2 = [1] 。
 * - 将 nums1[1] 变为 2 。 nums1 = [2,2], nums2 = [1] 。
 * - 将 nums2[0] 变为 4 。 nums1 = [2,2], nums2 = [4] 。
 * 提示：
 * 1 <= nums1.length, nums2.length <= 10^5
 * 1 <= nums1[i], nums2[i] <= 6
 *
 * @author huangdu
 * @version 2022/12/9
 */
public class MinOperation {
    public int minOperations(int[] nums1, int[] nums2) {
        int[] count1 = new int[7], count2 = new int[7];
        int sum1 = 0, sum2 = 0, ans = 0;
        for (int num : nums1) {
            count1[num]++;
            sum1 += num;
        }
        for (int num : nums2) {
            count2[num]++;
            sum2 += num;
        }
        if (sum1 == sum2) {return 0;}
        while (true) {
            if (sum1 < sum2) {
                int min1 = 1;
                while (count1[min1] == 0) {min1++;}
                int max2 = 6;
                while (count2[max2] == 0) {max2--;}
                int diff1 = 6 - min1, diff2 = max2 - 1;
                if (Math.max(diff1, diff2) == 0) {return -1;}
                if (Math.max(diff1, diff2) >= sum2 - sum1) {return ans + 1;}
                if (diff1 >= diff2) {
                    sum1 += diff1;
                    count1[min1]--;
                    count1[6]++;
                } else {
                    sum2 -= diff2;
                    count2[max2]--;
                    count2[1]++;
                }
            } else {
                int min2 = 1;
                while (count2[min2] == 0) {min2++;}
                int max1 = 6;
                while (count1[max1] == 0) {max1--;}
                int diff1 = max1 - 1, diff2 = 6 - min2;
                if (Math.max(diff1, diff2) == 0) {return -1;}
                if (Math.max(diff1, diff2) >= sum1 - sum2) {return ans + 1;}
                if (diff1 >= diff2) {
                    sum1 -= diff1;
                    count1[max1]--;
                    count1[1]++;
                } else {
                    sum2 += diff2;
                    count2[min2]--;
                    count2[6]++;
                }
            }
            ans++;
        }
    }

    public static void main(String[] args) {
        MinOperation mo = new MinOperation();
        int[] nums1 = {6, 6}, nums2 = {1};
        System.out.println(mo.minOperations(nums1, nums2));
    }
}
