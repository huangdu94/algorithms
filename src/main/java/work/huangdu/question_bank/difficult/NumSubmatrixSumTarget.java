package work.huangdu.question_bank.difficult;

/**
 * 1074. 元素和为目标值的子矩阵数量
 * 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
 * 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
 * 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
 * 示例 1：
 * 输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * 输出：4
 * 解释：四个只含 0 的 1x1 子矩阵。
 * 示例 2：
 * 输入：matrix = [[1,-1],[-1,1]], target = 0
 * 输出：5
 * 解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
 * 示例 3：
 * 输入：matrix = [[904]], target = 0
 * 输出：0
 * 提示：
 * 1 <= matrix.length <= 100
 * 1 <= matrix[0].length <= 100
 * -1000 <= matrix[i] <= 1000
 * -10^8 <= target <= 10^8
 *
 * @author huangdu
 * @version 2021/5/29
 */
public class NumSubmatrixSumTarget {
    /**
     * o(m^2*n^2)
     * 有 o(m^2*n) 的方法
     * {@link work.huangdu.exploration.start_from_scratch.hashmap.prefixsum.SubarraySum}
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length, count = 0;
        int[][] prefix = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int rowSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += matrix[i][j];
                prefix[i + 1][j + 1] = rowSum + prefix[i][j + 1];
            }
        }
        for (int x1 = 0; x1 < m; x1++) {
            for (int y1 = 0; y1 < n; y1++) {
                for (int x2 = x1; x2 < m; x2++) {
                    for (int y2 = y1; y2 < n; y2++) {
                        //System.out.println(String.format("(%s,%s)-(%s,%s):%s", x1, y1, x2, y2, subMatrixSum(prefix, x1, y1, x2, y2)));
                        if (subMatrixSum(prefix, x1, y1, x2, y2) == target) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    private int subMatrixSum(int[][] prefix, int x1, int y1, int x2, int y2) {
        return prefix[x2 + 1][y2 + 1] - prefix[x1][y2 + 1] - prefix[x2 + 1][y1] + prefix[x1][y1];
    }

    public static void main(String[] args) {
        NumSubmatrixSumTarget nsst = new NumSubmatrixSumTarget();
        int[][] matrix = {{0, 1, 0}, {1, 1, 1}, {0, 1, 0}};
        int target = 0;
        System.out.println(nsst.numSubmatrixSumTarget(matrix, target));
    }
}
