package work.huangdu.question_bank.medium;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 面试题 17.09. 第 k 个数
 * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
 * 示例 1:
 * 输入: k = 5
 * 输出: 9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/9/28
 */
public class GetKthMagicNumber {
    public int getKthMagicNumber(int k) {
        PriorityQueue<Long> queue = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>(k);
        queue.offer(1L);
        visited.add(1L);
        while (--k > 0) {
            long cur = queue.poll();
            for (long i = 3; i <= 7; i += 2) {
                long next = cur * i;
                if (visited.add(next)) {
                    queue.offer(next);
                }
            }
        }
        return Math.toIntExact(queue.poll());
    }

    public static void main(String[] args) {
        GetKthMagicNumber gkmn = new GetKthMagicNumber();
        System.out.println(gkmn.getKthMagicNumber(7));
    }
}
