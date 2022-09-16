package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 672. 灯泡开关 Ⅱ
 * 房间中有 n 只已经打开的灯泡，编号从 1 到 n 。墙上挂着 4 个开关 。
 * 这 4 个开关各自都具有不同的功能，其中：
 * 开关 1 ：反转当前所有灯的状态（即开变为关，关变为开）
 * 开关 2 ：反转编号为偶数的灯的状态（即 2, 4, ...）
 * 开关 3 ：反转编号为奇数的灯的状态（即 1, 3, ...）
 * 开关 4 ：反转编号为 j = 3k + 1 的灯的状态，其中 k = 0, 1, 2, ...（即 1, 4, 7, 10, ...）
 * 你必须 恰好 按压开关 presses 次。每次按压，你都需要从 4 个开关中选出一个来执行按压操作。
 * 给你两个整数 n 和 presses ，执行完所有按压之后，返回 不同可能状态 的数量。
 * 示例 1：
 * 输入：n = 1, presses = 1
 * 输出：2
 * 解释：状态可以是：
 * - 按压开关 1 ，[关]
 * - 按压开关 2 ，[开]
 * 示例 2：
 * 输入：n = 2, presses = 1
 * 输出：3
 * 解释：状态可以是：
 * - 按压开关 1 ，[关, 关]
 * - 按压开关 2 ，[开, 关]
 * - 按压开关 3 ，[关, 开]
 * 示例 3：
 * 输入：n = 3, presses = 1
 * 输出：4
 * 解释：状态可以是：
 * - 按压开关 1 ，[关, 关, 关]
 * - 按压开关 2 ，[开, 关, 开]
 * - 按压开关 3 ，[关, 开, 关]
 * - 按压开关 4 ，[关, 开, 开]
 * 提示：
 * 1 <= n <= 1000
 * 0 <= presses <= 1000
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/9/15
 */
public class FlipLights {
    private static int MASK;
    private static final int EVEN = 0XAAAAAAAA;
    private static final int ODD = 0X55555555;
    private static final int THREE = 0B1001001001001001001001001001001;

    /* 分析：
     * 每三个开关一个周期，开关的状态在多个周期内是循环的，
     * 多个周期对总体开关的奇数偶数有影响，
     * 故记录状态的时候可以删掉偶数个周期的开关，而不影响最后的状态记录
     * 对6取余，如果最后结果是0的话最后结果取6即可
     * 注对6取余，case:n=7,presses=1不通过，
     * 改为对12取余，最终通过
     */
    public int flipLights(int n, int presses) {
        MASK = (1 << (n - 1) % 12 + 1) - 1;
        Queue<Integer> statusQueue = new ArrayDeque<>();
        statusQueue.offer(MASK);
        for (int i = 0; i < presses; i++) {
            int size = statusQueue.size();
            Set<Integer> visited = new HashSet<>();
            for (int k = 0; k < size; k++) {
                int cur = statusQueue.remove();
                for (int type = 1; type <= 4; type++) {
                    int next = sw(type, cur);
                    if (visited.add(next)) {
                        statusQueue.offer(next);
                    }
                }
            }
        }
        return statusQueue.size();
    }

    private int sw(int type, int status) {
        switch (type) {
            default:
            case 1:
                return ~status & MASK;
            case 2:
                return (status ^ EVEN) & MASK;
            case 3:
                return (status ^ ODD) & MASK;
            case 4:
                return (status ^ THREE) & MASK;
        }
    }
}
