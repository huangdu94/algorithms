package work.huangdu.exploration.primary_algorithms.other;

/**
 * 268. 丢失的数字
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * 示例 1：
 * 输入：nums = [3,0,1]
 * 输出：2
 * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：2
 * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 3：
 * 输入：nums = [9,6,4,2,3,5,7,0,1]
 * 输出：8
 * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 4：
 * 输入：nums = [0]
 * 输出：1
 * 解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
 * 提示：
 * n == nums.length
 * 1 <= n <= 10^4
 * 0 <= nums[i] <= n
 * nums 中的所有数字都 独一无二
 * 进阶：你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/7/1 23:10
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        if (nums.length == 0) { return 0; }
        int[] sortNums = new int[nums.length + 1];
        for (int n : nums) { sortNums[n] = n; }
        for (int i = 0; i < sortNums.length; i++) {
            if (sortNums[i] != i) { return i; }
        }
        return 0;
    }

    public int missingNumber2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int index = nums[i];
            if (index < 0) {
                index = -index - 1;
            }
            if (index < n) {
                nums[index] = -nums[index] - 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                return i;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        System.out.println(new MissingNumber().missingNumber(new int[] {9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }
}
