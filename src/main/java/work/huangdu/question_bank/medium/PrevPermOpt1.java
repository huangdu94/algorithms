package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 1053. 交换一次的先前排列
 * 给你一个正整数数组 arr（可能存在重复的元素），请你返回可在 一次交换（交换两数字 arr[i] 和 arr[j] 的位置）后得到的、按字典序排列小于 arr 的最大排列。
 * 如果无法这么操作，就请返回原数组。
 * 示例 1：
 * 输入：arr = [3,2,1]
 * 输出：[3,1,2]
 * 解释：交换 2 和 1
 * 示例 2：
 * 输入：arr = [1,1,5]
 * 输出：[1,1,5]
 * 解释：已经是最小排列
 * 示例 3：
 * 输入：arr = [1,9,4,6,7]
 * 输出：[1,7,4,6,9]
 * 解释：交换 9 和 7
 * 提示：
 * 1 <= arr.length <= 10^4
 * 1 <= arr[i] <= 10^4
 *
 * @author huangdu
 * @version 2023/4/3
 */
public class PrevPermOpt1 {
    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        for (int i = n - 1; i > 0; i--) {
            if (arr[i - 1] > arr[i]) {
                if (arr[i - 1] > arr[n - 1]) {
                    swap(arr, i - 1, n - 1);
                } else {
                    for (int j = n - 2; ; j--) {
                        if (arr[i - 1] > arr[j]) {
                            while (arr[j - 1] == arr[j]) {j--;}
                            swap(arr, i - 1, j);
                            break;
                        }
                    }
                }
                break;
            }
        }
        return arr;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        PrevPermOpt1 ppo1 = new PrevPermOpt1();
        int[] arr = {5, 3, 1, 1, 3};
        System.out.println(Arrays.toString(ppo1.prevPermOpt1(arr)));
    }
}
