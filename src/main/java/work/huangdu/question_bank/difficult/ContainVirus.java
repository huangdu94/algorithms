package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 749. 隔离病毒
 * 病毒扩散得很快，现在你的任务是尽可能地通过安装防火墙来隔离病毒。
 * 假设世界由 m x n 的二维矩阵 isInfected 组成， isInfected[i][j] == 0 表示该区域未感染病毒，而  isInfected[i][j] == 1 表示该区域已感染病毒。可以在任意 2 个相邻单元之间的共享边界上安装一个防火墙（并且只有一个防火墙）。
 * 每天晚上，病毒会从被感染区域向相邻未感染区域扩散，除非被防火墙隔离。现由于资源有限，每天你只能安装一系列防火墙来隔离其中一个被病毒感染的区域（一个区域或连续的一片区域），且该感染区域对未感染区域的威胁最大且 保证唯一 。
 * 你需要努力使得最后有部分区域不被病毒感染，如果可以成功，那么返回需要使用的防火墙个数; 如果无法实现，则返回在世界被病毒全部感染时已安装的防火墙个数。
 * 示例 1：
 * 输入: isInfected = [[0,1,0,0,0,0,0,1],[0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0]]
 * 输出: 10
 * 解释:一共有两块被病毒感染的区域。
 * 在第一天，添加 5 墙隔离病毒区域的左侧。病毒传播后的状态是:
 * 第二天，在右侧添加 5 个墙来隔离病毒区域。此时病毒已经被完全控制住了。
 * 示例 2：
 * 输入: isInfected = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出: 4
 * 解释: 虽然只保存了一个小区域，但却有四面墙。
 * 注意，防火墙只建立在两个不同区域的共享边界上。
 * 示例 3:
 * 输入: isInfected = [[1,1,1,0,0,0,0,0,0],[1,0,1,0,1,1,1,1,1],[1,1,1,0,0,0,0,0,0]]
 * 输出: 13
 * 解释: 在隔离右边感染区域后，隔离左边病毒区域只需要 2 个防火墙。
 * 提示:
 * m == isInfected.length
 * n == isInfected[i].length
 * 1 <= m, n <= 50
 * isInfected[i][j] is either 0 or 1
 * 在整个描述的过程中，总有一个相邻的病毒区域，它将在下一轮 严格地感染更多未受污染的方块
 *
 * @author huangdu
 * @version 2022/8/27
 */
public class ContainVirus {
    public int containVirus(int[][] isInfected) {
        int[] directions = {-1, 0, 1, 0, -1};
        int m = isInfected.length, n = isInfected[0].length;
        int ans = 0;
        while (true) {
            // 记录每一片病毒区域，下一次扩散的病毒位置
            List<Set<Integer>> neighbors = new ArrayList<>();
            // 记录每一片病毒区域，完全隔离需要安装的防火墙数量
            List<Integer> firewalls = new ArrayList<>();
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (isInfected[i][j] == 1) {
                        Queue<Integer> queue = new ArrayDeque<>();
                        queue.offer((i << 16) ^ j);
                        Set<Integer> neighbor = new HashSet<>();
                        // 每一片连续病毒区域采用不同的标记，标记已经遍历过的节点
                        int firewall = 0, mark = -(neighbors.size() + 1);
                        isInfected[i][j] = mark;
                        while (!queue.isEmpty()) {
                            int hash = queue.poll();
                            int x = hash >> 16, y = hash & ((1 << 16) - 1);
                            for (int k = 0; k < 4; ++k) {
                                int nx = x + directions[k], ny = y + directions[k + 1];
                                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                    if (isInfected[nx][ny] == 1) {
                                        queue.offer((nx << 16) ^ ny);
                                        isInfected[nx][ny] = mark;
                                    } else if (isInfected[nx][ny] == 0) {
                                        ++firewall;
                                        neighbor.add((nx << 16) ^ ny);
                                    }
                                }
                            }
                        }
                        neighbors.add(neighbor);
                        firewalls.add(firewall);
                    }
                }
            }
            // 如果没有任何一块病毒区域，则直接返回
            if (neighbors.isEmpty()) {break;}
            // 找到威胁最大的区域，即为下一次扩散，扩散格子最多的区域
            int idx = 0;
            for (int i = 1; i < neighbors.size(); ++i) {
                if (neighbors.get(i).size() > neighbors.get(idx).size()) {
                    idx = i;
                }
            }
            // 将威胁最大的区域全部封锁，将防火墙数量累加到结果中
            ans += firewalls.get(idx);
            // 如果仅剩下一片病毒区域，那么封锁完后计算就结束了，没必要进行接下来的计算了
            if (neighbors.size() == 1) {break;}
            int mark = -(idx + 1);
            // 将已封锁的病毒区域中的格子标记为2，其它为负数的格子恢复成0
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (isInfected[i][j] < 0) {
                        if (isInfected[i][j] != mark) {
                            isInfected[i][j] = 1;
                        } else {
                            isInfected[i][j] = 2;
                        }
                    }
                }
            }
            // 完成病毒扩散
            for (int i = 0; i < neighbors.size(); ++i) {
                if (i != idx) {
                    for (int val : neighbors.get(i)) {
                        int x = val >> 16, y = val & ((1 << 16) - 1);
                        isInfected[x][y] = 1;
                    }
                }
            }
        }
        return ans;
    }
}
