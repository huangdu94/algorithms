package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 886. 可能的二分法
 * 给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
 * 给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和  bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
 * 示例 1：
 * 输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * 输出：true
 * 解释：group1 [1,4], group2 [2,3]
 * 示例 2：
 * 输入：n = 3, dislikes = [[1,2],[1,3],[2,3]]
 * 输出：false
 * 示例 3：
 * 输入：n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * 输出：false
 * 提示：
 * 1 <= n <= 2000
 * 0 <= dislikes.length <= 10^4
 * dislikes[i].length == 2
 * 1 <= dislikes[i][j] <= n
 * ai < bi
 * dislikes 中每一组都 不同
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/10/17
 */
public class PossibleBipartition {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, Set<Integer>> dislikeMap = new HashMap<>(n);
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] dislike : dislikes) {
            graph[dislike[0]].add(dislike[1]);
            graph[dislike[1]].add(dislike[0]);
        }
        // 分组 0 表示未分组 1 第一组 2 第二组
        int[] groups = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (groups[i] != 0) {continue;}
            if (!dfs(i, groups, graph, 1)) {return false;}
        }
        return true;
    }

    private boolean dfs(int cur, int[] groups, List<Integer>[] graph, int group) {
        groups[cur] = group;
        int anotherGroup = group == 1 ? 2 : 1;
        for (int next : graph[cur]) {
            if (groups[next] == anotherGroup) {continue;}
            if (groups[next] == group) {return false;}
            groups[next] = anotherGroup;
            if (!dfs(next, groups, graph, anotherGroup)) {return false;}
        }
        return true;
    }

    public static void main(String[] args) {
        PossibleBipartition pb = new PossibleBipartition();
        int n = 10;
        int[][] dislikes = {{4, 7}, {4, 8}, {5, 6}, {1, 6}, {3, 7}, {2, 5}, {5, 8}, {1, 2}, {4, 9}, {6, 10}, {8, 10}, {3, 6}, {2, 10}, {9, 10}, {3, 9}, {2, 3}, {1, 9}, {4, 6}, {5, 7}, {3, 8}, {1, 8}, {1, 7}, {2, 4}};
        System.out.println(pb.possibleBipartition(n, dislikes));
    }

    // 回溯超时
    class FailSolution {
        private int n;
        private Map<Integer, Set<Integer>> dislikeMap;
        private Set<Integer> groupA;
        private Set<Integer> groupB;

        private int[][] dislikes;

        public boolean possibleBipartition(int n, int[][] dislikes) {
            this.n = dislikes.length;
            this.dislikeMap = new HashMap<>(n);
            for (int[] dislike : dislikes) {
                dislikeMap.computeIfAbsent(dislike[0], k -> new HashSet<>()).add(dislike[1]);
                dislikeMap.computeIfAbsent(dislike[1], k -> new HashSet<>()).add(dislike[0]);
            }
            this.groupA = new HashSet<>(n);
            this.groupB = new HashSet<>(n);
            this.dislikes = dislikes;
            return backtrack(0);
        }

        private boolean backtrack(int idx) {
            if (idx == n) {return true;}
            int a = dislikes[idx][0], b = dislikes[idx][1];
            if (groupA.contains(a) && groupA.contains(b)) {return false;}
            if (groupB.contains(a) && groupB.contains(b)) {return false;}
            if (groupA.contains(a) && groupB.contains(b) || groupA.contains(b) && groupB.contains(a)) {return backtrack(idx + 1);}
            if (groupA.contains(a)) {
                groupB.add(b);
                if (backtrack(idx + 1)) {return true;}
                groupB.remove(b);
            }
            if (groupA.contains(b)) {
                groupB.add(a);
                if (backtrack(idx + 1)) {return true;}
                groupB.remove(a);
            }
            if (check(groupA, dislikeMap.get(a)) && check(groupB, dislikeMap.get(b))) {
                groupA.add(a);
                groupB.add(b);
                if (backtrack(idx + 1)) {return true;}
                groupA.remove(a);
                groupB.remove(b);
            }
            if (check(groupA, dislikeMap.get(b)) && check(groupB, dislikeMap.get(a))) {
                groupA.add(b);
                groupB.add(a);
                if (backtrack(idx + 1)) {return true;}
                groupA.remove(b);
                groupB.remove(a);
            }
            return false;
        }

        private boolean check(Set<Integer> group, Set<Integer> dislikeSet) {
            if (dislikeSet != null) {
                for (int num : group) {
                    if (dislikeSet.contains(num)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
