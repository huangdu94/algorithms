package work.huangdu.exploration.start_from_scratch.graph_search.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 提示：
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/6/9
 */
public class PermuteUnique {
    private int n;
    private List<List<Integer>> permutations;
    private List<Integer> permutation;

    public List<List<Integer>> permuteUnique(int[] nums) {
        this.n = nums.length;
        this.permutations = new ArrayList<>();
        this.permutation = new ArrayList<>();
        Arrays.sort(nums);
        for (int num : nums) {
            permutation.add(num);
        }
        backtrack(0);
        return this.permutations;
    }

    private void backtrack(int i) {
        if (i == n) {
            permutations.add(new ArrayList<>(permutation));
            return;
        }
        backtrack(i + 1);
        Set<Integer> used = new HashSet<>();
        for (int j = i + 1; j < n; j++) {
            if (!permutation.get(i).equals(permutation.get(j)) && !used.contains(permutation.get(j))) {
                used.add(permutation.get(j));
                Collections.swap(permutation, i, j);
                backtrack(i + 1);
                Collections.swap(permutation, i, j);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, -1, 2, 1, -1, 2, 1};
        PermuteUnique permute = new PermuteUnique();
        System.out.println(permute.permuteUnique(nums));
    }

    static class Old {
        private int n;
        private int[] nums;
        private boolean[] visited;
        private List<List<Integer>> res;
        private List<Integer> permute;

        public List<List<Integer>> permuteUnique(int[] nums) {
            this.n = nums.length;
            this.nums = nums;
            this.visited = new boolean[n];
            res = new ArrayList<>();
            permute = new ArrayList<>(n);
            Arrays.sort(nums);
            backtrack(0);
            return res;
        }

        private void backtrack2(int index) {
            if (index == n) {
                res.add(new ArrayList<>(permute));
                return;
            }
            Set<Integer> used = new HashSet<>();
            for (int i = index; i < n; i++) {
                if (used.contains(nums[i])) { continue; }
                permute.add(nums[i]);
                swap(nums, index, i);
                backtrack2(index + 1);
                permute.remove(index);
                swap(nums, index, i);
                used.add(nums[i]);
            }
        }

        private void swap(int[] nums, int a, int b) {
            int temp = nums[b];
            nums[b] = nums[a];
            nums[a] = temp;
        }

        private void backtrack(int index) {
            if (index == n) {
                res.add(new ArrayList<>(permute));
                return;
            }
            for (int i = 0; i < n; i++) {
                // 精妙
                if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) { continue; }
                permute.add(nums[i]);
                visited[i] = true;
                backtrack(index + 1);
                permute.remove(index);
                visited[i] = false;
            }
        }
    }
}
