package work.huangdu.exploration.primary_algorithms.sort_search;

/**
 * 88. 合并两个有序数组
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * 示例 1：
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 示例 2：
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * 提示：
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -10^9 <= nums1[i], nums2[i] <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/6/23 15:05
 */
public class Merge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m + n - 1, p1 = m - 1, p2 = n - 1;
        while (p2 >= 0) {
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[i--] = nums1[p1--];
            } else {
                nums1[i--] = nums2[p2--];
            }
        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        if (n <= 0) { return; }
        int i = -1;
        int j = 0;
        while (++i < m + j) {
            if (nums1[i] > nums2[j]) {
                /*for (int k = i; k < m + j; k++) {
                    nums1[k + 1] = nums1[k];
                }*/
                System.arraycopy(nums1, i, nums1, i + 1, m - i + j);
                nums1[i] = nums2[j++];
                if (j >= n) { return; }
            }
        }
        while (j < n) { nums1[i++] = nums2[j++]; }
    }

    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        int j = m + n - 1, i1 = m - 1, i2 = n - 1;
        while (j >= 0) {
            if (i1 < 0) {
                nums1[j] = nums2[i2--];
            } else if (i2 < 0) {
                nums1[j] = nums1[i1--];
            } else if (nums1[i1] >= nums2[i2]) {
                nums1[j] = nums1[i1--];
            } else {
                nums1[j] = nums2[i2--];
            }
            j--;
        }
    }
}
