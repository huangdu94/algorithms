package work.huangdu.exploration.primary_algorithms.array;

/**
 * 26.删除排序数组中的重复项
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 示例 1:
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 你可以想象内部操作如下:
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 *
 * @author huangdu
 * @version 2020/7/26 15:02
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] && index == 0) {
                index = i;
            }
            if (nums[i] != nums[i - 1] && index != 0) {
                nums[index++] = nums[i];
            }
        }
        return index == 0 ? nums.length : index;
    }

    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int num : nums) {
            if (i == 0 || num != nums[i - 1]) {
                nums[i++] = num;
            }
        }
        return i;
    }

    public int removeDuplicates3(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return n;
        }
        int point = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[point]) {
                nums[++point] = nums[i];
            }
        }
        return point + 1;
    }

    public int removeDuplicates4(int[] nums) {
        int i = 1, j = 1, n = nums.length;
        while (j < n) {
            if (nums[j] != nums[j - 1]) {
                nums[i++] = nums[j];
            }
            j++;
        }
        return i;
    }
}
