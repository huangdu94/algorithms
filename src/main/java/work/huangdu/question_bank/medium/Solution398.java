package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 398. 随机数索引
 * 给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
 * 注意：
 * 数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
 * 示例:
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 * // pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
 * solution.pick(3);
 * // pick(1) 应该返回 0。因为只有nums[0]等于1。
 * solution.pick(1);
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/4/25
 */
public class Solution398 {
    // 水塘抽样算法 时间换空间很有意思。
    class Solution {
        private final int[] nums;
        private final Random random;

        public Solution(int[] nums) {
            this.nums = nums;
            this.random = new Random();
        }

        public int pick(int target) {
            int ans = 0, cnt = 0, idx = 0;
            for (int num : nums) {
                if (num == target) {
                    if (random.nextInt(++cnt) == 0) {
                        ans = idx;
                    }
                }
                idx++;
            }
            return ans;
        }
    }

    private final Map<Integer, List<Integer>> numIndexListMap;

    public Solution398(int[] nums) {
        this.numIndexListMap = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            List<Integer> indexList = numIndexListMap.computeIfAbsent(nums[i], k -> new ArrayList<>());
            indexList.add(i);
        }
    }

    public int pick(int target) {
        List<Integer> indexList = numIndexListMap.get(target);
        return indexList.get((int)(Math.random() * indexList.size()));
    }
}
