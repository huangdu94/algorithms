package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * 675. 为高尔夫比赛砍树
 * 你被请来给一个要举办高尔夫比赛的树林砍树。树林由一个 m x n 的矩阵表示， 在这个矩阵中：
 * 0 表示障碍，无法触碰
 * 1 表示地面，可以行走
 * 比 1 大的数 表示有树的单元格，可以行走，数值表示树的高度
 * 每一步，你都可以向上、下、左、右四个方向之一移动一个单位，如果你站的地方有一棵树，那么你可以决定是否要砍倒它。
 * 你需要按照树的高度从低向高砍掉所有的树，每砍过一棵树，该单元格的值变为 1（即变为地面）。
 * 你将从 (0, 0) 点开始工作，返回你砍完所有树需要走的最小步数。 如果你无法砍完所有的树，返回 -1 。
 * 可以保证的是，没有两棵树的高度是相同的，并且你至少需要砍倒一棵树。
 * 示例 1：
 * 输入：forest = [[1,2,3],[0,0,4],[7,6,5]]
 * 输出：6
 * 解释：沿着上面的路径，你可以用 6 步，按从最矮到最高的顺序砍掉这些树。
 * 示例 2：
 * 输入：forest = [[1,2,3],[0,0,0],[7,6,5]]
 * 输出：-1
 * 解释：由于中间一行被障碍阻塞，无法访问最下面一行中的树。
 * 示例 3：
 * 输入：forest = [[2,3,4],[0,0,5],[8,7,6]]
 * 输出：6
 * 解释：可以按与示例 1 相同的路径来砍掉所有的树。
 * (0,0) 位置的树，可以直接砍去，不用算步数。
 * 提示：
 * m == forest.length
 * n == forest[i].length
 * 1 <= m, n <= 50
 * 0 <= forest[i][j] <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/5/23
 */
public class CutOffTree {
    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size(), n = forest.get(0).size();
        Queue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        for (List<Integer> row : forest) {
            for (int h : row) {
                if (h > 1) {pq.offer(h);}
            }
        }
        int[] directions = {0, 1, 0, -1, 0};
        int curX = 0, curY = 0, ans = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        while (!pq.isEmpty()) {
            int targetH = pq.poll();
            if (targetH == forest.get(curX).get(curY)) {continue;}
            int step = 0;
            boolean find = false;
            queue.offer(new int[] {curX, curY});
            visited.add(curX * n + curY);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int[] cur = queue.poll();
                    int x = cur[0], y = cur[1];
                    for (int k = 0; k < 4; k++) {
                        int nextX = x + directions[k], nextY = y + directions[k + 1];
                        if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && forest.get(nextX).get(nextY) > 0 && visited.add(nextX * n + nextY)) {
                            if (forest.get(nextX).get(nextY) == targetH) {
                                ans += step + 1;
                                curX = nextX;
                                curY = nextY;
                                find = true;
                                break;
                            }
                            queue.offer(new int[] {nextX, nextY});
                        }
                    }
                    if (find) {break;}
                }
                if (find) {break;}
                step++;
            }
            if (!find) {return -1;}
            queue.clear();
            visited.clear();
        }
        return ans;
    }

    public static void main(String[] args) {
        CutOffTree cot = new CutOffTree();
        List<List<Integer>> forest = Arrays.asList(Arrays.asList(4, 2, 3), Arrays.asList(0, 0, 1), Arrays.asList(7, 6, 5));
        System.out.println(cot.cutOffTree(forest));
    }
}
