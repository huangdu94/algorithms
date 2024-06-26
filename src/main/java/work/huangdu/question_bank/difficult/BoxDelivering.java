package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1687. 从仓库到码头运输箱子
 * 你有一辆货运卡车，你需要用这一辆车把一些箱子从仓库运送到码头。这辆卡车每次运输有 箱子数目的限制 和 总重量的限制 。
 * 给你一个箱子数组 boxes 和三个整数 portsCount, maxBoxes 和 maxWeight ，其中 boxes[i] = [portsi, weighti] 。
 * portsi 表示第 i 个箱子需要送达的码头， weightsi 是第 i 个箱子的重量。
 * portsCount 是码头的数目。
 * maxBoxes 和 maxWeight 分别是卡车每趟运输箱子数目和重量的限制。
 * 箱子需要按照 数组顺序 运输，同时每次运输需要遵循以下步骤：
 * 卡车从 boxes 队列中按顺序取出若干个箱子，但不能违反 maxBoxes 和 maxWeight 限制。
 * 对于在卡车上的箱子，我们需要 按顺序 处理它们，卡车会通过 一趟行程 将最前面的箱子送到目的地码头并卸货。如果卡车已经在对应的码头，那么不需要 额外行程 ，箱子也会立马被卸货。
 * 卡车上所有箱子都被卸货后，卡车需要 一趟行程 回到仓库，从箱子队列里再取出一些箱子。
 * 卡车在将所有箱子运输并卸货后，最后必须回到仓库。
 * 请你返回将所有箱子送到相应码头的 最少行程 次数。
 * 示例 1：
 * 输入：boxes = [[1,1],[2,1],[1,1]], portsCount = 2, maxBoxes = 3, maxWeight = 3
 * 输出：4
 * 解释：最优策略如下：
 * - 卡车将所有箱子装上车，到达码头 1 ，然后去码头 2 ，然后再回到码头 1 ，最后回到仓库，总共需要 4 趟行程。
 * 所以总行程数为 4 。
 * 注意到第一个和第三个箱子不能同时被卸货，因为箱子需要按顺序处理（也就是第二个箱子需要先被送到码头 2 ，然后才能处理第三个箱子）。
 * 示例 2：
 * 输入：boxes = [[1,2],[3,3],[3,1],[3,1],[2,4]], portsCount = 3, maxBoxes = 3, maxWeight = 6
 * 输出：6
 * 解释：最优策略如下：
 * - 卡车首先运输第一个箱子，到达码头 1 ，然后回到仓库，总共 2 趟行程。
 * - 卡车运输第二、第三、第四个箱子，到达码头 3 ，然后回到仓库，总共 2 趟行程。
 * - 卡车运输第五个箱子，到达码头 3 ，回到仓库，总共 2 趟行程。
 * 总行程数为 2 + 2 + 2 = 6 。
 * 示例 3：
 * 输入：boxes = [[1,4],[1,2],[2,1],[2,1],[3,2],[3,4]], portsCount = 3, maxBoxes = 6, maxWeight = 7
 * 输出：6
 * 解释：最优策略如下：
 * - 卡车运输第一和第二个箱子，到达码头 1 ，然后回到仓库，总共 2 趟行程。
 * - 卡车运输第三和第四个箱子，到达码头 2 ，然后回到仓库，总共 2 趟行程。
 * - 卡车运输第五和第六个箱子，到达码头 3 ，然后回到仓库，总共 2 趟行程。
 * 总行程数为 2 + 2 + 2 = 6 。
 * 示例 4：
 * 输入：boxes = [[2,4],[2,5],[3,1],[3,2],[3,7],[3,1],[4,4],[1,3],[5,2]], portsCount = 5, maxBoxes = 5, maxWeight = 7
 * 输出：14
 * 解释：最优策略如下：
 * - 卡车运输第一个箱子，到达码头 2 ，然后回到仓库，总共 2 趟行程。
 * - 卡车运输第二个箱子，到达码头 2 ，然后回到仓库，总共 2 趟行程。
 * - 卡车运输第三和第四个箱子，到达码头 3 ，然后回到仓库，总共 2 趟行程。
 * - 卡车运输第五个箱子，到达码头 3 ，然后回到仓库，总共 2 趟行程。
 * - 卡车运输第六和第七个箱子，到达码头 3 ，然后去码头 4 ，然后回到仓库，总共 3 趟行程。
 * - 卡车运输第八和第九个箱子，到达码头 1 ，然后去码头 5 ，然后回到仓库，总共 3 趟行程。
 * 总行程数为 2 + 2 + 2 + 2 + 3 + 3 = 14 。
 * 提示：
 * 1 <= boxes.length <= 10^5
 * 1 <= portsCount, maxBoxes, maxWeight <= 10^5
 * 1 <= portsi <= portsCount
 * 1 <= weightsi <= maxWeight
 *
 * @author huangdu
 * @version 2022/12/5
 */
public class BoxDelivering {
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        if (n == 1) {return 2;}
        int[] weightPrefixSum = new int[n + 1], diffPrefixSum = new int[n], dp = new int[n];
        for (int i = 0; i < n; i++) {
            weightPrefixSum[i + 1] = weightPrefixSum[i] + boxes[i][1];
        }
        for (int i = 1; i < n; i++) {
            diffPrefixSum[i] = diffPrefixSum[i - 1] + (boxes[i][0] == boxes[i - 1][0] ? 0 : 1);
        }
        dp[0] = 2;
        Deque<Integer> indexDeque = new ArrayDeque<>(), valueDeque = new ArrayDeque<>();
        indexDeque.push(0);
        valueDeque.push(0);
        for (int i = 1; i < n; i++) {
            int value = dp[i - 1] - diffPrefixSum[i];
            while (!valueDeque.isEmpty() && value <= valueDeque.peek()) {
                indexDeque.pop();
                valueDeque.pop();
            }
            valueDeque.push(value);
            indexDeque.push(i);
            while (i + 1 - indexDeque.peekLast() > maxBoxes || weightPrefixSum[i + 1] - weightPrefixSum[indexDeque.peekLast()] > maxWeight) {
                indexDeque.pollLast();
                valueDeque.pollLast();
            }
            dp[i] = valueDeque.peekLast() + diffPrefixSum[i] + 2;
        }
        return dp[n - 1];
    }
}
