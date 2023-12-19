package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 1901. 寻找峰值 II
 * 一个 2D 网格中的 峰值 是指那些 严格大于 其相邻格子(上、下、左、右)的元素。
 * 给你一个 从 0 开始编号 的 m x n 矩阵 mat ，其中任意两个相邻格子的值都 不相同 。找出 任意一个 峰值 mat[i][j] 并 返回其位置 [i,j] 。
 * 你可以假设整个矩阵周边环绕着一圈值为 -1 的格子。
 * 要求必须写出时间复杂度为 O(m log(n)) 或 O(n log(m)) 的算法
 * 示例 1:
 * 输入: mat = [[1,4],[3,2]]
 * 输出: [0,1]
 * 解释: 3 和 4 都是峰值，所以[1,0]和[0,1]都是可接受的答案。
 * 示例 2:
 * 输入: mat = [[10,20,15],[21,30,14],[7,16,32]]
 * 输出: [1,1]
 * 解释: 30 和 32 都是峰值，所以[1,1]和[2,2]都是可接受的答案。
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 500
 * 1 <= mat[i][j] <= 10^5
 * 任意两个相邻元素均不相等.
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class FindPeakGrid {
    public int[] findPeakGrid(int[][] mat) {
        int n = mat[0].length, left = -1, right = mat.length - 1;
        while (left + 1 < right) {
            int i = (left + right) >>> 1, j = indexOfMax(mat[i], n);
            if (mat[i][j] < mat[i + 1][j]) {
                left = i; // 峰顶行号 > i
            } else {
                right = i; // 峰顶行号 <= i
            }
        }
        return new int[] {right, indexOfMax(mat[right], n)};
    }

    private int indexOfMax(int[] a, int n) {
        int idx = 0;
        for (int i = 1; i < n; i++) {
            if (a[i] > a[idx]) {
                idx = i;
            }
        }
        return idx;
    }

    public int[] findPeakGrid1(int[][] mat) {
        int m = mat.length, n = mat[0].length, i = 0, j = 0, go;
        while ((go = isPeak(mat, m, n, i, j)) != -1) {
            switch (go) {
                case 0:
                    i--;
                    break;
                case 1:
                    i++;
                    break;
                case 2:
                    j--;
                    break;
                case 3:
                    j++;
            }
        }
        return new int[] {i, j};
    }

    /**
     * -1 是山峰
     * 0 上
     * 1 下
     * 2 左
     * 3 右
     */
    private int isPeak(int[][] mat, int m, int n, int i, int j) {
        int cur = mat[i][j];
        if (i > 0 && mat[i - 1][j] > cur) {return 0;}
        if (i < m - 1 && mat[i + 1][j] > cur) {return 1;}
        if (j > 0 && mat[i][j - 1] > cur) {return 2;}
        if (j < n - 1 && mat[i][j + 1] > cur) {return 3;}
        return -1;
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 4}, {3, 2}};
        FindPeakGrid fpg = new FindPeakGrid();
        System.out.println(Arrays.toString(fpg.findPeakGrid(mat)));
    }

    public int[] findPeakGrid2(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = mat[i][j];
                if ((i == 0 || mat[i - 1][j] < cur) && (i == m - 1 || mat[i + 1][j] < cur) && (j == 0 || mat[i][j - 1] < cur) && (j == n - 1 || mat[i][j + 1] < cur)) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }
}
