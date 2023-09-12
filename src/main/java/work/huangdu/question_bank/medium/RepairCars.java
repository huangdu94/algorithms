package work.huangdu.question_bank.medium;

/**
 * 2594. 修车的最少时间
 * 给你一个整数数组 ranks ，表示一些机械工的 能力值 。ranks_i 是第 i 位机械工的能力值。能力值为 r 的机械工可以在 r * n^2 分钟内修好 n 辆车。
 * 同时给你一个整数 cars ，表示总共需要修理的汽车数目。
 * 请你返回修理所有汽车 最少 需要多少时间。
 * 注意：所有机械工可以同时修理汽车。
 * 示例 1：
 * 输入：ranks = [4,2,3,1], cars = 10
 * 输出：16
 * 解释：
 * - 第一位机械工修 2 辆车，需要 4 * 2 * 2 = 16 分钟。
 * - 第二位机械工修 2 辆车，需要 2 * 2 * 2 = 8 分钟。
 * - 第三位机械工修 2 辆车，需要 3 * 2 * 2 = 12 分钟。
 * - 第四位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
 * 16 分钟是修理完所有车需要的最少时间。
 * 示例 2：
 * 输入：ranks = [5,1,8], cars = 6
 * 输出：16
 * 解释：
 * - 第一位机械工修 1 辆车，需要 5 * 1 * 1 = 5 分钟。
 * - 第二位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
 * - 第三位机械工修 1 辆车，需要 8 * 1 * 1 = 8 分钟。
 * 16 分钟时修理完所有车需要的最少时间。
 * 提示：
 * 1 <= ranks.length <= 10^5
 * 1 <= ranks[i] <= 100
 * 1 <= cars <= 10^6
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class RepairCars {
    public long repairCars(int[] ranks, int cars) {
        int maxRank = 0;
        for (int rank : ranks) {maxRank = Math.max(maxRank, rank);}
        long left = 0, right = (long)maxRank * cars * cars;
        while (left <= right) {
            long mid = left + (right - left >> 1);
            int count = 0;
            for (int rank : ranks) {
                count += Math.sqrt(mid / rank);
            }
            if (count < cars) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        RepairCars rc = new RepairCars();
        System.out.println(rc.repairCars(new int[] {1, 2, 3, 4}, 10));
    }
}