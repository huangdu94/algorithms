package work.huangdu.exploration.start_from_scratch.graph_search.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * 332. 重新安排行程
 * 给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。
 * 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
 * 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。
 * 示例 1：
 * 输入：tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * 输出：["JFK","MUC","LHR","SFO","SJC"]
 * 示例 2：
 * 输入：tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"] ，但是它字典排序更大更靠后。
 * 提示：
 * 1 <= tickets.length <= 300
 * tickets[i].length == 2
 * from_i.length == 3
 * to_i.length == 3
 * from_i 和 to_i 由大写英文字母组成
 * from_i != to_i
 *
 * @author huangdu
 * @version 2022/9/27
 */
public class FindItinerary {
    private int total;
    private int used;
    private Map<String, TreeMap<String, Integer>> edges;
    private List<String> ans;

    public List<String> findItinerary(List<List<String>> tickets) {
        this.total = tickets.size();
        this.used = 0;
        this.edges = new HashMap<>();
        this.ans = new ArrayList<>(tickets.size() + 1);
        for (List<String> ticket : tickets) {
            String from = ticket.get(0), to = ticket.get(1);
            TreeMap<String, Integer> nextMap = edges.computeIfAbsent(from, k -> new TreeMap<>());
            nextMap.merge(to, 1, Integer::sum);
        }
        dfs("JFK");
        return ans;
    }

    private boolean dfs(String cur) {
        ans.add(cur);
        if (used == total) {return true;}
        if (edges.containsKey(cur)) {
            TreeMap<String, Integer> nextMap = edges.get(cur);
            for (String next : nextMap.keySet()) {
                if (nextMap.get(next) == 0) {continue;}
                nextMap.merge(next, -1, Integer::sum);
                used++;
                if (dfs(next)) {return true;}
                nextMap.merge(next, 1, Integer::sum);
                used--;
            }
        }
        ans.remove(ans.size() - 1);
        return false;
    }

    public List<String> findItinerary2(List<List<String>> tickets) {
        Map<String, Queue<String>> startDesMap = new HashMap<>();
        for (List<String> ticket : tickets) {
            //Queue<String> desQueue = startDesMap.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>());
            if (!startDesMap.containsKey(ticket.get(0))) {
                startDesMap.put(ticket.get(0), new PriorityQueue<>());
            }
            startDesMap.get(ticket.get(0)).offer(ticket.get(1));
            //desQueue.offer(ticket.get(1));
        }
        LinkedList<String> journey = new LinkedList<>();
        helper(startDesMap, journey, "JFK");
        return journey;
    }

    private void helper(Map<String, Queue<String>> startDesMap, LinkedList<String> journey, String start) {
        Queue<String> nextQueue = startDesMap.get(start);
        while (nextQueue != null && !nextQueue.isEmpty()) {
            helper(startDesMap, journey, nextQueue.poll());
        }
        journey.addFirst(start);
    }

    // 处理节点
/*    private void helper(Map<String, Queue<String>> startDesMap, List<String> trunk, String start, String end) {
        trunk.add(start);
        if (start.equals(end)) {
            return;
        }
        Queue<String> desQueue = startDesMap.get(start);
        // 如果可选目的地址只有一个，则直接加入trunk最终行程
        while (desQueue != null && desQueue.size() == 1) {
            String next = desQueue.poll();
            trunk.add(next);
            desQueue = startDesMap.get(next);
        }
        // 进入分支处理
        if (desQueue != null && desQueue.size() > 1) {
            // 多个分支最多只会有一个最终不会回到start，该分支存到branch里，其它肯定都为环加入trunk中
            List<String> notRing = null;
            while (!desQueue.isEmpty()) {
                String cur = desQueue.poll();
                List<String> ring = new ArrayList<>();
                helper(startDesMap, ring, cur, start);
                if (ring.get(ring.size() - 1).equals(start)) {
                    trunk.addAll(ring);
                } else {
                    notRing = ring;
                }
            }
            if (notRing != null) {
                trunk.addAll(notRing);
            }
        }
    }*/

