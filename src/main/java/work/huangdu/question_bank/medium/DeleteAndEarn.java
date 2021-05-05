package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 740. 删除并获得点数
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * 示例 1：
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * 示例 2：
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i] <= 10^4
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/5
 */
public class DeleteAndEarn {
    // TODO动态规划 see 打家劫舍
    private static final int MAX = 10000;
    private static int[] counts;
    private final Map<Integer, Integer> memo = new HashMap<>();

    public int deleteAndEarn(int[] nums) {
        counts = new int[MAX + 1];
        for (int num : nums) { counts[num]++; }
        int earn = 0;
        for (int i = 1; i <= MAX; i++) {
            if (counts[i] == 0) { continue; }
            int start = i;
            while (i <= MAX && counts[i] != 0) { i++; }
            memo.clear();
            earn += earn(start, i);
        }
        return earn;
    }

    private int earn(int i, int end) {
        if (memo.containsKey(i)) {return memo.get(i);}
        if (i > end || counts[i] == 0) { return 0; }
        int earn = Math.max(earn(i + 1, end), counts[i] * i + earn(i + 2, end));
        memo.put(i, earn);
        return earn;
    }

    public static void main(String[] args) {
        int[] nums = {10, 8, 4, 2, 1, 3, 4, 8, 2, 9, 10, 4, 8, 5, 9, 1, 5, 1, 6, 8, 1, 1, 6, 7, 8, 9, 1, 7, 6, 8, 4, 5, 4, 1, 5, 9, 8, 6, 10, 6, 4, 3, 8, 4, 10, 8, 8, 10, 6, 4, 4, 4, 9, 6, 9, 10, 7,
            1, 5, 3, 4, 4, 8, 1, 1, 2, 1, 4, 1, 1, 4, 9, 4, 7, 1, 5, 1, 10, 3, 5, 10, 3, 10, 2, 1, 10, 4, 1, 1, 4, 1, 2, 10, 9, 7, 10, 1, 2, 7, 5};
        //int[] nums = {1,3};
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        System.out.println(map);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() * entry.getValue());
        }
        System.out.println(new DeleteAndEarn().deleteAndEarn(nums));
        System.out.println("Done!");
    }
}
