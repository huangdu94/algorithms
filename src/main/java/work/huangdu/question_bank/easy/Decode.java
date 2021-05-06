package work.huangdu.question_bank.easy;

import java.util.Arrays;

/**
 * 1720. 解码异或后的数组
 * 未知 整数数组 arr 由 n 个非负整数组成。
 * 经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。例如，arr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。
 * 给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。
 * 请解码返回原数组 arr 。可以证明答案存在并且是唯一的。
 * 示例 1：
 * 输入：encoded = [1,2,3], first = 1
 * 输出：[1,0,2,1]
 * 解释：若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
 * 示例 2：
 * 输入：encoded = [6,2,7,3], first = 4
 * 输出：[4,2,0,7,4]
 * 提示：
 * 2 <= n <= 10^4
 * encoded.length == n - 1
 * 0 <= encoded[i] <= 10^5
 * 0 <= first <= 10^5
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/6
 */
public class Decode {
    public int[] decode(int[] encoded, int first) {
        int n = encoded.length;
        int[] decoded = new int[n + 1];
        decoded[0] = first;
        for (int i = 1; i <= n; i++) {
            decoded[i] = encoded[i - 1] ^ decoded[i - 1];
        }
        return decoded;
    }

    public static void main(String[] args) {
        Decode decode = new Decode();
        int[] encoded = {1, 2, 3};
        System.out.println(Arrays.toString(decode.decode(encoded, 1)));
    }
}
