package work.huangdu.exploration.start_from_scratch.hashmap.prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
 * 525. 连续数组
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * 示例 1:
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
 * 示例 2:
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums[i] 不是 0 就是 1
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/11/8 17:59
 */
public class FindMaxLength {
    // 遇到1加1，遇到0减1
    public int findMaxLength(int[] nums) {
        int n = nums.length, difference = 0, diff = 0;
        for (int num : nums) {
            difference += num == 0 ? -1 : 1;
        }
        if (difference == 0) { return n; }
        Map<Integer, Integer> head = new HashMap<>(), tail = new HashMap<>();
        head.put(0, -1);
        tail.put(0, n);
        for (int i = 0; i < n && diff != difference; i++) {
            diff += nums[i] == 0 ? -1 : 1;
            if (!head.containsKey(diff)) {
                head.put(diff, i);
            }
        }
        diff = 0;
        for (int i = n - 1; i >= 0 && diff != difference; i--) {
            diff += nums[i] == 0 ? -1 : 1;
            if (!tail.containsKey(diff)) {
                tail.put(diff, i);
            }
        }
        int max = 0;
        for (int d : head.keySet()) {
            int h = head.get(d);
            Integer t = tail.get(difference - d);
            if (t != null) {
                if (max < t - h - 1) {
                    max = t - h - 1;
                }
            }
        }
        return max;
    }

    // 由于[0和1的数量相同]等价于[1的数量减去0的数量等于0]，我们可以将数组中的0视作−1，则原问题转换成[求最长的连续子数组，其元素和为0]。
    public int findMaxLength2(int[] nums) {
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int counter = 0;
        map.put(counter, -1);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == 1) {
                counter++;
            } else {
                counter--;
            }
            if (map.containsKey(counter)) {
                int prevIndex = map.get(counter);
                maxLength = Math.max(maxLength, i - prevIndex);
            } else {
                map.put(counter, i);
            }
        }
        return maxLength;
    }
}
