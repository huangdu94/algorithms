package work.huangdu.exploration.start_from_scratch.array.transform_2d;

/**
 * 566. 重塑矩阵
 * 在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
 * 给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
 * 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
 * 如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
 * 示例 1:
 * 输入:
 * nums =
 * [[1,2],
 * [3,4]]
 * r = 1, c = 4
 * 输出:
 * [[1,2,3,4]]
 * 解释:
 * 行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 * 4 矩阵, 用之前的元素值一行一行填充新矩阵。
 * 示例 2:
 * 输入:
 * nums =
 * [[1,2],
 * [3,4]]
 * r = 2, c = 4
 * 输出:
 * [[1,2],
 * [3,4]]
 * 解释:
 * 没有办法将 2 * 2 矩阵转化为 2 * 4 矩阵。 所以输出原矩阵。
 * 注意：
 * 给定矩阵的宽和高范围在 [1, 100]。
 * 给定的 r 和 c 都是正数。
 *
 * @author huangdu
 * @version 2020/9/18 17:35
 */
public class MatrixReshape {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int m = nums.length, n = nums[0].length;
        if (m * n != r * c) return nums;
        int[][] res = new int[r][c];
        int _r = 0, _c = 0;
        for (int[] row : nums) {
            for (int j = 0; j < n; j++) {
                res[_r][_c++] = row[j];
                if (_c == c) {
                    _c = 0;
                    _r++;
                }
            }
        }
        return res;
    }

    public int[][] matrixReshape2(int[][] nums, int r, int c) {
        int row = nums.length, col = nums[0].length;
        if (row * col != r * c || r == row) return nums;
        int[][] newMatrix = new int[r][c];
        int i0 = 0, j0 = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                newMatrix[i0][j0] = nums[i][j];
                j0++;
                if (j0 == c) {
                    j0 = 0;
                    i0++;
                }
            }
        }
        return newMatrix;
    }
}
