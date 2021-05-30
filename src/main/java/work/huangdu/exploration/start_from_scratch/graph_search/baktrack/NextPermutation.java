package work.huangdu.exploration.start_from_scratch.graph_search.baktrack;

import java.util.Arrays;

/**
 * 31. 下一个排列
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/11/10 12:22
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int n = nums.length, i = n - 1;
        while (i - 1 >= 0 && nums[i - 1] >= nums[i]) {
            i--;
        }
        int preIndex = i - 1;
        if (preIndex >= 0) {
            while (i + 1 < n && nums[preIndex] < nums[i + 1]) {
                i++;
            }
            swap(nums, preIndex, i);
        }
        Arrays.sort(nums, preIndex + 1, n);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1};
        NextPermutation np = new NextPermutation();
        np.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation2(int[] nums) {
        int n = nums.length, start = n - 1;
        // 1. 从后往前找到第一个降序位置（从前往后看是升序）
        while (start != 0 && nums[start - 1] >= nums[start]) {
            start--;
        }
        // 2. 如果该位置是0，则翻转nums
        if (start == 0) {
            reverse(nums, 0, n - 1);
        } else {
            // 3. 从后往前找第一个大于start-1位置的数的位置
            int needChange = nums[start - 1], end = n - 1;
            while (nums[end] <= needChange) {
                end--;
            }
            // 4. 交换needChange和end位置的数
            swap(nums, start - 1, end);
            // 5. 翻转从start到n-1之间的数
            reverse(nums, start, n - 1);
        }
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }
}
