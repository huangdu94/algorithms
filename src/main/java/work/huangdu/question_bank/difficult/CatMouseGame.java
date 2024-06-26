package work.huangdu.question_bank.difficult;

/**
 * 913. 猫和老鼠
 * 两位玩家分别扮演猫和老鼠，在一张 无向 图上进行游戏，两人轮流行动。
 * 图的形式是：graph[a] 是一个列表，由满足 ab 是图中的一条边的所有节点 b 组成。
 * 老鼠从节点 1 开始，第一个出发；猫从节点 2 开始，第二个出发。在节点 0 处有一个洞。
 * 在每个玩家的行动中，他们 必须 沿着图中与所在当前位置连通的一条边移动。例如，如果老鼠在节点 1 ，那么它必须移动到 graph[1] 中的任一节点。
 * 此外，猫无法移动到洞中（节点 0）。
 * 然后，游戏在出现以下三种情形之一时结束：
 * 如果猫和老鼠出现在同一个节点，猫获胜。
 * 如果老鼠到达洞中，老鼠获胜。
 * 如果某一位置重复出现（即，玩家的位置和移动顺序都与上一次行动相同），游戏平局。
 * 给你一张图 graph ，并假设两位玩家都都以最佳状态参与游戏：
 * 如果老鼠获胜，则返回 1；
 * 如果猫获胜，则返回 2；
 * 如果平局，则返回 0 。
 * 示例 1：
 * 输入：graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
 * 输出：0
 * 示例 2：
 * 输入：graph = [[1,3],[0],[3],[0,2]]
 * 输出：1
 * 提示：
 * 3 <= graph.length <= 50
 * 1 <= graph[i].length < graph.length
 * 0 <= graph[i][j] < graph.length
 * graph[i][j] != i
 * graph[i] 互不相同
 * 猫和老鼠在游戏中总是移动
 *
 * @author huangdu
 * @version 2022/1/4
 */
public class CatMouseGame {
    private static final int DRAW = 0;
    private static final int MOUSE_WIN = 1;
    private static final int CAT_WIN = 2;
    private static final int MOUSE_TURN = 0;
    private int[][] graph;
    private int drawTurn;
    private int[][][] memo;

    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        this.graph = graph;
        this.drawTurn = 2 * n * (n - 1);
        this.memo = new int[n][n][drawTurn];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < drawTurn; k++) {
                    memo[i][j][k] = -1;
                }
            }
        }
        return dfs(1, 2, 0);
    }

    private int dfs(int mouse, int cat, int turn) {
        if (turn >= drawTurn) {return DRAW;}
        if (mouse == 0) {return MOUSE_WIN;}
        if (mouse == cat) {return CAT_WIN;}
        if (memo[mouse][cat][turn] != -1) {return memo[mouse][cat][turn];}
        boolean lose = true;
        if ((turn & 1) == MOUSE_TURN) {
            for (int next : graph[mouse]) {
                int nextResult = dfs(next, cat, turn + 1);
                if (nextResult == MOUSE_WIN) {return memo[mouse][cat][turn] = MOUSE_WIN;}
                if (lose && nextResult == DRAW) {lose = false;}
            }
            return memo[mouse][cat][turn] = lose ? CAT_WIN : DRAW;
        } else {
            for (int next : graph[cat]) {
                if (next == 0) {continue;}
                int nextResult = dfs(mouse, next, turn + 1);
                if (nextResult == CAT_WIN) {return memo[mouse][cat][turn] = CAT_WIN;}
                if (lose && nextResult == DRAW) {lose = false;}
            }
            return memo[mouse][cat][turn] = lose ? MOUSE_WIN : DRAW;
        }
    }
}