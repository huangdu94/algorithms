package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 1734. 解码异或后的排列
 * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
 * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
 * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 * 示例 1：
 * 输入：encoded = [3,1]
 * 输出：[1,2,3]
 * 解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
 * 示例 2：
 * 输入：encoded = [6,5,4,6]
 * 输出：[2,4,1,5,3]
 * 提示：
 * 3 <= n < 10^5
 * n 是奇数。
 * encoded.length == n - 1
 *
 * @author huangdu
 * @version 2021/5/11
 */
public class Decode {

    public int[] decode(int[] encoded) {
        int len = encoded.length, n = len + 1, first = 0;
        for (int i = 1; i <= n; i++) {
            first ^= i;
        }
        for (int i = 1; i < len; i += 2) {
            first ^= encoded[i];
        }
        int[] perm = new int[n];
        perm[0] = first;
        for (int i = 1; i < n; i++) {
            perm[i] = encoded[i - 1] ^ perm[i - 1];
        }
        return perm;
    }

    // 依然超时
    public int[] decode2(int[] encoded) {
        int n = encoded.length + 1, i = 0, firstXorLast = 0;
        for (int e : encoded) {
            firstXorLast ^= e;
        }
        int[] result = new int[n];
        Set<Integer> set = new HashSet<>(n);
        for (int first = 1; first <= n; first++) {
            int last = firstXorLast ^ first;
            if (last == 0 || last > n) {
                continue;
            }
            set.add(first);
            set.add(last);
            int next = first;
            for (int e : encoded) {
                next ^= e;
                if (next == 0 || next > n || !set.add(next)) {
                    break;
                }
            }
            if (set.size() == n) {
                result[0] = first;
                int num = first;
                for (int e : encoded) {
                    result[++i] = (num ^= e);
                }
                break;
            }
            set.clear();
        }
        return result;
    }

    // 超时
    public int[] decode3(int[] encoded) {
        int n = encoded.length + 1, i = 0;
        int[] result = new int[n];
        Set<Integer> set = new LinkedHashSet<>(n);
        for (int first = 1; first <= n; first++) {
            set.add(first);
            int next = first;
            for (int e : encoded) {
                next = next ^ e;
                if (next == 0 || next > n || !set.add(next)) {
                    break;
                }
            }
            if (set.size() == n) {
                for (int num : set) {
                    result[i++] = num;
                }
                break;
            }
            set.clear();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] encoded = {3, 12, 1, 15, 5, 2, 3, 7};
        Decode decode = new Decode();
        // [7,4,8,9,6,3,1,2,5]
        System.out.println(Arrays.toString(decode.decode(encoded)));
    }
}
