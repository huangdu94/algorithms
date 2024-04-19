package work.huangdu.question_bank.difficult;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1235. 规划兼职工作
 * 你打算利用空闲时间来做兼职工作赚些零花钱。
 * 这里有 n 份兼职工作，每份工作预计从 startTime[i] 开始到 endTime[i] 结束，报酬为 profit[i]。
 * 给你一份兼职工作表，包含开始时间 startTime，结束时间 endTime 和预计报酬 profit 三个数组，请你计算并返回可以获得的最大报酬。
 * 注意，时间上出现重叠的 2 份工作不能同时进行。
 * 如果你选择的工作在时间 X 结束，那么你可以立刻进行在时间 X 开始的下一份工作。
 * 示例 1：
 * 输入：startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * 输出：120
 * 解释：
 * 我们选出第 1 份和第 4 份工作，
 * 时间范围是 [1-3]+[3-6]，共获得报酬 120 = 50 + 70。
 * 示例 2：
 * 输入：startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * 输出：150
 * 解释：
 * 我们选择第 1，4，5 份工作。
 * 共获得报酬 150 = 20 + 70 + 60。
 * 示例 3：
 * 输入：startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * 输出：6
 * 提示：
 * 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
 * 1 <= startTime[i] < endTime[i] <= 10^9
 * 1 <= profit[i] <= 10^4
 *
 * @author huangdu
 * @version 2022/10/24
 */
public class JobScheduling {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        int[][] data = new int[n][3];
        for (int i = 0; i < n; i++) {
            data[i][0] = startTime[i];
            data[i][1] = endTime[i];
            data[i][2] = profit[i];
        }
        Arrays.sort(data, Comparator.comparingInt(o -> o[1]));
        // dp[i]表示 前i个工作 可获得的最大报酬
        int[] dp = new int[n + 1];
        // 边界条件 0个工作 获得的报酬肯定是0
        dp[0] = 0;
        // 状态转移方程 dp[i] = Math.max(dp[i-1], dp[k] + profit[i-1])
        // 也就是前i个工作可获得的最大报酬，
        // 1. 不选当前工作，那么最大的报酬和前i-1个工作一样 2. 选当前工作，则报酬为当前工作报酬 + 结束时间<=当前工作开始时间的所有工作数量的最大报酬
        // 取1和2中最大的
        for (int i = 1; i <= n; i++) {
            int k = binarySearch(data, data[i - 1][0]);
            dp[i] = Math.max(dp[i - 1], dp[k] + data[i - 1][2]);
        }
        return dp[n];
    }

    private int binarySearch(int[][] data, int target) {
        int i = 0, j = data.length, result = -1;
        while (i <= j) {
            int mid = i + (j - i >> 1);
            if (data[mid][1] <= target) {
                result = mid;
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return result + 1;
    }

    public static void main(String[] args) {
        JobScheduling js = new JobScheduling();
        int[] startTime = {6, 15, 7, 11, 1, 3, 16, 2};
        int[] endTime = {19, 18, 19, 16, 10, 8, 19, 8};
        int[] profit = {2, 9, 1, 19, 5, 7, 3, 19};
        System.out.println(js.jobScheduling(startTime, endTime, profit));
    }
}
