package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2454. 下一个更大元素 IV
 * 给你一个下标从 0 开始的非负整数数组 nums 。对于 nums 中每一个整数，你必须找到对应元素的 第二大 整数。
 * 如果 nums[j] 满足以下条件，那么我们称它为 nums[i] 的 第二大 整数：
 * j > i
 * nums[j] > nums[i]
 * 恰好存在 一个 k 满足 i < k < j 且 nums[k] > nums[i] 。
 * 如果不存在 nums[j] ，那么第二大整数为 -1 。
 * 比方说，数组 [1, 2, 4, 3] 中，1 的第二大整数是 4 ，2 的第二大整数是 3 ，3 和 4 的第二大整数是 -1 。
 * 请你返回一个整数数组 answer ，其中 answer[i]是 nums[i] 的第二大整数。
 * 示例 1：
 * 输入：nums = [2,4,0,9,6]
 * 输出：[9,6,6,-1,-1]
 * 解释：
 * 下标为 0 处：2 的右边，4 是大于 2 的第一个整数，9 是第二个大于 2 的整数。
 * 下标为 1 处：4 的右边，9 是大于 4 的第一个整数，6 是第二个大于 4 的整数。
 * 下标为 2 处：0 的右边，9 是大于 0 的第一个整数，6 是第二个大于 0 的整数。
 * 下标为 3 处：右边不存在大于 9 的整数，所以第二大整数为 -1 。
 * 下标为 4 处：右边不存在大于 6 的整数，所以第二大整数为 -1 。
 * 所以我们返回 [9,6,6,-1,-1] 。
 * 示例 2：
 * 输入：nums = [3,3]
 * 输出：[-1,-1]
 * 解释：
 * 由于每个数右边都没有更大的数，所以我们返回 [-1,-1] 。
 * 提示：
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 *
 * @author huangdu
 */
public class SecondGreaterElement {
    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length, top = 0;
        int[] ans = new int[n], stack = new int[n], greater = new int[n];
        List<Integer>[] smaller = new List[n];
        Arrays.fill(ans, -1);
        Arrays.fill(greater, -1);
        Arrays.setAll(smaller, (o) -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            while (top > 0 && nums[stack[top - 1]] < num) {
                greater[stack[--top]] = i;
                smaller[i].add(stack[top]);
            }
            if (top > 0 && !smaller[stack[top - 1]].isEmpty()) {
                List<Integer> smallerList = smaller[stack[top - 1]];
                int left = -1, right = smallerList.size();
                while (left + 1 < right) {
                    int mid = left + (right - left >> 1);
                    if (nums[smallerList.get(mid)] < num) {
                        left = mid;
                    } else {
                        right = mid;
                    }
                }
                for (int k = 0; k < right; k++) {
                    ans[smallerList.get(k)] = num;
                }
                smaller[stack[top - 1]] = right == smallerList.size() ? new ArrayList<>() : smallerList.subList(right, smallerList.size() - 1);
            }
            stack[top++] = i;
        }
        for (int i = 0; i < n; i++) {
            if (ans[i] == -1 && greater[i] != -1 && greater[greater[i]] != -1) {
                ans[i] = nums[greater[greater[i]]];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {272, 238, 996, 406, 763, 164, 102, 948, 217, 760, 609, 700, 848, 637, 748, 718, 469, 449, 502, 703, 292, 86, 91, 551, 699, 293, 244, 406, 22, 968, 434, 805, 910, 927, 623, 79, 108, 541, 411};
        System.out.println(Arrays.toString(new SecondGreaterElement().secondGreaterElement(nums)));
    }
}
