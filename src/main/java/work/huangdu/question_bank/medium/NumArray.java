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
 * @author huangdu
 * @version 2022/4/4
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

    /*
    线段树 segmentTree 是一个二叉树，每个结点保存数组 nums 在区间 [s,e] 的最小值、最大值或者总和等信息。线段树可以用树也可以用数组（堆式存储）来实现。对于数组实现，假设根结点的下标为 0，如果一个结点在数组的下标为 node，那么它的左子结点下标为 node×2+1，右子结点下标为 node×2+2。
    建树 build 函数
    我们在结点 node 保存数组 nums 在区间 [s,e] 的总和。
    s=e 时，结点 node 是叶子结点，它保存的值等于 nums[s]。
    s<e 时，结点 node 的左子结点保存区间 [s,[(s+e)/2]] 的总和，右子结点保存区间 [[(s+e)/2]+1,e] 的总和，那么结点 node 保存的值等于它的两个子结点保存的值之和。
    假设 nums 的大小为 n，我们规定根结点 node=0 保存区间 [0,n−1] 的总和，然后自下而上递归地建树。
    单点修改 change 函数
    当我们要修改 nums[index] 的值时，我们找到对应区间 [index,index] 的叶子结点，val，并自下而上递归地更新父结点的值。
    范围求和 range 函数
    给定区间 [left,right] 时，我们将区间 [left,right] 拆成多个结点对应的区间。
    如果结点 node 对应的区间与 [left,right] 相同，可以直接返回该结点的值，即当前区间和。
    如果结点 node 对应的区间与 [left,right] 不同，设左子结点对应的区间的右端点为 m，那么将区间 [left,right] 沿点 m 拆成两个区间，分别计算左子结点和右子结点。
    我们从根结点开始递归地拆分区间 [left,right]。
     */
    static class NumArray3 {
        private final int n;
        private final int[] tree;

        public NumArray3(int[] nums) {
            this.n = nums.length;
            tree = new int[n << 2];
            for (int i = 0; i < n; i++) {
                update(i, nums[i]);
            }
        }

        public void update(int index, int val) {
            update(0, n - 1, index, 1, val);
        }

        public int sumRange(int left, int right) {
            return query(0, n - 1, left, right, 1);
        }

        private void update(int l, int r, int index, int idx, int val) {
            if (l == r) {
                tree[idx] = val;
                return;
            }
            int mid = l + ((r - l) >> 1), leftIdx = idx << 1, rightIdx = (idx << 1) + 1;
            if (index <= mid) {
                update(l, mid, index, leftIdx, val);
            } else {
                update(mid + 1, r, index, rightIdx, val);
            }
            tree[idx] = tree[leftIdx] + tree[rightIdx];
        }

        private int query(int l, int r, int start, int end, int idx) {
            if (start <= l && r <= end) {
                return tree[idx];
            }
            int mid = l + ((r - l) >> 1), sum = 0;
            if (start <= mid) {
                sum += query(l, mid, start, end, idx << 1);
            }
            if (end > mid) {
                sum += query(mid + 1, r, start, end, (idx << 1) + 1);
            }
            return sum;
        }

    }
}