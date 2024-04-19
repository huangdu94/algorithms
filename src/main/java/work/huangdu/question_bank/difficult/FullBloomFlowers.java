package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 2251. 花期内花的数目
 * 给你一个下标从 0 开始的二维整数数组 flowers ，其中 flowers[i] = [start_i, end_i] 表示第 i 朵花的 花期 从 start_i 到 end_i （都 包含）。同时给你一个下标从 0 开始大小为 n 的整数数组 people ，people[i] 是第 i 个人来看花的时间。
 * 请你返回一个大小为 n 的整数数组 answer ，其中 answer[i]是第 i 个人到达时在花期内花的 数目 。
 * 示例 1：
 * 输入：flowers = [[1,6],[3,7],[9,12],[4,13]], people = [2,3,7,11]
 * 输出：[1,2,2,2]
 * 解释：上图展示了每朵花的花期时间，和每个人的到达时间。
 * 对每个人，我们返回他们到达时在花期内花的数目。
 * 示例 2：
 * 输入：flowers = [[1,10],[3,3]], people = [3,3,2]
 * 输出：[2,2,1]
 * 解释：上图展示了每朵花的花期时间，和每个人的到达时间。
 * 对每个人，我们返回他们到达时在花期内花的数目。
 * 提示：
 * 1 <= flowers.length <= 5 * 10^4
 * flowers[i].length == 2
 * 1 <= start_i <= end_i <= 10^9
 * 1 <= people.length <= 5 * 10^4
 * 1 <= people[i] <= 10^9
 *
 * @author huangdu
 */
public class FullBloomFlowers {
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        Map<Integer, Integer> map = discretize(flowers, people);
        int n = people.length, m = map.size();
        int[] ans = new int[n], diff = new int[m + 1];
        for (int[] flower : flowers) {
            diff[map.get(flower[0])]++;
            diff[map.get(flower[1]) + 1]--;
        }
        for (int i = 1; i <= m; i++) {diff[i] += diff[i - 1];}
        for (int i = 0; i < n; i++) {ans[i] = diff[map.get(people[i])];}
        return ans;
    }

    /**
     * 离散化
     */
    private Map<Integer, Integer> discretize(int[][] flowers, int[] people) {
        // 1. 去重
        Set<Integer> set = new HashSet<>((flowers.length << 1) + people.length);
        for (int[] flower : flowers) {
            set.add(flower[0]);
            set.add(flower[1]);
        }
        for (int p : people) {set.add(p);}
        // 2. 排序
        List<Integer> list = new ArrayList<>(set);
        list.sort(Integer::compare);
        // 3. 映射
        Map<Integer, Integer> map = new HashMap<>(list.size());
        int index = 0;
        for (int node : list) {map.put(node, index++);}
        return map;
    }
}
