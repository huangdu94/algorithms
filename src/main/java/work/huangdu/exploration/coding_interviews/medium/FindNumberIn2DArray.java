package work.huangdu.exploration.coding_interviews.medium;

/**
 * 剑指 Offer 04. 二维数组中的查找
 *
 * @author huangdu
 * @version 2021/6/23
 * @see work.huangdu.exploration.intermediate_algorithms.sort_search.SearchMatrix
 */
public class FindNumberIn2DArray {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {return false;}
        int m = matrix.length, n = matrix[0].length, i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            int num = matrix[i][j];
            if (num == target) {
                return true;
            }
            if (target < num) {
                i--;
            } else {
                j++;
            }
        }
        return false;
    }
}
