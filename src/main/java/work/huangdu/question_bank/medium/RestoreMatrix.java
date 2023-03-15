package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1605. 给定行和列的和求可行矩阵
 * 给你两个非负整数数组 rowSum 和 colSum ，其中 rowSum[i] 是二维矩阵中第 i 行元素的和， colSum[j] 是第 j 列元素的和。换言之你不知道矩阵里的每个元素，但是你知道每一行和每一列的和。
 * 请找到大小为 rowSum.length x colSum.length 的任意 非负整数 矩阵，且该矩阵满足 rowSum 和 colSum 的要求。
 * 请你返回任意一个满足题目要求的二维矩阵，题目保证存在 至少一个 可行矩阵。
 * 示例 1：
 * 输入：rowSum = [3,8], colSum = [4,7]
 * 输出：[[3,0],
 * *     [1,7]]
 * 解释：
 * 第 0 行：3 + 0 = 3 == rowSum[0]
 * 第 1 行：1 + 7 = 8 == rowSum[1]
 * 第 0 列：3 + 1 = 4 == colSum[0]
 * 第 1 列：0 + 7 = 7 == colSum[1]
 * 行和列的和都满足题目要求，且所有矩阵元素都是非负的。
 * 另一个可行的矩阵为：[[1,2],
 * *                [3,5]]
 * 示例 2：
 * 输入：rowSum = [5,7,10], colSum = [8,6,8]
 * 输出：[[0,5,0],
 * *     [6,1,0],
 * *     [2,0,8]]
 * 示例 3：
 * 输入：rowSum = [14,9], colSum = [6,9,8]
 * 输出：[[0,9,5],
 * *     [6,0,3]]
 * 示例 4：
 * 输入：rowSum = [1,0], colSum = [1]
 * 输出：[[1],
 * *     [0]]
 * 示例 5：
 * 输入：rowSum = [0], colSum = [0]
 * 输出：[[0]]
 * 提示：
 * 1 <= rowSum.length, colSum.length <= 500
 * 0 <= rowSum[i], colSum[i] <= 10^8
 * sum(rowSum) == sum(colSum)
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/3/14
 */
public class RestoreMatrix {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length, n = colSum.length;
        int[][] ans = new int[m][n], map = new int[m + n][];
        // [值,index,是否删除0已删除,1未删除]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < m; i++) {
            int value = rowSum[i];
            if (value > 0) {
                int[] data = new int[] {value, i, 1};
                pq.offer(data);
                map[i] = data;
            }
        }
        for (int i = 0; i < n; i++) {
            int value = colSum[i];
            if (value > 0) {
                int[] data = new int[] {value, m + i, 1};
                pq.offer(data);
                map[m + i] = data;
            }
        }
        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            if (data[2] == 0) {continue;}
            int value = data[0], dataIndex = data[1], inputI, inputJ, index;
            if (dataIndex < m) {
                inputI = dataIndex;
                inputJ = 0;
                while (ans[inputI][inputJ] != 0 || map[m + inputJ] == null) {inputJ++;}
                index = m + inputJ;
            } else {
                inputI = 0;
                inputJ = dataIndex - m;
                while (ans[inputI][inputJ] != 0 || map[inputI] == null) {inputI++;}
                index = inputI;
            }
            ans[inputI][inputJ] = value;
            map[dataIndex] = null;
            map[index][2] = 0;
            if (map[index][0] == value) {
                map[index] = null;
            } else if (map[index][0] > value) {
                map[index] = new int[] {map[index][0] - value, index, 1};
                pq.offer(map[index]);
            }
        }
        return ans;
    }

    public int[][] restoreMatrix2(int[] rowSum, int[] colSum) {
        int m = rowSum.length, n = colSum.length;
        int[][] ans = new int[m][n], map = new int[m + n][];
        int[] pointers = new int[m + n];
        // [值,index,是否删除0已删除,1未删除]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < m; i++) {
            int value = rowSum[i];
            int[] data = new int[] {value, i, 1};
            if (value > 0) {pq.offer(data);}
            map[i] = data;
        }
        for (int i = 0; i < n; i++) {
            int value = colSum[i];
            int[] data = new int[] {value, m + i, 1};
            if (value > 0) {pq.offer(data);}
            map[m + i] = data;
        }
        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            if (data[2] == 0) {continue;}
            int value = data[0], dataIndex = data[1], inputI, inputJ, index;
            if (dataIndex < m) {
                inputI = dataIndex;
                inputJ = pointers[dataIndex];
                while (ans[inputI][inputJ] != 0 || map[m + inputJ][0] == 0) {inputJ++;}
                pointers[dataIndex] = inputJ;
                index = m + inputJ;
            } else {
                inputI = pointers[dataIndex];
                inputJ = dataIndex - m;
                while (ans[inputI][inputJ] != 0 || map[inputI][0] == 0) {inputI++;}
                pointers[dataIndex] = inputI;
                index = inputI;
            }
            ans[inputI][inputJ] = value;
            data[0] = 0;
            map[index][2] = 0;
            if (map[index][0] - value >= 0) {
                map[index] = new int[] {map[index][0] - value, index, 1};
                if (map[index][0] > 0) {
                    pq.offer(map[index]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] rowSum = {4, 12, 10, 1, 0};
        int[] colSum = {1, 0, 3, 16, 7};
        RestoreMatrix rm = new RestoreMatrix();
        System.out.println(Arrays.deepToString(rm.restoreMatrix(rowSum, colSum)));
    }
}
