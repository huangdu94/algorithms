package work.huangdu.question_bank.difficult;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1707. 与数组中元素的最大异或值
 * 给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
 * 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR xi) ，其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
 * 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
 * 示例 1：
 * 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
 * 输出：[3,3,7]
 * 解释：
 * 1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
 * 2) 1 XOR 2 = 3.
 * 3) 5 XOR 2 = 7.
 * 示例 2：
 * 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
 * 输出：[15,-1,5]
 * 提示：
 * 1 <= nums.length, queries.length <= 105
 * queries[i].length == 2
 * 0 <= nums[j], xi, mi <= 10^9
 *
 * @author huangdu
 * @version 2021/5/23
 */
public class MaximizeXor {
    // 保险起见可以设置为L，但根据题目范围，此值29即可
    private static final int L = 29;

    public int[] maximizeXor(int[] nums, int[][] queries) {
        int len = nums.length, n = queries.length, cursor = 0;
        // 1. nums排序
        Arrays.sort(nums);
        // 2. queries处理增加一个位置用于存储index，以便在排序后保留原位置信息
        int[][] queryWithIndexArr = new int[n][3];
        for (int i = 0; i < n; i++) {
            int[] queryWithIndex = queryWithIndexArr[i], query = queries[i];
            queryWithIndex[0] = query[0];
            queryWithIndex[1] = query[1];
            queryWithIndex[2] = i;
        }
        // 3. queries排序
        Arrays.sort(queryWithIndexArr, Comparator.comparingInt(o -> o[1]));
        int[] result = new int[n];
        TrieNode root = new TrieNode();
        // 4. 使用字典树处理得到结果
        for (int[] queryWithIndex : queryWithIndexArr) {
            int m = queryWithIndex[1];
            // 4.1. 特殊情况处理：如果nums中的所有元素都大于mi，最终答案就是-1
            if (m < nums[0]) {
                result[queryWithIndex[2]] = -1;
                continue;
            }
            // 4.2. 把当前小于等于m的数都加入字典树
            while (cursor < len && nums[cursor] <= m) {
                addNum(root, nums[cursor++]);
            }
            // 4.3. 根据当前字典树求x与它们的最大异或值
            result[queryWithIndex[2]] = getXorMax(root, queryWithIndex[0]);
        }
        return result;
    }

    private static class TrieNode {
        private TrieNode zero;
        private TrieNode one;
    }

    private void addNum(TrieNode cur, int num) {
        for (int i = L; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (bit == 0) {
                if (cur.zero == null) {
                    cur.zero = new TrieNode();
                }
                cur = cur.zero;
            } else {
                if (cur.one == null) {
                    cur.one = new TrieNode();
                }
                cur = cur.one;
            }
        }
    }

    private int getXorMax(TrieNode cur, int x) {
        int max = 0;
        for (int i = L; i >= 0; i--) {
            max <<= 1;
            int bit = (x >> i) & 1;
            if (bit == 0) {
                if (cur.one != null) {
                    max |= 1;
                    cur = cur.one;
                } else {
                    cur = cur.zero;
                }
            } else {
                if (cur.zero != null) {
                    max |= 1;
                    cur = cur.zero;
                } else {
                    cur = cur.one;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {5, 2, 4, 6, 6, 3};
        int[][] queries = {{12, 4}, {8, 1}, {6, 3}};
        MaximizeXor mx = new MaximizeXor();
        System.out.println(Arrays.toString(mx.maximizeXor(nums, queries)));
    }
}

class MaximizeXor2 {
    // 保险起见可以设置为L，但根据题目范围，此值29即可
    private static final int L = 29;

    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = queries.length;
        int[] result = new int[n];
        TrieNode root = new TrieNode();
        // 1. nums中数加入字典树
        for (int num : nums) {
            addNum(root, num);
        }
        // 2. 求结果
        for (int i = 0; i < n; i++) {
            int[] query = queries[i];
            result[i] = getXorMax(root, query[0], query[1]);
        }
        return result;
    }

    private static class TrieNode {
        private final TrieNode[] next = new TrieNode[2];
        private int min = Integer.MAX_VALUE;
    }

    private void addNum(TrieNode cur, int num) {
        if (cur.min > num) {
            cur.min = num;
        }
        for (int i = L; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (cur.next[bit] == null) {
                cur.next[bit] = new TrieNode();
            }
            cur = cur.next[bit];
            if (cur.min > num) {
                cur.min = num;
            }
        }
    }

    private int getXorMax(TrieNode cur, int x, int m) {
        if (cur.min > m) { return -1; }
        int max = 0;
        for (int i = L; i >= 0; i--) {
            max <<= 1;
            int bit = (x >> i) & 1;
            if (cur.next[bit ^ 1] != null && cur.next[bit ^ 1].min <= m) {
                max |= 1;
                bit ^= 1;
            }
            cur = cur.next[bit];
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4};
        int[][] queries = {{3, 1}, {1, 3}, {5, 6}};
        MaximizeXor2 mx = new MaximizeXor2();
        System.out.println(Arrays.toString(mx.maximizeXor(nums, queries)));
    }
}

class MaximizeXor3 {
    // 保险起见可以设置为L，但根据题目范围，此值29即可
    private static final int L = 29;

    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = queries.length;
        int[] result = new int[n];
        TrieNode root = new TrieNode();
        // 1. nums中数加入字典树
        for (int num : nums) {
            addNum(root, num);
        }
        // 2. 求结果
        for (int i = 0; i < n; i++) {
            int[] query = queries[i];
            result[i] = getXorMax(root, query[0], query[1]);
        }
        return result;
    }

    private static class TrieNode {
        private TrieNode zero;
        private TrieNode one;
        private int min = Integer.MAX_VALUE;
    }

    private void addNum(TrieNode cur, int num) {
        if (cur.min > num) {
            cur.min = num;
        }
        for (int i = L; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (bit == 0) {
                if (cur.zero == null) {
                    cur.zero = new TrieNode();
                }
                cur = cur.zero;
            } else {
                if (cur.one == null) {
                    cur.one = new TrieNode();
                }
                cur = cur.one;
            }
            if (cur.min > num) {
                cur.min = num;
            }
        }
    }

    private int getXorMax(TrieNode cur, int x, int m) {
        if (cur.min > m) { return -1; }
        int max = 0;
        for (int i = L; i >= 0; i--) {
            max <<= 1;
            int bit = (x >> i) & 1;
            if (bit == 0) {
                if (cur.one != null && cur.one.min <= m) {
                    max |= 1;
                    cur = cur.one;
                } else {
                    cur = cur.zero;
                }
            } else {
                if (cur.zero != null && cur.zero.min <= m) {
                    max |= 1;
                    cur = cur.zero;
                } else {
                    cur = cur.one;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4};
        int[][] queries = {{3, 1}, {1, 3}, {5, 6}};
        MaximizeXor3 mx = new MaximizeXor3();
        System.out.println(Arrays.toString(mx.maximizeXor(nums, queries)));
    }
}
