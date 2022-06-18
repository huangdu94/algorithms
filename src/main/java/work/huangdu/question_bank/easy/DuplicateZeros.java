package work.huangdu.question_bank.easy;

import java.util.Arrays;

/**
 * 1089. 复写零
 * 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
 * 注意：请不要在超过该数组长度的位置写入元素。
 * 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
 * 示例 1：
 * 输入：[1,0,2,3,0,4,5,0]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
 * 示例 2：
 * 输入：[1,2,3]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,2,3]
 * 提示：
 * 1 <= arr.length <= 10000
 * 0 <= arr[i] <= 9
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2022/6/17
 */
public class DuplicateZeros {
    public void duplicateZeros(int[] arr) {
        int n = arr.length, p1 = 0, p2 = 0;
        int[] copy = Arrays.copyOf(arr, n);
        while (p2 < n) {
            int val = copy[p1++];
            if (val == 0) {
                arr[p2++] = 0;
                if (p2 < n) {
                    arr[p2++] = 0;
                }
            } else {
                arr[p2++] = val;
            }
        }
    }
}
