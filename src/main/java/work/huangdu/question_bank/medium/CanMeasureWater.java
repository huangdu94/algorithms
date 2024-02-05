package work.huangdu.question_bank.medium;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 365. 水壶问题
 * 有两个水壶，容量分别为 jug1Capacity 和 jug2Capacity 升。水的供应是无限的。确定是否有可能使用这两个壶准确得到 targetCapacity 升。
 * 如果可以得到 targetCapacity 升水，最后请用以上水壶中的一或两个来盛放取得的 targetCapacity 升水。
 * 你可以：
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * 示例 1:
 * 输入: jug1Capacity = 3, jug2Capacity = 5, targetCapacity = 4
 * 输出: true
 * 解释：来自著名的 "Die Hard"
 * 示例 2:
 * 输入: jug1Capacity = 2, jug2Capacity = 6, targetCapacity = 5
 * 输出: false
 * 示例 3:
 * 输入: jug1Capacity = 1, jug2Capacity = 2, targetCapacity = 3
 * 输出: true
 * 提示:
 * 1 <= jug1Capacity, jug2Capacity, targetCapacity <= 10^6
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class CanMeasureWater {
    // TODO 复制粘贴 需要复习
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        Deque<int[]> stack = new LinkedList<>();
        stack.push(new int[] {0, 0});
        Set<Long> seen = new HashSet<>();
        while (!stack.isEmpty()) {
            if (seen.contains(hash(stack.peek()))) {
                stack.pop();
                continue;
            }
            seen.add(hash(stack.peek()));

            int[] state = stack.pop();
            int remain_x = state[0], remain_y = state[1];
            if (remain_x == targetCapacity || remain_y == targetCapacity || remain_x + remain_y == targetCapacity) {
                return true;
            }
            // 把 X 壶灌满。
            stack.push(new int[] {jug1Capacity, remain_y});
            // 把 Y 壶灌满。
            stack.push(new int[] {remain_x, jug2Capacity});
            // 把 X 壶倒空。
            stack.push(new int[] {0, remain_y});
            // 把 Y 壶倒空。
            stack.push(new int[] {remain_x, 0});
            // 把 X 壶的水灌进 Y 壶，直至灌满或倒空。
            stack.push(new int[] {remain_x - Math.min(remain_x, jug2Capacity - remain_y), remain_y + Math.min(remain_x, jug2Capacity - remain_y)});
            // 把 Y 壶的水灌进 X 壶，直至灌满或倒空。
            stack.push(new int[] {remain_x + Math.min(remain_y, jug1Capacity - remain_x), remain_y - Math.min(remain_y, jug1Capacity - remain_x)});
        }
        return false;
    }

    public long hash(int[] state) {
        return (long)state[0] * 1000001 + state[1];
    }
}
