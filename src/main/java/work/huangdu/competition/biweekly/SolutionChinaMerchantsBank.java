package work.huangdu.competition.biweekly;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 招商银行专场竞赛
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/4/10
 */
public class SolutionChinaMerchantsBank {
    // TODO 待研究
    /**
     * 招商银行-01. 文本编辑程序设计
     * 请你设计一个文本编辑程序，需要编辑的稿件 article 为仅由大写字母、小写字母与空格组成的字符串（下标从 0 开始），光标所在字符串下标位置记作 index，程序运行后，若光标停留位置为空格，不作操作，返回原文本；否则光标所在位置对应的整个单词将被删除，并返回编辑后经过整理空格的文本。
     * 注意：
     * 输入保证字符串不以空格开头和结尾且不包含连续的空格。
     * 返回的字符串不以空格开头和结尾且不包含连续的空格。若删除单词后有多余的空格，需要删除。
     * 示例 1：
     * 输入：article = "Singing dancing in the rain", index = 10
     * 输出："Singing in the rain"
     * 解释：
     * "Singing dancing in the rain" 光标位于 "dancing" 单词的字符 'n'
     * 删除光标所在的单词 "dancing" 及其前或后的一个空格。
     * 示例 2：
     * 输入：article = "Hello World", index = 2
     * 输出："World"
     * 解释：删除 article[2] 所在的单词 "Hello" 及其后方空格。
     * 示例 3：
     * 输入：article = "Hello World", index = 5
     * 输出："Hello World"
     * 解释：光标位于空格处，不作处理。
     * 提示：
     * 1 <= article.length <= 10^5
     * 0 <= index < article.length
     * article[i] 仅包含大写字母、小写字母与空格
     */
    class DeleteText {
        public String deleteText(String article, int index) {
            if (article.charAt(index) == ' ') {return article;}
            int n = article.length(), i = index, j = index;
            while (i > 0 && article.charAt(i) != ' ') {
                i--;
            }
            while (j < n && article.charAt(j) != ' ') {
                j++;
            }
            if (i == 0 && j == n) {return "";}
            if (i == 0) {
                return article.substring(j + 1);
            }
            if (j == n) {
                return article.substring(0, i);
            }
            return article.substring(0, i) + article.substring(j);
        }
    }

    /**
     * 招商银行-02. 公园规划
     * 公园规划小组为了让公园景观层次丰富，决定按以下方案对各花坛内的植物进行统一规划：
     * 一条小路两端的花坛不能栽种同一种花
     * 与同一花坛相连的两个花坛也不能栽种同一种花。
     * 已知公园内有编号为 0 ~ num-1的若干花坛，任意两个花坛均可通过小路直接或间接到达。
     * 公园中共有 num-1 条双向小路连接花坛，roads[i] = [x, y] 表示花坛 x 和花坛 y 之间存在小路将二者相连。
     * 请返回这些花坛最少需要几种花。
     * 示例 1：
     * 输入：roads = [[0,1],[1,3],[1,2]]
     * 输出：4
     * 解释：4 个花坛中编号为 1 的花坛与其余花坛均相连，因此至少需要栽种 4 种的花。
     * 示例 2：
     * 输入：roads = [[0,1],[0,2],[1,3],[2,5],[3,6],[5,4]]
     * 输出：3
     * 提示：
     * 1 <= roads.length <= 10^5
     * 0 <= roads[i][0],roads[i][1] <= roads.length
     */
    class NumFlowers {
        public int numFlowers(int[][] roads) {
            int n = roads.length + 1;
            int[] degrees = new int[n];
            for (int[] road : roads) {
                degrees[road[0]]++;
                degrees[road[1]]++;
            }
            int max = 0;
            for (int degree : degrees) {
                max = Math.max(max, degree);
            }
            return max + 1;
        }
    }

    public int[] lightSticks(int height, int width, int[] indices) {
        int m = height + 1, n = width + 1;
        int[][] degrees = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(degrees[i], 4);
        }
        for (int i = 0; i < m; i++) {
            degrees[i][0]--;
            degrees[i][n - 1]--;
        }
        for (int j = 0; j < n; j++) {
            degrees[n - 1][j]--;
            degrees[m - 1][j]--;
        }
        degrees[0][0]--;
        degrees[0][n - 1]--;
        degrees[m - 1][0]--;
        degrees[m - 1][n - 1]--;
        List<Integer>[] edges = new List[m * n];
        for (int i = 0; i < m; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<Integer> nextList = new ArrayList<>();
                if (i - 1 >= 0) {
                    nextList.add((i - 1) * n + j);
                }
                if (i + 1 < m) {
                    nextList.add((i + 1) * n + j);
                }
                if (j - 1 >= 0) {
                    nextList.add(i * n + j - 1);
                }
                if (j + 1 < n) {
                    nextList.add(i * n + j + 1);
                }
            }
        }
        for (int indice : indices) {
            int[] edge = convert(indice, width);
            int i0 = edge[0] / n, j0 = edge[0] % n, i1 = edge[1] / n, j1 = edge[1] % n;
            degrees[i0][j0]--;
            degrees[i1][j1]--;
            edges[edge[0]].remove(edge[1]);
            edges[edge[1]].remove(edge[0]);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (degrees[i][j] != 0) {
                    Queue<Integer> queue = new ArrayDeque<>();
                    boolean[] visited = new boolean[m * n];
                    queue.offer(i * n + j);
                    visited[i * n + j] = true;
                    while (!queue.isEmpty()) {
                        int cur = queue.poll();
                        for (int next : edges[cur]) {
                            if (!visited[next]) {
                                visited[next] = true;
                                queue.offer(next);
                            }
                        }
                    }
                    for (int k = 0; k < m * n; k++) {
                        if (!visited[k] && degrees[k / n][k % n] != 0) {
                            return new int[0];
                        }
                    }
                }
            }
        }
        // 如果剩下的非0degree都相同则全都返回
        int standard = -1;
        List<Integer> ansList = new ArrayList<>();
        boolean flag = true;
        boolean haveZero = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (degrees[i][j] != 0) {
                    if (standard == -1) {
                        standard = degrees[i][j];
                    } else {
                        if (standard != degrees[i][j]) {
                            flag = false;
                            break;
                        }
                    }
                    ansList.add(i * n + j);
                }
            }
            if (!flag) {break;}
        }
        if (flag) {
            int[] ans = new int[ansList.size()];
            for (int i = 0; i < ans.length; i++) {
                ans[i] = ansList.get(i);
            }
            return ans;
        }

        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> degrees[o / n][o % n]));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (degrees[i][j] != 0) {
                    queue.offer(i * n + j);
                }
            }
        }
        // TODO 放弃 超过能力范围
        return new int[0];
    }

    private int[] convert(int indice, int width) {
        int i = indice / (2 * width + 1), j = indice % (2 * width + 1);
        boolean h = j < width;
        if (j >= width) {
            j -= width;
        }
        if (h) {
            return new int[] {i * (width + 1) + j, i * (width + 1) + j + 1};
        }
        return new int[] {i * (width + 1) + j, (i + 1) * (width + 1) + j};
    }

    private int covert(int[] edge, int width) {
        int node0 = edge[0], node1 = edge[1], i0 = node0 / (width + 1), j0 = node0 % (width + 1), i1 = node1 / (width + 1);
        if (i0 == i1) {
            return i0 * (2 * width + 1) + j0;
        } else {
            return Math.min(i0, i1) * (2 * width + 1) + j0 + width;
        }
    }
}
