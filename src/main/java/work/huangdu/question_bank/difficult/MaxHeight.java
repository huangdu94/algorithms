package work.huangdu.question_bank.difficult;

import java.util.Arrays;

/**
 * 1691. 堆叠长方体的最大高度
 * 给你 n 个长方体 cuboids ，其中第 i 个长方体的长宽高表示为 cuboids[i] = [width_i, length_i, height_i]（下标从 0 开始）。请你从 cuboids 选出一个 子集 ，并将它们堆叠起来。
 * 如果 width_i <= width_j 且 length_i <= length_j 且 height_i <= height_j ，你就可以将长方体 i 堆叠在长方体 j 上。你可以通过旋转把长方体的长宽高重新排列，以将它放在另一个长方体上。
 * 返回 堆叠长方体 cuboids 可以得到的 最大高度 。
 * 示例 1：
 * 输入：cuboids = [[50,45,20],[95,37,53],[45,23,12]]
 * 输出：190
 * 解释：
 * 第 1 个长方体放在底部，53x37 的一面朝下，高度为 95 。
 * 第 0 个长方体放在中间，45x20 的一面朝下，高度为 50 。
 * 第 2 个长方体放在上面，23x12 的一面朝下，高度为 45 。
 * 总高度是 95 + 50 + 45 = 190 。
 * 示例 2：
 * 输入：cuboids = [[38,25,45],[76,35,3]]
 * 输出：76
 * 解释：
 * 无法将任何长方体放在另一个上面。
 * 选择第 1 个长方体然后旋转它，使 35x3 的一面朝下，其高度为 76 。
 * 示例 3：
 * 输入：cuboids = [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]
 * 输出：102
 * 解释：
 * 重新排列长方体后，可以看到所有长方体的尺寸都相同。
 * 你可以把 11x7 的一面朝下，这样它们的高度就是 17 。
 * 堆叠长方体的最大高度为 6 * 17 = 10^2 。
 * 提示：
 * n == cuboids.length
 * 1 <= n <= 100
 * 1 <= width_i, length_i, height_i <= 100
 *
 * @author huangdu
 * @version 2022/12/10
 */
public class MaxHeight {
    public int maxHeight(int[][] cuboids) {
        int n = cuboids.length;
        for (int[] cuboid : cuboids) {Arrays.sort(cuboid);}
        Arrays.sort(cuboids, (o1, o2) -> o1[0] - o2[0] != 0 ? o1[0] - o2[0] : o1[1] - o2[1] != 0 ? o1[1] - o2[1] : o1[2] - o2[2]);
        int[] dp = new int[n];
        int maxHeight = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int curLength = cuboids[i][1], curHeight = cuboids[i][2];
                // 寻找i之前，i可以放在其下面的最长的
                if (cuboids[j][1] <= curLength && cuboids[j][2] <= curHeight) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            // 拼上当前i
            dp[i] += cuboids[i][2];
            maxHeight = Math.max(maxHeight, dp[i]);
        }
        return maxHeight;
    }
}
