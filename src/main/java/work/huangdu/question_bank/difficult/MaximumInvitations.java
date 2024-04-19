package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2127. 参加会议的最多员工数
 * 一个公司准备组织一场会议，邀请名单上有 n 位员工。公司准备了一张 圆形 的桌子，可以坐下 任意数目 的员工。
 * 员工编号为 0 到 n - 1 。每位员工都有一位 喜欢 的员工，每位员工 当且仅当 他被安排在喜欢员工的旁边，他才会参加会议。每位员工喜欢的员工 不会 是他自己。
 * 给你一个下标从 0 开始的整数数组 favorite ，其中 favorite[i] 表示第 i 位员工喜欢的员工。请你返回参加会议的 最多员工数目 。
 * 示例 1：
 * 输入：favorite = [2,2,1,2]
 * 输出：3
 * 解释：
 * 上图展示了公司邀请员工 0，1 和 2 参加会议以及他们在圆桌上的座位。
 * 没办法邀请所有员工参与会议，因为员工 2 没办法同时坐在 0，1 和 3 员工的旁边。
 * 注意，公司也可以邀请员工 1，2 和 3 参加会议。
 * 所以最多参加会议的员工数目为 3 。
 * 示例 2：
 * 输入：favorite = [1,2,0]
 * 输出：3
 * 解释：
 * 每个员工都至少是另一个员工喜欢的员工。所以公司邀请他们所有人参加会议的前提是所有人都参加了会议。
 * 座位安排同图 1 所示：
 * - 员工 0 坐在员工 2 和 1 之间。
 * - 员工 1 坐在员工 0 和 2 之间。
 * - 员工 2 坐在员工 1 和 0 之间。
 * 参与会议的最多员工数目为 3 。
 * 示例 3：
 * 输入：favorite = [3,0,1,4,1]
 * 输出：4
 * 解释：
 * 上图展示了公司可以邀请员工 0，1，3 和 4 参加会议以及他们在圆桌上的座位。
 * 员工 2 无法参加，因为他喜欢的员工 0 旁边的座位已经被占领了。
 * 所以公司只能不邀请员工 2 。
 * 参加会议的最多员工数目为 4 。
 * 提示：
 * n == favorite.length
 * 2 <= n <= 10^5
 * 0 <= favorite[i] <= n - 1
 * favorite[i] != i
 *
 * @author huangdu
 */
public class MaximumInvitations {
    public int maximumInvitations(int[] favorite) {
        int ans = 0, n = favorite.length, cache = 0;
        List<Integer>[] previous = new List[n];
        Arrays.setAll(previous, (o) -> new ArrayList<>());
        for (int i = 0; i < n; i++) {previous[favorite[i]].add(i);}
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) {continue;}
            int cur = i;
            while (!visited[cur]) {
                visited[cur] = true;
                cur = favorite[cur];
            }
            int end = cur, length = 0, branch = 0;
            cur = i;
            while (cur != end) {
                visited[cur] = false;
                cur = favorite[cur];
            }
            do {
                length++;
                branch += dfs(cur, previous, visited);
                cur = favorite[cur];
            } while (cur != end);
            if (length == 2) {
                cache += length + branch;
            } else {
                ans = Math.max(ans, length);
            }
        }
        return Math.max(ans, cache);
    }

    private int dfs(int node, List<Integer>[] previous, boolean[] visited) {
        if (previous[node].isEmpty() || previous[node].size() == 1 && visited[previous[node].get(0)]) {return 0;}
        int max = 0;
        for (int prev : previous[node]) {
            if (visited[prev]) {continue;}
            visited[prev] = true;
            max = Math.max(max, dfs(prev, previous, visited));
        }
        return max + 1;
    }

    public static void main(String[] args) {
        MaximumInvitations mi = new MaximumInvitations();
        int[] favorite = {6, 4, 4, 5, 0, 3, 3};
        System.out.println(mi.maximumInvitations(favorite));
    }
}