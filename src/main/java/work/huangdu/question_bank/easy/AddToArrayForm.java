package work.huangdu.question_bank.easy;

import java.util.*;

/**
 * 989. 数组形式的整数加法
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 * 示例 1：
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 * 示例 2：
 * 输入：A = [2,7,4], K = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 * 示例 3：
 * 输入：A = [2,1,5], K = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 * 示例 4：
 * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * 输出：[1,0,0,0,0,0,0,0,0,0,0]
 * 解释：9999999999 + 1 = 10000000000
 * 提示：
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * 如果 A.length > 1，那么 A[0] != 0
 *
 * @author huangdu
 * @version 2021/1/22
 */
public class AddToArrayForm {
    public List<Integer> addToArrayForm2(int[] A, int K) {
        int a_n = A.length;
        List<Integer> result = new ArrayList<>(a_n);
        while (K != 0) {
            result.add(K % 10);
            K /= 10;
        }
        int k_n = result.size();
        for (int i = 0, j = a_n - 1; i < a_n / 2; i++, j--) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }
        int add = 0;
        for (int i = 0; i < a_n || i < k_n || add != 0; i++) {
            int sum = (i < a_n ? A[i] : 0) + (i < k_n ? result.get(i) : 0) + add;
            if (i < k_n) {
                result.set(i, sum % 10);
            } else {
                result.add(sum % 10);
            }
            add = sum / 10;
        }
        Collections.reverse(result);
        return result;
    }

    public List<Integer> addToArrayForm(int[] A, int K) {
        int n = A.length;
        LinkedList<Integer> result = new LinkedList<>();
        int i = n - 1;
        while (i >= 0 || K != 0) {
            K += (i >= 0 ? A[i--] : 0);
            result.addFirst(K % 10);
            K /= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        AddToArrayForm ataf = new AddToArrayForm();
        System.out.println(ataf.addToArrayForm(new int[]{1, 2, 0, 0}, 34));
    }
}
