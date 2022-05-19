package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 488. 祖玛游戏
 * 你正在参与祖玛游戏的一个变种。
 * 在这个祖玛游戏变体中，桌面上有 一排 彩球，每个球的颜色可能是：红色 'R'、黄色 'Y'、蓝色 'B'、绿色 'G' 或白色 'W' 。你的手中也有一些彩球。
 * 你的目标是 清空 桌面上所有的球。每一回合：
 * 从你手上的彩球中选出 任意一颗 ，然后将其插入桌面上那一排球中：两球之间或这一排球的任一端。
 * 接着，如果有出现 三个或者三个以上 且 颜色相同 的球相连的话，就把它们移除掉。
 * 如果这种移除操作同样导致出现三个或者三个以上且颜色相同的球相连，则可以继续移除这些球，直到不再满足移除条件。
 * 如果桌面上所有球都被移除，则认为你赢得本场游戏。
 * 重复这个过程，直到你赢了游戏或者手中没有更多的球。
 * 给你一个字符串 board ，表示桌面上最开始的那排球。另给你一个字符串 hand ，表示手里的彩球。请你按上述操作步骤移除掉桌上所有球，计算并返回所需的 最少 球数。如果不能移除桌上所有的球，返回 -1 。
 * 示例 1：
 * 输入：board = "WRRBBW", hand = "RB"
 * 输出：-1
 * 解释：无法移除桌面上的所有球。可以得到的最好局面是：
 * - 插入一个 'R' ，使桌面变为 WRRRBBW 。WRRRBBW -> WBBW
 * - 插入一个 'B' ，使桌面变为 WBBBW 。WBBBW -> WW
 * 桌面上还剩着球，没有其他球可以插入。
 * 示例 2：
 * 输入：board = "WWRRBBWW", hand = "WRBRW"
 * 输出：2
 * 解释：要想清空桌面上的球，可以按下述步骤：
 * - 插入一个 'R' ，使桌面变为 WWRRRBBWW 。WWRRRBBWW -> WWBBWW
 * - 插入一个 'B' ，使桌面变为 WWBBBWW 。WWBBBWW -> WWWW -> empty
 * 只需从手中出 2 个球就可以清空桌面。
 * 示例 3：
 * 输入：board = "G", hand = "GGGGG"
 * 输出：2
 * 解释：要想清空桌面上的球，可以按下述步骤：
 * - 插入一个 'G' ，使桌面变为 GG 。
 * - 插入一个 'G' ，使桌面变为 GGG 。GGG -> empty
 * 只需从手中出 2 个球就可以清空桌面。
 * 示例 4：
 * 输入：board = "RBYYBBRRB", hand = "YRBGB"
 * 输出：3
 * 解释：要想清空桌面上的球，可以按下述步骤：
 * - 插入一个 'Y' ，使桌面变为 RBYYYBBRRB 。RBYYYBBRRB -> RBBBRRB -> RRRB -> B
 * - 插入一个 'B' ，使桌面变为 BB 。
 * - 插入一个 'B' ，使桌面变为 BBB 。BBB -> empty
 * 只需从手中出 3 个球就可以清空桌面。
 * 提示：
 * 1 <= board.length <= 16
 * 1 <= hand.length <= 5
 * board 和 hand 由字符 'R'、'Y'、'B'、'G' 和 'W' 组成
 * 桌面上一开始的球中，不会有三个及三个以上颜色相同且连着的球
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/11/18
 */
public class FindMinStep {
    private static final int COLOR_KIND = 5;
    private static final Map<Character, Integer> COLOR_ID_MAP = new HashMap<>();
    private static final Map<Integer, Character> ID_COLOR_MAP = new HashMap<>();

    static {
        COLOR_ID_MAP.put('R', 0);
        COLOR_ID_MAP.put('Y', 1);
        COLOR_ID_MAP.put('B', 2);
        COLOR_ID_MAP.put('G', 3);
        COLOR_ID_MAP.put('W', 4);
        ID_COLOR_MAP.put(0, 'R');
        ID_COLOR_MAP.put(1, 'Y');
        ID_COLOR_MAP.put(2, 'B');
        ID_COLOR_MAP.put(3, 'G');
        ID_COLOR_MAP.put(4, 'W');
    }

    public int findMinStep(String board, String hand) {
        int ans = 0;
        Set<Integer> visited = new HashSet<>();
        Queue<Status> queue = new ArrayDeque<>();
        Status initStatus = new Status(board, hand);
        queue.offer(initStatus);
        visited.add(initStatus.hash());
        while (!queue.isEmpty()) {
            ans++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Status status = queue.poll();
                char[] curBoard = status.board;
                int[] curHand = status.hand;
                int n = curBoard.length;
                for (int j = 0; j < COLOR_KIND; j++) {
                    if (curHand[j] > 0) {
                        curHand[j]--;
                        char c = ID_COLOR_MAP.get(j);
                        for (int k = 0; k <= n; k++) {
                            if (k > 0 && curBoard[k - 1] == c) {continue;}
                            if (k > 0 && k < n && curBoard[k - 1] != curBoard[k] && curBoard[k - 1] != c && curBoard[k] != c) {continue;}
                            char[] newBoard = clear(curBoard, c, k);
                            if (newBoard.length == 0) {return ans;}
                            if (status.handSum() > 0) {
                                Status newStatus = new Status(newBoard, curHand);
                                if (visited.add(newStatus.hash())) {
                                    queue.offer(newStatus);
                                }
                            }
                        }
                        curHand[j]++;
                    }
                }
            }
        }
        return -1;
    }

    class Status {
        char[] board;
        int[] hand;

        Status(String board, String hand) {
            this.board = board.toCharArray();
            this.hand = new int[COLOR_KIND];
            for (int i = 0, n = hand.length(); i < n; i++) {
                this.hand[COLOR_ID_MAP.get(hand.charAt(i))]++;
            }
        }

        Status(char[] board, int[] hand) {
            this.board = board;
            this.hand = Arrays.copyOf(hand, COLOR_KIND);
        }

        int handSum() {
            int handSum = 0;
            for (int num : hand) {
                handSum += num;
            }
            return handSum;
        }

        int hash() {
            return 31 * Arrays.hashCode(board) + Arrays.hashCode(hand);
        }
    }

    private char[] clear(char[] board, char insertChar, int insertIndex) {
        int n = board.length, index = 0;
        char[] charStack = new char[n + 1];
        int[] countStack = new int[n + 1];
        int top = 0;
        while (index <= n) {
            char c = index == insertIndex ? insertChar : (index < insertIndex ? board[index] : board[index - 1]);
            if (top == 0) {
                charStack[top] = c;
                countStack[top++] = 1;
                index++;
            } else {
                if (charStack[top - 1] == c) {
                    countStack[top - 1]++;
                    index++;
                } else {
                    if (countStack[top - 1] >= 3) {top--;} else {
                        charStack[top] = c;
                        countStack[top++] = 1;
                        index++;
                    }
                }
            }
        }
        if (countStack[top - 1] >= 3) {top--;}
        n = 0;
        index = 0;
        for (int i = 0; i < top; i++) {n += countStack[i];}
        char[] newBoard = new char[n];
        for (int i = 0; i < top; i++) {
            char c = charStack[i];
            int count = countStack[i];
            for (int k = 0; k < count; k++) {
                newBoard[index++] = c;
            }
        }
        return newBoard;
    }

    public static void main(String[] args) {
        FindMinStep fms = new FindMinStep();
        System.out.println(fms.findMinStep("RRWWRRBBRR", "WB"));
    }
}