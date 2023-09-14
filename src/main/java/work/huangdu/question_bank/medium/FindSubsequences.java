package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 491. 递增子序列
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 * 示例:
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * 说明:
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/8/25 0:09
 */
public class FindSubsequences {
    private int len;
    private List<List<Integer>> resList;
    //private Set<Integer> hashSet;
    //private Set<List<Integer>> resSet;
    private LinkedList<Integer> res;

    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums == null || nums.length < 2) {
            return new ArrayList<>();
        }
        len = nums.length;
        //resSet = new HashSet<>();
        resList = new ArrayList<>();
        //hashSet = new HashSet<>();
        res = new LinkedList<>();
        helper(nums, -101, 0);
/*        for (int i = 1; i < len; i++) {
            res.add(nums[i - 1]);
            helper(nums, i - 1, i);
            res.removeLast();
        }
        return new ArrayList<>(resSet);*/
        return resList;
    }

    private void helper(int[] nums, int preValue, int curIndex) {
        if (curIndex >= len) {
            if (res.size() >= 2) {
                resList.add(new ArrayList<>(res));
            }
            return;
        }
        int curValue = nums[curIndex];
        // 1. 可以选择跳过cur(增加条件，只有满足条件的才可以选择跳不跳过)
        if (preValue != curValue) {
            helper(nums, preValue, curIndex + 1);
        }
        // 2. 如果cur大于pre可以选择加入cur到res中，加完要记得回溯
        if (preValue <= curValue) {
            res.add(curValue);
            helper(nums, curValue, curIndex + 1);
            res.removeLast();
        }
    }

/*    private void helper(int[] nums, int pre, int cur) {
        if (cur >= len) return;
        // 1. 可以选择跳过cur
        helper(nums, pre, cur + 1);
        // 2. 如果cur大于pre可以选择加入cur到res中，加完要记得回溯
        if (nums[pre] <= nums[cur]) {
            res.add(nums[cur]);
            //resSet.add(new ArrayList<>(res));
            *//*int hash = getHash();
            if (!hashSet.contains(hash)) {
                resList.add(new ArrayList<>(res));
                hashSet.add(hash);
            }*//*
            helper(nums, cur, cur + 1);
            res.removeLast();
        }
    }*/

/*    private int getHash() {
        int base = 263;
        int mod = (int) (1E9 + 7);
        int hashValue = 0;
        for (int x : res) {
            hashValue = hashValue * base % mod + (x + 101);
            hashValue %= mod;
        }
        return hashValue;
    }*/

    static class Solution {
        public List<List<Integer>> findSubsequences(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            Queue<List<Integer>> queue = new ArrayDeque<>();
            queue.offer(new ArrayList<>());
            Set<Integer> visited = new HashSet<>();
            for (int num : nums) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    List<Integer> curList = queue.poll();
                    queue.offer(curList);
                    if (curList.isEmpty() || curList.get(curList.size() - 1) <= num) {
                        List<Integer> nextList = new ArrayList<>(curList);
                        nextList.add(num);
                        int hash = hashCode(nextList);
                        if (visited.add(hash)) {
                            if (nextList.size() >= 2) {
                                ans.add(nextList);
                            }
                            queue.offer(nextList);
                        }
                    }
                }
            }
            return ans;
        }

        public int hashCode(List<Integer> list) {
            if (list.isEmpty()) {return 0;}
            int result = 0;
            for (int element : list) {
                element += 101;
                result = result * 202 + element;
            }
            return result;
        }

        public static void main(String[] args) {
            int[][] arr1 = {{-48, -33}, {-48, -34}, {-52, 72}, {-33, 72}, {-48, 72}, {-48, -34, 72}, {-48, -33, 72}, {72, 75}, {-52, 75}, {-52, 72, 75}, {-34, 75}, {-33, 75}, {-33, 72, 75}, {-48, 75}, {-48, 72, 75}, {-48, -34, 75},
                {-48, -34, 72, 75}, {-48, -33, 75}, {-48, -33, 72, 75}, {-52, -12}, {-34, -12}, {-33, -12}, {-48, -12}, {-48, -34, -12}, {-48, -33, -12}, {-12, 72}, {72, 72}, {-52, -12, 72}, {-52, 72, 72}, {-34, -12, 72}, {-33, -12, 72},
                {-33, 72, 72}, {-48, -12, 72}, {-48, 72, 72}, {-48, -34, -12, 72}, {-48, -34, 72, 72}, {-48, -33, -12, 72}, {-48, -33, 72, 72}, {-52, -45}, {-48, -45}};
            int[][] arr2 = {{-48, -33}, {-48, -33, 72}, {-48, -33, 72, 75}, {-48, -33, 72, 72}, {-48, -33, 75}, {-48, -33, -12}, {-48, -33, -12, 72}, {-48, -34}, {-48, -34, 72}, {-48, -34, 72, 75}, {-48, -34, 72, 72}, {-48, -34, 75},
                {-48, -34, -12}, {-48, -34, -12, 72}, {-48, 72}, {-48, 72, 75}, {-48, 72, 72}, {-48, 75}, {-48, -12}, {-48, -12, 72}, {-48, -45}, {-33, 72}, {-33, 72, 75}, {-33, 72, 72}, {-33, 75}, {-33, -12}, {-33, -12, 72}, {-34, 72},
                {-34, 72, 75}, {-34, 72, 72}, {-34, 75}, {-34, -12}, {-34, -12, 72}, {-52, 72}, {-52, 72, 75}, {-52, 72, 72}, {-52, 75}, {-52, -12}, {-52, -12, 72}, {-52, -45}, {72, 75}, {72, 72}, {-12, 72}};
            Comparator<int[]> comparator = (o1, o2) -> {
                for (int i = 0; i < Math.min(o1.length, o2.length); i++) {
                    if (o1[i] == o2[i]) {continue;}
                    return Integer.compare(o1[i], o2[i]);
                }
                return Integer.compare(o1.length, o2.length);
            };
            Arrays.sort(arr1, comparator);
            Arrays.sort(arr2, comparator);
            System.out.println(Arrays.deepToString(arr1));
            System.out.println(Arrays.deepToString(arr2));
        }
    }
}