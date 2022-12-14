package work.huangdu.question_bank.difficult;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1697. 检查边长度限制的路径是否存在
 * 给你一个 n 个点组成的无向图边集 edgeList ，其中 edgeList[i] = [u_i, v_i, dis_i] 表示点 u_i 和点 v_i 之间有一条长度为 dis_i 的边。请注意，两个点之间可能有 超过一条边 。
 * 给你一个查询数组queries ，其中 queries[j] = [p_j, q_j, limit_j] ，你的任务是对于每个查询 queries[j] ，判断是否存在从 p_j 到 q_j 的路径，且这条路径上的每一条边都 严格小于 limit_j 。
 * 请你返回一个 布尔数组 answer ，其中 answer.length == queries.length ，当 queries[j] 的查询结果为 true 时， answer 第 j 个值为 true ，否则为 false 。
 * 示例 1：
 * 输入：n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
 * 输出：[false,true]
 * 解释：上图为给定的输入数据。注意到 0 和 1 之间有两条重边，分别为 2 和 16 。
 * 对于第一个查询，0 和 1 之间没有小于 2 的边，所以我们返回 false 。
 * 对于第二个查询，有一条路径（0 -> 1 -> 2）两条边都小于 5 ，所以这个查询我们返回 true 。
 * 示例 2：
 * 输入：n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
 * 输出：[true,false]
 * 解释：上图为给定数据。
 * 提示：
 * 2 <= n <= 10^5
 * 1 <= edgeList.length, queries.length <= 10^5
 * edgeList[i].length == 3
 * queries[j].length == 3
 * 0 <= u_i, v_i, p_j, qj <= n - 1
 * u_i != v_i
 * p_j != q_j
 * 1 <= dis_i, limit_j <= 10^9
 * 两个点之间可能有 多条 边。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/12/14
 */
public class DistanceLimitedPathsExist {
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int edgeListLen = edgeList.length, queriesLen = queries.length, k = 0;
        int[] parent = new int[n];
        Integer[] index = new Integer[queriesLen];
        for (int i = 0; i < n; i++) {parent[i] = i;}
        for (int i = 0; i < queriesLen; i++) {index[i] = i;}
        Arrays.sort(edgeList, Comparator.comparingInt(o -> o[2]));
        Arrays.sort(index, Comparator.comparingInt(i -> queries[i][2]));
        boolean[] ans = new boolean[queriesLen];
        for (int i : index) {
            int[] query = queries[i];
            int p = query[0], q = query[1], limit = query[2];
            while (k < edgeListLen && edgeList[k][2] < limit) {
                merge(parent, edgeList[k][0], edgeList[k][1]);
                k++;
            }
            ans[i] = find(parent, p) == find(parent, q);
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        if (parent[x] == x) {return x;}
        return parent[x] = find(parent, parent[x]);
    }

    private void merge(int[] parent, int x, int y) {
        int parentX = find(parent, x);
        int parentY = find(parent, y);
        if (parentX == parentY) {return;}
        parent[parentX] = parentY;
    }
}