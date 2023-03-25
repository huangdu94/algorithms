package work.huangdu.question_bank.medium;

/**
 * 1574. 删除最短的子数组使剩余数组有序
 * 给你一个整数数组 arr ，请你删除一个子数组（可以为空），使得 arr 中剩下的元素是 非递减 的。
 * 一个子数组指的是原数组中连续的一个子序列。
 * 请你返回满足题目要求的最短子数组的长度。
 * 示例 1：
 * 输入：arr = [1,2,3,10,4,2,3,5]
 * 输出：3
 * 解释：我们需要删除的最短子数组是 [10,4,2] ，长度为 3 。剩余元素形成非递减数组 [1,2,3,3,5] 。
 * 另一个正确的解为删除子数组 [3,10,4] 。
 * 示例 2：
 * 输入：arr = [5,4,3,2,1]
 * 输出：4
 * 解释：由于数组是严格递减的，我们只能保留一个元素。所以我们需要删除长度为 4 的子数组，要么删除 [5,4,3,2]，要么删除 [4,3,2,1]。
 * 示例 3：
 * 输入：arr = [1,2,3]
 * 输出：0
 * 解释：数组已经是非递减的了，我们不需要删除任何元素。
 * 示例 4：
 * 输入：arr = [1]
 * 输出：0
 * 提示：
 * 1 <= arr.length <= 10^5
 * 0 <= arr[i] <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/3/25
 */
public class FindLengthOfShortestSubarray {
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        if (n == 1) {return 0;}
        int left = 0;
        while (left + 1 < n && arr[left] <= arr[left + 1]) {left++;}
        if (left == n - 1) {return 0;}
        int right = n;
        while (right > left && arr[right - 1] <= arr(arr, right) && arr[right - 1] >= arr[left]) {right--;}
        int ans = right - left - 1;
        for (int i = left - 1; i >= -1; i--) {
            int leftVal = arr(arr, i);
            while (right > left && arr[right - 1] <= arr(arr, right) && arr[right - 1] >= leftVal) {right--;}
            ans = Math.min(ans, right - i - 1);
        }
        return ans;
    }

    private int arr(int[] arr, int i) {
        if (i == -1) {return -1;}
        if (i == arr.length) {return 1000000001;}
        return arr[i];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 10, 4, 2, 3, 5};
        FindLengthOfShortestSubarray floss = new FindLengthOfShortestSubarray();
        System.out.println(floss.findLengthOfShortestSubarray(arr));
    }
}
