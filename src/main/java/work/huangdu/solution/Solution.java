package work.huangdu.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * Solution
 *
 * @author huangdu
 * @version 2025/5/27
 */
public class Solution {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        Map<Integer, Integer> visited1 = traversal(edges, node1), visited2 = traversal(edges, node2);
        int ans = -1, min = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : visited1.entrySet()) {
            int node = entry.getKey();
            if (visited2.containsKey(node)) {
                int distance = Math.max(entry.getValue(), visited2.get(node));
                if (min > distance) {
                    ans = node;
                    min = distance;
                }
            }
        }
        return ans;
    }

    private Map<Integer, Integer> traversal(int[] edges, int start) {
        Map<Integer, Integer> visited = new HashMap<>();
        int distance = 0;
        while (start != -1 && !visited.containsKey(start)) {
            visited.put(start, distance++);
            start = edges[start];
        }
        return visited;
    }


    public static void main(String[] args) {
        int[] edges = {14, 6, 6, 6, 11, 8, 15, 15, 1, 13, 17, 17, 2, 16, 15, 11, 7, 0};
        Solution solution = new Solution();
        System.out.println(solution.closestMeetingNode(edges, 3, 10));
    }
}
