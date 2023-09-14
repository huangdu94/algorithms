package work.huangdu.question_bank.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1711. 大餐计数
 * 大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
 * 你可以搭配 任意 两道餐品做一顿大餐。
 * 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i​​​​​​​​​​​​​​ 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。
 * 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
 * 示例 1：
 * 输入：deliciousness = [1,3,5,7,9]
 * 输出：4
 * 解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
 * 它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
 * 示例 2：
 * 输入：deliciousness = [1,1,1,3,3,3,7]
 * 输出：15
 * 解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。
 * 提示：
 * 1 <= deliciousness.length <= 10^5
 * 0 <= deliciousness[i] <= 2^20
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/7/7
 */
public class CountPairs {
    private static final int BASE = (int)1e9 + 7;

    public int countPairs(int[] deliciousness) {
        int max = 0, count = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int delicious : deliciousness) {
            countMap.merge(delicious, 1, Integer::sum);
            if (max < delicious) {max = delicious;}
        }
        max *= 2;
        for (int delicious : deliciousness) {
            countMap.merge(delicious, -1, Integer::sum);
            for (int sum = 1; sum <= max; sum <<= 1) {
                count += countMap.getOrDefault(sum - delicious, 0);
                if (count >= BASE) {count -= BASE;}
            }
        }
        return count;
    }

    public int countPairs2(int[] deliciousness) {
        int maxVal = 0;
        for (int val : deliciousness) {
            maxVal = Math.max(maxVal, val);
        }
        int maxSum = maxVal * 2;
        int count = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int delicious : deliciousness) {
            for (int sum = 1; sum <= maxSum; sum <<= 1) {
                count += countMap.getOrDefault(sum - delicious, 0);
                if (count >= BASE) {count -= BASE;}
            }
            countMap.put(delicious, countMap.getOrDefault(delicious, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        CountPairs cp = new CountPairs();
        int[] deliciousness = {1, 1, 1, 3, 3, 3, 7};
        System.out.println(cp.countPairs(deliciousness));
    }
}
