package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 907. 子数组的最小值之和
 * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 * 示例 1：
 * 输入：arr = [3,1,2,4]
 * 输出：17
 * 解释：
 * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
 * 示例 2：
 * 输入：arr = [11,81,94,43,3]
 * 输出：444
 * 提示：
 * 1 <= arr.length <= 3 * 10^4
 * 1 <= arr[i] <= 3 * 10^4
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/10/28
 */
public class SumSubarrayMins {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length, top = 0;
        int[] left = new int[n], right = new int[n], stack = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        for (int i = 0; i < n; i++) {
            while (top > 0 && arr[stack[top - 1]] >= arr[i]) {
                right[stack[top - 1]] = i;
                top--;
            }
            if (top > 0) {left[i] = stack[top - 1];}
            stack[top++] = i;
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = (ans + (long)(right[i] - i) * (i - left[i]) * arr[i]) % 1000000007;
        }
        return (int)ans;
    }

    public static void main(String[] args) {
        SumSubarrayMins ssm = new SumSubarrayMins();
        System.out.println(ssm.sumSubarrayMins(new int[] {3, 1, 2, 4}));
    }
}
