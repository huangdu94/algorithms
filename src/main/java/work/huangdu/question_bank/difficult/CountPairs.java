package work.huangdu.question_bank.difficult;

/**
 * 1803. 统计异或值在范围内的数对有多少
 * 给你一个整数数组 nums （下标 从 0 开始 计数）以及两个整数：low 和 high ，请返回 漂亮数对 的数目。
 * 漂亮数对 是一个形如 (i, j) 的数对，其中 0 <= i < j < nums.length 且 low <= (nums[i] XOR nums[j]) <= high 。
 * 示例 1：
 * 输入：nums = [1,4,2,7], low = 2, high = 6
 * 输出：6
 * 解释：所有漂亮数对 (i, j) 列出如下：
 * *     - (0, 1): nums[0] XOR nums[1] = 5
 * *     - (0, 2): nums[0] XOR nums[2] = 3
 * *     - (0, 3): nums[0] XOR nums[3] = 6
 * *     - (1, 2): nums[1] XOR nums[2] = 6
 * *     - (1, 3): nums[1] XOR nums[3] = 3
 * *     - (2, 3): nums[2] XOR nums[3] = 5
 * 示例 2：
 * 输入：nums = [9,8,4,2,1], low = 5, high = 14
 * 输出：8
 * 解释：所有漂亮数对 (i, j) 列出如下：
 * *     - (0, 2): nums[0] XOR nums[2] = 13
 * *     - (0, 3): nums[0] XOR nums[3] = 11
 * *     - (0, 4): nums[0] XOR nums[4] = 8
 * *     - (1, 2): nums[1] XOR nums[2] = 12
 * *     - (1, 3): nums[1] XOR nums[3] = 10
 * *     - (1, 4): nums[1] XOR nums[4] = 9
 * *     - (2, 3): nums[2] XOR nums[3] = 6
 * *     - (2, 4): nums[2] XOR nums[4] = 5
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i] <= 2 * 10^4
 * 1 <= low <= high <= 2 * 10^4
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/1/5
 */
public class CountPairs {
    private final int LIMIT = 14;
    private Trie root;

    public int countPairs(int[] nums, int low, int high) {
        int ans = 0;
        this.root = new Trie();
        for (int num : nums) {
            // 计算 XOR小于等于high的数量 - XOR小于low的数量
            ans += count(num, high, true) - count(num, low, false);
            // 将num加入Trie中
            insert(num);
        }
        return ans;
    }

    private int count(int num, int upper, boolean equal) {
        int cnt = 0;
        Trie cur = root;
        for (int i = LIMIT; i >= 0 && cur != null; i--) {
            int numBit = num >> i & 1, upperBit = upper >> i & 1;
            if (upperBit == 1 && cur.children[numBit] != null) {
                cnt += cur.children[numBit].count;
            }
            cur = cur.children[numBit ^ upperBit];
        }
        if (equal && cur != null) {cnt += cur.count;}
        return cnt;
    }

    private void insert(int num) {
        Trie cur = root;
        for (int i = LIMIT; i >= 0; i--) {
            int bit = num >> i & 1;
            if (cur.children[bit] == null) {
                cur.children[bit] = new Trie();
            }
            cur = cur.children[bit];
            cur.count++;
        }
    }

    static class Trie {
        Trie[] children = new Trie[2];
        int count = 0;
    }
}
