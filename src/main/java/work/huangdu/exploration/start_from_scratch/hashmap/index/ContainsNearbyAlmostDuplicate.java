package work.huangdu.exploration.start_from_scratch.hashmap.index;

import java.util.*;

/**
 * 220. 存在重复元素 III
 * 在整数数组 nums 中，是否存在两个下标 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值小于等于 t ，且满足 i 和 j 的差的绝对值也小于等于 ķ 。
 * 如果存在则返回 true，不存在返回 false。
 * 示例 1:
 * 输入: nums = [1,2,3,1], k = 3, t = 0
 * 输出: true
 * 示例 2:
 * 输入: nums = [1,0,1,1], k = 1, t = 2
 * 输出: true
 * 示例 3:
 * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出: false
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/11/3 21:17
 * @see work.huangdu.exploration.primary_algorithms.array.ContainsDuplicate
 * @see work.huangdu.exploration.start_from_scratch.hashmap.index.ContainsNearbyAlmostDuplicate
 */
public class ContainsNearbyAlmostDuplicate {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k == 0) {
            return false;
        }
        if (t == 0) {
            Set<Integer> set = new HashSet<>(k + 1);
            for (int i = 0, n = nums.length; i < n; i++) {
                if (set.size() > k) {
                    set.remove(nums[i - k - 1]);
                }
                if (!set.add(nums[i])) {
                    return true;
                }
            }
            return false;
        }
        long w = (long) t + 1;
        Map<Integer, Integer> buckets = new HashMap<>(k + 1);
        for (int i = 0, n = nums.length; i < n; i++) {
            int num = nums[i];
            int bucketId = getId(num, w);
            if (buckets.containsKey(bucketId)
                    || buckets.containsKey(bucketId - 1) && (long) num - buckets.get(bucketId - 1) <= t
                    || buckets.containsKey(bucketId + 1) && (long) buckets.get(bucketId + 1) - num <= t) {
                return true;
            }
            buckets.put(bucketId, num);
            if (buckets.size() > k) {
                buckets.remove(getId(nums[i - k], w));
            }
        }
        return false;
    }

    private int getId(int num, long w) {
        return (int) (num < 0 ? (num + 1) / w - 1 : num / w);
    }

    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            Integer large = set.ceiling(nums[i]);
            if (large != null && (long) large - nums[i] <= t) {
                return true;
            }
            Integer small = set.floor(nums[i]);
            if (small != null && (long) nums[i] - small <= t) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    // 暴力解超时
    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        if (t < 0) {
            return false;
        }
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = Math.max(0, i - k); j < i; j++) {
                if (Math.abs((long) nums[i] - nums[j]) <= t) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsNearbyAlmostDuplicate cnad = new ContainsNearbyAlmostDuplicate();
        int[] nums = {2147483647, -1, 2147483647};
        int k = 1;
        int t = 2147483647;
        System.out.println(cnad.containsNearbyAlmostDuplicate(nums, k, t));
    }
}
