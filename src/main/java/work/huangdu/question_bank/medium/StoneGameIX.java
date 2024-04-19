package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 2029. 石子游戏 IX
 * Alice 和 Bob 再次设计了一款新的石子游戏。现有一行 n 个石子，每个石子都有一个关联的数字表示它的价值。给你一个整数数组 stones ，其中 stones[i] 是第 i 个石子的价值。
 * Alice 和 Bob 轮流进行自己的回合，Alice 先手。每一回合，玩家需要从 stones 中移除任一石子。
 * 如果玩家移除石子后，导致 所有已移除石子 的价值 总和 可以被 3 整除，那么该玩家就 输掉游戏 。
 * 如果不满足上一条，且移除后没有任何剩余的石子，那么 Bob 将会直接获胜（即便是在 Alice 的回合）。
 * 假设两位玩家均采用 最佳 决策。如果 Alice 获胜，返回 true ；如果 Bob 获胜，返回 false 。
 * 示例 1：
 * 输入：stones = [2,1]
 * 输出：true
 * 解释：游戏进行如下：
 * - 回合 1：Alice 可以移除任意一个石子。
 * - 回合 2：Bob 移除剩下的石子。
 * 已移除的石子的值总和为 1 + 2 = 3 且可以被 3 整除。因此，Bob 输，Alice 获胜。
 * 示例 2：
 * 输入：stones = [2]
 * 输出：false
 * 解释：Alice 会移除唯一一个石子，已移除石子的值总和为 2 。
 * 由于所有石子都已移除，且值总和无法被 3 整除，Bob 获胜。
 * 示例 3：
 * 输入：stones = [5,1,2,4,3]
 * 输出：false
 * 解释：Bob 总会获胜。其中一种可能的游戏进行方式如下：
 * - 回合 1：Alice 可以移除值为 1 的第 2 个石子。已移除石子值总和为 1 。
 * - 回合 2：Bob 可以移除值为 3 的第 5 个石子。已移除石子值总和为 = 1 + 3 = 4 。
 * - 回合 3：Alices 可以移除值为 4 的第 4 个石子。已移除石子值总和为 = 1 + 3 + 4 = 8 。
 * - 回合 4：Bob 可以移除值为 2 的第 3 个石子。已移除石子值总和为 = 1 + 3 + 4 + 2 = 10.
 * - 回合 5：Alice 可以移除值为 5 的第 1 个石子。已移除石子值总和为 = 1 + 3 + 4 + 2 + 5 = 15.
 * Alice 输掉游戏，因为已移除石子值总和（15）可以被 3 整除，Bob 获胜。
 * 提示：
 * 1 <= stones.length <= 10^5
 * 1 <= stones[i] <= 10^4
 *
 * @author huangdu
 * @version 2022/1/20
 */
public class StoneGameIX {
    private final Map<Integer, Boolean> memo = new HashMap<>();

    // TODO 本题也可以直接分析得到结果，不需要用回溯
    /*
    石头的数值大于3对于本题没有任何意义，所以石头的数值可以转化为3的余数，即问题简化为三种数值的石头，0、1、2
    Alice开局只能拿 1 或 2
    当石头总数（3的余数）为1（或2）时（为0游戏已经结束了），角色只可以继续拿1（或2）或0
    0是万金油，拿了0石头的总数相当于没变
    如果Bob拿了0，Alice继续拿0则场上局势不会有任何改变，所以如果0的数量如果是偶数的话，则可以直接忽略。
    如果0的个数是奇数的话，分成两种情况，若0的数量为1，则Bob拿完石头后，Alice没有拿0的机会，Bob有一次用0改变局势的机会。
    若0的数量大于1，则Alice总是有机会跟Bob拼着一直拿0，直到0的数量为3个，所以以上数量可以作进一步简化
    如果拿了1（或2），对手可以拿的石头除了0以外，就只有2（或1）发生了一次转换
    为什么0的数量为3或以上的时候不能转化成1个，用以下例子说明
    1 1 5  a1 b0 | a2 b0 a2 b1 | a2 b2 a0 b1 | a2 b2 a1 b0
    3 1 5  a1 b0 a0 b0 | a2 b0 ...
    总之实测0的数量为3以上，转化成3才能保证程序正确
     */
    public boolean stoneGameIX(int[] stones) {
        int[] counts = new int[3];
        for (int stone : stones) {counts[stone % 3]++;}
        if ((counts[0] & 1) == 1) {
            counts[0] = counts[0] > 1 ? 3 : 1;
        } else {
            counts[0] = 0;
        }
        return dfs(counts, 0, true);
    }

    /**
     * @param counts 剩下的石头数量
     * @param total  当前石头总数（除以三的余数）
     * @param round  true 表示当前是Alice的轮次
     * @return true Alice的获胜 false Bob获胜
     */
    private boolean dfs(int[] counts, int total, boolean round) {
        int hash = Arrays.hashCode(counts);
        if (memo.containsKey(hash)) {return memo.get(hash);}
        // 如果石头拿完了，还没决出胜负则说明Bob获胜
        if (counts[0] == 0 && counts[1] == 0 && counts[2] == 0) {return false;}
        switch (total) {
            // 只有Alice第一次拿的时候，total可能为0，否则就已经决出胜负了
            case 0:
                boolean res1 = false, res2 = false;
                if (counts[1] > 0) {
                    counts[1]--;
                    res1 = dfs(counts, 1, !round);
                    counts[1]++;
                }
                if (counts[2] > 0) {
                    counts[2]--;
                    res2 = dfs(counts, 2, !round);
                    counts[2]++;
                }
                return res1 || res2;
            // 如果当前round为false则，结果就想true；如果round为true，结果就想false
            case 1:
            default:
                res1 = !round;
                res2 = !round;
                if (counts[0] > 0) {
                    counts[0]--;
                    res1 = dfs(counts, total, !round);
                    counts[0]++;
                }
                if (counts[total] > 0) {
                    counts[total]--;
                    res2 = dfs(counts, total == 1 ? 2 : 1, !round);
                    counts[total]++;
                }
                boolean res = round ? res1 || res2 : res1 && res2;
                memo.put(hash, res);
                return res;
        }
    }

    public static void main(String[] args) {
        StoneGameIX sg9 = new StoneGameIX();
        System.out.println(sg9.stoneGameIX(new int[] {20, 3, 20, 17, 2, 12, 15, 17, 4}));
    }
}
