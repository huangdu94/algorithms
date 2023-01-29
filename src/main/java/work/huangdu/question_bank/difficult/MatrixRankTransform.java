package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * 1632. 矩阵转换后的秩
 * 给你一个 m x n 的矩阵 matrix ，请你返回一个新的矩阵 answer ，其中 answer[row][col] 是 matrix[row][col] 的秩。
 * 每个元素的 秩 是一个整数，表示这个元素相对于其他元素的大小关系，它按照如下规则计算：
 * 秩是从 1 开始的一个整数。
 * 如果两个元素 p 和 q 在 同一行 或者 同一列 ，那么：
 * 如果 p < q ，那么 rank(p) < rank(q)
 * 如果 p == q ，那么 rank(p) == rank(q)
 * 如果 p > q ，那么 rank(p) > rank(q)
 * 秩 需要越 小 越好。
 * 题目保证按照上面规则 answer 数组是唯一的。
 * 示例 1：
 * 输入：matrix = [[1,2],[3,4]]
 * 输出：[[1,2],[2,3]]
 * 解释：
 * matrix[0][0] 的秩为 1 ，因为它是所在行和列的最小整数。
 * matrix[0][1] 的秩为 2 ，因为 matrix[0][1] > matrix[0][0] 且 matrix[0][0] 的秩为 1 。
 * matrix[1][0] 的秩为 2 ，因为 matrix[1][0] > matrix[0][0] 且 matrix[0][0] 的秩为 1 。
 * matrix[1][1] 的秩为 3 ，因为 matrix[1][1] > matrix[0][1]， matrix[1][1] > matrix[1][0] 且 matrix[0][1] 和 matrix[1][0] 的秩都为 2 。
 * 示例 2：
 * 输入：matrix = [[7,7],[7,7]]
 * 输出：[[1,1],[1,1]]
 * 示例 3：
 * 输入：matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
 * 输出：[[4,2,3],[1,3,4],[5,1,6],[1,3,4]]
 * 示例 4：
 * 输入：matrix = [[7,3,6],[1,4,5],[9,8,2]]
 * 输出：[[5,1,4],[1,2,3],[6,3,1]]
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 500
 * -10^9 <= matrix[row][col] <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/1/29
 */
public class MatrixRankTransform {
    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] ans = new int[m][n];
        TreeMap<Integer, List<int[]>> numIndexsMap = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                numIndexsMap.computeIfAbsent(matrix[i][j], k -> new ArrayList<>()).add(new int[] {i, j});
            }
        }
        int[] rowMaxs = new int[m], colMaxs = new int[n], ranks = new int[m + n];
        UnionFindSet ufs = new UnionFindSet(m + n);
        for (List<int[]> coordsList : numIndexsMap.values()) {
            if (coordsList.size() == 1) {
                int i = coordsList.get(0)[0], j = coordsList.get(0)[1];
                ans[i][j] = rowMaxs[i] = colMaxs[j] = Math.max(rowMaxs[i], colMaxs[j]) + 1;
                continue;
            }
            for (int[] coords : coordsList) {
                ufs.union(coords[0], m + coords[1]);
            }
            for (int[] coords : coordsList) {
                int parent = ufs.find(coords[0]);
                ranks[parent] = Math.max(ranks[parent], Math.max(rowMaxs[coords[0]], colMaxs[coords[1]]) + 1);
            }
            for (int[] coords : coordsList) {
                int i = coords[0], j = coords[1];
                ans[i][j] = rowMaxs[i] = colMaxs[j] = ranks[ufs.find(i)];
            }
            for (int[] coords : coordsList) {
                ufs.clear(coords[0], m + coords[1]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = {{28, -13, 42, 49, -20, -9, -46, 21, -8, -25, -18, -39, -8, -13, 6, -7, -44, -33}, {-30, 9, 4, -45, 14, -7, 44, 47, -30, 9, -40, 43, -50, -15, 36, -1, 46, -35},
            {-24, 19, 2, -27, -20, 23, 6, -39, 32, 3, -14, -47, -36, 23, -10, 17, 32, 27}, {42, 9, 40, 11, -38, -23, 16, -13, -30, -3, -32, -5, -6, -35, 32, -1, 30, 33},
            {32, -33, 26, 49, -32, -25, 22, -7, 8, -1, -26, 21, -28, 31, 22, 33, 28, 47}, {-46, 37, -4, -1, -22, -35, -48, -37, -2, 37, -16, -5, 6, 17, -36, 3, 30, 41},
            {40, 19, 38, -3, -12, -29, 14, -7, -44, 19, -10, 49, -8, -5, -6, -31, -12, -49}, {6, -35, -16, -5, 22, -27, 24, 35, 2, 45, -8, -49, 10, -43, -36, 11, 34, -39},
            {-4, -21, 18, -7, 24, -21, 26, 1, 12, 15, 46, -35, -20, 7, -26, 1, 36, 39}, {-14, 33, -16, 43, 42, 49, 12, -17, -18, 49, -16, 3, -34, 49, -24, -29, -6, -47},
            {-4, -21, 46, 1, 8, -41, 18, -43, 4, 35, -46, 13, 4, 47, -30, -7, 4, 43}, {-18, -11, 4, -21, 38, 1, 32, -49, 10, 37, 12, 19, 2, -27, 32, -33, -46, 33},
            {-36, 11, -38, 17, -20, 15, 26, -39, -48, -29, -42, -15, -32, 35, -6, 49, 24, -21}, {-14, 33, -20, -41, -6, 5, 8, -49, -46, 41, -24, -21, -38, -35, 28, -1, 2, -43},
            {44, -37, 18, 45, 36, 23, -26, 21, 44, -21, -46, -47, 24, 35, 22, -7, 40, 47}, {10, -7, -16, -33, 38, -23, 0, -33, -38, -39, -16, 27, 14, 49, 24, 15, -38, -19},
            {36, -1, -6, -19, 4, -17, 46, -23, 8, -9, 42, -43, -48, 31, -30, -3, 0, 31}, {-10, -23, -20, 15, 30, -7, 16, 43, 18, 17, 32, -29, -38, 17, 24, -13, 6, -27}};
        MatrixRankTransform mrt = new MatrixRankTransform();
        System.out.println(Arrays.deepToString(mrt.matrixRankTransform(matrix)));
    }
}

class UnionFindSet {
    private int[] p;
    private int[] size;

    public UnionFindSet(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            if (size[pa] > size[pb]) {
                p[pb] = pa;
                size[pa] += size[pb];
            } else {
                p[pa] = pb;
                size[pb] += size[pa];
            }
        }
    }

    public void clear(int x, int y) {
        p[x] = x;
        p[y] = y;
        size[x] = size[y] = 1;
    }
}