package work.huangdu.question_bank.easy;

/**
 * 3442. 奇偶频次间的最大差值 I
 * MaxDifference
 *
 * @author huangdu
 * @version 2025/6/10
 */
public class MaxDifference {
    public int maxDifference(String s) {
        int[] cnt = new int[26];
        for (int i = 0, n = s.length(); i < n; i++) {
            char ch = s.charAt(i);
            cnt[ch - 'a']++;
        }
        int max = 0, min = Integer.MAX_VALUE;
        for(int count: cnt) {
            if (count == 0) {
                continue;
            }
            if ((count & 1) == 1) {
                max = Math.max(max, count);
            } else {
                min = Math.min(min, count);
            }
        }
        return max - min;
    }
}
