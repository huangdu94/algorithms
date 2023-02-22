package work.huangdu.question_bank.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1140. 石子游戏 II
 * 爱丽丝和鲍勃继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。
 * 爱丽丝和鲍勃轮流进行，爱丽丝先开始。最初，M = 1。
 * 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
 * 游戏一直持续到所有石子都被拿走。
 * 假设爱丽丝和鲍勃都发挥出最佳水平，返回爱丽丝可以得到的最大数量的石头。
 * 示例 1：
 * 输入：piles = [2,7,9,4,4]
 * 输出：10
 * 解释：如果一开始Alice取了一堆，Bob取了两堆，然后Alice再取两堆。爱丽丝可以得到2 + 4 + 4 = 10堆。如果Alice一开始拿走了两堆，那么Bob可以拿走剩下的三堆。在这种情况下，Alice得到2 + 7 = 9堆。返回10，因为它更大。
 * 示例 2:
 * 输入：piles = [1,2,3,4,5,100]
 * 输出：104
 * 提示：
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 10^4
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/2/22
 */
public class StoneGameII {
    public int stoneGameII(int[] piles) {
        int sum = 0;
        for (int pile : piles) {sum += pile;}
        Map<Integer, Integer> memo = new HashMap<>();
        int ans = (sum + select(piles, 1, 0, memo)) / 2;
        return ans;
    }

    private int select(int[] piles, int m, int cur, Map<Integer, Integer> memo) {
        if (cur == piles.length) {return 0;}
        int key = m * 100 + cur;
        if (memo.containsKey(key)) {return memo.get(key);}
        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = cur; i < Math.min(2 * m + cur, piles.length); i++) {
            sum += piles[i];
            int score = select(piles, Math.max(m, i - cur + 1), i + 1, memo);
            max = Math.max(max, sum - score);
        }
        memo.put(key, max);
        return max;
    }

    public static void main(String[] args) {
        int[] piles = {9, 2, 2, 8, 3, 7, 9, 9};
        StoneGameII sg2 = new StoneGameII();
        System.out.println(sg2.stoneGameII(piles));
    }

    // 超时
    static class Solution1 {
        public int stoneGameII(int[] piles) {
            Map<Integer, int[]> memo = new HashMap<>();
            int ans = select(piles, 1, 0, 0, memo)[0];
            return ans;
        }

        private int[] select(int[] piles, int m, int cur, int turn, Map<Integer, int[]> memo) {
            if (cur == piles.length) {return new int[] {0, 0};}
            int key = m * 200 + cur * 2 + turn;
            if (memo.containsKey(key)) {return memo.get(key);}
            int max = 0, sum = 0;
            int[] result = {0, 0};
            for (int i = cur; i < Math.min(2 * m + cur, piles.length); i++) {
                sum += piles[i];
                int[] temp = select(piles, Math.max(m, i - cur + 1), i + 1, (turn + 1) & 1, memo);
                if (max < sum + temp[turn]) {
                    max = sum + temp[turn];
                    result[turn] = max;
                    result[turn ^ 1] = temp[turn ^ 1];
                }
            }
            memo.put(key, result);
            return result;
        }

        public static void main(String[] args) {
            int[] piles = {9, 2, 2, 8, 3, 7, 9, 9};
            StoneGameII.Solution1 sg2 = new StoneGameII.Solution1();
            System.out.println(sg2.stoneGameII(piles));
        }
    }

    // 超时
    static class Solution2 {
        public int stoneGameII(int[] piles) {
            int sum = 0;
            for (int pile : piles) {sum += pile;}
            Map<Integer, Integer> memo = new HashMap<>();
            int ans = (sum + select(piles, 1, 0, 0, memo)) / 2;
            return ans;
        }

        private int select(int[] piles, int m, int cur, int turn, Map<Integer, Integer> memo) {
            if (cur == piles.length) {return 0;}
            int key = m * 200 + cur * 2 + turn;
            if (memo.containsKey(key)) {return memo.get(key);}
            int result = turn == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE, sum = 0;
            for (int i = cur; i < Math.min(2 * m + cur, piles.length); i++) {
                sum += piles[i];
                int score = select(piles, Math.max(m, i - cur + 1), i + 1, (turn + 1) % 2, memo);
                if (turn == 0) {
                    result = Math.max(result, sum + score);
                } else {
                    result = Math.min(result, score - sum);
                }
            }
            memo.put(key, result);
            return result;
        }

        public static void main(String[] args) {
            int[] piles = {9, 2, 2, 8, 3, 7, 9, 9};
            StoneGameII.Solution2 sg2 = new StoneGameII.Solution2();
            System.out.println(sg2.stoneGameII(piles));
        }
    }
}
