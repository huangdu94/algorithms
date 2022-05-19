package work.huangdu.question_bank.medium;

import java.util.TreeMap;

/**
 * 1705. 吃苹果的最大数目
 * 有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i] 个苹果，这些苹果将会在 days[i] 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。也可能有那么几天，树上不会长出新的苹果，此时用 apples[i] == 0 且 days[i] == 0 表示。
 * 你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。
 * 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。
 * 示例 1：
 * 输入：apples = [1,2,3,5,2], days = [3,2,1,4,2]
 * 输出：7
 * 解释：你可以吃掉 7 个苹果：
 * - 第一天，你吃掉第一天长出来的苹果。
 * - 第二天，你吃掉一个第二天长出来的苹果。
 * - 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
 * - 第四天到第七天，你吃的都是第四天长出来的苹果。
 * 示例 2：
 * 输入：apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
 * 输出：5
 * 解释：你可以吃掉 5 个苹果：
 * - 第一天到第三天，你吃的都是第一天长出来的苹果。
 * - 第四天和第五天不吃苹果。
 * - 第六天和第七天，你吃的都是第六天长出来的苹果。
 * 提示：
 * apples.length == n
 * days.length == n
 * 1 <= n <= 2 * 10^4
 * 0 <= apples[i], days[i] <= 2 * 10^4
 * 只有在 apples[i] = 0 时，days[i] = 0 才成立
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/12/24
 */
public class EatenApples {
    // 优先队列会更快一些。
    public int eatenApples(int[] apples, int[] days) {
        TreeMap<Integer, Integer> dayAppleMap = new TreeMap<>();
        int n = days.length, count = 0;
        for (int i = 0; i < n; i++) {
            if (apples[i] > 0) {
                dayAppleMap.merge(i + days[i], apples[i], Integer::sum);
            }
            dayAppleMap.remove(i);
            if (!dayAppleMap.isEmpty()) {
                if (dayAppleMap.merge(dayAppleMap.firstKey(), -1, Integer::sum) == 0) {
                    dayAppleMap.remove(dayAppleMap.firstKey());
                }
                count++;
            }
        }
        dayAppleMap.remove(n);
        int i = n;
        while (!dayAppleMap.isEmpty()) {
            int day = dayAppleMap.firstKey(), apple = dayAppleMap.remove(day), eaten = Math.min(day - i, apple);
            count += eaten;
            i += eaten;
        }
        return count;
    }

    public int eatenApples2(int[] apples, int[] days) {
        TreeMap<Integer, Integer> dayAppleMap = new TreeMap<>(Integer::compare);
        int n = days.length, i = 0, count = 0;
        while (i < n || !dayAppleMap.isEmpty()) {
            if (i < n && apples[i] > 0) {
                dayAppleMap.merge(i + days[i], apples[i], Integer::sum);
            }
            dayAppleMap.remove(i);
            if (!dayAppleMap.isEmpty()) {
                if (dayAppleMap.merge(dayAppleMap.firstKey(), -1, Integer::sum) == 0) {
                    dayAppleMap.remove(dayAppleMap.firstKey());
                }
                count++;
            }
            i++;
        }
        return count;
    }

    public static void main(String[] args) {
        EatenApples ea = new EatenApples();
        int[] apples = {5, 2, 3};
        int[] days = {6, 9, 10};
        System.out.println(ea.eatenApples(apples, days));
    }
}
