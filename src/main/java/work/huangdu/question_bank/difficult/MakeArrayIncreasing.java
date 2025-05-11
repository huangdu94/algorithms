package work.huangdu.question_bank.difficult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1187. 使数组严格递增
 * 给你两个整数数组 arr_1 和 arr_2，返回使 arr_1 严格递增所需要的最小「操作」数（可能为 0）。
 * 每一步「操作」中，你可以分别从 arr_1 和 arr_2 中各选出一个索引，分别为 i 和 j，0 <= i < arr_1.length 和 0 <= j < arr_2.length，然后进行赋值运算 arr_1[i] = arr_2[j]。
 * 如果无法让 arr_1 严格递增，请返回 -1。
 * 示例 1：
 * 输入：arr_1 = [1,5,3,6,7], arr_2 = [1,3,2,4]
 * 输出：1
 * 解释：用 2 来替换 5，之后 arr1 = [1, 2, 3, 6, 7]。
 * 示例 2：
 * 输入：arr_1 = [1,5,3,6,7], arr_2 = [4,3,1]
 * 输出：2
 * 解释：用 3 来替换 5，然后用 4 来替换 3，得到 arr1 = [1, 3, 4, 6, 7]。
 * 示例 3：
 * 输入：arr_1 = [1,5,3,6,7], arr_2 = [1,6,3,3]
 * 输出：-1
 * 解释：无法使 arr_1 严格递增。
 * 提示：
 * 1 <= arr_1.length, arr_2.length <= 2000
 * 0 <= arr_1[i], arr_2[i] <= 10^9
 *
 * @author huangdu
 * @version 2023/4/20
 */
public class MakeArrayIncreasing {
    private int[] a;
    private int[] b;

    private Map<Integer, Integer>[] memo;

    public int makeArrayIncreasing(int[] a, int[] b) {
        Arrays.sort(b);
        this.a = a;
        this.b = b;
        this.memo = new HashMap[a.length];
        int ans = dfs(a.length - 1, Integer.MAX_VALUE);
        return ans < Integer.MAX_VALUE >> 1 ? ans : -1;
    }

    private int dfs(int i, int pre) {
        if (i < 0) {return 0;}
        if (memo[i] == null) {
            memo[i] = new HashMap<>();
        } else if (memo[i].containsKey(pre)) {
            return memo[i].get(pre);
        }
        int ans = Integer.MAX_VALUE >> 1;
        if (a[i] < pre) {
            ans = Math.min(ans, dfs(i - 1, a[i]));
        }
        int idx = lowBound(pre) - 1;
        if (idx >= 0) {
            ans = Math.min(ans, dfs(i - 1, b[idx]) + 1);
        }
        memo[i].put(pre, ans);
        return ans;
    }

    private int lowBound(int target) {
        int left = -1, right = b.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (b[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
