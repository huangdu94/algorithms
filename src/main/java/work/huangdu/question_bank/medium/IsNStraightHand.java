package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.TreeMap;

/**
 * 846. 一手顺子
 * Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
 * 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * 输出：true
 * 解释：Alice 手中的牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
 * 示例 2：
 * 输入：hand = [1,2,3,4,5], groupSize = 4
 * 输出：false
 * 解释：Alice 手中的牌无法被重新排列成几个大小为 4 的组。
 * 提示：
 * 1 <= hand.length <= 10^4
 * 0 <= hand[i] <= 10^9
 * 1 <= groupSize <= hand.length
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/12/30
 */
public class IsNStraightHand {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) { return false; }
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for (int num : hand) {
            count.merge(num, 1, Integer::sum);
        }
        // 当前需要的数量
        int needAmount = 0, pre = -1;
        // 当前需要的数量， 将在 num + groupSize 数的时候减少， 减少的数量恰恰是 num 数的时候需要的数量的增加值
        Queue<int[]> recordQueue = new ArrayDeque<>();
        for (int num : count.keySet()) {
            // 如果需要的数量大于0 且 当前数和上一个数不连续则直接返回false
            if (needAmount > 0 && num != pre + 1) { return false; }
            // 当前数的数量
            int amount = count.get(num);
            // 如果当前数的数量小于需要的数量则直接返回false
            if (amount < needAmount) { return false; }
            // 需要的数量的增加值
            int addNeed = amount - needAmount;
            if (addNeed > 0) {
                // 当前数需求的增加值将在 num + groupSize 不再需要
                recordQueue.offer(new int[] {num + groupSize, -addNeed});
                needAmount += addNeed;
            }
            // 记录上一个数
            pre = num;
            // 下一个数需要的数量
            if (!recordQueue.isEmpty()) {
                int[] record = recordQueue.peek();
                if (record[0] <= num + 1) {
                    needAmount += record[1];
                    recordQueue.poll();
                }
            }
        }
        return needAmount == 0;
    }

    public static void main(String[] args) {
        IsNStraightHand insh = new IsNStraightHand();
        int[] hand = {1, 1, 2, 2, 3, 3};
        int groupSize = 2;
        System.out.println(insh.isNStraightHand(hand, groupSize));
    }
}
