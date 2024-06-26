package work.huangdu.exploration.start_from_scratch.array.statistics;

/**
 * 645. 错误的集合
 * 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 * 示例 1:
 * 输入: nums = [1,2,2,4]
 * 输出: [2,3]
 * 注意:
 * 给定数组的长度范围是 [2, 10000]。
 * 给定的数组是无序的。
 *
 * @author huangdu
 * @version 2020/9/14 15:15
 */
public class FindErrorNums {
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] *= -1;
            } else {
                res[0] = index + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                res[1] = i + 1;
            }
        }
        return res;
    }

    public int[] findErrorNums2(int[] nums) {
        int n = nums.length;
        int[] result = new int[2];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            num = num > 0 ? num : -num;
            if (nums[num - 1] < 0) {
                result[0] = num;
            } else {
                nums[num - 1] *= -1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                result[1] = i + 1;
                break;
            }
        }
        return result;
    }
}
