package work.huangdu.question_bank.medium;

/**
 * 930. 和相同的二元子数组
 * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
 * 子数组 是数组的一段连续部分。
 * 示例 1：
 * 输入：nums = [1,0,1,0,1], goal = 2
 * 输出：4
 * 解释：
 * 如下面黑体所示，有 4 个满足题目要求的子数组：
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * 示例 2：
 * 输入：nums = [0,0,0,0,0], goal = 0
 * 输出：15
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * nums[i] 不是 0 就是 1
 * 0 <= goal <= nums.length
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/7/8
 */
public class NumSubarraysWithSum {
    // TODO 尝试滑动窗口
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length, sum = 0, count = 0;
        int[] prefixMap = new int[n + 1];
        prefixMap[0] = 1;
        for (int num : nums) {
            sum += num;
            if (sum >= goal) {count += prefixMap[sum - goal];}
            prefixMap[sum]++;
        }
        return count;
    }

    // 超时
    public int numSubarraysWithSum2(int[] nums, int goal) {
        int n = nums.length, count = 0;
        int[] sumPrefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sumPrefix[i + 1] = sumPrefix[i] + nums[i];
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (sumPrefix[j + 1] - sumPrefix[i] == goal) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumSubarraysWithSum numSubarraysWithSum = new NumSubarraysWithSum();
        int[] nums = {0, 0, 0, 0, 0};
        int goal = 0;
        System.out.println(numSubarraysWithSum.numSubarraysWithSum(nums, goal));
    }
}
