package work.huangdu.competition.biweekly;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 第 85 场双周赛
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/8/20
 */
public class Solution85 {

    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length(), bCnt = 0;
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) == 'B') {
                bCnt++;
            }
        }
        int ans = k - bCnt;
        for (int i = k; i < n; i++) {
            if (blocks.charAt(i) == 'B') {
                bCnt++;
            }
            if (blocks.charAt(i - k) == 'B') {
                bCnt--;
            }
            ans = Math.min(ans, k - bCnt);
        }
        return ans;
    }

    public int secondsToRemoveOccurrences(String s) {
        int ans = 0;
        while (s.contains("01")) {
            s = s.replace("01", "10");
            ans++;
        }
        return ans;
    }

    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] diffs = new int[n + 1];
        for (int[] shift : shifts) {
            int start = shift[0], end = shift[1], direction = shift[2];
            if (direction == 1) {
                diffs[start]++;
                diffs[end + 1]--;
            } else {
                diffs[start]--;
                diffs[end + 1]++;
            }
        }
        int diff = 0;
        for (int i = 0; i < n; i++) {
            chars[i] = (char)((((chars[i] - 'a') + (diff += diffs[i])) % 26 + 26) % 26 + 'a');
        }
        return new String(chars);
    }

    public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
        int n = nums.length;
        long[] ans = new long[n];
        TreeMap<Long, Integer> sumMap = new TreeMap<>();
        TreeSet<Integer> removeIdxSet = new TreeSet<>();
        // 前缀和 计算 [start, end]的和 sumPrefix[end+1] - sumPrefix[start]
        long[] sumPrefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sumPrefix[i + 1] = sumPrefix[i] + nums[i];
        }
        sumMap.put(sumPrefix[n] - sumPrefix[0], 1);
        for (int i = 0; i < n; i++) {
            int idx = removeQueries[i];
            Integer start = removeIdxSet.lower(idx);
            start = start == null ? 0 : start + 1;
            Integer end = removeIdxSet.higher(idx);
            end = end == null ? n - 1 : end - 1;
            long oldSum = sumPrefix[end + 1] - sumPrefix[start];
            if (sumMap.merge(oldSum, -1, Integer::sum) == 0) {
                sumMap.remove(oldSum);
            }
            if (start <= idx - 1) {
                long leftSum = sumPrefix[idx] - sumPrefix[start];
                sumMap.merge(leftSum, 1, Integer::sum);
            }
            if (idx + 1 <= end) {
                long rightSum = sumPrefix[end + 1] - sumPrefix[idx + 1];
                sumMap.merge(rightSum, 1, Integer::sum);
            }
            removeIdxSet.add(idx);
            if (sumMap.isEmpty()) {
                ans[i] = 0;
            } else {
                ans[i] = sumMap.lastKey();
            }
        }
        return ans;
    }
}
