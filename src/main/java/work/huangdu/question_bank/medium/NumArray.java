package work.huangdu.question_bank.medium;

import work.huangdu.data_structure.BinaryIndexedTree;

/**
 * 307. 区域和检索 - 数组可修改
 * 给你一个数组 nums ，请你完成两类查询。
 * 其中一类查询要求 更新 数组 nums 下标对应的值
 * 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值 更新 为 val
 * int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 （即，nums[left] + nums[left + 1], ..., nums[right]）
 * 示例 1：
 * 输入：
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * 输出：
 * [null, 9, null, 8]
 * 解释：
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1,2,5]
 * numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * 调用 update 和 sumRange 方法次数不大于 3 * 10^4
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/4/4
 */
public class NumArray {
    static class Solution1 {
        private final BinaryIndexedTree binaryIndexedTree;
        private final int[] nums;

        public Solution1(int[] nums) {
            int n = nums.length;
            this.binaryIndexedTree = new BinaryIndexedTree(n + 1);
            for (int i = 0; i < n; i++) {
                binaryIndexedTree.update(i + 1, nums[i]);
            }
            this.nums = nums;
        }

        public void update(int index, int val) {
            binaryIndexedTree.update(index + 1, val - nums[index]);
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            return binaryIndexedTree.query(left + 1, right + 1);
        }
    }

    private final int size;
    private final int[] block;
    private final int[] nums;

    public NumArray(int[] nums) {
        int n = nums.length;
        this.size = (int)Math.sqrt(n);
        this.block = new int[(n - 1) / size + 1];
        this.nums = nums;
        for (int i = 0; i < n; i++) {
            block[i / size] += nums[i];
        }
    }

    public void update(int index, int val) {
        block[index / size] += val - nums[index];
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        int sum = 0;
        while (left <= right) {
            if (left % size == 0 && left + size - 1 <= right) {
                sum += block[left / size];
                left += size;
            } else {
                sum += nums[left++];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        // -98  49  10  -3
        int[] nums = {41, -53, -11, -58, 94, -18, -80, 10, -98, -3};
        NumArray na = new NumArray(nums);
        System.out.println(na.sumRange(5, 6));
        na.update(1, 77);
        System.out.println(na.sumRange(0, 3));
        na.update(5, 21);
        na.update(0, -45);
        na.update(8, 6);
        System.out.println(na.sumRange(7, 7));
        System.out.println(na.sumRange(9, 9));
        na.update(4, 70);
        na.update(5, 61);
    }
}