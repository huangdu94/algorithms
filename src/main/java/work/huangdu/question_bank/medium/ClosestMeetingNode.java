package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 2359. 找到离给定两个节点最近的节点
 * ClosestMeetingNode
 *
 * @author huangdu
 * @version 2025/5/30
 */
public class ClosestMeetingNode {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length, ans = -1, min = n;
        int[] visited1 = traversal(n, edges, node1), visited2 = traversal(n, edges, node2);
        for (int i = 0; i < n; i++) {
            int distance = Math.max(visited1[i], visited2[i]);
            if (min > distance) {
                ans = i;
                min = distance;
            }
        }
        return min == n ? -1 : ans;
    }

    private int[] traversal(int n, int[] edges, int start) {
        int[] visited = new int[n];
        Arrays.fill(visited, n);
        int distance = 0;
        while (start != -1 && visited[start] == n) {
            visited[start] = distance++;
            start = edges[start];
        }
        return visited;
    }
}
