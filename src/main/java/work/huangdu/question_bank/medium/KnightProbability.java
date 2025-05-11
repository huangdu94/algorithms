package work.huangdu.question_bank.medium;

/**
 * 688. 骑士在棋盘上的概率
 * 在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
 * 骑士继续移动，直到它走了k步 或离开了棋盘。
 * 返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
 * 示例 1：
 * 输入: n = 3, k = 2, row = 0, column = 0
 * 输出: 0.0625
 * 解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
 * 在每一个位置上，也有两种移动可以让骑士留在棋盘上。
 * 骑士留在棋盘上的总概率是0.0625。
 * 示例 2：
 * 输入: n = 1, k = 0, row = 0, column = 0
 * 输出: 1.00000
 * 提示:
 * 1 <= n <= 25
 * 0 <= k <= 100
 * 0 <= row, column <= n
 *
 * @author huangdu
 * @version 2022/2/17
 */
public class KnightProbability {
    private static final int[][] MOVE = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};

    // TODO 我的思维跟答案是反着的。
    public double knightProbability(int n, int k, int row, int column) {
        // 还没移动就在棋盘外
        if (row == n || column == n) {return 0;}
        // 没有移动次数
        if (k == 0) {return 1;}
        double[][][] dp = new double[k + 1][n][n];
        dp[0][row][column] = 1.0;
        for (int i = 1; i <= k; i++) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (dp[i - 1][r][c] > 0) {
                        for (int[] m : MOVE) {
                            int nextR = r + m[0], nextC = c + m[1];
                            if (nextR >= 0 && nextR < n && nextC >= 0 && nextC < n) {
                                dp[i][nextR][nextC] += dp[i - 1][r][c] / 8;
                            }
                        }
                    }
                }
            }
        }
        double ans = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                ans += dp[k][r][c];
            }
        }
        return ans;
    }

    public double knightProbability2(int n, int k, int row, int column) {
        // 还没移动就在棋盘外
        if (row == n || column == n) {return 0;}
        // 没有移动次数
        if (k == 0) {return 1;}
        double[][] dp = new double[n][n];
        dp[row][column] = 1.0;
        for (int i = 1; i <= k; i++) {
            double[][] dp2 = new double[n][n];
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (dp[r][c] > 0) {
                        for (int[] m : MOVE) {
                            int nextR = r + m[0], nextC = c + m[1];
                            if (nextR >= 0 && nextR < n && nextC >= 0 && nextC < n) {
                                dp2[nextR][nextC] += dp[r][c] / 8;
                            }
                        }
                    }
                }
            }
            dp = dp2;
        }
        double ans = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                ans += dp[r][c];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        KnightProbability kp = new KnightProbability();
        System.out.println(kp.knightProbability(8, 15, 6, 4));
    }

    /*    public double knightProbability(int n, int k, int row, int column) {
        // 还没移动就在棋盘外
        if (row == n || column == n) {return 0;}
        // 没有移动次数
        if (k == 0) {return 1;}
        double outProbability = 0, coefficient = 1.0 / 8;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(row * n + column);
        while (!queue.isEmpty() && k > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                int r = index / n, c = index % n;
                for (int[] m : MOVE) {
                    int nextR = r + m[0], nextC = c + m[1];
                    if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                        outProbability += coefficient;
                    } else {
                        queue.offer(nextR * n + nextC);
                    }
                }
            }
            coefficient /= 8;
            k--;
        }
        return 1 - outProbability;
    }

    public double knightProbability2(int n, int k, int row, int column) {
        // 还没移动就在棋盘外
        if (row == n || column == n) {return 0;}
        // 没有移动次数
        if (k == 0) {return 1;}
        final BigDecimal eight = new BigDecimal("8");
        BigDecimal outProbability = new BigDecimal("0"), coefficient = new BigDecimal("1").divide(eight);
        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        queue.offer(row * n + column);
        countMap.merge(row * n + column, 1, Integer::sum);
        while (!queue.isEmpty() && k > 0) {
            int size = queue.size();
            Map<Integer, Integer> newCountMap = new HashMap<>();
            for (int i = 0; i < size; i++) {
                int index = queue.poll(), count = countMap.get(index);
                int r = index / n, c = index % n;
                for (int[] m : MOVE) {
                    int nextR = r + m[0], nextC = c + m[1];
                    if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                        outProbability = outProbability.add(coefficient.multiply(BigDecimal.valueOf(count)));
                    } else {
                        if (!newCountMap.containsKey(nextR * n + nextC)) {
                            queue.offer(nextR * n + nextC);
                        }
                        newCountMap.merge(nextR * n + nextC, count, Integer::sum);
                    }
                }
            }
            countMap = newCountMap;
            coefficient = coefficient.divide(eight);
            k--;
        }
        return BigDecimal.ONE.subtract(outProbability).doubleValue();
    }*/
}