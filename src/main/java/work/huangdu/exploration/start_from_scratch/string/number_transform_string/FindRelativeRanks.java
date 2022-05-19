package work.huangdu.exploration.start_from_scratch.string.number_transform_string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 506. 相对名次
 * 给出 N 名运动员的成绩，找出他们的相对名次并授予前三名对应的奖牌。前三名运动员将会被分别授予 “金牌”，“银牌” 和“ 铜牌”（"Gold Medal", "Silver Medal", "Bronze Medal"）。
 * (注：分数越高的选手，排名越靠前。)
 * 示例 1:
 * 输入: [5, 4, 3, 2, 1]
 * 输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 * 解释: 前三名运动员的成绩为前三高的，因此将会分别被授予 “金牌”，“银牌”和“铜牌” ("Gold Medal", "Silver Medal" and "Bronze Medal").
 * 余下的两名运动员，我们只需要通过他们的成绩计算将其相对名次即可。
 * 提示:
 * N 是一个正整数并且不会超过 10000。
 * 所有运动员的成绩都不相同。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/9/26 17:44
 */
public class FindRelativeRanks {
    public String[] findRelativeRanks(int[] nums) {
        int len = nums.length, rank = len;
        // 记录各分数对应的下标
        // Map<Integer, Integer> scoreIndexMap = new TreeMap<>((o1, o2) -> o2 - o1);
        Map<Integer, Integer> scoreIndexMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            scoreIndexMap.put(nums[i], i);
        }
        Arrays.sort(nums);
        String[] res = new String[len];
        for (int n : nums) {
            res[scoreIndexMap.get(n)] = getRankString(rank--);
        }
        /*for (int index : scoreIndexMap.values()) {
            res[index] = getRankString(rank++);
        }*/
        return res;
    }

    private String getRankString(int rank) {
        if (rank > 3) return Integer.toString(rank);
        if (rank == 1) return "Gold Medal";
        if (rank == 2) return "Silver Medal";
        return "Bronze Medal";
    }

    private static final String GOLD_MEDAL = "Gold Medal";
    private static final String SILVER_MEDAL = "Silver Medal";
    private static final String BRONZE_MEDAL = "Bronze Medal";

    public String[] findRelativeRanks2(int[] score) {
        int n = score.length;
        if (n == 1) {return new String[] {GOLD_MEDAL};}
        if (n == 2) {return score[0] > score[1] ? new String[] {GOLD_MEDAL, SILVER_MEDAL} : new String[] {SILVER_MEDAL, GOLD_MEDAL};}
        String[] answer = new String[n];
        int[][] scoreIndex = new int[n][2];
        for (int i = 0; i < n; i++) {
            scoreIndex[i][0] = score[i];
            scoreIndex[i][1] = i;
        }
        Arrays.sort(scoreIndex, (o1, o2) -> Integer.compare(o2[0], o1[0]));
        answer[scoreIndex[0][1]] = GOLD_MEDAL;
        answer[scoreIndex[1][1]] = SILVER_MEDAL;
        answer[scoreIndex[2][1]] = BRONZE_MEDAL;
        for (int i = 3; i < n; i++) {
            answer[scoreIndex[i][1]] = Integer.toString(i + 1);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindRelativeRanks().findRelativeRanks2(new int[] {10, 3, 8, 9, 4})));
    }
}
