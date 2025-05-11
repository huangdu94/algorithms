package work.huangdu.question_bank.difficult;

/**
 * 2569. 更新数组后处理求和查询
 * 给你两个下标从 0 开始的数组 nums1 和 nums2 ，和一个二维数组 queries 表示一些操作。总共有 3 种类型的操作：
 * 操作类型 1 为 queries[i] = [1, l, r] 。你需要将 nums1 从下标 l 到下标 r 的所有 0 反转成 1 或将 1 反转成 0 。l 和 r 下标都从 0 开始。
 * 操作类型 2 为 queries[i] = [2, p, 0] 。对于 0 <= i < n 中的所有下标，令 nums2[i] = nums2[i] + nums1[i] * p 。
 * 操作类型 3 为 queries[i] = [3, 0, 0] 。求 nums2 中所有元素的和。
 * 请你返回一个数组，包含所有第三种操作类型的答案。
 * 示例 1：
 * 输入：nums1 = [1,0,1], nums2 = [0,0,0], queries = [[1,1,1],[2,1,0],[3,0,0]]
 * 输出：[3]
 * 解释：第一个操作后 nums1 变为 [1,1,1] 。第二个操作后，nums2 变成 [1,1,1] ，所以第三个操作的答案为 3 。所以返回 [3] 。
 * 示例 2：
 * 输入：nums1 = [1], nums2 = [5], queries = [[2,0,0],[3,0,0]]
 * 输出：[5]
 * 解释：第一个操作后，nums2 保持不变为 [5] ，所以第二个操作的答案是 5 。所以返回 [5] 。
 * 提示：
 * 1 <= nums1.length,nums2.length <= 10^5
 * nums1.length = nums2.length
 * 1 <= queries.length <= 10^5
 * queries[i].length = 3
 * 0 <= l <= r <= nums1.length - 1
 * 0 <= p <= 10^6
 * 0 <= nums1[i] <= 1
 * 0 <= nums2[i] <= 10^9
 *
 * @author huangdu
 * @version 2023/7/26
 */
public class HandleQuery {
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length, m = 0, i = 0;
        this.node = new int[n * 4];
        this.lazy = new boolean[n * 4];
        build(nums1, 0, 0, n - 1);
        long sum = 0L;
        for (int num : nums2) {sum += num;}
        for (int[] query : queries) {if (query[0] == 3) {++m;}}
        long[] ans = new long[m];
        for (int[] query : queries) {
            if (query[0] == 1) {
                update(0, 0, n - 1, query[1], query[2]);
            } else if (query[0] == 2) {
                sum += (long)query[1] * node[0];
            } else {
                ans[i++] = sum;
            }
        }
        return ans;
    }

    private int[] node;
    private boolean[] lazy;

    /**
     * 初始化线段树   o,l,r=0,0,n-1
     */
    private void build(int[] num, int idx, int left, int right) {
        if (left == right) {
            node[idx] = num[left];
            return;
        }
        int mid = left + (right - left >> 1);
        build(num, idx * 2 + 1, left, mid);
        build(num, idx * 2 + 2, mid + 1, right);
        pushUp(idx);
    }

    /**
     * 反转区间 [L,R]   o,l,r=0,0,n-1
     */
    private void update(int idx, int left, int right, int L, int R) {
        if (L <= left && right <= R) {
            pushDown(idx, left, right);
            return;
        }
        int mid = left + (right - left >> 1);
        if (lazy[idx]) {
            pushDown(idx * 2 + 1, left, mid);
            pushDown(idx * 2 + 2, mid + 1, right);
            lazy[idx] = false;
        }
        if (mid >= L) {update(idx * 2 + 1, left, mid, L, R);}
        if (mid < R) {update(idx * 2 + 2, mid + 1, right, L, R);}
        pushUp(idx);
    }

    /**
     * 维护区间1的个数
     */
    private void pushUp(int idx) {
        node[idx] = node[idx * 2 + 1] + node[idx * 2 + 2];
    }

    /**
     * 执行区间反转
     */
    private void pushDown(int idx, int left, int right) {
        node[idx] = right - left + 1 - node[idx];
        lazy[idx] = !lazy[idx];
    }
}
