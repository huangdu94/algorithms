package work.huangdu.question_bank.difficult;

/**
 * 1659. 最大化网格幸福感
 * 给你四个整数 m、n、introvertsCount 和 extrovertsCount 。有一个 m x n 网格，和两种类型的人：内向的人和外向的人。总共有 introvertsCount 个内向的人和 extrovertsCount 个外向的人。
 * 请你决定网格中应当居住多少人，并为每个人分配一个网格单元。 注意，不必 让所有人都生活在网格中。
 * 每个人的 幸福感 计算如下：
 * 内向的人 开始 时有 120 个幸福感，但每存在一个邻居（内向的或外向的）他都会 失去  30 个幸福感。
 * 外向的人 开始 时有 40 个幸福感，每存在一个邻居（内向的或外向的）他都会 得到  20 个幸福感。
 * 邻居是指居住在一个人所在单元的上、下、左、右四个直接相邻的单元中的其他人。
 * 网格幸福感 是每个人幸福感的 总和 。 返回 最大可能的网格幸福感 。
 * 示例 1：
 * 输入：m = 2, n = 3, introvertsCount = 1, extrovertsCount = 2
 * 输出：240
 * 解释：假设网格坐标 (row, column) 从 1 开始编号。
 * 将内向的人放置在单元 (1,1) ，将外向的人放置在单元 (1,3) 和 (2,3) 。
 * - 位于 (1,1) 的内向的人的幸福感：120（初始幸福感）- (0 * 30)（0 位邻居）= 120
 * - 位于 (1,3) 的外向的人的幸福感：40（初始幸福感）+ (1 * 20)（1 位邻居）= 60
 * - 位于 (2,3) 的外向的人的幸福感：40（初始幸福感）+ (1 * 20)（1 位邻居）= 60
 * 网格幸福感为：120 + 60 + 60 = 240
 * 上图展示该示例对应网格中每个人的幸福感。内向的人在浅绿色单元中，而外向的人在浅紫色单元中。
 * 示例 2：
 * 输入：m = 3, n = 1, introvertsCount = 2, extrovertsCount = 1
 * 输出：260
 * 解释：将内向的人放置在单元 (1,1) 和 (3,1) ，将外向的人放置在单元 (2,1) 。
 * - 位于 (1,1) 的内向的人的幸福感：120（初始幸福感）- (1 * 30)（1 位邻居）= 90
 * - 位于 (2,1) 的外向的人的幸福感：40（初始幸福感）+ (2 * 20)（2 位邻居）= 80
 * - 位于 (3,1) 的内向的人的幸福感：120（初始幸福感）- (1 * 30)（1 位邻居）= 90
 * 网格幸福感为 90 + 80 + 90 = 260
 * 示例 3：
 * 输入：m = 2, n = 2, introvertsCount = 4, extrovertsCount = 0
 * 输出：240
 * 提示：
 * 1 <= m, n <= 5
 * 0 <= introvertsCount, extrovertsCount <= min(m * n, 6)
 *
 * @author huangdu
 * @version 2023/6/25
 */
public class GetMaxGridHappiness {
    private int m;
    private int size;
    // 行内分数，index对应行的状态（状态为3进制数，0空 1内向 2外向）
    private int[] inlineScore;
    // 行与行之间分数
    private int[][] betweenLineScore;
    private final int[] table1d = {0, 120, 40};
    private final int[][] table2d = {{0, 0, 0}, {0, -60, -10}, {0, -10, 40}};
    private int[][][][] memo;

    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        this.m = m;
        this.size = (int)Math.pow(3, n);
        this.inlineScore = new int[size];
        this.betweenLineScore = new int[size][size];
        for (int i = 0; i < size; i++) {
            inlineScore[i] = table1d(i);
            for (int j = 0; j < size; j++) {
                betweenLineScore[i][j] = i <= j ? table2d(i, j) : betweenLineScore[j][i];
            }
        }
        this.memo = new int[m][size][introvertsCount + 1][extrovertsCount + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k <= introvertsCount; k++) {
                    for (int p = 0; p <= extrovertsCount; p++) {
                        memo[i][j][k][p] = -1;
                    }
                }
            }
        }
        return dfs(0, 0, introvertsCount, extrovertsCount);
    }

    private int dfs(int i, int pre, int ic, int ec) {
        if (i == m || (ic == 0 && ec == 0)) {return 0;}
        if (memo[i][pre][ic][ec] != -1) {return memo[i][pre][ic][ec];}
        int max = 0;
        for (int status = 0; status < size; status++) {
            int[] count = new int[2];
            int s = status;
            while (s > 0) {
                if (s % 3 > 0) {count[s % 3 - 1]++;}
                s /= 3;
            }
            if (ic >= count[0] && ec >= count[1]) {
                max = Math.max(max, inlineScore[status] + betweenLineScore[pre][status] + dfs(i + 1, status, ic - count[0], ec - count[1]));
            }
        }
        return memo[i][pre][ic][ec] = max;
    }

    private int table1d(int status) {
        int score = 0, pre = 0;
        while (status > 0) {
            int cur = status % 3;
            score += table1d[cur] + table2d[pre][cur];
            status /= 3;
            pre = cur;
        }
        return score;
    }

    private int table2d(int statusI, int statusJ) {
        int score = 0;
        while (statusI > 0 || statusJ > 0) {
            score += table2d[statusI % 3][statusJ % 3];
            statusI /= 3;
            statusJ /= 3;
        }
        return score;
    }

    public static void main(String[] args) {
        GetMaxGridHappiness gmgh = new GetMaxGridHappiness();
        System.out.println(gmgh.getMaxGridHappiness(2, 3, 1, 2));
    }
}
