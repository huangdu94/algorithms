package work.huangdu.question_bank.difficult;

import java.util.HashMap;
import java.util.Map;

/**
 * 952. 按公因数计算最大组件大小
 * 给定一个由不同正整数的组成的非空数组 nums ，考虑下面的图：
 * 有 nums.length 个节点，按从 nums[0] 到 nums[nums.length - 1] 标记；
 * 只有当 nums[i] 和 nums[j] 共用一个大于 1 的公因数时，nums[i] 和 nums[j]之间才有一条边。
 * 返回 图中最大连通组件的大小 。
 * 示例 1：
 * 输入：nums = [4,6,15,35]
 * 输出：4
 * 示例 2：
 * 输入：nums = [20,50,9,63]
 * 输出：2
 * 示例 3：
 * 输入：nums = [2,3,6,7,4,12,21,39]
 * 输出：8
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i] <= 10^5
 * nums 中所有值都 不同
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2022/7/30
 */
public class LargestComponentSize {
    private final int[] parent = new int[100001];

    public int largestComponentSize(int[] nums) {
        for (int i = 0; i <= 100000; i++) {
            parent[i] = i;
        }
        for (int num : nums) {
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    union(num, i);
                    union(num, num / i);
                }
            }
        }
        int ans = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            ans = Math.max(ans, count.merge(find(num), 1, Integer::sum));
        }
        return ans;
    }

    private int find(int x) {
        if (parent[x] == x) {return x;}
        return parent[x] = find(parent[x]);
    }

    private void union(int x, int y) {
        int parentX = find(x), parentY = find(y);
        if (parentX != parentY) {
            parent[parentX] = parentY;
        }
    }

    public static void main(String[] args) {
        LargestComponentSize lcs = new LargestComponentSize();
        System.out.println(lcs.largestComponentSize(new int[] {4, 6, 15, 35}));
    }
}
