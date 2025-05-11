package work.huangdu.question_bank.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1027. 最长等差数列
 * 给你一个整数数组 nums，返回 nums 中最长等差子序列的长度。
 * 回想一下，nums 的子序列是一个列表 nums[i1], nums[i2], ..., nums[ik] ，且 0 <= i1 < i2 < ... < ik <= nums.length - 1。并且如果 seq[i+1] - seq[i]( 0 <= i < seq.length - 1) 的值都相同，那么序列 seq 是等差的。
 * 示例 1：
 * 输入：nums = [3,6,9,12]
 * 输出：4
 * 解释：
 * 整个数组是公差为 3 的等差数列。
 * 示例 2：
 * 输入：nums = [9,4,7,2,10]
 * 输出：3
 * 解释：
 * 最长的等差子序列是 [4,7,10]。
 * 示例 3：
 * 输入：nums = [20,1,15,3,10,5,8]
 * 输出：4
 * 解释：
 * 最长的等差子序列是 [20,15,10,5]。
 * 提示：
 * 2 <= nums.length <= 1000
 * 0 <= nums[i] <= 500
 *
 * @author huangdu
 * @version 2023/4/23
 */
public class LongestArithSeqLength {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        int ans = 0;
        Map<Integer, Integer>[] f = new Map[n];
        f[0] = new HashMap<>(0);
        for (int i = 1; i < n; i++) {
            Map<Integer, Integer> result = new HashMap<>();
            for (int j = i - 1; j >= 0; j--) {
                int diff = nums[i] - nums[j];
                if (!result.containsKey(diff)) {
                    result.put(diff, f[j].getOrDefault(diff, 1) + 1);
                    ans = Math.max(ans, result.get(diff));
                }
            }
            f[i] = result;
        }
        return ans;
    }

    public int longestArithSeqLength2(int[] nums) {
        int n = nums.length, ans = 0;
        Map<Integer, Map<Integer, Integer>> diffMap = new HashMap<>();
        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            for (int j = 0; j < i; j++) {
                int pre = nums[j], diff = cur - pre;
                if (diffMap.containsKey(diff)) {
                    Map<Integer, Integer> tailMap = diffMap.get(diff);
                    if (tailMap.containsKey(j)) {
                        tailMap.put(i, Math.max(tailMap.getOrDefault(i, 0), tailMap.remove(j) + 1));
                    } else {
                        tailMap.put(i, 2);
                    }
                } else {
                    diffMap.put(diff, new HashMap<>());
                    diffMap.get(diff).put(i, 2);
                }
            }
        }
        for (Map.Entry<Integer, Map<Integer, Integer>> diffEntry : diffMap.entrySet()) {
            for (Map.Entry<Integer, Integer> tailEntry : diffEntry.getValue().entrySet()) {
                ans = Math.max(ans, tailEntry.getValue());
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {17, 19, 21, 21, 23, 25};
        LongestArithSeqLength lasl = new LongestArithSeqLength();
        System.out.println(lasl.longestArithSeqLength(nums));
    }
}
