package work.huangdu.competition.biweekly;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/4/10
 */
public class SolutionZhaoShang {
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
