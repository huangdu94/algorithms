package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 473. 火柴拼正方形
 * 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
 * 如果你能使这个正方形，则返回 true ，否则返回 false 。
 * 示例 1:
 * 输入: matchsticks = [1,1,2,2,2]
 * 输出: true
 * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
 * 示例 2:
 * 输入: matchsticks = [3,3,3,3,4]
 * 输出: false
 * 解释: 不能用所有火柴拼成一个正方形。
 * 提示:
 * 1 <= matchsticks.length <= 15
 * 1 <= matchsticks[i] <= 10^8
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/6/1
 */
public class Makesquare {
    public boolean makesquare(int[] matchsticks) {
        int sum = 0, max = 0, min = Integer.MAX_VALUE;
        for (int matchstick : matchsticks) {
            sum += matchstick;
            if (max < matchstick) {max = matchstick;}
            if (min > matchstick) {min = matchstick;}
        }
        if (sum % 4 != 0) {return false;}
        int side = sum / 4;
        if (max > side) {return false;}
        Arrays.sort(matchsticks);
        for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[j];
            matchsticks[j] = temp;
        }
        return dfs(matchsticks, side, 0, 0, 0, 0, 0);
    }

    private boolean dfs(int[] matchsticks, int side, int side1, int side2, int side3, int side4, int index) {
        if (side1 > side || side2 > side || side3 > side || side4 > side) {return false;}
        if (index == matchsticks.length) {return true;}
        int matchstick = matchsticks[index];
        return dfs(matchsticks, side, side1 + matchstick, side2, side3, side4, index + 1) ||
            dfs(matchsticks, side, side1, side2 + matchstick, side3, side4, index + 1) ||
            dfs(matchsticks, side, side1, side2, side3 + matchstick, side4, index + 1) ||
            dfs(matchsticks, side, side1, side2, side3, side4 + matchstick, index + 1);
    }

    public static void main(String[] args) {
        Makesquare makesquare = new Makesquare();
        int[] matchsticks = {5969561, 8742425, 2513572, 3352059, 9084275, 2194427, 1017540, 2324577, 6810719, 8936380, 7868365, 2755770, 9954463, 9912280, 4713511};
        System.out.println(makesquare.makesquare(matchsticks));
    }
}
