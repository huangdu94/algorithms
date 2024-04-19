package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 1345. 跳跃游戏 IV
 * 给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
 * 每一步，你可以从下标 i 跳到下标：
 * i + 1 满足：i + 1 < arr.length
 * i - 1 满足：i - 1 >= 0
 * j 满足：arr[i] == arr[j] 且 i != j
 * 请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。
 * 注意：任何时候你都不能跳到数组外面。
 * 示例 1：
 * 输入：arr = [100,-23,-23,404,100,23,23,23,3,404]
 * 输出：3
 * 解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。
 * 示例 2：
 * 输入：arr = [7]
 * 输出：0
 * 解释：一开始就在最后一个元素处，所以你不需要跳跃。
 * 示例 3：
 * 输入：arr = [7,6,9,6,9,6,9,7]
 * 输出：1
 * 解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
 * 示例 4：
 * 输入：arr = [6,1,9]
 * 输出：2
 * 示例 5：
 * 输入：arr = [11,22,7,7,7,7,7,7,7,22,13]
 * 输出：3
 * 提示：
 * 1 <= arr.length <= 5 * 10^4
 * -10^8 <= arr[i] <= 10^8
 *
 * @author huangdu
 * @version 2022/1/21
 */
public class MinJumps {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 2) {return n - 1;}
        Map<Integer, List<Integer>> numIndexListMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> indexList = numIndexListMap.computeIfAbsent(arr[i], k -> new ArrayList<>());
            indexList.add(i);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> idxVisited = new HashSet<>(), valVisited = new HashSet<>();
        queue.offer(0);
        idxVisited.add(0);
        int ans = 1;
        while (true) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int cur = queue.poll();
                if (cur - 1 > 0 && !idxVisited.contains(cur - 1)) {
                    queue.offer(cur - 1);
                    idxVisited.add(cur - 1);
                }
                if (cur + 1 < n && !idxVisited.contains(cur + 1)) {
                    if (cur + 1 == n - 1) {return ans;}
                    queue.offer(cur + 1);
                    idxVisited.add(cur + 1);
                }
                int curValue = arr[cur];
                if (!valVisited.contains(curValue)) {
                    valVisited.add(curValue);
                    for (int next : numIndexListMap.get(curValue)) {
                        if (next == n - 1) {return ans;}
                        if (next == cur || idxVisited.contains(next)) {continue;}
                        queue.offer(next);
                        idxVisited.add(next);
                    }
                }
            }
            ans++;
        }
    }

    // 超时 而且没法用memo备忘录
/*    private int dfs(int cur) {
        if (cur == n - 1) {return 0;}
        visited[cur] = true;
        int min = n - 1;
        if (cur - 1 > 0 && !visited[cur - 1]) {
            min = Math.min(min, dfs(cur - 1) + 1);
        }
        if (cur + 1 < n && !visited[cur + 1]) {
            min = Math.min(min, dfs(cur + 1) + 1);
        }
        for (int next : numIndexListMap.get(arr[cur])) {
            if (next == cur || visited[next]) {continue;}
            min = Math.min(min, dfs(next) + 1);
        }
        visited[cur] = false;
        return min;
    }*/

    public static void main(String[] args) {
        MinJumps mj = new MinJumps();
        int[] arr = new int[50000];
        Arrays.fill(arr, 7);
        arr[49999] = 1;
        System.out.println(mj.minJumps(arr));
    }
}
