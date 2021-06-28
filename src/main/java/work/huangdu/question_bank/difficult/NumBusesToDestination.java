package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 815. 公交路线
 * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
 * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
 * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
 * 示例 1：
 * 输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * 输出：2
 * 解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
 * 示例 2：
 * 输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * 输出：-1
 * 提示：
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 10^5
 * routes[i] 中的所有值 互不相同
 * sum(routes[i].length) <= 10^5
 * 0 <= routes[i][j] < 10^6
 * 0 <= source, target < 10^6
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/6/28
 */
public class NumBusesToDestination {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {return 0;}
        int n = routes.length;
        // key: no value: stations
        Map<Integer, List<Integer>> routeMap = new HashMap<>(n);
        // key: station value: nos
        Map<Integer, List<Integer>> numberMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int[] route = routes[i];
            List<Integer> stationList = new ArrayList<>(route.length);
            for (int station : route) {
                stationList.add(station);
                List<Integer> list = numberMap.computeIfAbsent(station, k -> new ArrayList<>());
                list.add(i);
            }
            routeMap.put(i, stationList);
        }
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int no : routeMap.keySet()) {
            List<Integer> stations = routeMap.get(no);
            Set<Integer> nexts = new HashSet<>();
            for (int station : stations) {
                List<Integer> next = numberMap.get(station);
                for (int i : next) {
                    if (i != no) {
                        nexts.add(i);
                    }
                }
            }
            graph.put(no, nexts);
        }
        List<Integer> sourceNo = numberMap.get(source);
        if (sourceNo == null) {return -1;}
        Set<Integer> targetNo = new HashSet<>(numberMap.getOrDefault(target, new ArrayList<>()));
        if (targetNo.isEmpty()) {return -1;}
        Queue<Integer> queue = new ArrayDeque<>(sourceNo);
        Set<Integer> visited = new HashSet<>(sourceNo);
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.remove();
                if (targetNo.contains(cur)) {return count;}
                for (int next : graph.get(cur)) {
                    if (visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }
            count++;
        }
        return -1;
    }

    public static void main(String[] args) {
        //int[][] routes = {{1, 2, 7}, {3, 6, 7}};
        //int source = 1;
        //int target = 6;
        int[][] routes = {{7, 12}, {4, 5, 15}, {6}, {15, 19}, {9, 12, 13}};
        int source = 15;
        int target = 12;
        NumBusesToDestination nbtd = new NumBusesToDestination();
        System.out.println(nbtd.numBusesToDestination(routes, source, target));
    }
}
