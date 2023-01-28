package work.huangdu.question_bank.difficult;

/**
 * 1815. 得到新鲜甜甜圈的最多组数
 * 有一个甜甜圈商店，每批次都烤 batchSize 个甜甜圈。这个店铺有个规则，就是在烤一批新的甜甜圈时，之前 所有 甜甜圈都必须已经全部销售完毕。给你一个整数 batchSize 和一个整数数组 groups ，数组中的每个整数都代表一批前来购买甜甜圈的顾客，其中 groups[i] 表示这一批顾客的人数。每一位顾客都恰好只要一个甜甜圈。
 * 当有一批顾客来到商店时，他们所有人都必须在下一批顾客来之前购买完甜甜圈。如果一批顾客中第一位顾客得到的甜甜圈不是上一组剩下的，那么这一组人都会很开心。
 * 你可以随意安排每批顾客到来的顺序。请你返回在此前提下，最多 有多少组人会感到开心。
 * 示例 1：
 * 输入：batchSize = 3, groups = [1,2,3,4,5,6]
 * 输出：4
 * 解释：你可以将这些批次的顾客顺序安排为 [6,2,4,5,1,3] 。那么第 1，2，4，6 组都会感到开心。
 * 示例 2：
 * 输入：batchSize = 4, groups = [1,3,2,5,2,2,1,6]
 * 输出：4
 * 提示：
 * 1 <= batchSize <= 9
 * 1 <= groups.length <= 30
 * 1 <= groups[i] <= 10^9
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2023/1/28
 */
public class MaxHappyGroups {
    public int maxHappyGroups(int batchSize, int[] groups) {
        int[] counts = new int[batchSize];
        for (int group : groups) {
            counts[group % batchSize]++;
        }
        int ans = counts[0];
        for (int k = 2, n = groups.length; k < n; k++) {
            while (backtrack(batchSize, counts, 0, k, 0)) {
                ans++;
            }
        }
        for (int i = 1; i < batchSize; i++) {
            if (counts[i] > 0) {
                return ans + 1;
            }
        }
        return ans;
    }

    private boolean backtrack(int batchSize, int[] counts, int sum, int k, int i) {
        if (i == k) {
            return sum == batchSize;
        }
        for (int num = 1; num < batchSize; num++) {
            if (counts[num] == 0) {
                continue;
            }
            if (sum + num > batchSize) {
                break;
            }
            counts[num]--;
            if (backtrack(batchSize, counts, sum + num, k, i + 1)) {
                return true;
            }
            counts[num]++;
        }
        return false;
    }

    public static void main(String[] args) {
        // TODO 做失败了
        MaxHappyGroups mhg = new MaxHappyGroups();
        int batchSize = 7;
        int[] groups = {2, 7, 5, 2, 3, 2, 6, 5, 3, 6, 2, 3, 7, 2, 2, 5, 4, 6, 6, 4, 7, 5, 6, 1, 6, 2, 6, 6, 2, 5};
        System.out.println(mhg.maxHappyGroups(batchSize, groups));
    }
}
