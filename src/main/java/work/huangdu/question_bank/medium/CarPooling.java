package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1094. 拼车
 * 车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
 * 给定整数 capacity 和一个数组 trips ,  trip[i] = [numPassengers_i, from_i, to_i] 表示第 i 次旅行有 numPassengers_i 乘客，接他们和放他们的位置分别是 from_i 和 to_i 。这些位置是从汽车的初始位置向东的公里数。
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
 * 示例 1：
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
 * 输出：false
 * 示例 2：
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 5
 * 输出：true
 * 提示：
 * 1 <= trips.length <= 1000
 * trips[i].length == 3
 * 1 <= numPassengers_i <= 100
 * 0 <= from_i < to_i <= 1000
 * 1 <= capacity <= 105
 *
 * @author huangdu
 */
public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, Comparator.comparingInt(o -> o[1]));
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int[] trip : trips) {
            int count = trip[0], from = trip[1], to = trip[2];
            while (!queue.isEmpty() && queue.peek()[0] <= from) {
                capacity += queue.poll()[0];
            }
            capacity -= count;
            if (capacity < 0) {return false;}
            queue.offer(new int[] {to, count});
        }
        return true;
    }
}
