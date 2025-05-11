package work.huangdu.question_bank.easy;

import java.util.Arrays;

/**
 * 1337. 矩阵中战斗力最弱的 K 行
 * 给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。
 * 请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。
 * 如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
 * 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
 * 示例 1：
 * 输入：mat =
 * [[1,1,0,0,0],
 * [1,1,1,1,0],
 * [1,0,0,0,0],
 * [1,1,0,0,0],
 * [1,1,1,1,1]],
 * k = 3
 * 输出：[2,0,3]
 * 解释：
 * 每行中的军人数目：
 * 行 0 -> 2
 * 行 1 -> 4
 * 行 2 -> 1
 * 行 3 -> 2
 * 行 4 -> 5
 * 从最弱到最强对这些行排序后得到 [2,0,3,1,4]
 * 示例 2：
 * 输入：mat =
 * [[1,0,0,0],
 * [1,1,1,1],
 * [1,0,0,0],
 * [1,0,0,0]],
 * k = 2
 * 输出：[0,2]
 * 解释：
 * 每行中的军人数目：
 * 行 0 -> 1
 * 行 1 -> 4
 * 行 2 -> 1
 * 行 3 -> 1
 * 从最弱到最强对这些行排序后得到 [0,2,3,1]
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 2 <= n, m <= 100
 * 1 <= k <= m
 * matrix[i][j] 不是 0 就是 1
 *
 * @author huangdu
 * @version 2021/8/1
 */
public class KWeakestRows {
    public int[] kWeakestRows(int[][] mat, int k) {
        int n = mat.length;
        int[][] record = new int[n][2];
        for (int i = 0; i < n; i++) {
            record[i][0] = i;
            record[i][1] = count(mat, i);
        }
        specialQuickSort(record, 0, n - 1, k);
        Arrays.sort(record, 0, k, (o1, o2) -> {
            if (o1[1] != o2[1]) { return Integer.compare(o1[1], o2[1]); }
            return Integer.compare(o1[0], o2[0]);
        });
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = record[i][0];
        }
        return result;
    }

    private int count(int[][] mat, int rowIndex) {
        int[] rowArray = mat[rowIndex];
        int i = 0, j = rowArray.length;
        while (i < j) {
            int mid = i + ((j - i) >> 1);
            if (rowArray[mid] == 1) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return i;
    }

    private void specialQuickSort(int[][] record, int l, int r, int k) {
        if (l >= r) { return; }
        int pivotIndex = record[l][0], pivotCount = record[l][1];
        int i = l, j = r;
        while (i < j) {
            // 保证pivot右边都是比pivot大的
            while (i < j && (record[j][1] > pivotCount || record[j][1] == pivotCount && record[j][0] > pivotIndex)) {
                j--;
            }
            while (i < j && (record[i][1] < pivotCount || record[i][1] == pivotCount && record[i][0] <= pivotIndex)) {
                i++;
            }
            if (i < j) {
                int[] temp = record[i];
                record[i] = record[j];
                record[j] = temp;
            }
        }
        int[] pivot = record[l];
        record[l] = record[i];
        record[i] = pivot;
        if (i > k) { specialQuickSort(record, l, i - 1, k); } else if (i < k) { specialQuickSort(record, i + 1, r, k); }
    }

    public static void main(String[] args) {
        KWeakestRows kwr = new KWeakestRows();
        int[][] mat = {{1, 1, 1, 1, 1}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 1, 1, 1, 1}};
        System.out.println(Arrays.toString(kwr.kWeakestRows(mat, 3)));
    }
}
