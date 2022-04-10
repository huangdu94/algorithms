package work.huangdu.competition.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 第 288 场周赛
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/4/10
 */
public class Solution288 {

    public int largestInteger(int num) {
        List<Integer> list = new ArrayList<>();
        while (num > 0) {
            list.add(0, num % 10);
            num /= 10;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int n1 = list.get(i), maxJ = -1;
            for (int j = i + 1; j < size; j++) {
                int n2 = list.get(j);
                if (n2 > n1 && (n2 & 1) == (n1 & 1) && (maxJ == -1 || n2 > list.get(maxJ))) {
                    maxJ = j;
                }
            }
            if (maxJ != -1) {
                int temp = list.get(i);
                list.set(i, list.get(maxJ));
                list.set(maxJ, temp);
            }
        }
        int ans = 0;
        for (int n : list) {
            ans = ans * 10 + n;
        }
        return ans;
    }

    public String minimizeResult(String expression) {
        int n = expression.length(), addIndex = expression.indexOf('+'), index1 = 0, index2 = n;
        long min = Integer.MAX_VALUE;
        for (int i = 0; i < addIndex; i++) {
            for (int j = addIndex + 2; j <= n; j++) {
                String part1 = expression.substring(0, i), part3 = expression.substring(j);
                int n1 = part1.length() == 0 ? 1 : Integer.parseInt(part1), n2 = Integer.parseInt(expression.substring(i, addIndex)) + Integer.parseInt(expression.substring(addIndex + 1, j)), n3 = part3.length() == 0 ? 1 : Integer.parseInt(part3);
                if (min > (long)n1 * n2 * n3) {
                    min = (long)n1 * n2 * n3;
                    index1 = i;
                    index2 = j;
                }
            }
        }
        StringBuilder ans = new StringBuilder(expression);
        ans.insert(index1, '(');
        ans.insert(index2 + 1, ')');
        return ans.toString();
    }

    public int maximumProduct(int[] nums, int k) {
        final int mod = 1000000007;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
        }
        while (k > 0) {
            queue.offer(queue.poll() + 1);
            k--;
        }
        long ans = 1L;
        for (int num : queue) {
            if (num == 0) {return 0;}
            ans = ans * num % mod;
        }
        return (int)ans;
    }

    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        Arrays.sort(flowers);
        int n = flowers.length, fullCount = 0;
        for (int i = n - 1; i >= 0; i--) {
            int flower = flowers[i];
            if (flower >= target) {
                fullCount++;
            } else {
                if (newFlowers >= target - flower) {
                    newFlowers -= target - flower;
                    fullCount++;
                } else {
                    break;
                }
            }
        }
        if (fullCount == n) {
            if (partial * (target - 1) <= full) {
                return (long)fullCount * full;
            } else {
                if (flowers[0] < target) {
                    return (long)(fullCount - 1) * full + (long)partial * (target - 1);
                } else {
                    return (long)fullCount * full;
                }
            }
        }
        int notFullCount = n - fullCount;
        int count = partial * (target - 1) / full;
        // TODO 时间不够来不及完成了
        return 0;
    }

    public static void main(String[] args) {
        Solution288 solution = new Solution288();
        System.out.println(solution.largestInteger(3159));
        System.out.println(solution.minimizeResult("999+999"));
        System.out.println(solution.maximumProduct(new int[] {6, 3, 3, 2}, 2));
        int[] flowers = {1, 3, 1, 1};
        int newFlowers = 7;
        int target = 6;
        int full = 12;
        int partial = 1;
        System.out.println(solution.maximumBeauty(flowers, newFlowers, target, full, partial));
    }
}
