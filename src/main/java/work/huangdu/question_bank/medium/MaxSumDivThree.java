package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 1262. 可被三整除的最大和
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
 * 示例 1：
 * 输入：nums = [3,6,5,1,8]
 * 输出：18
 * 解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。
 * 示例 2：
 * 输入：nums = [4]
 * 输出：0
 * 解释：4 不能被 3 整除，所以无法选出数字，返回 0。
 * 示例 3：
 * 输入：nums = [1,2,3,4,4]
 * 输出：12
 * 解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
 * 提示：
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/6/20
 */
public class MaxSumDivThree {
    public int maxSumDivThree(int[] nums) {
        int ans = 0;
        int[][] record = new int[3][2];
        for (int i = 1; i < 3; i++) {
            Arrays.fill(record[i], 10001);
        }
        for (int num : nums) {
            if (num % 3 != 0) {
                record[0][num % 3 - 1]++;
                int[] min = record[num % 3];
                if (min[0] > num) {
                    min[1] = min[0];
                    min[0] = num;
                } else if (min[1] > num) {
                    min[1] = num;
                }
            }
            ans += num;
        }
        int size = record[0][0] + record[0][1] * 2;
        if (size % 3 == 0) {return ans;}
        if (size % 3 == 1) {
            return ans - Math.min(record[1][0], record[2][0] + record[2][1]);
        }
        return ans - Math.min(record[1][0] + record[1][1], record[2][0]);
    }

    public static void main(String[] args) {
        int[] nums = {366, 809, 6, 792, 822, 181, 210, 588, 344, 618, 341, 410, 121, 864, 191, 749, 637, 169, 123, 472, 358, 908, 235, 914, 322, 946, 738, 754, 908, 272, 267, 326, 587, 267, 803, 281, 586, 707, 94, 627, 724, 469, 568, 57,
            103, 984, 787, 552, 14, 545, 866, 494, 263, 157, 479, 823, 835, 100, 495, 773, 729, 921, 348, 871, 91, 386, 183, 979, 716, 806, 639, 290, 612, 322, 289, 910, 484, 300, 195, 546, 499, 213, 8, 623, 490, 473, 603, 721, 793, 418,
            551, 331, 598, 670, 960, 483, 154, 317, 834, 352};
        MaxSumDivThree msdt = new MaxSumDivThree();
        System.out.println(msdt.maxSumDivThree(nums));
    }
}
