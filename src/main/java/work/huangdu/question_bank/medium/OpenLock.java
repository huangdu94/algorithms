package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * 752. 打开转盘锁
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 * 示例 1:
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 * 示例 2:
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 * 示例 3:
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：
 * 无法旋转到目标数字且不被锁定。
 * 示例 4:
 * 输入: deadends = ["0000"], target = "8888"
 * 输出：-1
 * 提示：
 * 1 <= deadends.length <= 500
 * deadends[i].length == 4
 * target.length == 4
 * target 不在 deadends 之中
 * target 和 deadends[i] 仅由若干位数字组成
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/6/25
 */
public class OpenLock {
    private static final Map<Integer, List<Integer>> GRAPH = new HashMap<>();

    static {
        char[] chars = new char[4];
        for (chars[0] = '0'; chars[0] <= '9'; chars[0]++) {
            for (chars[1] = '0'; chars[1] <= '9'; chars[1]++) {
                for (chars[2] = '0'; chars[2] <= '9'; chars[2]++) {
                    for (chars[3] = '0'; chars[3] <= '9'; chars[3]++) {
                        GRAPH.put(getNodeIndex(chars), getNextNodeIndexList(chars));
                    }
                }
            }
        }
    }

    public int openLock(String[] deadends, String target) {
        Set<Integer> visited = new HashSet<>();
        for (String deadend : deadends) {
            visited.add(Integer.parseInt(deadend));
        }
        if (visited.contains(0)) {return -1;}
        return bfs(visited, Integer.parseInt(target));
        // return dfs(0, visited, 0, Integer.parseInt(target));
    }

    private int bfs(Set<Integer> visited, Integer target) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        int len = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.remove();
                if (cur == target) { return len; }
                for (Integer next : GRAPH.get(cur)) {
                    if (visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }
            len++;
        }
        return -1;
    }

    // 传统DFS方法不行 应该用启发式搜索
    private int dfs(int cur, Set<Integer> visited, int depth, int target) {
        if (cur == target) {return depth;}
        int res = -1;
        for (int next : GRAPH.get(cur)) {
            if (visited.add(next)) {
                int d = dfs(next, visited, depth + 1, target);
                if (d != -1) {
                    if (res == -1) {
                        res = d;
                    } else {
                        res = Math.min(res, d);
                    }
                }
            }
        }
        return res;
    }

    private static int getNodeIndex(char[] chars) {
        int index = 0;
        for (int i = 0; i < 4; i++) {
            index = index * 10 + (chars[i] - '0');
        }
        return index;
    }

    private static List<Integer> getNextNodeIndexList(char[] chars) {
        List<Integer> nextList = new ArrayList<>(8);
        for (int i = 0; i < 4; i++) {
            char c = chars[i];
            chars[i] = c - 1 < '0' ? '9' : (char)(c - 1);
            nextList.add(getNodeIndex(chars));
            chars[i] = c + 1 > '9' ? '0' : (char)(c + 1);
            nextList.add(getNodeIndex(chars));
            chars[i] = c;
        }
        return nextList;
    }

    public static void main(String[] args) {
        OpenLock openLock = new OpenLock();
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        System.out.println(openLock.openLock(deadends, target));
    }

    // TODO 启发式搜索 有时间自己写一遍
    static class Solution {
        public int openLock(String[] deadends, String target) {
            if ("0000".equals(target)) {
                return 0;
            }
            Set<String> dead = new HashSet<>(Arrays.asList(deadends));
            if (dead.contains("0000")) { return -1; }
            PriorityQueue<AStar> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.f));
            pq.offer(new AStar("0000", target, 0));
            Set<String> seen = new HashSet<>();
            seen.add("0000");

            while (!pq.isEmpty()) {
                AStar node = pq.poll();
                for (String nextStatus : get(node.status)) {
                    if (!seen.contains(nextStatus) && !dead.contains(nextStatus)) {
                        if (nextStatus.equals(target)) {
                            return node.g + 1;
                        }
                        pq.offer(new AStar(nextStatus, target, node.g + 1));
                        seen.add(nextStatus);
                    }
                }
            }

            return -1;
        }

        public char numPrev(char x) {
            return x == '0' ? '9' : (char)(x - 1);
        }

        public char numSucc(char x) {
            return x == '9' ? '0' : (char)(x + 1);
        }

        // 枚举 status 通过一次旋转得到的数字
        public List<String> get(String status) {
            List<String> ret = new ArrayList<String>();
            char[] array = status.toCharArray();
            for (int i = 0; i < 4; ++i) {
                char num = array[i];
                array[i] = numPrev(num);
                ret.add(new String(array));
                array[i] = numSucc(num);
                ret.add(new String(array));
                array[i] = num;
            }
            return ret;
        }
    }

    static class AStar {
        String status;
        int f, g, h;

        public AStar(String status, String target, int g) {
            this.status = status;
            this.g = g;
            this.h = getH(status, target);
            this.f = this.g + this.h;
        }

        // 计算启发函数
        public static int getH(String status, String target) {
            int ret = 0;
            for (int i = 0; i < 4; ++i) {
                int dist = Math.abs(status.charAt(i) - target.charAt(i));
                ret += Math.min(dist, 10 - dist);
            }
            return ret;
        }
    }
}