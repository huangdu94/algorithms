package work.huangdu.exploration.start_from_scratch.graph_search.build;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 565. 数组嵌套
 * 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到最大的集合S并返回其大小，其中 S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。
 * 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。
 * 示例 1:
 * 输入: A = [5,4,0,3,1,6,2]
 * 输出: 4
 * 解释:
 * A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 * 其中一种最长的 S[K]:
 * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 * 提示：
 * N是[1, 20,000]之间的整数。
 * A中不含有重复的元素。
 * A中的元素大小在[0, N-1]之间。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/3/16
 */
public class ArrayNesting {
    public int arrayNesting(int[] nums) {
        int n = nums.length, max = 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] != -1) {
                int cur = i, size = 0;
                do {
                    int next = nums[cur];
                    nums[cur] = -1;
                    cur = next;
                    size++;
                } while (nums[cur] != -1);
                if (max < size) { max = size; }
            }
        }
        return max;
    }

    public int arrayNesting2(int[] nums) {
        int n = nums.length, max = 1;
        int[] visited = new int[n];
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                int cur = i, size = 0;
                do {
                    visited[cur] = i;
                    cur = nums[cur];
                    size++;
                } while (visited[cur] != i);
                if (max < size) { max = size; }
            }
        }
        return max;
    }

    public int arrayNesting3(int[] nums) {
        int n = nums.length, max = 1;
        boolean[] allVisited = new boolean[n];
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!allVisited[i]) {
                int cur = i, size = 0;
                do {
                    visited[cur] = true;
                    allVisited[cur] = true;
                    cur = nums[cur];
                    size++;
                } while (!visited[cur]);
                Arrays.fill(visited, false);
                if (max < size) { max = size; }
            }
        }
        return max;
    }

    public int arrayNesting4(int[] nums) {
        int n = nums.length, max = 1;
        boolean[] allVisited = new boolean[n];
        // Set<Integer> visited = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            if (!allVisited[i]) {
                Set<Integer> visited = new HashSet<>();
                int cur = i;
                do {
                    visited.add(cur);
                    allVisited[cur] = true;
                    cur = nums[cur];
                } while (!visited.contains(cur));
                max = Math.max(max, visited.size());
                // visited.clear();
            }
        }
        return max;
    }

    public static void main(String[] args) {
        ArrayNesting an = new ArrayNesting();
        int[] nums = {5, 4, 0, 3, 1, 6, 2};
        System.out.println(an.arrayNesting(nums));
    }
}
