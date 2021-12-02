package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1218. 最长定差子序列
 * 给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
 * 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
 * 示例 1：
 * 输入：arr = [1,2,3,4], difference = 1
 * 输出：4
 * 解释：最长的等差子序列是 [1,2,3,4]。
 * 示例 2：
 * 输入：arr = [1,3,5,7], difference = 1
 * 输出：1
 * 解释：最长的等差子序列是任意单个元素。
 * 示例 3：
 * 输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * 输出：4
 * 解释：最长的等差子序列是 [7,5,3,1]。
 * 提示：
 * 1 <= arr.length <= 10^5
 * -10^4 <= arr[i], difference <= 10^4
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/11/5
 */
public class LongestSubsequence {
    public int longestSubsequence(int[] arr, int difference) {
        int n = arr.length, max = 0;
        Map<Integer, Integer> record = new HashMap<>(n);
        for (int num : arr) {
            int len = record.getOrDefault(num - difference, 0) + 1;
            record.put(num, len);
            max = Math.max(max, len);
        }
        return max;
    }

    public int longestSubsequence3(int[] arr, int difference) {
        int n = arr.length, max = 0;
        Map<Integer, Integer> record = new HashMap<>(n);
        for (int num : arr) {
            /* record.getOrDefault(num - difference, 0) + 1 >= record.get(num)
               因为如果 左 < 右， 那我一定能用计算右时候的那个数+1得到与之相同的结果，所以 左 >= 右
               所以以下代码存在冗余 */
            int len = Math.max(record.getOrDefault(num, 0), record.getOrDefault(num - difference, 0) + 1);
            record.put(num, len);
            max = Math.max(max, len);
        }
        return max;
    }

    public int longestSubsequence2(int[] arr, int difference) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        Map<Integer, List<Integer>> numIndexMap = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            List<Integer> indexList = numIndexMap.computeIfAbsent(arr[i], k -> new ArrayList<>());
            indexList.add(i);
        }
        for (int i = 0; i < n; i++) {
            int key = difference + arr[i];
            if (!numIndexMap.containsKey(key)) {continue;}
            List<Integer> indexList = numIndexMap.get(key);
            for (int j : indexList) {
                if (j > i) {
                    ans[j] = Math.max(ans[j], ans[i] + 1);
                }
            }
        }
        int max = 1;
        for (int num : ans) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestSubsequence ls = new LongestSubsequence();
        int[] arr = {1, 2, 3, 4};
        System.out.println(ls.longestSubsequence(arr, 1));
    }
}
