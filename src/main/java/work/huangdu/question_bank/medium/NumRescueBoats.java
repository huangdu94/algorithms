package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 881. 救生艇
 * 第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
 * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
 * 示例 1：
 * 输入：people = [1,2], limit = 3
 * 输出：1
 * 解释：1 艘船载 (1, 2)
 * 示例 2：
 * 输入：people = [3,2,2,1], limit = 3
 * 输出：3
 * 解释：3 艘船分别载 (1, 2), (2) 和 (3)
 * 示例 3：
 * 输入：people = [3,5,3,4], limit = 5
 * 输出：4
 * 解释：4 艘船分别载 (3), (3), (4), (5)
 * 提示：
 * 1 <= people.length <= 50000
 * 1 <= people[i] <= limit <= 30000
 *
 * @author huangdu
 * @version 2021/8/26
 */
public class NumRescueBoats {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int count = 0, i = 0, j = people.length - 1;
        while (i <= j) {
            if (people[i] + people[j--] <= limit) {
                i++;
            }
            count++;
        }
        return count;
    }

    public int numRescueBoats2(int[] people, int limit) {
        final int MAX = 30000;
        int[] counts = new int[MAX + 1];
        for (int weight : people) {
            counts[weight]++;
        }
        int result = 0, thin = 0, fat = MAX;
        while (thin <= fat) {
            while (thin <= fat && counts[thin] == 0) {
                thin++;
            }
            while (thin <= fat && counts[fat] == 0) {
                fat--;
            }
            if (thin <= fat) {
                if (thin + fat > limit) {
                    result += counts[fat];
                    counts[fat] = 0;
                } else {
                    if (thin == fat) {
                        result += counts[fat] / 2 + counts[fat] % 2;
                        counts[fat] = 0;
                    } else if (counts[thin] > counts[fat]) {
                        result += counts[fat];
                        counts[thin] -= counts[fat];
                        counts[fat] = 0;
                    } else {
                        result += counts[thin];
                        counts[fat] -= counts[thin];
                        counts[thin] = 0;
                    }
                }
            }
        }
        return result;
    }
}
