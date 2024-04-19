package work.huangdu.question_bank.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 1015. 可被 K 整除的最小整数
 * 给定正整数 k ，你需要找出可以被 k 整除的、仅包含数字 1 的最 小 正整数 n 的长度。
 * 返回 n 的长度。如果不存在这样的 n ，就返回-1。
 * 注意： n 不符合 64 位带符号整数。
 * 示例 1：
 * 输入：k = 1
 * 输出：1
 * 解释：最小的答案是 n = 1，其长度为 1。
 * 示例 2：
 * 输入：k = 2
 * 输出：-1
 * 解释：不存在可被 2 整除的正整数 n 。
 * 示例 3：
 * 输入：k = 3
 * 输出：3
 * 解释：最小的答案是 n = 111，其长度为 3。
 * 提示：
 * 1 <= k <= 10^5
 *
 * @author huangdu
 * @version 2023/5/10
 */
public class SmallestRepunitDivByK {
    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0 || k % 5 == 0) {return -1;}
        int x = 1 % k;
        for (int i = 1; ; i++) {
            if (x == 0) {return i;}
            x = (x * 10 + 1) % k;
        }
    }

    public int smallestRepunitDivByK2(int k) {
        Set<Integer> set = new HashSet<>(k);
        int num = 0;
        while (set.add(num)) {
            num = (num * 10 + 1) % k;
            if (num == 0) {return set.size();}
        }
        return -1;
    }
}