    public static void main(String[] args) {
        FindItinerary findItinerary = new FindItinerary();
        String input = "[[\"AXA\",\"ADL\"],[\"BNE\",\"AXA\"],[\"EZE\",\"OOL\"],[\"EZE\",\"CBR\"],[\"EZE\",\"AXA\"],[\"ADL\",\"AUA\"],[\"TIA\",\"MEL\"],[\"LST\",\"CBR\"],[\"MEL\",\"EZE\"],[\"LST\",\"AXA\"],[\"CNS\",\"ANU\"],[\"JFK\",\"ADL\"],[\"CBR\",\"JFK\"],[\"TIA\",\"ADL\"],[\"EZE\",\"OOL\"],[\"CNS\",\"MEL\"],[\"ADL\",\"TIA\"],[\"AXA\",\"LST\"],[\"AXA\",\"JFK\"],[\"DRW\",\"AXA\"],[\"ANU\",\"BIM\"],[\"BNE\",\"PER\"],[\"CNS\",\"MEL\"],[\"AUA\",\"AXA\"],[\"HBA\",\"LST\"],[\"MEL\",\"BNE\"],[\"ANU\",\"AXA\"],[\"AXA\",\"LST\"],[\"DRW\",\"AXA\"],[\"AXA\",\"TIA\"],[\"CNS\",\"JFK\"],[\"AUA\",\"TIA\"],[\"CNS\",\"ADL\"],[\"TIA\",\"JFK\"],[\"JFK\",\"ANU\"],[\"TIA\",\"AUA\"],[\"OOL\",\"PER\"],[\"OOL\",\"AUA\"],[\"AXA\",\"LST\"],[\"JFK\",\"BNE\"],[\"MEL\",\"DRW\"],[\"TIA\",\"CNS\"],[\"TIA\",\"EZE\"],[\"PER\",\"AUA\"],[\"OOL\",\"LST\"],[\"ADL\",\"BNE\"],[\"LST\",\"AUA\"],[\"EZE\",\"ADL\"],[\"LST\",\"HBA\"],[\"BNE\",\"ANU\"],[\"OOL\",\"EZE\"],[\"CNS\",\"TIA\"],[\"TIA\",\"HBA\"],[\"AUA\",\"EZE\"],[\"EZE\",\"AUA\"],[\"ADL\",\"EZE\"],[\"JFK\",\"AUA\"],[\"CBR\",\"CNS\"],[\"AUA\",\"LST\"],[\"AUA\",\"OOL\"],[\"ADL\",\"DRW\"],[\"HBA\",\"DRW\"],[\"AUA\",\"JFK\"],[\"LST\",\"AUA\"],[\"LST\",\"TIA\"],[\"LST\",\"JFK\"],[\"AUA\",\"AXA\"],[\"ANU\",\"LST\"],[\"AXA\",\"CNS\"],[\"AXA\",\"EZE\"],[\"JFK\",\"EZE\"],[\"PER\",\"ADL\"],[\"ADL\",\"LST\"],[\"BNE\",\"CNS\"],[\"ADL\",\"JFK\"],[\"AXA\",\"BNE\"],[\"JFK\",\"PER\"],[\"DRW\",\"OOL\"],[\"JFK\",\"TIA\"],[\"LST\",\"AXA\"],[\"CBR\",\"CNS\"],[\"EZE\",\"CBR\"],[\"LST\",\"BNE\"],[\"ANU\",\"MEL\"],[\"PER\",\"TIA\"],[\"AUA\",\"ADL\"],[\"JFK\",\"TIA\"],[\"BNE\",\"CNS\"],[\"CNS\",\"LST\"],[\"MEL\",\"ANU\"],[\"TIA\",\"CNS\"]]";
        List<List<String>> tickets = getTickets(input);
        List<String> res = findItinerary.findItinerary(tickets);
        System.out.println(res);
    }

    private static List<List<String>> getTickets(String sample) {
        sample = sample.replace(" ", "");
        // 去开头和结尾的[],按逗号分割成多个["JFK","MUC"]
        String[] subArray = sample.substring(2, sample.length() - 2).split("],\\[");
        List<List<String>> tickets = new ArrayList<>(subArray.length);
        for (String sub : subArray) {
            String[] location = sub.substring(1, sub.length() - 1).split("\",\"");
            tickets.add(Arrays.asList(location[0], location[1]));
        }
        return tickets;
    }
}
