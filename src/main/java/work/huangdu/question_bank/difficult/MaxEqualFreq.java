package work.huangdu.question_bank.difficult;

import java.util.HashMap;
import java.util.Map;

/**
 * 1224. 最大相等频率
 * 给你一个正整数数组 nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回该前缀的长度：
 * 从前缀中 恰好删除一个 元素后，剩下每个数字的出现次数都相同。
 * 如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。
 * 示例 1：
 * 输入：nums = [2,2,1,1,5,3,3,5]
 * 输出：7
 * 解释：对于长度为 7 的子数组 [2,2,1,1,5,3,3]，如果我们从中删去 nums[4] = 5，就可以得到 [2,2,1,1,3,3]，里面每个数字都出现了两次。
 * 示例 2：
 * 输入：nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
 * 输出：13
 * 提示：
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/8/18
 */
public class MaxEqualFreq {
    public int maxEqualFreq(int[] nums) {
        int n = nums.length, ans = 0;
        int[] freqs = new int[100001];
        Map<Integer, Integer> freqFreqs = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int freq = freqs[nums[i]]++;
            if (freq != 0) {
                if (freqFreqs.merge(freq, -1, Integer::sum) == 0) {
                    freqFreqs.remove(freq);
                }
            }
            freqFreqs.merge(freq + 1, 1, Integer::sum);
            if (check(freqFreqs)) {
                ans = i + 1;
            }
        }
        return ans;
    }

    private boolean check(Map<Integer, Integer> freqFreqs) {
        if (freqFreqs.size() == 1) {
            for (Map.Entry<Integer, Integer> entry : freqFreqs.entrySet()) {
                return entry.getValue() == 1 || entry.getKey() == 1;
            }
        }
        if (freqFreqs.size() == 2) {
            Map.Entry<Integer, Integer> entry1 = null, entry2 = null;
            for (Map.Entry<Integer, Integer> entry : freqFreqs.entrySet()) {
                if (entry1 == null) {
                    entry1 = entry;
                } else {
                    entry2 = entry;
                }
            }
            int freq1 = entry1.getKey(), cnt1 = entry1.getValue(), freq2 = entry2.getKey(), cnt2 = entry2.getValue();
            if (cnt1 != 1 && cnt2 != 1) {return false;}
            return cnt1 == 1 && (freq1 == 1 || freq1 - freq2 == 1) || cnt2 == 1 && (freq2 == 1 || freq2 - freq1 == 1);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 2};
        MaxEqualFreq mef = new MaxEqualFreq();
        System.out.println(mef.maxEqualFreq(nums));
    }
}
