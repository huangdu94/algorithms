package work.huangdu.exploration.start_from_scratch.double_pointer.segment;

import java.util.Arrays;

/**
 * 475. 供暖器
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
 * 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
 * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
 * 示例 1:
 * 输入: houses = [1,2,3], heaters = [2]
 * 输出: 1
 * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
 * 示例 2:
 * 输入: houses = [1,2,3,4], heaters = [1,4]
 * 输出: 1
 * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
 * 示例 3：
 * 输入：houses = [1,5], heaters = [2]
 * 输出：3
 * 提示：
 * 1 <= houses.length, heaters.length <= 3 * 10^4
 * 1 <= houses[i], heaters[i] <= 10^9
 *
 * @author huangdu
 * @version 2020/12/20 13:15
 */
public class FindRadius {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int max = 0;
        for (int house : houses) {
            int nearestIndex = findNearestHeater(heaters, house);
            int heater = heaters[nearestIndex];
            if (heater == house) { continue; }
            int val;
            if (heater < house) {
                val = house - heater;
            } else if (nearestIndex > 0) {
                val = Math.min(heater - house, house - heaters[nearestIndex - 1]);
            } else {
                val = heater - house;
            }
            max = Math.max(max, val);
        }
        return max;
    }

    private int findNearestHeater(int[] heaters, int house) {
        int i = 0, j = heaters.length - 1;
        while (i < j) {
            int mid = i + ((j - i) >> 1);
            if (heaters[mid] < house) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return i;
    }

    // 暴力法
    public int findRadius2(int[] houses, int[] heaters) {
        int radius = 0;
        for (int house : houses) {
            int min = Integer.MAX_VALUE;
            for (int heater : heaters) {
                min = Math.min(min, Math.abs(heater - house));
                if (min < radius) { break; }
            }
            radius = Math.max(radius, min);
        }
        return radius;
    }
}
